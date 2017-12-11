package main.java.businesslogic.reportbl;

import main.java.businesslogic.inventorybl.InventoryGiftBillBl;
import main.java.businesslogic.inventorybl.InventoryGiftBillTool;
import main.java.businesslogic.inventorybl.InventoryLossOverBillBl;
import main.java.businesslogic.inventorybl.InventoryLossOverBillTool;
import main.java.businesslogic.salebl.SaleRefundBillBl;
import main.java.businesslogic.salebl.SaleRefundBillTool;
import main.java.businesslogic.salebl.SaleTradBillBl;
import main.java.businesslogic.salebl.SaleTradeBillTool;
import main.java.businesslogicservice.reportblservice.BusinessConditionBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;
import main.java.vo.bill.inventorybill.InventoryLossOverBillVO;
import main.java.vo.bill.inventorybill.LossOverItemVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;
import main.java.vo.goods.GiftItemVO;
import main.java.vo.report.BusinessConditionQueryVO;

import java.util.ArrayList;

public class BusinessConditionBl implements BusinessConditionBlService {
    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:07
     * @para: [query] 包括起始时间
     * @function:
     * 大作业说明：查看经营情况表    统计显示一段时间内的经营收支状况和利润。经营收入显示为折让后，并显示出折让了多少。
     * 显示信息：
     * 1. 收入类：销售收入、商品类收入（商品报溢收入 成本调价收入 进货退货差价 代金券与实际收款差额收入）。
     *            收入类显示折让后总收入，并显示折让了多少。
     * 2. 支出类：销售成本、商品类支出（商品报损 商品赠出）。支出类显示总支出。
     * 3. 利润：折让后总收入-总支出。
     *
     * 实现说明：为返回的List的每一位定好意义和数据，直接返回即可
     * ArrayList位数说明：
     * 位数：  1                   2           3             4               5               6
     * 意义：  销售折让后总收入    销售折让    商品总收入    商品报溢收入    成本调价收入    进货退货差价
     *
     * 位数：  7                           8           9           10          11          12
     * 意义：  代金券与实际收款差额收入    销售成本    商品支出    商品报损    商品赠出    利润
     */
    public ArrayList<Double> getCondition(BusinessConditionQueryVO query)throws Exception {
        BillQueryVO billQueryVO = new BillQueryVO();
        billQueryVO.start = query.start;
        billQueryVO.end = query.end;

        /* 销售收入以及折让，
         * 记录销售出货单的总额减去销售退货单总额，
         * 折让只为销售出货单的discount
         * */
        double saleTotalAfterDiscount = 0;//折让后销售收入
        double saleDiscount = 0;//折让

        SaleTradeBillTool saleTradeBillTool = new SaleTradBillBl();
        ArrayList<SaleTradeBillVO> saleTradeBillVOS = saleTradeBillTool.getSaleTradeBillList(billQueryVO);
        for(SaleTradeBillVO saleTradeBillVO : saleTradeBillVOS){
            saleTotalAfterDiscount += saleTradeBillVO.getTotalAfterDiscount();
            saleDiscount += saleTradeBillVO.getDiscount();
        }
        SaleRefundBillTool saleRefundBillTool = new SaleRefundBillBl();
        ArrayList<SaleRefundBillVO> saleRefundBillVOS = saleRefundBillTool.getSaleRefundBillList(billQueryVO);
        for(SaleRefundBillVO saleRefundBillVO : saleRefundBillVOS){
            saleTotalAfterDiscount -= saleRefundBillVO.getTotal();
        }

        /*****************************************************************/

        /*商品收入及商品折让
        * 商品收入应该是以下四个收入的总和
        * */
        double goodsTotal = 0;//商品收入

        //商品报溢收入，从库存溢损单里面取得商品和数量差然后相乘，求和
        double goodsOverflowTotal = 0;//报溢收入
        InventoryLossOverBillTool inventoryLossOverBillTool = new InventoryLossOverBillBl();
        ArrayList<InventoryLossOverBillVO> inventoryLossOverBillVOS = inventoryLossOverBillTool.getInventoryLossOverBillList(billQueryVO);
        for(InventoryLossOverBillVO inventoryLossOverBillVO : inventoryLossOverBillVOS){
            ArrayList<LossOverItemVO> lossOverItemVOS = inventoryLossOverBillVO.getLossOverList();
            for(LossOverItemVO lossOverItemVO : lossOverItemVOS)//只能实际数量大于系统数量，下面商品类支出会计算损失的
                if(lossOverItemVO.actualNumber > lossOverItemVO.goodsNumber)
                    goodsTotal += (lossOverItemVO.price * (lossOverItemVO.actualNumber - lossOverItemVO.goodsNumber));
        }

        //成本调价收入，对在库存的所有商品进行统计，目前无法知道所有库存的商品，除非统计所有的进货、退货和销售单还有报溢报损等
        double costTotal = 0;

        //进货退货差价,需要拿到同一批货的进价和退货的价格，目前无法判断同一批货
        double priceDiffTotal = 0;

        // 代金券与实际收款差额收入，这个无法理解
        double diffVoucherGetTotal = 0;

        /*销售成本*///这个等待讨论
        double saleCostTotal = 0;

        /*****************************************************************/

        /*商品类支出*/
        double goodsExpend = 0;
        //商品破损，通过损溢单来查找损失的数量乘以单价得到
        double goodsBrokenCost = 0;
        for(InventoryLossOverBillVO inventoryLossOverBillVO : inventoryLossOverBillVOS){
            ArrayList<LossOverItemVO> lossOverItemVOS = inventoryLossOverBillVO.getLossOverList();
            for(LossOverItemVO lossOverItemVO : lossOverItemVOS)//只能实际数量小于系统数量，即商品处在破损状态
                if(lossOverItemVO.actualNumber < lossOverItemVO.goodsNumber)
                    goodsBrokenCost += (lossOverItemVO.price * (lossOverItemVO.actualNumber - lossOverItemVO.goodsNumber));
        }

        //商品赠出,通过库存赠送单计算总额
        double goodsPresentCost = 0;
        InventoryGiftBillTool inventoryGiftBillTool = new InventoryGiftBillBl();
        ArrayList<InventoryGiftBillVO> inventoryGiftBillVOS = inventoryGiftBillTool.getInventoryGiftBillList(billQueryVO);
        for(InventoryGiftBillVO inventoryGiftBillVO : inventoryGiftBillVOS){
            ArrayList<GiftItemVO> giftItemVOS = inventoryGiftBillVO.getGiftList();
            for(GiftItemVO giftItemVO : giftItemVOS)
                goodsBrokenCost += (giftItemVO.number * giftItemVO.price);
        }

        goodsExpend = goodsBrokenCost + goodsPresentCost;

        /*****************************************************************/

        /*利润*/
        //理解为，折让后销售收入 + 商品收入 - 商品类总支出
        double profit = saleTotalAfterDiscount + goodsTotal - goodsExpend;

        ArrayList<Double> resultList = new ArrayList<>();

        //按顺序加入list，每一行是一类
        resultList.add(saleTotalAfterDiscount);resultList.add(saleDiscount);
        resultList.add(goodsTotal);resultList.add(goodsOverflowTotal);resultList.add(costTotal);resultList.add(priceDiffTotal);resultList.add(diffVoucherGetTotal);
        resultList.add(saleCostTotal);
        resultList.add(goodsExpend);resultList.add(goodsBrokenCost);resultList.add(goodsPresentCost);resultList.add(profit);

        return resultList;
    }
}

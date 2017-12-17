package main.java.businesslogic.reportbl;

import main.java.businesslogic.inventorybl.InventoryGiftBillBl;
import main.java.businesslogic.inventorybl.InventoryGiftBillTool;
import main.java.businesslogic.inventorybl.InventoryLossOverBillBl;
import main.java.businesslogic.inventorybl.InventoryLossOverBillTool;
import main.java.businesslogic.promotionbl.PromotionBl;
import main.java.businesslogic.promotionbl.PromotionTool;
import main.java.businesslogic.purchasebl.PurchaseRefundBillBl;
import main.java.businesslogic.purchasebl.PurchaseRefundBillTool;
import main.java.businesslogic.purchasebl.PurchaseTradeBillBl;
import main.java.businesslogic.purchasebl.PurchaseTradeBillTool;
import main.java.businesslogic.salebl.SaleRefundBillBl;
import main.java.businesslogic.salebl.SaleRefundBillTool;
import main.java.businesslogic.salebl.SaleTradBillBl;
import main.java.businesslogic.salebl.SaleTradeBillTool;
import main.java.businesslogicservice.reportblservice.BusinessConditionBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;
import main.java.vo.bill.inventorybill.InventoryLossOverBillVO;
import main.java.vo.bill.inventorybill.LossOverItemVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;
import main.java.vo.goods.GiftItemVO;
import main.java.vo.promotion.PromotionTotalVO;
import main.java.vo.promotion.PromotionVO;
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
     * 位数：  1          2            3           4
     * 意义：  销售收入   商品类收入   折让金额    折让后总收入
     *
     * 位数：  5           6             7         8
     * 意义：  销售成本    商品类支出    总支出    利润
     *
     * 细节理解：
     * 1、销售收入：销售单毛收入（折让前总额）- 销售退货单毛收入
     * 2、商品类收入：商品报溢收入（库存溢损单中库存比系统记录多的部分）+ 代金券与实际代金券使用金额的差额收入（即代金券未使用完的部分）
     * 3、折让金额：只算销售单中discount部分
     * 4、折让后总收入：销售收入 + 商品类收入 - 折让金额
     * 5、销售成本：进货单总金额 - 进货退货单总金额
     * 6、商品类支出：商品破损（通过损溢单来查找损失的数量乘以单价得到） + 商品赠出（通过库存赠送单计算总额）
     * 7、总支出：销售成本 + 商品类支出
     * 8、利润：总支出 - 折让后总收入
     *
     */
    public ArrayList<Double> getCondition(BusinessConditionQueryVO query)throws Exception {

        BillQueryVO billQueryVO = new BillQueryVO();
        if(query == null) billQueryVO = null;
        else{
            billQueryVO.start = query.start;
            billQueryVO.end = query.end;
        }

        /* 销售收入，
         * 记录销售出货单的总额减去销售退货单总额，
         * 折让只为销售出货单的discount以及促销策略部分
         * */
        double saleTotalBeforeDiscount = 0;//折让后销售收入
        double saleDiscount = 0;//折让

        SaleTradeBillTool saleTradeBillTool = new SaleTradBillBl();
        ArrayList<SaleTradeBillVO> saleTradeBillVOS = saleTradeBillTool.getSaleTradeBillList(billQueryVO);
        for(SaleTradeBillVO saleTradeBillVO : saleTradeBillVOS){
            saleTotalBeforeDiscount += saleTradeBillVO.getTotalBeforeDiscount();
            saleDiscount += saleTradeBillVO.getDiscount()+saleTradeBillVO.getAmountOfVoucher();
        }
        SaleRefundBillTool saleRefundBillTool = new SaleRefundBillBl();
        ArrayList<SaleRefundBillVO> saleRefundBillVOS = saleRefundBillTool.getSaleRefundBillList(billQueryVO);
        for(SaleRefundBillVO saleRefundBillVO : saleRefundBillVOS){
            saleTotalBeforeDiscount -= saleRefundBillVO.getTotal();
        }

        /*****************************************************************/

        /*商品收入
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

//        //成本调价收入，对在库存的所有商品进行统计，目前无法知道所有库存的商品，除非统计所有的进货、退货和销售单还有报溢报损等
//        double costTotal = 0;
//
//        //进货退货差价,需要拿到同一批货的进价和退货的价格，目前无法判断同一批货
//        double priceDiffTotal = 0;

        // 代金券与实际代金券差额收入，（销售单promotionID找到发放代金券总额，减去实际上代金券使用金额，求和）
        //实现：通过每一个销售单的PromotionID，拿到PromotionVO，通过type转成相应子类型，拿到代金券代金总额；
        // 然后每一个销售单的代金券是实际使用的钱。总额减去实际使用就是差额收入了
        double diffVoucherGetTotal = 0;
        double voucherTotal = 0;
        double voucherUse = 0;

        PromotionTool promotionTool = new PromotionBl();
        PromotionVO promotionVO = new PromotionVO();
        for(SaleTradeBillVO saleTradeBillVO : saleTradeBillVOS){
            promotionVO = saleTradeBillVO.getPromotion();
            voucherTotal += promotionVO.countVoucher(saleTradeBillVO.getSaleList(),saleTradeBillVO.getClient(),saleTradeBillVO.getTotalBeforeDiscount());
            voucherUse += saleTradeBillVO.getAmountOfVoucher();
        }

        diffVoucherGetTotal = voucherTotal - voucherUse;

        goodsTotal = goodsOverflowTotal + diffVoucherGetTotal;

        /*销售成本*/
        //进货单总金额 - 进货退货单总金额
        double saleCostTotal = 0;
        double purchaseTotal = 0;
        double purchaseRefundTotal = 0;
        PurchaseTradeBillTool purchaseTradeBillTool = new PurchaseTradeBillBl();
        PurchaseRefundBillTool purchaseRefundBillTool = new PurchaseRefundBillBl();
        ArrayList<PurchaseTradeBillVO> purchaseTradeBillVOS = purchaseTradeBillTool.getPurchaseTradeBillList(billQueryVO);
        ArrayList<PurchaseRefundBillVO> purchaseRefundBillVOS = purchaseRefundBillTool.getPurchaseRefundBillList(billQueryVO);
        for(PurchaseTradeBillVO purchaseTradeBillVO : purchaseTradeBillVOS)
            purchaseTotal += purchaseTradeBillVO.getTotal();
        for(PurchaseRefundBillVO purchaseRefundBillVO : purchaseRefundBillVOS)
            purchaseRefundTotal += purchaseRefundBillVO.getTotal();

        saleCostTotal = purchaseTotal - purchaseRefundTotal;


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

        /* 折让后总收入：销售收入 + 商品类收入 - 折让金额 */
        double totalAfterDisocunt = saleTotalBeforeDiscount + goodsTotal -saleDiscount;

        /* 总支出：销售成本 + 商品类支出 */
        double totalExpend = saleCostTotal + goodsExpend;

        /* 利润：总支出 - 折让后总收入 */
        double profit = totalExpend - totalAfterDisocunt;



        //按顺序加入list，每一行是一类
        ArrayList<Double> resultList = new ArrayList<>();
        resultList.add(saleTotalBeforeDiscount);resultList.add(goodsTotal);resultList.add(saleDiscount);resultList.add(totalAfterDisocunt);
        resultList.add(saleCostTotal);resultList.add(goodsExpend);resultList.add(totalExpend);resultList.add(profit);

        return resultList;
    }
}

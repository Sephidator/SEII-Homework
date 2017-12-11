package main.java.businesslogic.reportbl;

import main.java.businesslogic.inventorybl.InventoryLossOverBillBl;
import main.java.businesslogic.inventorybl.InventoryLossOverBillTool;
import main.java.businesslogic.salebl.SaleRefundBillBl;
import main.java.businesslogic.salebl.SaleRefundBillTool;
import main.java.businesslogic.salebl.SaleTradBillBl;
import main.java.businesslogic.salebl.SaleTradeBillTool;
import main.java.businesslogicservice.reportblservice.BusinessConditionBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryLossOverBillVO;
import main.java.vo.bill.inventorybill.LossOverItemVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;
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
     * 位数：  1                   2           3                   4           5               6               7
     * 意义：  销售折让后总收入    销售折让    商品折让后总收入    商品折让    商品报溢收入    成本调价收入    进货退货差价
     *
     * 位数：  8                           9           10          11          12          13
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
        double saleTotalAfterDiscount = 0;//销售折让后收入
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
        double goodsDiscount = 0;//商品折让

        //商品报溢收入，从库存溢损单里面取得商品和数量差然后相乘，求和
        double goodsOverflowTotal = 0;//报溢收入
        InventoryLossOverBillTool inventoryLossOverBillTool = new InventoryLossOverBillBl();
        ArrayList<InventoryLossOverBillVO> inventoryLossOverBillVOS = inventoryLossOverBillTool.getInventoryLossOverBillList(billQueryVO);
        for(InventoryLossOverBillVO inventoryLossOverBillVO : inventoryLossOverBillVOS){
            ArrayList<LossOverItemVO> lossOverItemVOS = inventoryLossOverBillVO.getLossOverList();
            for(LossOverItemVO lossOverItemVO : lossOverItemVOS)//溢出为正收入，否则为负收入（即损失）
                goodsTotal += (lossOverItemVO.price * (lossOverItemVO.actualNumber - lossOverItemVO.goodsNumber));
        }

        //成本调价收入，对在库存的所有商品进行统计，目前无法知道所有库存的商品，除非统计所有的进货、退货和销售单还有报溢报损等
        double costTotal = 0;

        //进货退货差价,需要拿到同一批货的进价和退货的价格，目前无法判断同一批货
        double priceDiffTotal = 0;

        // 代金券与实际收款差额收入，这个无法理解
        double diffVoucherGetTotal = 0;

        /*销售成本*/


        /*商品类支出*/
        //商品破损
        //商品赠出

        /*利润*/

        return null;
    }
}

package main.java.businesslogic.reportbl;

import main.java.businesslogic.financebl.*;
import main.java.businesslogic.inventorybl.InventoryGiftBillBl;
import main.java.businesslogic.inventorybl.InventoryGiftBillTool;
import main.java.businesslogic.inventorybl.InventoryLossOverBillBl;
import main.java.businesslogic.inventorybl.InventoryLossOverBillTool;
import main.java.businesslogic.purchasebl.PurchaseRefundBillBl;
import main.java.businesslogic.purchasebl.PurchaseRefundBillTool;
import main.java.businesslogic.purchasebl.PurchaseTradeBillBl;
import main.java.businesslogic.purchasebl.PurchaseTradeBillTool;
import main.java.businesslogic.salebl.SaleRefundBillBl;
import main.java.businesslogic.salebl.SaleRefundBillTool;
import main.java.businesslogic.salebl.SaleTradBillBl;
import main.java.businesslogic.salebl.SaleTradeBillTool;
import main.java.businesslogicservice.reportblservice.BusinessHistoryBlService;
import main.java.po.bill.financebill.ReceiptBillPO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.PaymentBillVO;
import main.java.vo.bill.financebill.ReceiptBillVO;
import main.java.vo.report.BusinessHistoryQueryVO;

import java.util.ArrayList;
import java.util.Date;

public class BusinessHistoryBl implements BusinessHistoryBlService {
    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:08
     * @para: [query] 
     * @function:
     * 大作业说明：查看经营历程表（查看一段时间里的所有单据，单据分为：
     * 1. 销售类单据（销售出货单，销售退货单）
     * 2. 进货类单据（进货单，进货退货单）
     * 3. 财务类单据（付款单，收款单，现金费用单）
     * 4. 库存类单据（库存溢损单，库存赠送单）
     * 筛选条件为：时间区间，单据类型，客户，业务员，仓库。
     *
     * 实现说明：通过判断单据type，再调用响应单据的getXXXList得到所有时间区间内的单据
     */
    public ArrayList<BillVO> getBillList(BusinessHistoryQueryVO query)throws Exception {

        /*将BusinessHistoryQueryVO转为BillQueryVO*/
        BillQueryVO billQueryVO = new BillQueryVO();
        billQueryVO.start = query.start;
        billQueryVO.end = query.end;
        billQueryVO.client = query.client;
        billQueryVO.operator = query.operator;
        billQueryVO.type = query.type;

        ArrayList<BillVO> billVOArrayList = new ArrayList<>();
        /*调用相应单据的getArrayList*/
        //财务类
        if(query == null ||query.type.equals("付款单")){
            PaymentBillTool paymentBillTool = new PaymentBillBl();
            billVOArrayList.addAll(paymentBillTool.getPaymentBillList(billQueryVO));
        }
        if(query == null ||query.type.equals("收款单")){
            ReceiptBillTool receiptBillTool = new ReceiptBillBl();
            billVOArrayList.addAll(receiptBillTool.getReceiptBillList(billQueryVO));
        }
        if(query == null ||query.type.equals("现金费用单")){
            CashBillTool cashBillTool = new CashBillBl();
            billVOArrayList.addAll(cashBillTool.getCashBillList(billQueryVO));
        }

        //销售类
        if(query == null ||query.type.equals("销售退货单")){
            SaleRefundBillTool saleRefundBillTool = new SaleRefundBillBl();
            billVOArrayList.addAll(saleRefundBillTool.getSaleRefundBillList(billQueryVO));
        }
        if(query == null ||query.type.equals("销售出货单")){
            SaleTradeBillTool saleTradeBillTool = new SaleTradBillBl();
            billVOArrayList.addAll(saleTradeBillTool.getSaleTradeBillList(billQueryVO));
        }

        //进货类
        if(query == null ||query.type.equals("进货退货单")){
            PurchaseRefundBillTool purchaseRefundBillTool = new PurchaseRefundBillBl();
            billVOArrayList.addAll(purchaseRefundBillTool.getPurchaseRefundBillList(billQueryVO));
        }
        if(query == null ||query.type.equals("进货单")){
            PurchaseTradeBillTool purchaseTradeBillTool = new PurchaseTradeBillBl();
            billVOArrayList.addAll(purchaseTradeBillTool.getPurchaseTradeBillList(billQueryVO));
        }

        //仓库类
        if(query == null ||query.type.equals("库存溢损单")){
            InventoryLossOverBillTool inventoryLossOverBillTool = new InventoryLossOverBillBl();
            billVOArrayList.addAll(inventoryLossOverBillTool.getInventoryLossOverBillList(billQueryVO));
        }
        if(query == null ||query.type.equals("库存赠送单")){
            InventoryGiftBillTool inventoryGiftBillTool = new InventoryGiftBillBl();
            billVOArrayList.addAll(inventoryGiftBillTool.getInventoryGiftBillList(billQueryVO));
        }

        return billVOArrayList;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.12.11 14:02
     * @para: [paymentBillVO]
     * @function: 生成一张数字相同的收款单并自动提交审批
     */
    public String reversePaymentBill(PaymentBillVO paymentBillVO) throws Exception {

        /*由PaymentBillVO生成ReceiptBillVO*/
        ReceiptBillVO receiptBillVO = new ReceiptBillVO();
        receiptBillVO.setOperator(paymentBillVO.getOperator());
        receiptBillVO.setClient(paymentBillVO.getClient());
        receiptBillVO.setTransList(paymentBillVO.getTransList());
        receiptBillVO.setComment(paymentBillVO.getComment()+"\n这张单据是为了红冲编号为"+paymentBillVO.getID()+"的单据而生成的（系统）");
        receiptBillVO.setTime(new Date());
        receiptBillVO.setTotal(paymentBillVO.getTotal());

        /*提交审批*/
        ReceiptBillTool receiptBillTool = new ReceiptBillBl();
        String id = receiptBillTool.submit(receiptBillVO);

        return id;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.12.11 14:02
     * @para: [receiptBillVO] 
     * @function: 
     */
    public String reverseReceiptBill(ReceiptBillVO receiptBillVO) throws Exception {

        /*由ReceiptBillVO生成PaymentBillVO*/
        PaymentBillVO paymentBillVO = new PaymentBillVO();
        paymentBillVO.setOperator(receiptBillVO.getOperator());
        paymentBillVO.setClient(receiptBillVO.getClient());
        paymentBillVO.setTransList(receiptBillVO.getTransList());
        paymentBillVO.setComment(receiptBillVO.getComment()+"\n这张单据是为了红冲编号为"+receiptBillVO.getID()+"的单据而生成的（系统）");
        paymentBillVO.setTime(new Date());
        paymentBillVO.setTotal(receiptBillVO.getTotal());

        /*提交审批*/
        PaymentBillTool paymentBillTool = new PaymentBillBl();
        String id = paymentBillTool.submit(paymentBillVO);

        return id;
    }
}

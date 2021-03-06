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
import main.java.businesslogic.salebl.SaleTradeBillBl;
import main.java.businesslogic.salebl.SaleTradeBillTool;
import main.java.businesslogicservice.reportblservice.BusinessHistoryBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.*;
import main.java.vo.report.BusinessHistoryQueryVO;
import main.java.vo.user.UserVO;

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
        BillQueryVO billQueryVO = new BillQueryVO("审批通过",null,null,null,null,null);
        if(query != null){
            billQueryVO = new BillQueryVO("审批通过",query.start,query.end,query.type,query.operator,query.client);
        }


        ArrayList<BillVO> billVOArrayList = new ArrayList<>();
        /*调用相应单据的getArrayList*/
        //仓库类
        if(billQueryVO.type==null || billQueryVO.type.equals("库存溢损单")){
            InventoryLossOverBillTool inventoryLossOverBillTool = new InventoryLossOverBillBl();
            billVOArrayList.addAll(inventoryLossOverBillTool.getInventoryLossOverBillList(billQueryVO));
        }
        if(billQueryVO.type==null || billQueryVO.type.equals("库存赠送单")){
            InventoryGiftBillTool inventoryGiftBillTool = new InventoryGiftBillBl();
            billVOArrayList.addAll(inventoryGiftBillTool.getInventoryGiftBillList(billQueryVO));
        }

        //进货类
        if(billQueryVO.type==null || billQueryVO.type.equals("进货退货单")){
            PurchaseRefundBillTool purchaseRefundBillTool = new PurchaseRefundBillBl();
            billVOArrayList.addAll(purchaseRefundBillTool.getPurchaseRefundBillList(billQueryVO));
        }
        if(billQueryVO.type==null || billQueryVO.type.equals("进货单")){
            PurchaseTradeBillTool purchaseTradeBillTool = new PurchaseTradeBillBl();
            billVOArrayList.addAll(purchaseTradeBillTool.getPurchaseTradeBillList(billQueryVO));
        }

        //销售类
        if(billQueryVO.type==null || billQueryVO.type.equals("销售单")){
            SaleTradeBillTool saleTradeBillTool = new SaleTradeBillBl();
            billVOArrayList.addAll(saleTradeBillTool.getSaleTradeBillList(billQueryVO));
        }
        if(billQueryVO.type==null || billQueryVO.type.equals("销售退货单")){
            SaleRefundBillTool saleRefundBillTool = new SaleRefundBillBl();
            billVOArrayList.addAll(saleRefundBillTool.getSaleRefundBillList(billQueryVO));
        }

        //财务类
        if(billQueryVO.type==null || billQueryVO.type.equals("付款单")){
            PaymentBillTool paymentBillTool = new PaymentBillBl();
            billVOArrayList.addAll(paymentBillTool.getPaymentBillList(billQueryVO));
        }
        if(billQueryVO.type==null || billQueryVO.type.equals("收款单")){
            ReceiptBillTool receiptBillTool = new ReceiptBillBl();
            billVOArrayList.addAll(receiptBillTool.getReceiptBillList(billQueryVO));
        }
        if(billQueryVO.type==null || billQueryVO.type.equals("现金费用单")){
            CashBillTool cashBillTool = new CashBillBl();
            billVOArrayList.addAll(cashBillTool.getCashBillList(billQueryVO));
        }

        return billVOArrayList;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.12.11 14:02
     * @para: [paymentBillVO]
     * @function: 生成用于红冲的付款单并自动提交审批
     */
    public String reversePaymentBill(PaymentBillVO paymentBillVO, UserVO operator) throws Exception {

        /*由ReceiptBillVO生成PaymentBillVO*/
        PaymentBillVO reverseBill = new PaymentBillVO();

        reverseBill.setOperator(operator);
        reverseBill.setClient(paymentBillVO.getClient());
        reverseBill.setComment("用于红冲编号为"+paymentBillVO.getID()+"的单据");
        reverseBill.setTime(new Date());
        reverseBill.setTotal(-paymentBillVO.getTotal());
        reverseBill.setState("待审批");

        ArrayList<TransItemVO> transList=new ArrayList<>();
        for(TransItemVO item: paymentBillVO.getTransList()){
            TransItemVO newItem=new TransItemVO(item.account,-item.transAmount,item.comment);
            transList.add(newItem);
        }
        reverseBill.setTransList(transList);

        /*提交审批*/
        PaymentBillTool paymentBillTool = new PaymentBillBl();
        String id = paymentBillTool.submit(reverseBill);

        return id;

    }

    @Override
    /**
     * @version: 1
     * @date: 2017.12.11 14:02
     * @para: [receiptBillVO] 
     * @function: 生成用于红冲的收款单并自动提交审批
     */
    public String reverseReceiptBill(ReceiptBillVO receiptBillVO, UserVO operator) throws Exception {
        /*由PaymentBillVO生成ReceiptBillVO*/
        ReceiptBillVO reverseBill = new ReceiptBillVO();

        reverseBill.setOperator(operator);
        reverseBill.setClient(receiptBillVO.getClient());
        reverseBill.setComment("用于红冲编号为"+receiptBillVO.getID()+"的单据");
        reverseBill.setTime(new Date());
        reverseBill.setTotal(-receiptBillVO.getTotal());
        reverseBill.setState("待审批");

        ArrayList<TransItemVO> transList=new ArrayList<>();
        for(TransItemVO item: receiptBillVO.getTransList()){
            TransItemVO newItem=new TransItemVO(item.account,-item.transAmount,item.comment);
            transList.add(newItem);
        }
        reverseBill.setTransList(transList);

        /*提交审批*/
        ReceiptBillTool receiptBillTool = new ReceiptBillBl();
        String id = receiptBillTool.submit(reverseBill);

        return id;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.12.11 14:02
     * @para: [receiptBillVO]
     * @function: 生成用于红冲的现金费用单并自动提交审批
     */
    public String reverseCashBill(CashBillVO cashBillVO, UserVO operator) throws Exception {
        /*由PaymentBillVO生成ReceiptBillVO*/
        CashBillVO reverseBill = new CashBillVO();

        reverseBill.setOperator(operator);
        reverseBill.setAccount(cashBillVO.getAccount());
        reverseBill.setComment("用于红冲编号为"+cashBillVO.getID()+"的单据");
        reverseBill.setTime(new Date());
        reverseBill.setTotal(-cashBillVO.getTotal());
        reverseBill.setState("待审批");

        ArrayList<CashItemVO> itemList=new ArrayList<>();
        for(CashItemVO item: cashBillVO.getItemList()){
            CashItemVO newItem=new CashItemVO(item.ItemName,item.amount,item.comment);
            itemList.add(newItem);
        }
        reverseBill.setItemList(itemList);

        /*提交审批*/
        CashBillTool cashBillTool = new CashBillBl();
        String id = cashBillTool.submit(reverseBill);

        return id;
    }
}

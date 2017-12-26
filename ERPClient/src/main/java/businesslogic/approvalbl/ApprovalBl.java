package main.java.businesslogic.approvalbl;

import main.java.businesslogic.financebl.*;
import main.java.businesslogic.inventorybl.InventoryGiftBillBl;
import main.java.businesslogic.inventorybl.InventoryGiftBillTool;
import main.java.businesslogic.inventorybl.InventoryLossOverBillBl;
import main.java.businesslogic.inventorybl.InventoryLossOverBillTool;
import main.java.businesslogic.logbl.LogBl;
import main.java.businesslogic.logbl.LogTool;
import main.java.businesslogic.messagebl.MessageBl;
import main.java.businesslogic.messagebl.MessageTool;
import main.java.businesslogic.purchasebl.PurchaseRefundBillBl;
import main.java.businesslogic.purchasebl.PurchaseRefundBillTool;
import main.java.businesslogic.purchasebl.PurchaseTradeBillBl;
import main.java.businesslogic.purchasebl.PurchaseTradeBillTool;
import main.java.businesslogic.salebl.SaleRefundBillBl;
import main.java.businesslogic.salebl.SaleRefundBillTool;
import main.java.businesslogic.salebl.SaleTradeBillBl;
import main.java.businesslogic.salebl.SaleTradeBillTool;
import main.java.businesslogicservice.approvalblservice.ApprovalBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.log.LogVO;
import main.java.vo.message.MessageVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;

public class ApprovalBl implements ApprovalBlService {
    /**
     * @version: 1
     * @date: 2017.11.26 12:59
     * @para: [query] 包含所需审批的单据的清单
     * @function: 直接调用相关单据的Tool.get得到相关单据，再打包成ArrayList返回
     * 说明：这里是总经理想要得到所有待审批（已经提交的单据）的单据所调用的方法，那么BillQueryVO应该只会指明
     *       单据状态为“待审批”，所以我将会调用所有单据的getArrayList，将它们全部加入billVOArrayList里面
     * */
    @Override
    public ArrayList<BillVO> getBillList(BillQueryVO query)  throws Exception{
        ArrayList<BillVO> billVOArrayList = new ArrayList<BillVO>();

        /*根据单据类型调用getArrayList*/
        if(query.type.equals("库存赠送单")){
            InventoryGiftBillTool inventoryGiftBillTool = new InventoryGiftBillBl();
            billVOArrayList.addAll(inventoryGiftBillTool.getInventoryGiftBillList(query));
        }
        else if(query.type.equals("库存溢损单")){
            InventoryLossOverBillTool inventoryLossOverBillTool = new InventoryLossOverBillBl();
            billVOArrayList.addAll(inventoryLossOverBillTool.getInventoryLossOverBillList(query));
        }
        else if(query.type.equals("进货单")){
            PurchaseTradeBillTool purchaseTradeBillTool = new PurchaseTradeBillBl();
            billVOArrayList.addAll(purchaseTradeBillTool.getPurchaseTradeBillList(query));
        }
        else if(query.type.equals("进货退货单")){
            PurchaseRefundBillTool purchaseRefundBillTool = new PurchaseRefundBillBl();
            billVOArrayList.addAll(purchaseRefundBillTool.getPurchaseRefundBillList(query));
        }
        else if(query.type.equals("销售单")){
            SaleRefundBillTool saleRefundBillTool = new SaleRefundBillBl();
            billVOArrayList.addAll(saleRefundBillTool.getSaleRefundBillList(query));
        }
        else if(query.type.equals("销售退货单")){
            SaleTradeBillTool saleTradeBillTool = new SaleTradeBillBl();
            billVOArrayList.addAll(saleTradeBillTool.getSaleTradeBillList(query));
        }
        else if(query.type.equals("付款单")){
            PaymentBillTool paymentBillTool = new PaymentBillBl();
            billVOArrayList.addAll(paymentBillTool.getPaymentBillList(query));
        }
        else if(query.type.equals("收款单")){
            ReceiptBillTool receiptBillTool = new ReceiptBillBl();
            billVOArrayList.addAll(receiptBillTool.getReceiptBillList(query));
        }
        else if(query.type.equals("现金费用单")){
            CashBillTool cashBillTool = new CashBillBl();
            billVOArrayList.addAll(cashBillTool.getCashBillList(query));
        }

        return billVOArrayList;
    }

    /**
     * @version: 1
     * @date: 2017.11.26 13:02
     * @para: [vo]需要通过的单据的信息列表
     * @function: 通过调用相应单据的Tool.pass，将单据列表中的审批状态全部改成审批通过状态，
     * 然后调用Tool.update,详情查看用例文档
     */
    @Override
    public void pass(BillVO billvo, UserVO sender)  throws Exception{

        /*通过单据*/
        billvo.getTool().pass(billvo);

        /*添加message*/
        MessageTool messageTool = new MessageBl();
        MessageVO messageVO = new MessageVO(billvo.getOperator(),sender,"你的编号为"+billvo.getID()+"的单据被"+sender.getID()+"审批通过（系统消息）");
        messageTool.addMessage(messageVO);

        /*记录操作日志*/
        LogTool logTool = new LogBl();
        LogVO logVO = new LogVO(sender,"通过编号为"+billvo.getID()+"的单据",new Date());
        logTool.addLog(logVO);


    }

    /**
     * @version: 1
     * @date: 2017.11.26 13:24
     * @para: [vo, result] 需要拒绝的单据，拒绝理由
     * @function: 通过调用相应单据的Tool.reject，将单据列表中的审批状态全部改成审批未通过状态
     */
    @Override
    public void reject(BillVO billvo, String reason, UserVO sender)  throws Exception{

        /*对单据相应处理*/
        billvo.getTool().reject(billvo);

        /*添加message*/
        MessageTool messageTool = new MessageBl();
        MessageVO messageVO = new MessageVO(billvo.getOperator(),sender,"你的编号为"+billvo.getID()+"的单据被"+sender.getID()+"审批拒绝，附加理由："+reason);
        messageTool.addMessage(messageVO);
    }

}

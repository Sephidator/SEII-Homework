package main.java.businesslogic.approvalbl;

import main.java.businesslogic.financebl.CashBillTool;
import main.java.businesslogic.logbl.LogBl;
import main.java.businesslogic.logbl.LogTool;
import main.java.businesslogic.messagebl.MessageBl;
import main.java.businesslogic.messagebl.MessageTool;
import main.java.businesslogic.purchasebl.PurchaseTradeBillBl;
import main.java.businesslogic.purchasebl.PurchaseTradeBillTool;
import main.java.businesslogicservice.approvalblservice.ApprovalBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.CashBillVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;
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
     * @function: 将BillQueryVO转换为BillQueryPO，调用相关单据的Tool.get得到相关单据，再打包成ArrayList返回
     */
    @Override
    public ArrayList<BillVO> getBillList(BillQueryVO query)  throws Exception{

        String type = query.type;
        ArrayList<BillVO> billVOArrayList = new ArrayList<BillVO>();

        return null;
    }

    /**
     * @version: 1
     * @date: 2017.11.26 13:02
     * @para: [vo]需要通过的单据的信息列表
     * @function: 通过调用相应单据的Tool.pass，将单据列表中的审批状态全部改成审批通过状态，
     * 然后调用Tool.update，返回ResultMessage,其中还需要修改相关数据，详情查看用例文档
     */
    @Override
    public void pass(BillVO billvo, UserVO sender)  throws Exception{

        /*通过单据并做相应数据修改*/
        String type = billvo.getType();

        /*操作日志*/
        LogTool logTool = new LogBl();
        LogVO logVO = new LogVO(sender,"通过编号为"+billvo.getID()+"的单据",new Date());

        /*添加message*/
        MessageTool messageTool = new MessageBl();
        MessageVO messageVO = new MessageVO(billvo.getOperator(),sender,"你的编号为"+billvo.getID()+"的单据审批通过（系统消息）");
        messageTool.addMessage(messageVO);
    }

    /**
     * @version: 1
     * @date: 2017.11.26 13:24
     * @para: [vo, result] 需要拒绝的单据，拒绝理由
     * @function: 通过调用相应单据的Tool.reject，将单据列表中的审批状态全部改成审批未通过状态，并且将result写入MessagePO
     * 然后调用Tool.update和MessageDataService.insert，返回ResultMessage
     */
    @Override
    public void reject(BillVO billvo, String reason, UserVO sender)  throws Exception{

        /*对单据相应处理*/
        String type = billvo.getType();

        /*操作日志*/
        LogTool logTool = new LogBl();
        LogVO logVO = new LogVO(sender,"拒绝编号为"+billvo.getID()+"的单据",new Date());

        /*添加message*/
        MessageTool messageTool = new MessageBl();
        MessageVO messageVO = new MessageVO(billvo.getOperator(),sender,reason);
        messageTool.addMessage(messageVO);
    }

}

package main.java.vo.bill.financebill;

import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.po.bill.financebill.ReceiptBillPO;
import main.java.po.bill.financebill.TransItemPO;
import main.java.presentation.financeui.ReceiptBillUIController;
import main.java.vo.client.ClientVO;
import main.java.vo.user.UserVO;

import java.util.*;

/*
 * 收款单数据
 * @author:
 * @version:
 */

public class ReceiptBillVO extends FinanceBillVO {

    private ClientVO client;//付款单的客户，包括供应商和销售商组成的字符

    private ArrayList<TransItemVO> transList;//付款单转账列表的银行账户和金额

    public ReceiptBillVO(){
        this.state = "";
        this.time = new Date();
        this.type = "收款单";
        this.operator = new UserVO();
        this.comment = "";
        this.total=0;
        this.client=new ClientVO();
        this.transList=new ArrayList<>();
    }

    public ReceiptBillVO(String state, Date time,UserVO operator, String comment, double total, ClientVO client, ArrayList<TransItemVO> transList) {
        this.state = state;
        this.time = time;
        this.type = "收款单";
        this.operator = operator;
        this.comment = comment;
        this.total=total;
        this.client=client;
        this.transList=transList;
    }

    public ClientVO getClient() {
        return client;
    }

    public ArrayList<TransItemVO> getTransList() {
        return transList;
    }

    public void setClient(ClientVO client) {
        this.client = client;
    }

    public void setTransList(ArrayList<TransItemVO> transList) {
        this.transList = transList;
    }

    /*得到receiptBillPO*/
    public ReceiptBillPO getReceiptBillPO(){
        ReceiptBillPO receiptBillPO = new ReceiptBillPO();
        receiptBillPO.setID(this.ID);
        receiptBillPO.setState(this.state);
        receiptBillPO.setTime(this.time);
        receiptBillPO.setType(this.type);
        receiptBillPO.setOperatorID(this.operator.getID());
        receiptBillPO.setComment(this.comment);
        receiptBillPO.setTotal(this.total);
        receiptBillPO.setClientID(this.client.getID());
        receiptBillPO.setVisible(this.visible);

        /*转transList<TransItemVO>到transList<TransItemPO>*/
        ArrayList<TransItemPO> transItemPOS = new ArrayList<>();
        for(TransItemVO transItemVO : this.transList){
            transItemPOS.add(transItemVO.getTransItemPO());
        }
        receiptBillPO.setTransList(transItemPOS);

        //return
        return receiptBillPO;
    }

    /*得到PO以后转成VO*/
    public ReceiptBillVO(ReceiptBillPO receiptBillPO)throws Exception{
        this.ID = receiptBillPO.getID();
        this.state = receiptBillPO.getState();
        this.time = receiptBillPO.getTime();
        this.type = receiptBillPO.getType();

        /*得到UserVO*/
        UserTool userTool = new UserBl();
        UserVO uservO = userTool.find(receiptBillPO.getOperatorID());
        this.operator = uservO;

        this.comment = receiptBillPO.getComment();
        this.total = receiptBillPO.getTotal();

        /*得到ClientVO*/
        ClientTool clientTool = new ClientBl();
        ClientVO clientVO = clientTool.find(receiptBillPO.getClientID());
        this.client = clientVO;

        this.visible = receiptBillPO.isVisible();
    }

    /*实现待定*/
    public ReceiptBillUIController getInfoUIController() {
        return new ReceiptBillUIController();
    }
}


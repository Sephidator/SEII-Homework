package main.java.vo.bill.financebill;

import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.po.bill.financebill.PaymentBillPO;
import main.java.po.bill.financebill.TransItemPO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.*;

/*
 * 付款单数据
 * @author:
 * @version:
 */

public class PaymentBillVO extends FinanceBillVO {

    private ClientVO client;//付款单的客户，包括供应商和销售商组成的字符

    private ArrayList<TransItemVO> transList;//付款单转账列表的银行账户和金额

    public PaymentBillVO(){

    }

    public PaymentBillVO(String state, Date time,UserVO operator, String comment, double total, ClientVO client, ArrayList<TransItemVO> transList) {
        this.state = state;
        this.time = time;
        this.type = "付款单";
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

    /*得到PaymentBillPO*/
    public PaymentBillPO getPaymentBillPO(){
        PaymentBillPO paymentBillPO = new PaymentBillPO();
        paymentBillPO.setID(this.ID);
        paymentBillPO.setState(this.state);
        paymentBillPO.setTime(this.time);
        paymentBillPO.setType(this.type);
        paymentBillPO.setOperatorID(this.operator.getID());
        paymentBillPO.setComment(this.comment);
        paymentBillPO.setTotal(this.total);
        paymentBillPO.setClientID(this.client.getID());
        paymentBillPO.setVisible(this.visible);

        /*转transList<TransItemVO>到transList<TransItemPO>*/
        ArrayList<TransItemPO> transItemPOS = new ArrayList<>();
        for(TransItemVO transItemVO : this.transList){
            transItemPOS.add(transItemVO.getTransItemPO());
        }
        paymentBillPO.setTransList(transItemPOS);

        //return
        return paymentBillPO;
    }

    /*得到PO以后转成VO*/
    public PaymentBillVO(PaymentBillPO paymentBillPO){
        this.ID = paymentBillPO.getID();
        this.state = paymentBillPO.getState();
        this.time = paymentBillPO.getTime();
        this.type = paymentBillPO.getType();

        /*得到UserVO*/
        UserTool userTool = new UserBl();
        UserQueryVO userQueryVO = new UserQueryVO();
        userQueryVO.ID = paymentBillPO.getOperatorID();
        userQueryVO.name = "";//初始化防止NPE
        userQueryVO.type = "";
        UserVO uservO = userTool.getUserList(userQueryVO).get(0);//拿到第一个userPO对象
        this.operator = uservO;

        this.comment = paymentBillPO.getComment();
        this.total = paymentBillPO.getTotal();

        /*得到ClientVO*/
        ClientTool clientTool = new ClientBl();
        ClientQueryVO clientQueryVO = new ClientQueryVO();
        clientQueryVO.ID = paymentBillPO.getClientID();
        clientQueryVO.name = "";
        ClientVO clientVO = clientTool.getClientList(clientQueryVO).get(0);
        this.client = clientVO;

        this.visible = paymentBillPO.isVisible();
    }
}


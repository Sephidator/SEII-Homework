package main.java.vo.bill.financebill;

import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.financebl.PaymentBillBl;
import main.java.businesslogic.financebl.PaymentBillTool;
import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.po.bill.financebill.PaymentBillPO;
import main.java.po.bill.financebill.TransItemPO;
import main.java.presentation.financeui.PaymentBillUIController;
import main.java.vo.client.ClientVO;
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
        this.state = "";
        this.time = new Date();
        this.type = "付款单";
        this.operator = new UserVO();
        this.comment = "";
        this.total=0;
        this.client=new ClientVO();
        this.transList=new ArrayList<TransItemVO>();
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
    public PaymentBillVO(PaymentBillPO paymentBillPO)throws Exception{
        this.ID = paymentBillPO.getID();
        this.state = paymentBillPO.getState();
        this.time = paymentBillPO.getTime();
        this.type = paymentBillPO.getType();

        /*得到UserVO*/
        UserTool userTool = new UserBl();
        UserVO uservO = userTool.find(paymentBillPO.getOperatorID());
        this.operator = uservO;

        this.comment = paymentBillPO.getComment();
        this.total = paymentBillPO.getTotal();

        /*得到ClientVO*/
        ClientTool clientTool = new ClientBl();
        ClientVO clientVO = clientTool.find(paymentBillPO.getClientID());
        this.client = clientVO;

        this.visible = paymentBillPO.isVisible();
        //transListPO到TransListVO
        ArrayList<TransItemPO> transItemPOS = paymentBillPO.getTransList();
        ArrayList<TransItemVO> transItemVOS = new ArrayList<>();
        for(TransItemPO transItemPO : transItemPOS)
            transItemVOS.add(new TransItemVO(transItemPO));
        this.setTransList(transItemVOS);
    }

    /*getTool*/
    public PaymentBillTool getTool(){
        PaymentBillTool paymentBillTool = new PaymentBillBl();
        return paymentBillTool;
    }

    /*实现待定*/
    public PaymentBillUIController getInfoUIController() {
        return new PaymentBillUIController();
    }
}


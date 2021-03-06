package main.java.vo.bill.purchasebill;

import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.purchasebl.PurchaseRefundBillBl;
import main.java.businesslogic.purchasebl.PurchaseRefundBillTool;
import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.po.bill.purchasebill.PurchaseRefundBillPO;
import main.java.po.goods.GoodsItemPO;
import main.java.presentation.purchaseui.PurchaseRefundBillUIController;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * 进货退货单VO类
 * */
public class PurchaseRefundBillVO extends PurchaseBillVO {

    public PurchaseRefundBillVO(String state, Date time, UserVO operator, String comment, ClientVO client, ArrayList<GoodsItemVO> purchaseList, double total) {
        this.state = state;
        this.time = time;
        this.type = "进货退货单";
        this.operator = operator;
        this.comment = comment;
        this.client=client;
        this.purchaseList=purchaseList;
        this.total=total;
    }

    public PurchaseRefundBillVO(){
        this.state = "";
        this.time = new Date();
        this.type = "进货退货单";
        this.operator = new UserVO();
        this.comment = "";
        this.client=new ClientVO();
        this.purchaseList=new ArrayList<>();
        this.total=0;
    }

    public PurchaseRefundBillPO getPurchaseRefundBillPO()throws Exception{
        PurchaseRefundBillPO purchaseRefundBillPO=new PurchaseRefundBillPO();
        purchaseRefundBillPO.setID(this.ID);
        purchaseRefundBillPO.setVisible(this.visible);
        purchaseRefundBillPO.setState(this.state);
        purchaseRefundBillPO.setTime(this.time);
        purchaseRefundBillPO.setType(this.type);
        purchaseRefundBillPO.setOperatorID(this.operator.getID());
        purchaseRefundBillPO.setComment(this.comment);
        purchaseRefundBillPO.setClientID(this.client.getID());
        purchaseRefundBillPO.setTotal(this.total);

        ArrayList<GoodsItemPO> goodsItemPOS=new ArrayList<>();
        for(GoodsItemVO goodsItemVO:this.purchaseList){
            goodsItemPOS.add(goodsItemVO.getGoodsItemPO());
        }
        purchaseRefundBillPO.setPurchaseList(goodsItemPOS);

        return purchaseRefundBillPO;
    }

    public PurchaseRefundBillVO(PurchaseRefundBillPO purchaseRefundBillPO)throws Exception{
        this.ID=purchaseRefundBillPO.getID();
        this.visible=purchaseRefundBillPO.isVisible();
        this.state=purchaseRefundBillPO.getState();
        this.time=purchaseRefundBillPO.getTime();
        this.type=purchaseRefundBillPO.getType();
        this.comment=purchaseRefundBillPO.getComment();
        this.total=purchaseRefundBillPO.getTotal();

        UserTool userTool=new UserBl();
        this.operator=userTool.find(purchaseRefundBillPO.getOperatorID());

        ClientTool clientTool=new ClientBl();
        this.client=clientTool.find(purchaseRefundBillPO.getClientID());

        ArrayList<GoodsItemVO> goodsItemVOS=new ArrayList<>();
        for(GoodsItemPO goodsItemPO:purchaseRefundBillPO.getPurchaseList()){
            goodsItemVOS.add(new GoodsItemVO(goodsItemPO));
        }
        this.purchaseList=goodsItemVOS;
    }

    public PurchaseRefundBillTool getTool(){
        PurchaseRefundBillTool purchaseRefundBillTool=new PurchaseRefundBillBl();
        return purchaseRefundBillTool;
    }

    public PurchaseRefundBillUIController getInfoUIController(){
        return new PurchaseRefundBillUIController();
    }
}
package main.java.vo.bill.purchasebill;

import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.purchasebl.PurchaseTradeBillBl;
import main.java.businesslogic.purchasebl.PurchaseTradeBillTool;
import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.po.bill.purchasebill.PurchaseTradeBillPO;
import main.java.po.goods.GoodsItemPO;
import main.java.presentation.purchaseui.PurchaseTradeBillUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * 进货退货单VO类
 * */
public class PurchaseTradeBillVO extends PurchaseBillVO {

    public PurchaseTradeBillVO(String state, Date time, UserVO operator, String comment, ClientVO client, ArrayList<GoodsItemVO> purchaseList, double total) {
        this.state = state;
        this.time = time;
        this.type = "进货单";
        this.operator = operator;
        this.comment = comment;
        this.client=client;
        this.purchaseList=purchaseList;
        this.total=total;
    }

    public PurchaseTradeBillVO(){
        this.ID = "";
        this.state = "";
        this.time=new Date();
        this.type = "进货单";
        this.operator = new UserVO();
        this.comment = "";
        this.client = new ClientVO();
        this.purchaseList = new ArrayList<>();
        this.total = 0;
    }

    public PurchaseTradeBillPO getPurchaseTradeBillPO()throws Exception{
        PurchaseTradeBillPO purchaseTradeBillPO=new PurchaseTradeBillPO();
        purchaseTradeBillPO.setID(this.ID);
        purchaseTradeBillPO.setVisible(this.visible);
        purchaseTradeBillPO.setState(this.state);
        purchaseTradeBillPO.setTime(this.time);
        purchaseTradeBillPO.setType(this.type);
        purchaseTradeBillPO.setOperatorID(this.operator.getID());
        purchaseTradeBillPO.setComment(this.comment);
        purchaseTradeBillPO.setClientID(this.client.getID());
        purchaseTradeBillPO.setTotal(this.total);

        ArrayList<GoodsItemPO> goodsItemPOS=new ArrayList<>();
        for(GoodsItemVO goodsItemVO:this.purchaseList){
            goodsItemPOS.add(goodsItemVO.getGoodsItemPO());
        }
        purchaseTradeBillPO.setPurchaseList(goodsItemPOS);

        return purchaseTradeBillPO;
    }

    public PurchaseTradeBillVO(PurchaseTradeBillPO purchaseTradeBillPO)throws Exception{
        this.ID=purchaseTradeBillPO.getID();
        this.visible=purchaseTradeBillPO.isVisible();
        this.state=purchaseTradeBillPO.getState();
        this.time=purchaseTradeBillPO.getTime();
        this.type=purchaseTradeBillPO.getType();
        this.comment=purchaseTradeBillPO.getComment();
        this.total=purchaseTradeBillPO.getTotal();

        UserTool userTool=new UserBl();
        this.operator=userTool.find(purchaseTradeBillPO.getOperatorID());

        ClientTool clientTool=new ClientBl();
        this.client=clientTool.find(purchaseTradeBillPO.getClientID());

        ArrayList<GoodsItemVO> goodsItemVOS=new ArrayList<>();
        for(GoodsItemPO goodsItemPO:purchaseTradeBillPO.getPurchaseList()){
            goodsItemVOS.add(new GoodsItemVO(goodsItemPO));
        }
        this.purchaseList=goodsItemVOS;
    }

    public PurchaseTradeBillTool getTool(){
        PurchaseTradeBillTool purchaseTradeBillTool=new PurchaseTradeBillBl();
        return purchaseTradeBillTool;
    }

    /*实现待定*/
    public PurchaseTradeBillUIController getInfoUIController() {
        return new PurchaseTradeBillUIController();
    }

}

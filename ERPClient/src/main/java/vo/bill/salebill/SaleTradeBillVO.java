package main.java.vo.bill.salebill;

import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.promotionbl.PromotionBl;
import main.java.businesslogic.promotionbl.PromotionTool;
import main.java.businesslogic.salebl.SaleTradeBillBl;
import main.java.businesslogic.salebl.SaleTradeBillTool;
import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.po.bill.salebill.SaleTradeBillPO;
import main.java.po.goods.GoodsItemPO;
import main.java.presentation.saleui.SaleTradeBillUIController;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.promotion.PromotionVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * 销售单VO类
 * */
public class SaleTradeBillVO extends SaleBillVO {
    private PromotionVO promotion; // 适用的促销策略
    private double totalBeforeDiscount; // 折让前总额
    private double discount; // 折让金额
    private double amountOfVoucher; // 代金卷金额
    private double totalAfterDiscount; // 折让后总额

    public SaleTradeBillVO(String state, Date time, UserVO operator, String comment, ClientVO client, UserVO salesman, ArrayList<GoodsItemVO> saleList, PromotionVO promotion, double totalBeforeDiscount, double discount, double amountOfVoucher, double totalAfterDiscount) {
        this.state = state;
        this.time = time;
        this.type = "销售单";
        this.operator = operator;
        this.comment = comment;
        this.client=client;
        this.salesman=salesman;
        this.saleList=saleList;
        this.promotion=promotion;
        this.totalBeforeDiscount=totalBeforeDiscount;
        this.discount=discount;
        this.amountOfVoucher=amountOfVoucher;
        this.totalAfterDiscount=totalAfterDiscount;
    }

    public SaleTradeBillVO(){
        this.state = "";
        this.time = new Date();
        this.type = "销售单";
        this.operator = new UserVO();
        this.comment = "";
        this.client=new ClientVO();
        this.salesman=new UserVO();
        this.saleList=new ArrayList<GoodsItemVO>();
        this.promotion=new PromotionVO();
        this.totalBeforeDiscount=0;
        this.discount=0;
        this.amountOfVoucher=0;
        this.totalAfterDiscount=0;
    }

    public SaleTradeBillPO getsaleTradeBillPO()throws Exception{
        SaleTradeBillPO saleTradeBillPO=new SaleTradeBillPO();
        saleTradeBillPO.setID(this.ID);
        saleTradeBillPO.setVisible(this.visible);
        saleTradeBillPO.setState(this.state);
        saleTradeBillPO.setTime(this.time);
        saleTradeBillPO.setType(this.type);
        saleTradeBillPO.setComment(this.comment);
        saleTradeBillPO.setTotalBeforeDiscount(this.totalBeforeDiscount);
        saleTradeBillPO.setDiscount(this.discount);
        saleTradeBillPO.setAmountOfVoucher(this.amountOfVoucher);
        saleTradeBillPO.setTotalAfterDiscount(this.totalAfterDiscount);

        saleTradeBillPO.setPromotionID(this.promotion.getID());

        ArrayList<GoodsItemPO> goodsItemPOS=new ArrayList<>();
        for(GoodsItemVO goodsItemVO:this.saleList){
            goodsItemPOS.add(goodsItemVO.getGoodsItemPO());
        }
        saleTradeBillPO.setSaleList(goodsItemPOS);

        saleTradeBillPO.setSalesmanID(this.salesman.getID());

        saleTradeBillPO.setClientID(this.client.getID());

        saleTradeBillPO.setOperatorID(this.operator.getID());

        return saleTradeBillPO;
    }

    public SaleTradeBillVO(SaleTradeBillPO saleTradeBillPO)throws Exception{
        this.ID=saleTradeBillPO.getID();
        this.visible=saleTradeBillPO.isVisible();
        this.state=saleTradeBillPO.getState();
        this.time=saleTradeBillPO.getTime();
        this.type=saleTradeBillPO.getType();
        this.comment=saleTradeBillPO.getComment();
        this.totalBeforeDiscount=saleTradeBillPO.getTotalBeforeDiscount();
        this.discount=saleTradeBillPO.getDiscount();
        this.amountOfVoucher=saleTradeBillPO.getAmountOfVoucher();
        this.totalAfterDiscount=saleTradeBillPO.getTotalAfterDiscount();

        PromotionTool promotionTool=new PromotionBl();
        this.promotion=promotionTool.find(saleTradeBillPO.getPromotionID());


        UserTool userTool=new UserBl();
        this.operator=userTool.find(saleTradeBillPO.getOperatorID());
        this.salesman=userTool.find(saleTradeBillPO.getSalesmanID());

        ClientTool clientTool=new ClientBl();
        this.client=clientTool.find(saleTradeBillPO.getClientID());

        ArrayList<GoodsItemVO> goodsItemVOS=new ArrayList<>();
        for(GoodsItemPO goodsItemPO:saleTradeBillPO.getSaleList()){
            goodsItemVOS.add(new GoodsItemVO(goodsItemPO));
        }
        this.saleList=goodsItemVOS;
    }
    
    public double getTotalBeforeDiscount() {
        return totalBeforeDiscount;
    }

    public double getDiscount() {
        return discount;
    }

    public double getAmountOfVoucher() {
        return amountOfVoucher;
    }

    public double getTotalAfterDiscount() {
        return totalAfterDiscount;
    }

    public PromotionVO getPromotion(){
        return promotion;
    }

    public void setPromotion(PromotionVO promotion) {
        this.promotion = promotion;
    }

    public void setTotalBeforeDiscount(double totalBeforeDiscount) {
        this.totalBeforeDiscount = totalBeforeDiscount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setAmountOfVoucher(double amountOfVoucher) {
        this.amountOfVoucher = amountOfVoucher;
    }

    public void setTotalAfterDiscount(double totalAfterDiscount) {
        this.totalAfterDiscount = totalAfterDiscount;
    }

    public SaleTradeBillTool getTool(){
        SaleTradeBillTool saleTradeBillTool=new SaleTradeBillBl();
        return saleTradeBillTool;
    }

    public SaleTradeBillUIController getInfoUIController(){
        return new SaleTradeBillUIController();
    }

}

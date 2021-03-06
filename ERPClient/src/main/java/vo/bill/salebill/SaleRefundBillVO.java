package main.java.vo.bill.salebill;

import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.salebl.SaleRefundBillBl;
import main.java.businesslogic.salebl.SaleRefundBillTool;
import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.po.bill.salebill.SaleRefundBillPO;
import main.java.po.goods.GoodsItemPO;
import main.java.presentation.saleui.SaleRefundBillUIController;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * 销售退货单PO类
 * */
public class SaleRefundBillVO extends SaleBillVO {
    private double total; // 总额

    public SaleRefundBillVO(String state, Date time, UserVO operator, String comment, ClientVO client, UserVO salesman, ArrayList<GoodsItemVO> saleList, double total) {
        this.state = state;
        this.time = time;
        this.type = "销售退货单";
        this.operator = operator;
        this.comment = comment;
        this.client=client;
        this.salesman=salesman;
        this.saleList=saleList;
        this.total=total;
    }

    public SaleRefundBillVO(){
        this.state = "";
        this.time = new Date();
        this.type = "销售退货单";
        this.operator = new UserVO();
        this.comment = "";
        this.client=new ClientVO();
        this.salesman=new UserVO();
        this.saleList=new ArrayList<GoodsItemVO>();
        this.total=0;
    }

    public SaleRefundBillPO getSaleRefundBillPO()throws Exception{
        SaleRefundBillPO saleRefundBillPO=new SaleRefundBillPO();
        saleRefundBillPO.setID(this.ID);
        saleRefundBillPO.setVisible(this.visible);
        saleRefundBillPO.setState(this.state);
        saleRefundBillPO.setTime(this.time);
        saleRefundBillPO.setType(this.type);
        saleRefundBillPO.setComment(this.comment);
        saleRefundBillPO.setTotal(this.total);

        ArrayList<GoodsItemPO> goodsItemPOS=new ArrayList<>();
        for(GoodsItemVO goodsItemVO:this.saleList){
            goodsItemPOS.add(goodsItemVO.getGoodsItemPO());
        }
        saleRefundBillPO.setSaleList(goodsItemPOS);

        saleRefundBillPO.setSalesmanID(this.salesman.getID());

        saleRefundBillPO.setClientID(this.client.getID());

        saleRefundBillPO.setOperatorID(this.operator.getID());

        return saleRefundBillPO;
    }

    public SaleRefundBillVO(SaleRefundBillPO saleRefundBillPO)throws Exception{
        this.ID=saleRefundBillPO.getID();
        this.visible=saleRefundBillPO.isVisible();
        this.state=saleRefundBillPO.getState();
        this.time=saleRefundBillPO.getTime();
        this.type=saleRefundBillPO.getType();
        this.comment=saleRefundBillPO.getComment();
        this.total=saleRefundBillPO.getTotal();

        UserTool userTool=new UserBl();
        this.operator=userTool.find(saleRefundBillPO.getOperatorID());
        this.salesman=userTool.find(saleRefundBillPO.getSalesmanID());

        ClientTool clientTool=new ClientBl();
        this.client=clientTool.find(saleRefundBillPO.getClientID());

        ArrayList<GoodsItemVO> goodsItemVOS=new ArrayList<>();
        for(GoodsItemPO goodsItemPO:saleRefundBillPO.getSaleList()){
            goodsItemVOS.add(new GoodsItemVO(goodsItemPO));
        }
        this.saleList=goodsItemVOS;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public SaleRefundBillTool getTool(){
        SaleRefundBillTool saleRefundBillTool=new SaleRefundBillBl();
        return saleRefundBillTool;
    }

    public SaleRefundBillUIController getInfoUIController(){
        return new SaleRefundBillUIController();
    }
}

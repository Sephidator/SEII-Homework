package main.java.vo.bill.salebill;

import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.salebl.SaleRefundBillBl;
import main.java.businesslogic.salebl.SaleRefundBillTool;
import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.po.bill.salebill.SaleRefundBillPO;
import main.java.po.goods.GoodsItemPO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.user.UserQueryVO;
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

    public SaleRefundBillVO(){}

    public SaleRefundBillPO getSaleRefundBillPO(){
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

    public SaleRefundBillVO(SaleRefundBillPO saleRefundBillPO){
        this.ID=saleRefundBillPO.getID();
        this.visible=saleRefundBillPO.isVisible();
        this.state=saleRefundBillPO.getState();
        this.time=saleRefundBillPO.getTime();
        this.type=saleRefundBillPO.getType();
        this.comment=saleRefundBillPO.getComment();
        this.total=saleRefundBillPO.getTotal();

        UserTool userTool=new UserBl();
        UserQueryVO userQueryVO=new UserQueryVO(saleRefundBillPO.getOperatorID(),null,null);
        UserQueryVO userQueryVO1=new UserQueryVO(saleRefundBillPO.getSalesmanID(),null,null);
        this.operator=userTool.getUserList(userQueryVO).get(0);
        this.salesman=userTool.getUserList(userQueryVO1).get(0);

        ClientTool clientTool=new ClientBl();
        ClientQueryVO clientQueryVO=new ClientQueryVO(saleRefundBillPO.getClientID(),null);
        this.client=clientTool.getClientList(clientQueryVO).get(0);

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
}

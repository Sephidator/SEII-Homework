package po;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 销售单PO类
 * */
public class XSDPO implements Serializable{
    private String ID; // 单据ID
    private String clientID; // 客户信息
    private String repositoryID; // 仓库
    private String salesmanID; // 业务员
    private String operatorID; // 操作员
    private ArrayList<String> goodsID;// 出货商品清单
    private double beforeDiscount; // 折让前总额
    private double discount; // 折让金额
    private double amountOfCoupon; // 代金卷金额
    private double afterDiscount; // 折让后总额
    private String comment; // 备注
    private int state;// 单据状态，0为草稿，1待审批，2为审批通过，3为审批不通过

    public XSDPO(String ID, String clientID, String repositoryID,
                 String salesmanID,String operatorID, ArrayList<String> goodsID,
                 double beforeDiscount,double discount, double amountOFCoupon,
                 double afterDiscount, String comment,int state){

        this.ID=ID;
        this.clientID=clientID;
        this.repositoryID=repositoryID;
        this.salesmanID=salesmanID;
        this.operatorID=operatorID;
        this.goodsID=goodsID;
        this.beforeDiscount=beforeDiscount;
        this.discount=discount;
        this.amountOfCoupon=amountOFCoupon;
        this.afterDiscount=afterDiscount;
        this.comment=comment;
        this.state=state;
    }

    public String getID(){
        return ID;
    }

    public String getClientID(){
        return clientID;
    }

    public String getRepositoryID(){
        return repositoryID;
    }

    public String getSalesmanID(){
        return salesmanID;
    }

    public String getOperatorID(){
        return operatorID;
    }

    public ArrayList<String> getGoodsID(){
        return goodsID;
    }

    public double getBeforeDiscount(){
        return beforeDiscount;
    }

    public double getDiscount(){
        return discount;
    }

    public double getAmountOfCoupon(){
        return amountOfCoupon;
    }

    public double getAfterDiscount(){
        return afterDiscount;
    }

    public String getComment(){
        return comment;
    }

    public int getState(){
        return state;
    }

    public void setState(int state){
        this.state=state;
    }
}

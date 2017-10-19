package po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * 进货单PO类
 * */
public class PurchaseRefundBillPO implements Serializable {
    private String ID; // 单据ID
    private String clientID; // 客户信息
    private String repositoryID; // 仓库
    private String operatorID; // 操作员
    private HashMap<String, Integer> goodslist=new HashMap();
    private double totalAmount; // 总额
    private String comment; // 备注
    private int state;// 单据状态，0为草稿，1待审批，2为审批通过，3为审批不通过
    private Date time; //单据建立时间

    public PurchaseRefundBillPO(String ID, String clientID, String repositoryID, String operatorID,
                                HashMap<String, Integer> goodsList, double totalAmount, String comment, int state){
        this.ID=ID;
        this.clientID=clientID;
        this.repositoryID=repositoryID;
        this.operatorID=operatorID;
        this.goodslist=goodsList;
        this.totalAmount=totalAmount;
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

    public String getOperatorID(){
        return operatorID;
    }

    public HashMap<String,Integer> getGoodslist(){
        return goodslist;
    }

    public double getTotalAmount(){
        return totalAmount;
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

    public Date getTime(){ return time; }
}

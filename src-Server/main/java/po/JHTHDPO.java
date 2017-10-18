package po;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 进货退货单PO类
 * */
public class JHTHDPO implements Serializable {
    private String ID; // 单据ID
    private String clientID; // 客户信息
    private String repositoryID; // 仓库
    private String operatorID; // 操作员
    private ArrayList<String> goodsID;// 出库商品列表
    private double totalAmount; // 总额
    private String comment; // 备注
    private int state;// 单据状态，0为草稿，1待审批，2为审批通过，3为审批不通过

    public JHTHDPO(String ID,String clientID,String repositoryID,String operatorID,
                 ArrayList<String> goodsID,double totalAmount,String comment,int state){
        this.ID=ID;
        this.clientID=clientID;
        this.repositoryID=repositoryID;
        this.operatorID=operatorID;
        this.goodsID=goodsID;
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

    public ArrayList<String> getGoodsID(){
        return goodsID;
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
}

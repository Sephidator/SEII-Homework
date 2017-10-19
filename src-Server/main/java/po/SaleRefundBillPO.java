package po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * 销售退货单PO类
 * */
public class SaleRefundBillPO implements Serializable {
    private String ID; // 单据ID
    private String clientID; // 客户信息
    private String repositoryID; // 仓库
    private String salesmanID; // 业务员
    private String operatorID; // 操作员
    private HashMap<String, Integer> goodsList=new HashMap();
    private double totalAmount; // 总额
    private String comment; // 备注
    private int state;// 单据状态，0为草稿，1待审批，2为审批通过，3为审批不通过
    private Date time; // 单据建立时间

    public SaleRefundBillPO(String ID, String clientID, String repositoryID, String salesmanID,
                            String operatorID, HashMap<String, Integer> goodsList, double totalAmount,
                            String comment, int state, Date time) {
        this.ID = ID;
        this.clientID = clientID;
        this.repositoryID = repositoryID;
        this.salesmanID = salesmanID;
        this.operatorID = operatorID;
        this.goodsList = goodsList;
        this.totalAmount = totalAmount;
        this.comment = comment;
        this.state = state;
    }

    public String getID() {
        return ID;
    }

    public String getClientID() {
        return clientID;
    }

    public String getRepositoryID() {
        return repositoryID;
    }

    public String getSalesmanID() {
        return salesmanID;
    }

    public String getOperatorID() {
        return operatorID;
    }

    public HashMap<String, Integer> getGoodsList() {
        return goodsList;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getComment() {
        return comment;
    }

    public int getState() {
        return state;
    }

    public Date getTime(){ return time; }
}

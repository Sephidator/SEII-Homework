package po;

import java.io.Serializable;
import java.util.HashMap;

public class PurchasePO extends BillPO {
    protected String clientID; // 客户信息
    protected String repositoryID; // 仓库
    protected String operatorID; // 操作员
    protected HashMap<String, Integer> goodslist=new HashMap();
    protected double totalAmount; // 总额
    protected String comment; // 备注

    public String getClientID() {
        return clientID;
    }

    public String getRepositoryID() {
        return repositoryID;
    }

    public String getOperatorID() {
        return operatorID;
    }

    public HashMap<String, Integer> getGoodslist() {
        return goodslist;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getComment() {
        return comment;
    }
}

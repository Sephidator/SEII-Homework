package po;

import java.util.HashMap;

public class SaleBillPO extends BillPO {
    protected String clientID; // 客户信息
    protected String repositoryID; // 仓库
    protected String salesmanID; // 业务员
    protected String operatorID; // 操作员
    protected HashMap<String, Integer> goodsList=new HashMap();
    protected String comment; // 备注

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

    public String getComment() {
        return comment;
    }
}

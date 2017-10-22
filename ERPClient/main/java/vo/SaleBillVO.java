package vo;

import java.util.HashMap;

public class SaleBillVO extends BillVO {
    protected ClientVO client; // 客户信息
    protected String repositoryID; // 仓库
    protected UserVO salesman; // 业务员
    protected UserVO operator; // 操作员
    protected HashMap<GoodsVO, Integer> goodsList=new HashMap();
    protected String comment; // 备注

    public ClientVO getClient() {
        return client;
    }

    public String getRepositoryID() {
        return repositoryID;
    }

    public UserVO getSalesman() {
        return salesman;
    }

    public UserVO getOperator() {
        return operator;
    }

    public HashMap<GoodsVO, Integer> getGoodsList() {
        return goodsList;
    }

    public String getComment() {
        return comment;
    }
}
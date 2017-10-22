package vo;

import java.util.HashMap;

public class PurchaseBillVO extends BillVO {
    protected ClientVO client; // 客户信息
    protected String repositoryID; // 仓库
    protected UserVO operator; // 操作员
    protected HashMap<GoodsVO, Integer> goodsList=new HashMap();
    protected double totalAmount; // 总额
    protected String comment; // 备注

    public ClientVO getClient() {
        return client;
    }

    public String getRepositoryID() {
        return repositoryID;
    }

    public UserVO getOperator() {
        return operator;
    }

    public HashMap<GoodsVO, Integer> getGoodsList() {
        return goodsList;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getComment() {
        return comment;
    }
}

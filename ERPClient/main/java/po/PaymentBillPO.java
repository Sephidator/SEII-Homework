package po;

import java.util.Date;
import java.util.HashMap;

/*
 * 付款单数据
 * @author:
 * @version:
 */

public class PaymentBillPO extends FinanceBillPO{

    private String client;//付款单的客户，包括供应商和销售商组成的字符

    private HashMap<String,Double> transList;//付款单转账列表的银行账户和金额

    public PaymentBillPO(String ID, int state, Date time, double total, String operator,
                         String comment, String client, HashMap<String,Double> transList){
        this.ID=ID;
        this.state=state;
        this.time=time;
        this.total=total;
        this.operator=operator;
        this.comment=comment;
        this.client=client;
        this.transList=transList;
    }

    public String getClient() {
        return client;
    }

    public HashMap<String, Double> getTransList() {
        return transList;
    }
}


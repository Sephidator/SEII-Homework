package vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * 付款单数据
 * @author:
 * @version:
 */

public class PaymentBillVO extends FinanceBillVO {

    public String client;//付款单的客户，包括供应商和销售商组成的字符

    public HashMap<String,Double> transList;//付款单转账列表的银行账户和金额

    public PaymentBillVO(String ID, int state, Date time, double total, String operator,
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

    /*根据条目清单中的每一个条目的金额的总额*/
    public double calcTotal(HashMap<String, Double> itemList){
        double total = 0;
        Iterator iter = itemList.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry)iter.next();
            total += Double.parseDouble(String.valueOf(entry.getValue()));
        }
        return total;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[单据编号=" + this.ID + ", " + "单据状态=" + this.state + ", 创建时间=" + this.time
                + ", 总额=" + this.total +  ", 操作员=" + this.operator + ", 备注=" + this.comment +
                ", 转账列表=" + this.transList +  "]";
    }
}


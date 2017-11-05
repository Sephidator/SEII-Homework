package vo.bill.financebill;

import vo.bill.financebill.FinanceBillVO;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * 现金费用单数据
 * @author:
 * @version:
 */

public class CashBillVO extends FinanceBillVO {

    private HashMap<String, Double> itemList;//现金费用单条目清单的条目名和金额

    public CashBillVO(String ID, int state, Date time, double total, String operator,
                      String comment, HashMap<String, Double> itemList) {
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.total = total;
        this.operator = operator;
        this.comment = comment;
        this.itemList = itemList;
    }

    /*根据条目清单中的每一个条目的金额的总额*/
    public double calcTotal(HashMap<String, Double> itemList) {
        double total = 0;
        Iterator iter = itemList.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            total += Double.parseDouble(String.valueOf(entry.getValue()));
        }
        return total;
    }

    public HashMap<String, Double> getItemList() {
        return itemList;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "[单据编号=" + this.ID + ", " + "单据状态=" + this.state + ", 创建时间=" + this.time
                + ", 总额=" + this.total + ", 操作员=" + this.operator + ", 备注=" + this.comment +
                ", 条目清单=" + this.itemList + "]";
    }
}


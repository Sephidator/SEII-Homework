package po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/*
 * 现金费用单数据
 * @author:
 * @version:
 */

public class CashBillPO extends FinanceBillPO{

    private HashMap<String,Double> itemList;//现金费用单条目清单的条目名和金额

    public CashBillPO(String ID, int state, Date time,double total, String operator,
                      String comment, HashMap<String,Double> itemList){
        this.ID=ID;
        this.state=state;
        this.time=time;
        this.total=total;
        this.operator=operator;
        this.comment=comment;
        this.itemList=itemList;
    }

    public HashMap<String, Double> getItemList() {
        return itemList;
    }
}


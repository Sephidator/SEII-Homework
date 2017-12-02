package main.java.po.bill.financebill;

import java.util.ArrayList;
import java.util.Date;

/*
 * 现金费用单数据
 * @author:
 * @version:
 */

public class CashBillPO extends FinanceBillPO {

    private ArrayList<CashItemPO> itemList;//现金费用单条目清单的条目名和金额

    public CashBillPO() {

    }

    public CashBillPO(String state, Date time, String operatorID, String comment, double total, ArrayList<CashItemPO> itemList) {
        this.state = state;
        this.time = time;
        this.operatorID = operatorID;
        this.comment = comment;
        type = "现金费用单";
        this.total = total;
        this.itemList = itemList;
    }

    public ArrayList<CashItemPO> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<CashItemPO> itemList) {
        this.itemList = itemList;
    }
}


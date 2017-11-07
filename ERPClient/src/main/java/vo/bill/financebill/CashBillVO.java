package vo.bill.financebill;

import vo.user.UserVO;

import java.util.*;

/*
 * 现金费用单数据
 * @author:
 * @version:
 */

public class CashBillVO extends FinanceBillVO {

    private ArrayList<CashItemVO> itemList;//现金费用单条目清单的条目名和金额

    public CashBillVO(){

    }

    public CashBillVO(String ID, String state, Date time, String type, UserVO operator, String comment, double total,  ArrayList<CashItemVO> itemList) {
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.type = type;
        this.operator = operator;
        this.comment = comment;
        this.total=total;
        this.itemList=itemList;
    }

    public ArrayList<CashItemVO> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<CashItemVO> itemList) {
        this.itemList = itemList;
    }
}


package main.java.vo.bill.financebill;
import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.po.bill.financebill.CashBillPO;
import main.java.po.bill.financebill.CashItemPO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

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

    public CashBillVO(String state, Date time,UserVO operator, String comment, double total,  ArrayList<CashItemVO> itemList) {
        this.state = state;
        this.time = time;
        this.type = "现金费用单";
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

    /*得到CashBillPO*/
    public CashBillPO getCashBillPO(){
        CashBillPO cashBillPO = new CashBillPO();
        cashBillPO.setID(this.ID);
        cashBillPO.setState(this.state);
        cashBillPO.setTime(this.time);
        cashBillPO.setType(this.type);
        cashBillPO.setOperatorID(this.operator.getID());
        cashBillPO.setComment(this.comment);
        cashBillPO.setTotal(this.total);
        cashBillPO.setVisible(this.visible);

        /*处理ArrayList<CashItemVO>*/
        ArrayList<CashItemPO> cashItemPOArrayList = new ArrayList<>();
        for(CashItemVO cashItemVO : this.itemList){
            cashItemPOArrayList.add(cashItemVO.getCashItemPO());
        }
        cashBillPO.setItemList(cashItemPOArrayList);

        return cashBillPO;
    }

    /*得到CashBillVO*/
    public CashBillVO(CashBillPO cashBillPO){
        this.ID = cashBillPO.getID();
        this.state = cashBillPO.getState();
        this.time = cashBillPO.getTime();
        this.type = cashBillPO.getType();
        this.visible = cashBillPO.isVisible();
        this.comment = cashBillPO.getComment();
        this.total = cashBillPO.getTotal();

        /*得到UserVO*/
        UserTool userTool = new UserBl();
        UserQueryVO userQueryVO = new UserQueryVO();
        userQueryVO.ID = cashBillPO.getOperatorID();
        userQueryVO.name = "";//初始化防止NPE
        userQueryVO.type = "";
        UserVO uservO = userTool.getUserList(userQueryVO).get(0);//拿到第一个userPO对象
        this.operator = uservO;

        /*转换CashItemPO到CashItemVO*/
        ArrayList<CashItemPO> cashItemPOS = cashBillPO.getItemList();
        ArrayList<CashItemVO> cashItemVOArrayList = new ArrayList<>();
        for(CashItemPO cashItemPO : cashItemPOS)
            cashItemVOArrayList.add(new CashItemVO(cashItemPO));
        this.itemList = cashItemVOArrayList;
    }
}


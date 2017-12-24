package main.java.vo.bill.financebill;
import main.java.businesslogic.accountbl.AccountBl;
import main.java.businesslogic.accountbl.AccountTool;
import main.java.businesslogic.financebl.CashBillBl;
import main.java.businesslogic.financebl.CashBillTool;
import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.po.bill.financebill.CashBillPO;
import main.java.po.bill.financebill.CashItemPO;
import main.java.presentation.financeui.CashBillUIController;
import main.java.vo.account.AccountVO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.*;

/*
 * 现金费用单数据
 * @author:
 * @version:
 */

public class CashBillVO extends FinanceBillVO {

    private AccountVO account;//现金费用单的银行账户

    private ArrayList<CashItemVO> itemList;//现金费用单条目清单的条目名和金额

    public CashBillVO(){
        this.account = new AccountVO();
        this.state = "";
        this.time = new Date();
        this.type = "现金费用单";
        this.operator = new UserVO();
        this.comment = "";
        this.total=0;
        this.itemList=new ArrayList<>();
    }

    public CashBillVO(AccountVO account, String state, Date time,UserVO operator, String comment, double total,  ArrayList<CashItemVO> itemList) {
        this.account = account;
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

    public AccountVO getAccount(){return this.account;}

    public void setAccount(AccountVO account){this.account = account;}

    /*得到CashBillPO*/
    public CashBillPO getCashBillPO(){
        CashBillPO cashBillPO = new CashBillPO();
        cashBillPO.setAccountID(this.account.getID());
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
    public CashBillVO(CashBillPO cashBillPO)throws Exception{
        this.ID = cashBillPO.getID();
        this.state = cashBillPO.getState();
        this.time = cashBillPO.getTime();
        this.type = cashBillPO.getType();
        this.visible = cashBillPO.isVisible();
        this.comment = cashBillPO.getComment();
        this.total = cashBillPO.getTotal();

        AccountTool accountTool = new AccountBl();
        this.account = accountTool.find(cashBillPO.getAccountID());

        /*得到UserVO*/
        UserTool userTool = new UserBl();
        UserVO uservO = userTool.find(cashBillPO.getOperatorID());
        this.operator = uservO;

        /*转换CashItemPO到CashItemVO*/
        ArrayList<CashItemPO> cashItemPOS = cashBillPO.getItemList();
        ArrayList<CashItemVO> cashItemVOArrayList = new ArrayList<>();
        for(CashItemPO cashItemPO : cashItemPOS)
            cashItemVOArrayList.add(new CashItemVO(cashItemPO));
        this.itemList = cashItemVOArrayList;
    }

    /*getTool*/
    public CashBillTool getTool(){
        CashBillTool cashBillTool = new CashBillBl();
        return cashBillTool;
    }

    /*实现待定*/
    public CashBillUIController getInfoUIController() {
        return new CashBillUIController();
    }
}


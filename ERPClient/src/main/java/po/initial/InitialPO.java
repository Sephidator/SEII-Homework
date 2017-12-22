package main.java.po.initial;

import main.java.po.PO;
import main.java.po.account.AccountPO;
import main.java.po.client.ClientPO;
import main.java.po.goods.GoodsPO;

import java.util.ArrayList;

public class InitialPO extends PO {

    private int year;//每一个账都有一个年份

    private ArrayList<GoodsPO> goodsList;//每一个商品都有一个ID

    private ArrayList<ClientPO> clientList;//每一个客户都有一个ID

    private ArrayList<AccountPO> accountList;//每一个账户都有一个ID

    public InitialPO() {
    }

    public InitialPO(int year, ArrayList<GoodsPO> goodsList, ArrayList<ClientPO> clientList, ArrayList<AccountPO> accountList) {
        this.year = year;
        this.goodsList = goodsList;
        this.clientList = clientList;
        this.accountList = accountList;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<GoodsPO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(ArrayList<GoodsPO> goodsList) {
        this.goodsList = goodsList;
    }

    public ArrayList<ClientPO> getClientList() {
        return clientList;
    }

    public void setClientList(ArrayList<ClientPO> clientList) {
        this.clientList = clientList;
    }

    public ArrayList<AccountPO> getAccountList() {
        return accountList;
    }

    public void setAccountList(ArrayList<AccountPO> accountList) {
        this.accountList = accountList;
    }
}

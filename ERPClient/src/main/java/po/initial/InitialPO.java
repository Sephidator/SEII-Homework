package main.java.po.initial;

import main.java.po.PO;

import java.util.ArrayList;

public class InitialPO extends PO {

    private int year;//每一个账都有一个年份

    private ArrayList<String> goodsIDList;//每一个商品都有一个ID

    private ArrayList<String> goodsSortIDList;//每一个商品分类都有一个ID

    private ArrayList<String> clientIDList;//每一个客户都有一个ID

    private ArrayList<String> accountIDList;//每一个账户都有一个ID

    public InitialPO() {
    }

    public InitialPO(int year, ArrayList<String> goodsIDList, ArrayList<String> goodsSortIDList, ArrayList<String> clientIDList, ArrayList<String> accountIDList) {
        this.year = year;
        this.goodsIDList = goodsIDList;
        this.goodsSortIDList = goodsSortIDList;
        this.clientIDList = clientIDList;
        this.accountIDList = accountIDList;
    }

    public int getYear() {
        return year;
    }

    public ArrayList<String> getGoodsIDList() {
        return goodsIDList;
    }

    public ArrayList<String> getGoodsSortIDList() {
        return goodsSortIDList;
    }

    public ArrayList<String> getClientIDList() {
        return clientIDList;
    }

    public ArrayList<String> getAccountIDList() {
        return accountIDList;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGoodsIDList(ArrayList<String> goodsIDList) {
        this.goodsIDList = goodsIDList;
    }

    public void setGoodsSortIDList(ArrayList<String> goodsSortIDList) {
        this.goodsSortIDList = goodsSortIDList;
    }

    public void setClientIDList(ArrayList<String> clientIDList) {
        this.clientIDList = clientIDList;
    }

    public void setAccountIDList(ArrayList<String> accountIDList) {
        this.accountIDList = accountIDList;
    }
}

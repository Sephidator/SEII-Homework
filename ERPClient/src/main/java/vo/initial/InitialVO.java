package vo.initial;

import vo.account.AccountVO;
import vo.client.ClientVO;
import vo.goods.GoodsSortVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;
//import GoodsPO
/*
 * 期初建账数据
 * @author:
 * @version:
 */

public class InitialVO{

    private int year;//每一个账都有一个年份

    private ArrayList<GoodsVO> goodsList;//每一个商品都有一个ID

    private ArrayList<GoodsSortVO> goodsSortList;//每一个商品分类都有一个ID

    private ArrayList<ClientVO> clientList;//每一个客户都有一个ID

    private ArrayList<AccountVO> accountList;//每一个账户都有一个ID

    public InitialVO() {
    }

    public InitialVO(int year, ArrayList<GoodsVO> goodsList, ArrayList<GoodsSortVO> goodsSortList, ArrayList<ClientVO> clientList, ArrayList<AccountVO> accountList) {
        this.year = year;
        this.goodsList = goodsList;
        this.goodsSortList = goodsSortList;
        this.clientList = clientList;
        this.accountList = accountList;
    }

    public int getYear() {
        return year;
    }

    public ArrayList<GoodsVO> getGoodsList() {
        return goodsList;
    }

    public ArrayList<GoodsSortVO> getGoodsSortList() {
        return goodsSortList;
    }

    public ArrayList<ClientVO> getClientList() {
        return clientList;
    }

    public ArrayList<AccountVO> getAccountList() {
        return accountList;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGoodsList(ArrayList<GoodsVO> goodsList) {
        this.goodsList = goodsList;
    }

    public void setGoodsSortList(ArrayList<GoodsSortVO> goodsSortList) {
        this.goodsSortList = goodsSortList;
    }

    public void setClientList(ArrayList<ClientVO> clientList) {
        this.clientList = clientList;
    }

    public void setAccountList(ArrayList<AccountVO> accountList) {
        this.accountList = accountList;
    }
}

package main.java.vo.initial;

import main.java.businesslogic.accountbl.AccountBl;
import main.java.businesslogic.accountbl.AccountTool;
import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogic.goodssortbl.GoodsSortBl;
import main.java.businesslogic.goodssortbl.GoodsSortTool;
import main.java.po.account.AccountPO;
import main.java.po.client.ClientPO;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsSortPO;
import main.java.po.initial.InitialPO;
import main.java.vo.VO;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsSortQueryVO;
import main.java.vo.goods.GoodsSortVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;
//import GoodsPO
/*
 * 期初建账数据
 * @author:
 * @version:
 */

public class InitialVO extends VO {

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

    /*不需要将VO转成PO*/

    /*将PO转换成VO*/
    public InitialVO(InitialPO initialPO)throws Exception{
        /*得到ArrayList<GoodsVO>*/
        ArrayList<GoodsVO> goodsVOArrayList = new ArrayList<>();
        for(GoodsPO goods : initialPO.getGoodsList()){
            goodsVOArrayList.add(new GoodsVO(goods));
        }
        this.goodsList = goodsVOArrayList;

        /*得到ArrayList<GoodsSortVO>*/
        ArrayList<GoodsSortVO> goodsSortVOArrayList = new ArrayList<>();
        for(GoodsSortPO goodsSort : initialPO.getGoodsSortList()){
            goodsSortVOArrayList.add(new GoodsSortVO(goodsSort));
        }
        this.goodsSortList = goodsSortVOArrayList;

        /*得到ArrayList<ClientVO>*/
        ArrayList<ClientVO> clientVOArrayList = new ArrayList<>();
        for(ClientPO client : initialPO.getClientList()){
            clientVOArrayList.add(new ClientVO(client));
        }
        this.clientList = clientVOArrayList;

        /*得到ArrayList<AccountVO>*/
        ArrayList<AccountVO> accountVOArrayList = new ArrayList<>();
        for(AccountPO account : initialPO.getAccountList()){
            accountVOArrayList.add(new AccountVO(account));
        }
        this.accountList = accountVOArrayList;
    }
}

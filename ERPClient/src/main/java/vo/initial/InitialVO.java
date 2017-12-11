package main.java.vo.initial;

import main.java.po.account.AccountPO;
import main.java.po.client.ClientPO;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsSortPO;
import main.java.po.initial.InitialPO;
import main.java.vo.VO;
import main.java.vo.account.AccountVO;
import main.java.vo.client.ClientVO;
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

    /*年份由UI层分配*/
    private int year;//每一个账都有一个年份

    private ArrayList<GoodsVO> goodsList;//每一个商品都有一个ID

    private ArrayList<GoodsSortVO> goodsSortList;//每一个商品分类都有一个ID

    private ArrayList<ClientVO> clientList;//每一个客户都有一个ID

    private ArrayList<AccountVO> accountList;//每一个账户都有一个ID

    public InitialVO() {
        this.year = 0;
        this.goodsList = new ArrayList<>();
        this.goodsSortList = new ArrayList<>();
        this.clientList = new ArrayList<>();
        this.accountList = new ArrayList<>();
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

    /*将VO转成PO*/
    public InitialPO getInitialPO(){
        InitialPO initialPO = new InitialPO();
        initialPO.setYear(this.getYear());

        /*转换AccountVOList到AccountPOList*/
        ArrayList<AccountPO> accountPOS = new ArrayList<>();
        ArrayList<AccountVO> accountVOS = this.getAccountList();
        for(AccountVO accountVO : accountVOS)
            accountPOS.add(accountVO.getAccountPO());
        initialPO.setAccountList(accountPOS);

        /*转换ClientVOList到ClientPOList*/
        ArrayList<ClientPO> clientPOS = new ArrayList<>();
        ArrayList<ClientVO> clientVOS = this.getClientList();
        for(ClientVO clientVO : clientVOS)
            clientPOS.add(clientVO.getClientPO());
        initialPO.setClientList(clientPOS);

        /*转换GoodsVOList到GoodsPOList*/
        ArrayList<GoodsPO> goodsPOS = new ArrayList<>();
        ArrayList<GoodsVO> goodsVOS = this.getGoodsList();
        for(GoodsVO goodsVO : goodsVOS)
            goodsPOS.add(goodsVO.getGoodsPO());
        initialPO.setGoodsList(goodsPOS);

        /*转换AccountVOList到AccountPOList*/
        ArrayList<GoodsSortPO> goodsSortPOS = new ArrayList<>();
        ArrayList<GoodsSortVO> goodsSortVOS = this.getGoodsSortList();
        for(GoodsSortVO goodsSortVO : goodsSortVOS)
            goodsSortPOS.add(goodsSortVO.getGoodsSortPO());
        initialPO.setGoodsSortList(goodsSortPOS);

        return initialPO;
    }

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

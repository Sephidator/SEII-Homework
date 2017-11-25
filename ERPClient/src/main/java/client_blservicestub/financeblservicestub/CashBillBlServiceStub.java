package main.java.client_blservicestub.financeblservicestub;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.financeblservice.CashBillBlService;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.financebill.CashBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class CashBillBlServiceStub implements CashBillBlService{

    @Override
    public String getID() {
        return "XJFYD-20171106-00001";
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        //String ID;商品编号String name;商品名称GoodsSortVO goodsSort;商品所在商品分类String model;商品型号
        //int number;商品数量double cost; 商品进价double retail;商品零售价double latestCost;商品最近进价
        //double latestRetail;商品最近零售价int alarmNum;商品报警数量String comment;商品的备注boolean visible;商品是否存在
        GoodsVO goodsVO = new GoodsVO("Goods-20171106-00001","灯",null,"大",10,10,11,9,11,100,"",true);
        ArrayList<GoodsVO> goodsVOArrayList = new ArrayList<GoodsVO>();
        goodsVOArrayList.add(goodsVO);
        return goodsVOArrayList;
    }

    @Override
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) {
        AccountVO accountVO = new AccountVO("Account-20171106-00001","6212262402011111111","公司甲帐",10000);
        ArrayList<AccountVO> accountVOArrayList = new ArrayList<AccountVO>();
        accountVOArrayList.add(accountVO);
        return accountVOArrayList;
    }

    @Override
    public ResultMessage submit(CashBillVO vo) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage saveDraft(CashBillVO vo) {
        return ResultMessage.success;
    }
}

package main.java.businesslogicservice.goodssortblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.goods.GoodsSortQueryVO;
import main.java.vo.goods.GoodsSortVO;

import java.util.ArrayList;

public interface GoodsSortBLService {

    //根据输入信息返回相应的商品分类
    public ArrayList<GoodsSortVO> getGoodsSortList(GoodsSortQueryVO query) throws Exception;

    //添加一个商品分类
    public String addGoodsSort(GoodsSortVO goodsSort) throws Exception;

    //删除一个商品分类
    public void deleteGoodsSort(String goodsSortID) throws Exception;

    //编辑一个商品分类
    public void editGoodsSort(GoodsSortVO goodsSort) throws Exception;

}

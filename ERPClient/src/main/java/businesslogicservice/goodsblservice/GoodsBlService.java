package main.java.businesslogicservice.goodsblservice;

import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface GoodsBlService {

    //返回商品列表
    ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception;

    //添加一个商品
    String addGoods(GoodsVO goods) throws Exception;

    //编辑一个商品
    void editGoods(GoodsVO goods) throws Exception;

    //删除一个商品
    void deleteGoods(String goodsID) throws Exception;
}

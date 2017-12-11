package main.java.businesslogicservice.goodssortblservice;

import main.java.vo.goods.GoodsSortVO;

public interface GoodsSortBlService {

    //寻找商品分类的根节点
    public GoodsSortVO getRoot() throws Exception;

    //查找一个商品分类
    public GoodsSortVO find(String goodsSortID) throws Exception;

    //添加一个商品分类
    public String addGoodsSort(GoodsSortVO goodsSort) throws Exception;

    //删除一个商品分类
    public void deleteGoodsSort(String goodsSortID) throws Exception;

    //编辑一个商品分类
    public void editGoodsSort(GoodsSortVO goodsSort) throws Exception;

}

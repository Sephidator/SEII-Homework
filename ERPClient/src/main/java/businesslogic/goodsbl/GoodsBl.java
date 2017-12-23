package main.java.businesslogic.goodsbl;

import main.java.businesslogicservice.goodsblservice.GoodsBlService;
import main.java.datafactory.goodsdatafactory.GoodsDataFactory;
import main.java.dataservice.goodsdataservice.GoodsDataService;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsQueryPO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class GoodsBl implements GoodsBlService, GoodsTool {
    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的商品查询对象
     * @return: 返回ArrayList<GoodsVO>的商品列表
     */
    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception {
        ArrayList<GoodsPO> goodsPOS;
        ArrayList<GoodsVO> goodsVOS = new ArrayList<>();

        /*将GoodsQueryVO转为GoodsQueryPO*/
        GoodsQueryPO goodsQueryPO = null;
        if (query != null) {
            goodsQueryPO = query.getGoodsQueryPO();
        }

        /*调用GoodsDataFactory得到ArrayList<GoodsPO>*/
        GoodsDataService goodsDataService = GoodsDataFactory.getService();
        goodsPOS = goodsDataService.finds(goodsQueryPO);

        for (GoodsPO goodsPO : goodsPOS) {
            goodsVOS.add(new GoodsVO(goodsPO));
        }

        return goodsVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [goods] 增加的的商品对象，用于增加数据库中该商品数据
     * @return: 返回String的增加商品的ID
     */
    @Override
    public String addGoods(GoodsVO goods) throws Exception {
        String id = "";

        /*将GoodsVO转换为GoodsPO*/
        GoodsPO goodsPO = goods.getGoodsPO();

        /*调用GoodsDataFactory得到String的ID*/
        GoodsDataService goodsDataService = GoodsDataFactory.getService();
        id = goodsDataService.insert(goodsPO);

        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [goods] 修改的的商品对象，用于修改数据库中该商品数据
     * @return:
     */
    @Override
    public void editGoods(GoodsVO goods) throws Exception {

        /*将GoodsVO转成GoodsPO*/
        GoodsPO goodsPO = goods.getGoodsPO();

        /*调用GoodsDataFactory完成对商品数据的修改*/
        GoodsDataService goodsDataService = GoodsDataFactory.getService();
        goodsDataService.update(goodsPO);

    }

    /**
     * @version: 1
     * @date:
     * @param: [goodsID] 查询的的商品对象的ID，用于查询数据库中该商品数据
     * @return： GoodsVO的商品信息
     */
    @Override
    public GoodsVO find(String goodsID) throws Exception {
        GoodsVO goodsVO;
        GoodsPO goodsPO;

         /*调用GoodsDataFactory完成对商品数据的修改*/
        GoodsDataService goodsDataService = GoodsDataFactory.getService();
        goodsPO = goodsDataService.find(goodsID);

        /*转换GoodsPO*/
        goodsVO = new GoodsVO(goodsPO);

        return goodsVO;
    }

    /**
     * @version: 1
     * @date:
     * @param: [goodsID] 删除的的商品对象的ID，用于删除数据库中该商品数据
     * @return：
     */
    @Override
    public void deleteGoods(String goodsID) throws Exception {
        /*调用GoodsDataFactory完成对商品的修改*/
        GoodsDataService goodsDataService = GoodsDataFactory.getService();
        goodsDataService.delete(goodsID);

    }
}

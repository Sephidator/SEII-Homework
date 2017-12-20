package main.java.businesslogic.goodssortbl;

import main.java.businesslogicservice.goodssortblservice.GoodsSortBlService;
import main.java.datafactory.goodssortdatafactory.GoodsSortDataFactory;
import main.java.dataservice.goodssortdataservice.GoodsSortDataService;
import main.java.po.goods.GoodsSortPO;
import main.java.vo.goods.GoodsSortVO;

public class GoodsSortBl implements GoodsSortBlService, GoodsSortTool {
    /**
     * @version: 1
     * @date:
     * @param:
     * @return: GoodsSortVO的根商品分类
     */
    @Override
    public GoodsSortVO getRoot() throws Exception {

        GoodsSortPO goodsSortPO;
        GoodsSortVO goodsSortVO;

        /*调用GoodsDataService.getRoot*/
        GoodsSortDataService goodsSortDataService = GoodsSortDataFactory.getService();
        goodsSortPO = goodsSortDataService.getRoot();

        /*GoodsSortPO转换为GoodsSortVO*/
        goodsSortVO = new GoodsSortVO(goodsSortPO);

        return goodsSortVO;
    }

    /**
     * @version: 1
     * @date:
     * @param: [goodsSortID] 查询的的商品分类对象的ID，用于查询数据库中该商品分类数据
     * @return: GoodsSortVO的商品分类信息
     */
    @Override
    public GoodsSortVO find(String goodsSortID) throws Exception {
        GoodsSortVO goodsSortVO;
        GoodsSortPO goodsSortPO;

        /*调用GoodsSortDataFactory*/
        GoodsSortDataService goodsSortDataService = GoodsSortDataFactory.getService();
        goodsSortPO = goodsSortDataService.find(goodsSortID);

       /*GoodsSortPO转换为GoodsSortVO*/
        goodsSortVO = new GoodsSortVO(goodsSortPO);

        return goodsSortVO;
    }

    /**
     * @version: 1
     * @date:
     * @param: [goodsSort] 增加的的商品分类对象，用于增加数据库中该商品分类数据
     * @return: 返回String的增加的商品分类的ID
     */
    @Override
    public String addGoodsSort(GoodsSortVO goodsSort) throws Exception {
        String id = "";

        /*将GoodsSortVO转成GoodsSortPO*/
        GoodsSortPO goodsSortPO = goodsSort.getGoodsSortPO();

        /*调用GoodsSortDataFactory服务得到增加的商品分类ID*/
        GoodsSortDataService goodsSortDataService = GoodsSortDataFactory.getService();
        id = goodsSortDataService.insert(goodsSortPO);

        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [goodsSortID] 删除的的商品分类对象的ID，用于删除数据库中该商品分类数据
     * @return:
     */
    @Override
    public void deleteGoodsSort(String goodsSortID) throws Exception {

        /*调用GoodsSortDataFactory完成对商品分类的删除*/
        GoodsSortDataService goodsSortDataService = GoodsSortDataFactory.getService();
        goodsSortDataService.delete(goodsSortID);

    }

    /**
     * @version: 1
     * @date:
     * @param: [goodsSort] 修改的的商品分类对象，用于修改数据库中该商品分类数据
     * @return：
     */
    @Override
    public void editGoodsSort(GoodsSortVO goodsSort) throws Exception {
        /*将GoodsSortVO转成GoodsSortPO*/
        GoodsSortPO goodsSortPO = goodsSort.getGoodsSortPO();

        /*调用GoodsSortDataFactory完成对商品分类的修改*/
        GoodsSortDataService goodsSortDataService = GoodsSortDataFactory.getService();
        goodsSortDataService.update(goodsSortPO);

    }

}

package main.java.businesslogic.goodssortbl;

import main.java.businesslogicservice.goodssortblservice.GoodsSortBlService;
import main.java.data_stub.goodssortdataservicestub.GoodsSortDataServiceStub;
import main.java.datafactory.goodssortdatafactory.GoodsSortDataFactory;
import main.java.dataservice.goodssortdataservice.GoodsSortDataService;
import main.java.po.goods.GoodsSortPO;
import main.java.vo.goods.GoodsSortVO;

public class GoodsSortBl implements GoodsSortBlService,GoodsSortTool {
    /**
     * @version: 1
     * @date:
     * @param:
     * @return: GoodsSortVO的根商品分类
     */
    @Override
    public GoodsSortVO getRoot() throws Exception{

        GoodsSortPO goodsSortPO=new GoodsSortPO();
        GoodsSortVO goodsSortVO=new GoodsSortVO();

        /*调用GoodsDataService.getRoot*/
        GoodsSortDataFactory goodsSortDataFactory=new GoodsSortDataFactory();
        goodsSortPO=goodsSortDataFactory.getService().getRoot();

//        /*调用dataservice*/
//        GoodsSortDataService goodsSortDataService=new GoodsSortDataServiceStub();
//        goodsSortPO=goodsSortDataService.getRoot();

        /*GoodsSortPO转换为GoodsSortVO*/
        goodsSortVO=new GoodsSortVO(goodsSortPO);

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
        GoodsSortVO goodsSortVO=new GoodsSortVO();
        GoodsSortPO goodsSortPO=new GoodsSortPO();

        /*调用GoodsSortDataFactory*/
        GoodsSortDataFactory goodsSortDataFactory=new GoodsSortDataFactory();
        goodsSortPO=goodsSortDataFactory.getService().find(goodsSortID);

//        /*调用dataservice*/
//        GoodsSortDataService goodsSortDataService=new GoodsSortDataServiceStub();
//        goodsSortPO=goodsSortDataService.find(goodsSortID);

       /*GoodsSortPO转换为GoodsSortVO*/
        goodsSortVO=new GoodsSortVO(goodsSortPO);

        return goodsSortVO;
    }

    /**
     * @version: 1
     * @date:
     * @param: [goodsSort] 增加的的商品分类对象，用于增加数据库中该商品分类数据
     * @return: 返回String的增加的商品分类的ID
     */
    @Override
    public String addGoodsSort(GoodsSortVO goodsSort) throws Exception{
        String id="";

        /*将GoodsSortVO转成GoodsSortPO*/
        GoodsSortPO goodsSortPO=goodsSort.getGoodsSortPO();

        /*调用GoodsSortDataFactory服务得到增加的商品分类ID*/
        GoodsSortDataFactory goodsSortDataFactory=new GoodsSortDataFactory();
        id=goodsSortDataFactory.getService().insert(goodsSortPO);

//         /*调用dataservice的桩*/
//         GoodsSortDataService goodsSortDataService=new GoodsSortDataServiceStub();
//         id=goodsSortDataService.insert(goodsSortPO);

        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [goodsSortID] 删除的的商品分类对象的ID，用于删除数据库中该商品分类数据
     * @return:
     */
    @Override
    public void deleteGoodsSort(String goodsSortID) throws Exception{

        /*调用GoodsSortDataFactory完成对商品分类的删除*/
        GoodsSortDataFactory goodsSortDataFactory=new GoodsSortDataFactory();
        goodsSortDataFactory.getService().delete(goodsSortID);

//         /*调用dataservice的桩*/
//         GoodsSortDataService goodsSortDataService=new GoodsSortDataServiceStub();
//         goodsSortDataService.delete(goodsSortID);

    }

    /**
     * @version: 1
     * @date:
     * @param: [goodsSort] 修改的的商品分类对象，用于修改数据库中该商品分类数据
     * @return：
     */
    @Override
    public void editGoodsSort(GoodsSortVO goodsSort) throws Exception{
        /*将GoodsSortVO转成GoodsSortPO*/
        GoodsSortPO goodsSortPO=goodsSort.getGoodsSortPO();

        /*调用GoodsSortDataFactory完成对商品分类的修改*/
        GoodsSortDataFactory goodsSortDataFactory=new GoodsSortDataFactory();
        goodsSortDataFactory.getService().update(goodsSortPO);

//         /*调用dataservice的桩*/
//         GoodsSortDataService goodsSortDataService=new GoodsSortDataServiceStub();
//         goodsSortDataService.update(goodsSortPO);


    }

}

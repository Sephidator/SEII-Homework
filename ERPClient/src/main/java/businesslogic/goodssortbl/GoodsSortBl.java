package main.java.businesslogic.goodssortbl;

import main.java.businesslogicservice.goodssortblservice.GoodsSortBLService;
import main.java.po.goods.GoodsSortPO;
import main.java.vo.goods.GoodsSortQueryVO;
import main.java.vo.goods.GoodsSortVO;

import java.util.ArrayList;

public class GoodsSortBl implements GoodsSortBLService,GoodsSortTool {
    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的商品分类查询对象
     * @function: 将GoodsSortQueryVO转为GoodsSortQueryPO，调用GoodsSortDatdaService.find服务
     *             得到ArrayList<GoodsSortPO>以后转成ArrayList<GoodsSortVO>，返回ArrayList<GoodsSortVO>
     */
    @Override
    public ArrayList<GoodsSortVO> getGoodsSortList(GoodsSortQueryVO query) {



        return null;
    }

    /**
     * @version: 1
     * @date:
     * @param: [goodsSort] 增加的的商品分类对象，用于增加数据库中该商品分类数据
     * @function: 将GoodsSortVO转成GoodsSortPO，并调用GoodsSortDataService.insert服务，返回ResultMessage
     */
    @Override
    public String addGoodsSort(GoodsSortVO goodsSort) {
        String id="";

        GoodsSortPO goodsSortPO=new GoodsSortPO();


        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [goodsSort] 修改的的商品分类对象，用于修改数据库中该商品分类数据
     * @function: 将GoodsSortVO转成GoodsSortPO，并调用GoodsSortDataService.update服务，返回ResultMessage
     */
    @Override
    public void deleteGoodsSort(String goodsSortID) {
        GoodsSortPO goodsSortPO=new GoodsSortPO();


    }

    /**
     * @version: 1
     * @date:
     * @param: [goodsSort] 删除的的商品分类对象，用于删除数据库中该商品分类数据
     * @function: 将GoodsSortVO转成GoodsSortPO，并调用GoodsSortDataService.delete服务，返回ResultMessage
     */
    @Override
    public void editGoodsSort(GoodsSortVO goodsSort) {
        GoodsSortPO goodsSortPO=new GoodsSortPO();


    }

}

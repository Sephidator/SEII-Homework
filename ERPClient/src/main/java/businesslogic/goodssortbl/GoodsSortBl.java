package main.java.businesslogic.goodssortbl;

import main.java.businesslogicservice.goodssortblservice.GoodsSortBLService;
import main.java.po.goods.GoodsSortPO;
import main.java.po.goods.GoodsSortQueryPO;
import main.java.vo.goods.GoodsSortQueryVO;
import main.java.vo.goods.GoodsSortVO;

import java.util.ArrayList;

public class GoodsSortBl implements GoodsSortBLService,GoodsSortTool {
    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的商品分类查询对象
     * @return: 返回ArrayList<GoodsSortVO>的商品分类列表
     */
    @Override
    public ArrayList<GoodsSortVO> getGoodsSortList(GoodsSortQueryVO query) throws Exception{
        ArrayList<GoodsSortVO> goodsSortVOS=new ArrayList<>();
        ArrayList<GoodsSortPO> goodsSortPOS=new ArrayList<>();

        /*将GoodsSortQueryVO转为GoodsSortQueryPO*/
        GoodsSortQueryPO goodsSortQueryPO=query.getGoodsSortQueryPO();

        /*调用GoodsSortDatdaService.find服务得到ArrayList<GoodsSortPO>的商品分类列表*/

        /*ArrayList<GoodsSortPO>以后转成ArrayList<GoodsSortVO>*/
        for(GoodsSortPO goodsSortPO:goodsSortPOS){
            goodsSortVOS.add(new GoodsSortVO(goodsSortPO));
        }

        return goodsSortVOS;
    }

    @Override
    public GoodsSortVO find(String goodsSortID) {
        return null;
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

        /*调用GoodsSortDataService.insert服务得到增加的商品分类ID*/

        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [goodsSortID] 修改的的商品分类对象的ID，用于修改数据库中该商品分类数据
     * @return:
     */
    @Override
    public void deleteGoodsSort(String goodsSortID) throws Exception{

        /*调用GoodsSortDataService.delete服务完成对商品分类的删除*/

    }

    /**
     * @version: 1
     * @date:
     * @param: [goodsSort] 删除的的商品分类对象，用于删除数据库中该商品分类数据
     * @return：
     */
    @Override
    public void editGoodsSort(GoodsSortVO goodsSort) throws Exception{
        /*将GoodsSortVO转成GoodsSortPO*/
        GoodsSortPO goodsSortPO=goodsSort.getGoodsSortPO();

        /*调用GoodsSortDataService.update服务完成对商品分类的修改*/


    }

}

package main.java.businesslogic.goodsbl;

import main.java.businesslogicservice.goodsblservice.GoodsBlService;
import main.java.data_stub.goodsdataservicestub.GoodsDataServiceStub;
import main.java.dataservice.goodsdataservice.GoodsDataService;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsQueryPO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class GoodsBl implements GoodsBlService,GoodsTool {
    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的商品查询对象
     * @return: 返回ArrayList<GoodsVO>的商品列表
     */
    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception{
        ArrayList<GoodsPO> goodsPOS=new ArrayList<>();
        ArrayList<GoodsVO> goodsVOS=new ArrayList<>();

        /*将GoodsQueryVO转为GoodsQueryPO*/
        GoodsQueryPO goodsQueryPO=new GoodsQueryPO("","");
        if(query==null){
            goodsQueryPO=null;
        }
        else{
            goodsQueryPO=query.getGoodsQueryPO();
        }

        /*调用GoodsDadaService.find得到ArrayList<GoodsPO>*/

        /*调用dataservice的桩*/
        GoodsDataService goodsDataService=new GoodsDataServiceStub();
        goodsPOS=goodsDataService.finds(goodsQueryPO);

        for(GoodsPO goodsPO:goodsPOS){
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
    public String addGoods(GoodsVO goods) throws Exception{
        String id="";

        /*将GoodsVO转换为GoodsPO*/
        GoodsPO goodsPO=goods.getGoodsPO();

        /*调用GoodsDatdaService.insert得到String的ID*/

        /*调用dataservice的桩*/
        GoodsDataService goodsDataService=new GoodsDataServiceStub();
        id=goodsDataService.insert(goodsPO);

        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [goods] 修改的的商品对象，用于修改数据库中该商品数据
     * @return:
     */
    @Override
    public void editGoods(GoodsVO goods) throws Exception{

        /*将GoodsVO转成GoodsPO*/
        GoodsPO goodsPO=goods.getGoodsPO();

        /*调用GoodsDatdaService.update完成对商品数据的修改*/

        /*调用dataservice的桩*/
        GoodsDataService goodsDataService=new GoodsDataServiceStub();
        goodsDataService.update(goodsPO);

    }

    @Override
    public GoodsVO find(String goodsID) throws Exception {
        GoodsVO goodsVO=new GoodsVO();
        GoodsPO goodsPO=new GoodsPO();

         /*调用GoodsDatdaService.find完成对商品数据的修改*/

        /*调用dataservice的桩*/
        GoodsDataService goodsDataService=new GoodsDataServiceStub();
        goodsPO=goodsDataService.find(goodsID);

        /*转换GoodsPO*/
        goodsVO=new GoodsVO(goodsPO);

        return goodsVO;
    }

    /**
     * @version: 1
     * @date:
     * @param: [goodsID] 删除的的商品对象的ID，用于删除数据库中该商品数据
     * @return：
     */
    @Override
    public void deleteGoods(String goodsID) throws Exception{
        /*调用GoodsDadaService.delete完成对商品的修改*/

         /*调用dataservice的桩*/
         GoodsDataService goodsDataService=new GoodsDataServiceStub();
         goodsDataService.delete(goodsID);

    }
}

package main.java.businesslogic.goodsbl;

import main.java.businesslogicservice.goodsblservice.GoodsBLService;
import main.java.po.goods.GoodsPO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class GoodsBl implements GoodsBLService,GoodsTool {
    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的商品查询对象
     * @function: 将GoodsQueryVO转为GoodsQueryPO，调用GoodsDatdaService.find服务
     *             得到ArrayList<GoodsPO>以后转成ArrayList<GoodsVO>，返回ArrayList<GoodsVO>
     */
    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        return null;
    }

    /**
     * @version: 1
     * @date:
     * @param: [goods] 增加的的商品对象，用于增加数据库中该商品数据
     * @function: 将GoodsVO转成GoodsPO，并调用GoodsDataService.insert服务，返回ResultMessage
     */
    @Override
    public String addGoods(GoodsVO goods) {
        String id="";
        GoodsPO goodsPO=new GoodsPO();
        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [goods] 修改的的商品对象，用于修改数据库中该商品数据
     * @function: 将GoodsVO转成GoodsPO，并调用GoodsDataService.update服务，返回ResultMessage
     */
    @Override
    public void editGoods(GoodsVO goods) {
        GoodsPO goodsPO=new GoodsPO();

    }

    /**
     * @version: 1
     * @date:
     * @param: [goods] 删除的的商品对象，用于删除数据库中该商品数据
     * @function: 将GoodsVO转成GoodsPO，并调用GoodsDataService.delete服务，返回ResultMessage
     */
    @Override
    public void deleteGoods(String goodsID) {
        GoodsPO goodsPO=new GoodsPO();

    }
}

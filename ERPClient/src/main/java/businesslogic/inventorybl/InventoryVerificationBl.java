package main.java.businesslogic.inventorybl;

import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogicservice.inventoryblservice.InventoryVerificationBlService;
import main.java.data_stub.goodsdataservicestub.GoodsDataServiceStub;
import main.java.dataservice.goodsdataservice.GoodsDataService;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsQueryPO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class InventoryVerificationBl implements InventoryVerificationBlService {
    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的商品查询对象
     * @function: 将GoodsQueryVO转为GoodsQueryPO，调用GoodsDatdaService.find服务
     *             得到ArrayList<GoodsPO>以后转成ArrayList<GoodsVO>，返回ArrayList<GoodsVO>
     */
    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query)throws Exception {
        ArrayList<GoodsVO> goodsVOS=new ArrayList<>();

        GoodsTool goodsTool=new GoodsBl();
        goodsVOS=goodsTool.getGoodsList(query);

        return goodsVOS;
    }
}

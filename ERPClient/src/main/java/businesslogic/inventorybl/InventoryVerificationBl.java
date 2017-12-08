package main.java.businesslogic.inventorybl;

import main.java.businesslogicservice.inventoryblservice.InventoryVerificationBLService;
import main.java.data_stub.goodsdataservicestub.GoodsDataServiceStub;
import main.java.dataservice.goodsdataservice.GoodsDataService;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsQueryPO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class InventoryVerificationBl implements InventoryVerificationBLService {
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
        ArrayList<GoodsPO> goodsPOS=new ArrayList<>();


        /*将GoodsQueryVO转为GoodsQueryPO*/
        GoodsQueryPO goodsQueryPO=query.getGoodsQueryPO();

        /*调用GoodsDatdaService.find服务得到ArrayList<GoodsPO>*/

        /*调用dataservice的桩*/
        GoodsDataService goodsDataService=new GoodsDataServiceStub();
        goodsPOS=goodsDataService.finds(goodsQueryPO);

        /*ArrayList<GoodsPO>以后转成ArrayList<GoodsVO>*/
        for(GoodsPO goodsPO:goodsPOS){
            goodsVOS.add(new GoodsVO(goodsPO));
        }

        return goodsVOS;
    }
}

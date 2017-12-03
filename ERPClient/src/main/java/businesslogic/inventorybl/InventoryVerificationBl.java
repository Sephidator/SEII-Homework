package main.java.businesslogic.inventorybl;

import main.java.businesslogicservice.inventoryblservice.InventoryVerificationBLService;
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
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        return null;
    }
}

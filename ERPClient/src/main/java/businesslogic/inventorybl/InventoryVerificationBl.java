package businesslogic.inventorybl;

import businesslogicservice.inventoryblservice.InventoryVerificationBLService;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public class InventoryVerificationBl implements InventoryVerificationBLService {
    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        return null;
    }
}

package businesslogicservice.inventoryblservice;

import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public interface InventoryVerificationBLService {

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);
}

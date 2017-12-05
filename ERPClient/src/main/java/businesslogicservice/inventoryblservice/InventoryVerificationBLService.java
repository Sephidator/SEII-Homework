package main.java.businesslogicservice.inventoryblservice;

import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface InventoryVerificationBLService {

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception;
}

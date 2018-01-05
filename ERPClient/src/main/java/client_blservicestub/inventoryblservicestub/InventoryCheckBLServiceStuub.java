package main.java.client_blservicestub.inventoryblservicestub;

import main.java.businesslogicservice.inventoryblservice.InventoryCheckBlService;
import main.java.vo.goods.InventoryCheckItemVO;

import java.util.ArrayList;
import java.util.Date;

public class InventoryCheckBlServiceStuub implements InventoryCheckBlService {

    @Override
    public ArrayList<InventoryCheckItemVO> getInventoryCheck(Date start, Date end) throws Exception {
        return new ArrayList<>();
    }
}

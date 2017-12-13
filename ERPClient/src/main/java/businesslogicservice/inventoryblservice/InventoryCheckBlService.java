package main.java.businesslogicservice.inventoryblservice;

import main.java.vo.goods.InventoryCheckItemVO;

import java.util.ArrayList;
import java.util.Date;

public interface InventoryCheckBlService {

    public ArrayList<InventoryCheckItemVO> getInventoryCheck(Date start,Date end) throws Exception;
}

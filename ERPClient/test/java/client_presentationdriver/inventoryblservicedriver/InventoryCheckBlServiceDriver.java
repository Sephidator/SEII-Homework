package client_presentationdriver.inventoryblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.inventoryblservice.InventoryCheckBlService;
import client_blservicestub.inventoryblservicestub.InventoryCheckBlServiceStub;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class InventoryCheckBlServiceDriver {

    InventoryCheckBlService inventoryCheckBlService=new InventoryCheckBlServiceStub();

    @Test
    public void view() throws Exception {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = dateFormat1.parse("2017-9-25");
        Date endDate = dateFormat1.parse("2017-10-22");

        inventoryCheckBlService.view(beginDate,endDate);
        assertEquals(ResultMessage.success,inventoryCheckBlService.view(beginDate,endDate));
    }

}
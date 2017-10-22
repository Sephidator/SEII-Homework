package client_presentationdriver.inventoryblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.inventoryblservice.InventoryVerificationBlService;
import client_blservicestub.inventoryblservicestub.InventoryVerificationBlServiceStub;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class InventoryVerificationBlServiceDriver {

    InventoryVerificationBlService inventoryVerificationBlService=new InventoryVerificationBlServiceStub();

    @Test
    public void record() throws Exception {

        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date_1 = dateFormat1.parse("2017-10-01");
        inventoryVerificationBlService.record(date_1);
        assertEquals(ResultMessage.success,inventoryVerificationBlService.record(date_1));
    }

}
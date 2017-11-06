package client_presentationdriver.inventoryblservicedriver;

import businesslogicservice.inventoryblservice.InventoryCheckBLService;
import client_blservicestub.inventoryblservicestub.InventoryCheckBLServiceStuub;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class InventoryCheckBLServiceDriver {

    InventoryCheckBLService inventoryCheckBLService=new InventoryCheckBLServiceStuub();
    DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
    Date beginDate = dateFormat1.parse("2017-9-25");
    Date endDate = dateFormat1.parse("2017-10-22");


    public InventoryCheckBLServiceDriver() throws ParseException {
    }

    @Test
    public void getImportNumber() throws Exception {
        assertEquals(10,inventoryCheckBLService.getImportNumber(beginDate,endDate));
    }

    @Test
    public void getExportNumber() throws Exception {
        assertEquals(10,inventoryCheckBLService.getExportNumber(beginDate,endDate));
    }

    @Test
    public void getImportAmount() throws Exception {
        assertEquals(10.0,inventoryCheckBLService.getImportAmount(beginDate,endDate),0.1);
    }

    @Test
    public void getExportAmount() throws Exception {
        assertEquals(10.0,inventoryCheckBLService.getExportAmount(beginDate,endDate),0.1);
    }

    @Test
    public void getPurchaseNumber() throws Exception {
        assertEquals(10,inventoryCheckBLService.getPurchaseNumber(beginDate,endDate));
    }

    @Test
    public void getPurchaseAmount() throws Exception {
        assertEquals(10.0,inventoryCheckBLService.getPurchaseAmount(beginDate,endDate),0.1);
    }

    @Test
    public void getSaleNumber() throws Exception {
        assertEquals(10,inventoryCheckBLService.getSaleNumber(beginDate,endDate),0.1);
    }

    @Test
    public void getSaleAmount() throws Exception {
        assertEquals(10.0,inventoryCheckBLService.getSaleAmount(beginDate,endDate),0.1);
    }

}
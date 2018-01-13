package test.java.unit_test.salebltest;

import main.java.businesslogicservice.saleblservice.SaleTradeBillBlService;
import main.java.client_blservicestub.saleblservicestub.SaleTradeBillBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SaleTradeBillBlServiceTest {
    SaleTradeBillBlService service=new SaleTradeBillBlServiceStub();

    @Test
    public void getSellerList() throws Exception {
        assertEquals(0,service.getSellerList(null).size());
    }

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(0,service.getGoodsList(null).size());
    }

    @Test
    public void getPromotionList() throws Exception {
        assertEquals(0,service.getPromotionList(null).size());
    }

    @Test
    public void submit() throws Exception {
        assertEquals("",service.submit(null));
    }

    @Test
    public void getSaleTradeBillList() throws Exception {
        assertEquals(0,service.getSaleTradeBillList(null).size());
    }

    @Test
    public void editSaleTradeBill() throws Exception {
    }

}
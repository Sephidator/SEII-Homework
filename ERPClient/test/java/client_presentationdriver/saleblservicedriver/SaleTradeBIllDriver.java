package client_presentationdriver.saleblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.saleblservice.SaleTradeBillBlService;
import client_blservicestub.saleblservicestub.SaleTradeBillBlStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaleTradeBIllDriver {
    SaleTradeBillBlService service=new SaleTradeBillBlStub();

    @Test
    public void getID() throws Exception {
        assertEquals("123",service.getID());
    }

    @Test
    public void getSellerList() throws Exception {
        assertEquals(1,service.getSellerList(null).size());
    }

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(1,service.getGoodsList(null).size());
    }

    @Test
    public void getPromotionList() throws Exception {
        assertEquals(1,service.getPromotionList(null).size());
    }

    @Test
    public void submit() throws Exception {
        assertEquals(ResultMessage.success,service.submit(null));
    }

    @Test
    public void saveDraft() throws Exception {
        assertEquals(ResultMessage.success,service.saveDraft(null));
    }

    @Test
    public void getSaleTradeBillList() throws Exception {
        assertEquals(1,service.getSaleTradeBillList(null).size());
    }
}

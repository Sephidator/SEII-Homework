package test.java.unit_test.salebltest;

import main.java.businesslogicfactory.saleblfactory.SaleTradeBillBlFactory;
import main.java.businesslogicservice.saleblservice.SaleTradeBillBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SaleTradeBillBlServiceTest {
    SaleTradeBillBlService service= SaleTradeBillBlFactory.getService();

    @Test
    public void getSellerList() throws Exception {
        assertEquals(true,service.getSellerList(null).size()>=0);
    }

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(true,service.getGoodsList(null).size()>=0);
    }

    @Test
    public void getPromotionList() throws Exception {
        assertEquals(true,service.getPromotionList(null).size()>=0);
    }

    @Test
    public void submit() throws Exception {
        assertEquals("XSD",service.submit(new SaleTradeBillVO()).substring(0,3));
    }

    @Test
    public void getSaleTradeBillList() throws Exception {
        assertEquals(true,service.getSaleTradeBillList(
                new BillQueryVO("审批通过",null,null,"销售单",null,null)).size()>=0);
    }

}
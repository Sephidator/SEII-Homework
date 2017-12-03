//package test.java.businesslogic_mocktester.purchaseblmocktester;
//
//import main.java.businesslogic.purchasebl.PurchaseTradeBillTool;
//import main.java.businesslogic_mock.purchaseblmock.PurchaseTradeBillToolMock;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class PurchaseTradeBillBlMockTester {
//    PurchaseTradeBillTool tool=new PurchaseTradeBillToolMock();

//    @Test
//    public void pass() throws Exception {
//        assertEquals(ResultMessage.success,tool.pass(null));
//    }
//
//    @Test
//    public void reject() throws Exception {
//        assertEquals(ResultMessage.success,tool.reject(null));
//    }

//    @Test
//    public void getPurchaseTradeBillList() throws Exception {
//        assertEquals(1,tool.getPurchaseTradeBillList(null).size());
//    }
//}
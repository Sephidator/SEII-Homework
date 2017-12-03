//package test.java.businesslogic_mocktester.saleblmocktester;
//
//import main.java.businesslogic.salebl.SaleTradeBillTool;
//import main.java.businesslogic_mock.saleblmock.SaleTradeBillToolMock;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class SaleTradeBillBlMockTester {
//    SaleTradeBillTool tool=new SaleTradeBillToolMock();

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
//    public void getSaleTradeBillList() throws Exception {
//        assertEquals(1,tool.getSaleTradeBillList(null).size());
//    }
//
//}
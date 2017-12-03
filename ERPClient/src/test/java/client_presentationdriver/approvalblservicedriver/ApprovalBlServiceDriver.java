//package test.java.client_presentationdriver.approvalblservicedriver;
//
//import main.java.businesslogicservice.approvalblservice.ApprovalBlService;
//import main.java.client_blservicestub.approvalblservicestub.ApprovalBlServiceStub;
//import main.java.vo.bill.BillQueryVO;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class ApprovalBlServiceDriver {
//    ApprovalBlService service = new ApprovalBlServiceStub();
//
//    @Test
//    public void getBillList() throws Exception {
//        assertEquals(service.getBillList(new BillQueryVO()).size(), 2);
//    }
////
////    @Test
////    public void pass() throws Exception {
////        assertEquals(service.pass(new ArrayList<>()), ResultMessage.success);
////    }
////
////    @Test
////    public void fail() throws Exception {
////        assertEquals(service.reject(new BillVO(), "The number is too big"), ResultMessage.success);
////    }
//
//}
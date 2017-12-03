//package test.java.client_presentationdriver.clientblservicedriver;
//
//import main.java.businesslogicservice.clientblservice.ClientBlService;
//import main.java.client_blservicestub.clientblservicestub.ClientBlServiceStub;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class ClientBlServiceDriver {
//    ClientBlService service=new ClientBlServiceStub();
//
//    @Test
//    public void getClientList() throws Exception {
//        assertEquals(1,service.getClientList(null).size());
//    }
//
////    @Test
////    public void addClient() throws Exception {
////        assertEquals(ResultMessage.success,service.addClient(null));
////    }
////
////    @Test
////    public void editClient() throws Exception {
////        assertEquals(ResultMessage.success,service.editClient(null));
////    }
////
////    @Test
////    public void deleteClient() throws Exception {
////        assertEquals(ResultMessage.success,service.deleteClient(null));
////    }
//
////    @Test
////    public void getClientID() throws Exception {
////        assertEquals("123",service.getClientID());
////    }
//
//}
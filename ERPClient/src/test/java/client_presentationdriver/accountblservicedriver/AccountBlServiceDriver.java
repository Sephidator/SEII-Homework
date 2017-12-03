//package test.java.client_presentationdriver.accountblservicedriver;
//
//import main.java.businesslogicservice.accountblservice.AccountBlService;
//import main.java.client_blservicestub.accountblservicestub.AccountBlServiceStub;
//
//public class AccountBlServiceDriver {
//    AccountBlService accountBlService= new AccountBlServiceStub();

//    @org.junit.Test
//    public void addAccount() throws Exception {
//        AccountVO accountVO = new AccountVO();
//        assertEquals("",accountBlService.addAccount(accountVO));
//    }

//    @org.junit.Test
//    public void deleteAccount() throws Exception {
//        AccountVO accountVO = new AccountVO();
//        assertEquals(ResultMessage.success,accountBlService.deleteAccount(accountVO));
//    }
//
//}
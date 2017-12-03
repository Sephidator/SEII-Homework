package test.java.client_presentationdriver.initialblservicedriver;

//public class InitialBlServiceDriver {
//
//    InitialBlService initialBlService = new InitialBlServiceStub();
//    @Test
//    public void getYear() throws Exception {
//        assertEquals(2017,initialBlService.getYear());
//    }
//
//    @Test
//    public void getGoodsList() throws Exception {
//        assertEquals("Goods-20171106-00001",initialBlService.getGoodsList(new GoodsQueryVO()).get(0).getID());
//    }
//
//    @Test
//    public void getClientList() throws Exception {
//        assertEquals("Client-20171106-00001",initialBlService.getClientList(new ClientQueryVO()).get(0).getID());
//    }
//
//    @Test
//    public void getAccountList() throws Exception {
//        assertEquals("Account-20171106-00001",initialBlService.getAccountList(new AccountQueryVO()).get(0).getID());
//    }
//
//    @Test
//    public void establishInitial() throws Exception {
//        assertEquals(ResultMessage.success,initialBlService.establishInitial(new InitialVO()));
//    }
//
//    @Test
//    public void getInitial() throws Exception {
//        assertEquals(2017,initialBlService.getInitial(new InitialQueryVO()).get(0).getYear());
//    }
//
//}
package test.java.client_presentationdriver.inventoryblservicedriver;

//public class InventoryLossOverBillBLServiceDriver {
//
//    InventoryLossOverBillBLService inventoryLossOverBillBLService=new InventoryLossOverBillBLServiceStub();
//    GoodsQueryVO goodsQueryVO=new GoodsQueryVO();
//    InventoryLossOverBillVO inventoryLossOverBillVO=new InventoryLossOverBillVO();
//    BillQueryVO billQueryVO=new BillQueryVO();
//
//    @Test
//    public void getID() throws Exception {
//        assertEquals("YSD-20171022-00002",inventoryLossOverBillBLService.getID());
//    }
//
//    @Test
//    public void getGoodsList() throws Exception {
//        ArrayList<GoodsVO> goodsVOS=new ArrayList<GoodsVO>();
//        goodsVOS.add(null);
//        assertEquals(goodsVOS,inventoryLossOverBillBLService.getGoodsList(goodsQueryVO));
//    }
//
//    @Test
//    public void submit() throws Exception {
//        assertEquals(ResultMessage.success,inventoryLossOverBillBLService.submit(inventoryLossOverBillVO));
//
//    }
//
//    @Test
//    public void saveDraft() throws Exception {
//        assertEquals(ResultMessage.success,inventoryLossOverBillBLService.saveDraft(inventoryLossOverBillVO));
//    }
//
//    @Test
//    public void getInventoryLossOverBillList() throws Exception {
//        ArrayList<InventoryLossOverBillVO> inventoryLossOverBillVOS=new ArrayList<InventoryLossOverBillVO>();
//        inventoryLossOverBillVOS.add(null);
//        assertEquals(inventoryLossOverBillVOS,inventoryLossOverBillBLService.getInventoryLossOverBillList(billQueryVO));
//    }
//
//}
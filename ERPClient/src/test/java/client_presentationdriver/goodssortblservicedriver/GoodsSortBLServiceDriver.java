//package client_presentationdriver.goodssortblservicedriver;
//
//import main.java.exception.ResultMessage;
//import main.java.businesslogicservice.goodssortblservice.GoodsSortBLService;
//import main.java.client_blservicestub.goodssortblservicestub.GoodsSortBLServiceStub;
//import org.junit.Test;
//import main.java.vo.goods.GoodsSortQueryVO;
//import main.java.vo.goods.GoodsSortVO;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;

//public class GoodsSortBLServiceDriver {
//
//    GoodsSortBLService goodsSortBLService=new GoodsSortBLServiceStub();
//    GoodsSortVO goodsSortVO=new GoodsSortVO();
//    GoodsSortQueryVO goodsSortQueryVO=new GoodsSortQueryVO();
//
//    @Test
//    public void getGoodsSortList() throws Exception {
//        ArrayList<GoodsSortVO> goodsSortVOS=new ArrayList<GoodsSortVO>();
//        goodsSortVOS.add(null);
//        assertEquals(goodsSortVOS,goodsSortBLService.getGoodsSortList(goodsSortQueryVO));
//    }
//
//    @Test
//    public void addGoodsSort() throws Exception {
//        assertEquals(ResultMessage.success,goodsSortBLService.addGoodsSort(goodsSortVO));
//    }
//
//    @Test
//    public void deleteGoodsSort() throws Exception {
//        assertEquals(ResultMessage.success,goodsSortBLService.addGoodsSort(goodsSortVO));
//    }
//
//    @Test
//    public void editGoodsSort() throws Exception {
//        assertEquals(ResultMessage.success,goodsSortBLService.addGoodsSort(goodsSortVO));
//    }
//
//    @Test
//    public void getID() throws Exception {
//        assertEquals("C0002",goodsSortBLService.getID());
//    }
//
//}
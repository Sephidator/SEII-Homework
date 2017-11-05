package client_presentationdriver.saleblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.saleblservice.SaleTradeBillBlService;
import client_blservicestub.saleblservicestub.SaleTradeBillBlServiceStub;
import org.junit.Test;
import vo.goods.GoodsVO;
import vo.promotion.PromotionVO;
import vo.bill.salebill.SaleTradeBillVO;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SaleTradeBillBlServiceDriver {
    SaleTradeBillBlService service=new SaleTradeBillBlServiceStub();

    @Test
    public void getID() throws Exception {
        assertEquals(service.getID(),"123");
    }

    @Test
    public void calculateTotal() throws Exception {
        GoodsVO goods1=new GoodsVO();
        GoodsVO goods2=new GoodsVO();
        ArrayList<GoodsVO> goodsList=new ArrayList<>();
        goodsList.add(goods1);
        goodsList.add(goods2);

        assertEquals(service.calculateTotal(goodsList),0,0.1);
    }

    @Test
    public void getVoucher() throws Exception {
        PromotionVO promotion=new PromotionVO();
        assertEquals(service.getVoucher(promotion),0,0.1);
    }

    @Test
    public void submit() throws Exception {
        SaleTradeBillVO bill=new SaleTradeBillVO();
        assertEquals(service.submit(bill), ResultMessage.success);
    }

    @Test
    public void saveDraft() throws Exception {
        SaleTradeBillVO bill=new SaleTradeBillVO();
        assertEquals(service.saveDraft(bill), ResultMessage.success);
    }

}
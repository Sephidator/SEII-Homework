package client_presentationdriver.saleblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.saleblservice.SaleRefundBillBlService;
import client_blservicestub.saleblservicestub.SaleRefundBillBlServiceStub;
import org.junit.Test;
import vo.goods.GoodsVO;
import vo.bill.salebill.SaleRefundBillVO;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SaleRefundBillBlServiceDriver {
    SaleRefundBillBlService service=new SaleRefundBillBlServiceStub();

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
    public void submit() throws Exception {
        SaleRefundBillVO bill=new SaleRefundBillVO();
        assertEquals(service.submit(bill), ResultMessage.success);
    }

    @Test
    public void saveDraft() throws Exception {
        SaleRefundBillVO bill=new SaleRefundBillVO();
        assertEquals(service.saveDraft(bill), ResultMessage.success);
    }

}
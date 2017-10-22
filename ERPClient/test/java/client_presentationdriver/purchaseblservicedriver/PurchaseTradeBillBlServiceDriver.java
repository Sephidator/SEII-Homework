package client_presentationdriver.purchaseblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;
import client_blservicestub.purchaseblservicestub.PurchaseRefundBillBlServiceStub;
import org.junit.Test;
import vo.GoodsVO;
import vo.PurchaseTradeBillVO;

import java.util.ArrayList;
import static org.junit.Assert.*;

public class PurchaseTradeBillBlServiceDriver {
    PurchaseTradeBillBlService service=new PurchaseRefundBillBlServiceStub();

    @Test
    public void getID() throws Exception {
        assertEquals(service.getID(),"123");
    }

    @Test
    public void calculateTotal() throws Exception {
        GoodsVO goods1=new GoodsVO();
        GoodsVO goods2=new GoodsVO();
        ArrayList<GoodsVO> goodsList=new ArrayList<>();

        assertEquals(service.calculateTotal(goodsList),233,0.01);
    }

    @Test
    public void submit() throws Exception {
        assertEquals(service.submit(new PurchaseTradeBillVO()), ResultMessage.success);
    }

    @Test
    public void saveDraft() throws Exception {
        assertEquals(service.saveDraft(new PurchaseTradeBillVO()), ResultMessage.success);
    }

}
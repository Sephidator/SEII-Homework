package client_presentationdriver.reportblservicedriver;

import businesslogicservice.reportblservice.BusinessHistoryBlService;
import client_blservicestub.reportblservicestub.BusinessHistoryBlServiceStub;
import org.junit.Test;
import vo.BillVO;
import vo.PaymentBillVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

public class BusinessHistoryBlServiceDiver {
    BusinessHistoryBlService businessHistoryBlService = new BusinessHistoryBlServiceStub();
    ArrayList<BillVO> vos = new ArrayList<>();

    @Test
    public void filter() throws Exception {
        vos.add(new PaymentBillVO("FKD-20171004-12345", 1, new Date(), 456, "马冬梅", "合理", "张三", new HashMap<>()));
        assertEquals(businessHistoryBlService.filter(1, null, null, null, "张三", null, null).toString(), vos.toString());
        vos.clear();

        assertEquals(businessHistoryBlService.filter(1, null, null, null, null, "李雷", null).toString(), vos.toString());
    }
}
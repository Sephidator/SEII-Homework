package client_presentationdriver.reportblservicedriver;

import businesslogicservice.reportblservice.SaleDetailBlService;
import client_blservicestub.reportblservicestub.SaleDetailBlServiceStub;
import org.junit.Test;
import vo.report.SaleDetailQueryVO;

import static org.junit.Assert.*;

public class SaleDetailBlServiceDriver {
    @Test
    public void getSaleRecordList() throws Exception {
        SaleDetailBlService service = new SaleDetailBlServiceStub();
        assertEquals(service.getSaleRecordList(new SaleDetailQueryVO()).size(), 1);
    }

}
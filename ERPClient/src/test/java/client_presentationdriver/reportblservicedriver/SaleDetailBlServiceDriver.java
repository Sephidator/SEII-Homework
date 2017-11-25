package test.java.client_presentationdriver.reportblservicedriver;

import main.java.businesslogicservice.reportblservice.SaleDetailBlService;
import main.java.client_blservicestub.reportblservicestub.SaleDetailBlServiceStub;
import org.junit.Test;
import main.java.vo.report.SaleDetailQueryVO;

import static org.junit.Assert.*;

public class SaleDetailBlServiceDriver {
    @Test
    public void getSaleRecordList() throws Exception {
        SaleDetailBlService service = new SaleDetailBlServiceStub();
        assertEquals(service.getSaleRecordList(new SaleDetailQueryVO()).size(), 1);
    }

}
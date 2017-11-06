package client_presentationdriver.reportblservicedriver;

import businesslogicservice.reportblservice.BusinessHistoryBlService;
import client_blservicestub.reportblservicestub.BusinessHistoryBlServiceStub;
import org.junit.Test;
import vo.report.BusinessHistoryQueryVO;

import static org.junit.Assert.*;

public class BusinessHistoryBlServiceDriver {
    @Test
    public void getBillList() throws Exception {
        BusinessHistoryBlService service = new BusinessHistoryBlServiceStub();
        assertEquals(service.getBillList(new BusinessHistoryQueryVO()).size(), 2);
    }
}
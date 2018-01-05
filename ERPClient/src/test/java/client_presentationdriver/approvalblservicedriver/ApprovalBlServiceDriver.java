package test.java.client_presentationdriver.approvalblservicedriver;

import main.java.businesslogicservice.approvalblservice.ApprovalBlService;
import main.java.client_blservicestub.approvalblservicestub.ApprovalBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApprovalBlServiceDriver {
    ApprovalBlService service=new ApprovalBlServiceStub();

    @Test
    public void getBillList() throws Exception {
        assertEquals(0,service.getBillList(null).size());
    }

    @Test
    public void pass() throws Exception {
    }

    @Test
    public void reject() throws Exception {
    }

}
package client_presentationdriver.approvalblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.approvalblservice.ApprovalBlService;
import client_blservicestub.approvalblservicestub.ApprovalBlServiceStub;
import org.junit.Test;
import vo.bill.BillQueryVO;
import vo.bill.BillVO;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ApprovalBlServiceDriver {
    ApprovalBlService service = new ApprovalBlServiceStub();

    @Test
    public void getBillList() throws Exception {
        assertEquals(service.getBillList(new BillQueryVO()).size(), 2);
    }

    @Test
    public void pass() throws Exception {
        assertEquals(service.pass(new ArrayList<>()), ResultMessage.success);
    }

    @Test
    public void fail() throws Exception {
        assertEquals(service.reject(new BillVO(), "The number is too big"), ResultMessage.success);
    }

}
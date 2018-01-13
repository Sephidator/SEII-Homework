package test.java.unit_test.approvalbltest;

import main.java.businesslogicfactory.approvalblfactory.ApprovalBlFactory;
import main.java.businesslogicservice.approvalblservice.ApprovalBlService;
import main.java.client_blservicestub.approvalblservicestub.ApprovalBlServiceStub;
import main.java.vo.bill.BillQueryVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApprovalBlServiceTest {
    ApprovalBlService service= ApprovalBlFactory.getService();

    @Test
    public void getBillList() throws Exception {
        assertEquals(true,service.getBillList(
                new BillQueryVO("审批通过",null,null,"进货退货单",null,null)).size()>=0);
    }

}
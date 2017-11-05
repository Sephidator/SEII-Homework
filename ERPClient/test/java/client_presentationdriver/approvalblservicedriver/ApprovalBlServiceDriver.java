package client_presentationdriver.approvalblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.approvalblservice.ApprovalBlService;
import client_blservicestub.approvalblservicestub.ApprovalBlServiceStub;
import org.junit.Test;
import vo.bill.BillVO;
import vo.bill.financebill.PaymentBillVO;
import vo.bill.financebill.ReceiptBillVO;
import vo.bill.salebill.SaleTradeBillVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ApprovalBlServiceDriver {
    ApprovalBlService approvalBlService = new ApprovalBlServiceStub();
    ArrayList<BillVO> vos = new ArrayList<>();

    @Test
    public void show() throws Exception {
        vos.clear();
        ReceiptBillVO vo1 = new ReceiptBillVO("SKD-20160324-23132", 1, new Date(), 3000, "王冬梅", "合理", "赵四", new HashMap<>());
        PaymentBillVO vo2 = new PaymentBillVO("FKD-20171004-47945", 1, new Date(), 735.2, "王冬梅", "合理", "张三", new HashMap<>());
        vos.add(vo1);
        vos.add(vo2);
        assertEquals(approvalBlService.show().toString(), vos.toString());
    }

    @Test
    public void filter() throws Exception {
        vos.clear();
        ReceiptBillVO vo = new ReceiptBillVO("SKD-20160324-23132", 1, new Date(), 3000, "王冬梅", "合理", "赵四", new HashMap<>());
        vos.add(vo);
        assertEquals(approvalBlService.filter("收款单").toString(), vos.toString());
    }

    @Test
    public void pass() throws Exception {
        assertEquals(approvalBlService.pass(new SaleTradeBillVO()), ResultMessage.success);
    }

    @Test
    public void fail() throws Exception {
        assertEquals(approvalBlService.fail(new SaleTradeBillVO()), ResultMessage.success);
    }

    @Test
    public void quantities() throws Exception {
        assertEquals(approvalBlService.quantities(new ArrayList<>()), ResultMessage.success);
    }

}
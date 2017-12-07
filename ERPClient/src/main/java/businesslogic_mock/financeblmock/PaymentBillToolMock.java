package main.java.businesslogic_mock.financeblmock;

import main.java.businesslogic.financebl.PaymentBillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.PaymentBillVO;

import java.util.ArrayList;

public class PaymentBillToolMock implements PaymentBillTool {

    @Override
    public void pass(BillVO bill) throws Exception {
        bill.setState("审批通过");
    }

    @Override
    public void reject(BillVO bill) throws Exception {
        bill.setState("审批不通过");
    }

    @Override
    public ArrayList<PaymentBillVO> getPaymentBillList(BillQueryVO query) throws Exception {
        return null;
    }
}

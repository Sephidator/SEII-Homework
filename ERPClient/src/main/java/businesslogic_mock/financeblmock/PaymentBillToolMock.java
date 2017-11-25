package main.java.businesslogic_mock.financeblmock;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.financebl.PaymentBillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.financebill.PaymentBillVO;

import java.util.ArrayList;

public class PaymentBillToolMock implements PaymentBillTool{
    @Override
    public ResultMessage pass(PaymentBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage reject(PaymentBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<PaymentBillVO> getPaymentBillList(BillQueryVO query) {
        ArrayList<PaymentBillVO> paymentBillVOArrayList = new ArrayList<PaymentBillVO>();
        PaymentBillVO paymentBillVO = new PaymentBillVO();
        paymentBillVOArrayList.add(paymentBillVO);
        return paymentBillVOArrayList;
    }
}

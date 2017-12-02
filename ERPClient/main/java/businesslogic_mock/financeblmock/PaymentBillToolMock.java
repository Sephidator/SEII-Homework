package businesslogic_mock.financeblmock;

import businesslogic.blutility.ResultMessage;
import businesslogic.financebl.PaymentBillTool;
import vo.bill.BillQueryVO;
import vo.bill.financebill.PaymentBillVO;

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

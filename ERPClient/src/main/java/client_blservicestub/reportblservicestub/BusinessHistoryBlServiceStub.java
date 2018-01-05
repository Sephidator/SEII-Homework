package main.java.client_blservicestub.reportblservicestub;

import main.java.businesslogicservice.reportblservice.BusinessHistoryBlService;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.CashBillVO;
import main.java.vo.bill.financebill.PaymentBillVO;
import main.java.vo.bill.financebill.ReceiptBillVO;
import main.java.vo.report.BusinessHistoryQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class BusinessHistoryBlServiceStub implements BusinessHistoryBlService {

    @Override
    public ArrayList<BillVO> getBillList(BusinessHistoryQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public String reversePaymentBill(PaymentBillVO paymentBillVO, UserVO operator) throws Exception {
        return "";
    }

    @Override
    public String reverseReceiptBill(ReceiptBillVO receiptBillVO, UserVO operator) throws Exception {
        return "";
    }

    @Override
    public String reverseCashBill(CashBillVO cashBillVO, UserVO operator) throws Exception {
        return "";
    }
}

package main.java.businesslogicservice.reportblservice;

import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.CashBillVO;
import main.java.vo.bill.financebill.PaymentBillVO;
import main.java.vo.bill.financebill.ReceiptBillVO;
import main.java.vo.report.BusinessHistoryQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public interface BusinessHistoryBlService {
    ArrayList<BillVO> getBillList(BusinessHistoryQueryVO query) throws Exception;

    //红冲功能，只允许红冲财务单据
    String reversePaymentBill(PaymentBillVO paymentBillVO, UserVO operator) throws Exception;

    String reverseReceiptBill(ReceiptBillVO receiptBillVO, UserVO operator) throws Exception;

    String reverseCashBill (CashBillVO cashBillVO, UserVO operator) throws Exception;

}

package main.java.businesslogic_mock.financeblmock;

import main.java.businesslogic.financebl.ReceiptBillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.ReceiptBillVO;

import java.util.ArrayList;

public class ReceiptBillToolMock implements ReceiptBillTool {

    @Override
    public void pass(BillVO bill) throws Exception {
        bill.setState("审批通过");
    }

    @Override
    public void reject(BillVO bill) throws Exception {
        bill.setState("审批不通过");
    }

    @Override
    public ArrayList<ReceiptBillVO> getReceiptBillList(BillQueryVO query) {
        ArrayList<ReceiptBillVO> receiptBillVOArrayList = new ArrayList<ReceiptBillVO>();
        ReceiptBillVO receiptBillVO = new ReceiptBillVO();
        receiptBillVOArrayList.add(receiptBillVO);
        return receiptBillVOArrayList;
    }
}

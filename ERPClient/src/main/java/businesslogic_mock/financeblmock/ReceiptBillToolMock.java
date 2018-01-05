package main.java.businesslogic_mock.financeblmock;

import main.java.businesslogic.financebl.ReceiptBillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.ReceiptBillVO;

import java.util.ArrayList;

public class ReceiptBillToolMock implements ReceiptBillTool {

    @Override
    public void pass(BillVO bill) throws Exception {

    }

    @Override
    public void reject(BillVO bill) throws Exception {

    }

    @Override
    public ArrayList<ReceiptBillVO> getReceiptBillList(BillQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public String submit(ReceiptBillVO vo) throws Exception {
        return "";
    }
}

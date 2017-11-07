package businesslogic_mock.financeblmock;

import businesslogic.blutility.ResultMessage;
import businesslogic.financebl.ReceiptBillTool;
import vo.bill.BillQueryVO;
import vo.bill.financebill.ReceiptBillVO;

import java.util.ArrayList;

public class ReceiptBillToolMock implements ReceiptBillTool{
    @Override
    public ResultMessage pass(ReceiptBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage reject(ReceiptBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<ReceiptBillVO> getReceiptBillList(BillQueryVO query) {
        ArrayList<ReceiptBillVO> receiptBillVOArrayList = new ArrayList<ReceiptBillVO>();
        ReceiptBillVO receiptBillVO = new ReceiptBillVO();
        receiptBillVOArrayList.add(receiptBillVO);
        return receiptBillVOArrayList;
    }
}

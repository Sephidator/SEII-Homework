package businesslogic_mock.financeblmock;

import businesslogic.blutility.ResultMessage;
import businesslogic.financebl.CashBillTool;
import vo.bill.BillQueryVO;
import vo.bill.financebill.CashBillVO;

import java.util.ArrayList;

public class CashBillToolMock implements CashBillTool{
    @Override
    public ResultMessage pass(CashBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage reject(CashBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<CashBillVO> getCashBillList(BillQueryVO query) {
        CashBillVO cashBillVO = new CashBillVO();
        ArrayList<CashBillVO> cashBillVOArrayList = new ArrayList<CashBillVO>();
        cashBillVOArrayList.add(cashBillVO);
        return cashBillVOArrayList;
    }
}

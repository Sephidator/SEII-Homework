package main.java.businesslogic_mock.financeblmock;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.financebl.CashBillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.financebill.CashBillVO;

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

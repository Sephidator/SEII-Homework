package main.java.businesslogic_mock.financeblmock;

import main.java.businesslogic.financebl.CashBillTool;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.CashBillVO;
import main.java.vo.bill.financebill.CashItemVO;

import java.util.ArrayList;

public class CashBillToolMock implements CashBillTool {

    @Override
    public void pass(BillVO bill) throws Exception {

    }

    @Override
    public void reject(BillVO bill) throws Exception {

    }

    @Override
    public ArrayList<CashBillVO> getCashBillList(BillQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public String submit(CashBillVO vo) throws Exception {
        return "";
    }
}

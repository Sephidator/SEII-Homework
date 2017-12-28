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
        bill.setState("审批通过");
    }

    @Override
    public void reject(BillVO bill) throws Exception {
        bill.setState("审批不通过");
    }

    @Override
    public ArrayList<CashBillVO> getCashBillList(BillQueryVO query) {
        CashBillVO cashBillVO = new CashBillVO();
        cashBillVO.setType(query.type);
        cashBillVO.setState(query.state);
        cashBillVO.setAccount(new AccountVO("622xxx","test",123.321));
        cashBillVO.setID("testCashBillID");

        CashItemVO cashItemVO = new CashItemVO("测试灯",1,"测试");
        ArrayList<CashItemVO> cashItemVOS = new ArrayList<>();
        cashItemVOS.add(cashItemVO);
        cashBillVO.setItemList(cashItemVOS);

        ArrayList<CashBillVO> cashBillVOArrayList = new ArrayList<CashBillVO>();
        cashBillVOArrayList.add(cashBillVO);
        return cashBillVOArrayList;
    }

    @Override
    public String submit(CashBillVO bill) throws Exception {
        return "";
    }
}

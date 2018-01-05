package main.java.client_blservicestub.financeblservicestub;

import main.java.businesslogicservice.financeblservice.CashBillBlService;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.financebill.CashBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class CashBillBlServiceStub implements CashBillBlService {

    @Override
    public ArrayList<CashBillVO> getCashBillList(BillQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public String submit(CashBillVO vo) throws Exception {
        return "";
    }

    @Override
    public void editCashBill(CashBillVO vo) throws Exception {

    }
}

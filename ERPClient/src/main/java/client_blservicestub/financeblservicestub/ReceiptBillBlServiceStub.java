package main.java.client_blservicestub.financeblservicestub;

import main.java.businesslogicservice.financeblservice.ReceiptBillBlService;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.financebill.ReceiptBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public class ReceiptBillBlServiceStub implements ReceiptBillBlService {

    @Override
    public ArrayList<ReceiptBillVO> getReceiptBillList(BillQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public String submit(ReceiptBillVO vo) throws Exception {
        return "";
    }

    @Override
    public void editReceiptBill(ReceiptBillVO vo) throws Exception {

    }
}

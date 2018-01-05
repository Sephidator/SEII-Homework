package main.java.client_blservicestub.financeblservicestub;

import main.java.businesslogicservice.financeblservice.PaymentBillBlService;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.financebill.PaymentBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public class PaymentBillBlServiceStub implements PaymentBillBlService {

    @Override
    public ArrayList<PaymentBillVO> getPaymentBillList(BillQueryVO query) throws Exception {
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
    public String submit(PaymentBillVO vo) throws Exception {
        return "";
    }

    @Override
    public void editPaymentBill(PaymentBillVO vo) throws Exception {

    }
}

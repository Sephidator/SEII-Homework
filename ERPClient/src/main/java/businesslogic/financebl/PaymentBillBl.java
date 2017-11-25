package main.java.businesslogic.financebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.financeblservice.PaymentBilllBlService;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.financebill.PaymentBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public class PaymentBillBl implements PaymentBilllBlService,PaymentBillTool{
    @Override
    public ResultMessage pass(PaymentBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage reject(PaymentBillVO bill) {
        return null;
    }

    @Override
    public ArrayList<PaymentBillVO> getPaymentBillList(BillQueryVO query) {
        return null;
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) {
        return null;
    }

    @Override
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage submit(PaymentBillVO vo) {
        return null;
    }

    @Override
    public ResultMessage saveDraft(PaymentBillVO vo) {
        return null;
    }
}

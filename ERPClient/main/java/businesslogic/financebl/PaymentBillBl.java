package businesslogic.financebl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.financeblservice.PaymentBilllBlService;
import vo.account.AccountQueryVO;
import vo.account.AccountVO;
import vo.bill.BillQueryVO;
import vo.bill.financebill.PaymentBillVO;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;

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

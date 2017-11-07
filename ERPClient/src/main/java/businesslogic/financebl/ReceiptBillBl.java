package businesslogic.financebl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.financeblservice.ReceiptBillBlService;
import vo.account.AccountQueryVO;
import vo.account.AccountVO;
import vo.bill.BillQueryVO;
import vo.bill.financebill.ReceiptBillVO;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;

import java.util.ArrayList;

public class ReceiptBillBl implements ReceiptBillBlService,ReceiptBillTool{
    @Override
    public ResultMessage pass(ReceiptBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage reject(ReceiptBillVO bill) {
        return null;
    }

    @Override
    public ArrayList<ReceiptBillVO> getReceiptBillList(BillQueryVO query) {
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
    public ResultMessage submit(ReceiptBillVO vo) {
        return null;
    }

    @Override
    public ResultMessage saveDraft(ReceiptBillVO vo) {
        return null;
    }
}

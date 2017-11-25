package main.java.businesslogic.financebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.financeblservice.ReceiptBillBlService;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.financebill.ReceiptBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;

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

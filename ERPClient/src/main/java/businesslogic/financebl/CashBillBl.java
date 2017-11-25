package main.java.businesslogic.financebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.financeblservice.CashBillBlService;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.financebill.CashBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class CashBillBl implements CashBillBlService,CashBillTool{
    @Override
    public ResultMessage pass(CashBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage reject(CashBillVO bill) {
        return null;
    }

    @Override
    public ArrayList<CashBillVO> getCashBillList(BillQueryVO query) {
        return null;
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        return null;
    }

    @Override
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage submit(CashBillVO vo) {
        return null;
    }

    @Override
    public ResultMessage saveDraft(CashBillVO vo) {
        return null;
    }
}

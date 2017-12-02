package businesslogic.financebl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.financeblservice.CashBillBlService;
import vo.account.AccountQueryVO;
import vo.account.AccountVO;
import vo.bill.BillQueryVO;
import vo.bill.financebill.CashBillVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

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

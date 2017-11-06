package client_presentationdriver.financeblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.financeblservice.CashBillBlService;
import client_blservicestub.financeblservicestub.CashBillBlServiceStub;
import org.junit.Test;
import po.goods.GoodsQueryPO;
import vo.account.AccountQueryVO;
import vo.account.AccountVO;
import vo.bill.financebill.CashBillVO;
import vo.goods.GoodsQueryVO;

import static org.junit.Assert.*;

public class CashBillBlServiceDriver {
    CashBillBlService cashBillBlService = new CashBillBlServiceStub();
    @Test
    public void getID() throws Exception {
        assertEquals("XJFYD-20171106-00001",cashBillBlService.getID());
    }

    @Test
    public void getGoodsList() throws Exception {
        GoodsQueryVO goodsQueryVO = new GoodsQueryVO();
        assertEquals("Goods-20171106-00001",cashBillBlService.getGoodsList(goodsQueryVO).get(0).getID());
    }

    @Test
    public void getAccountList() throws Exception {
        AccountQueryVO accountQureyVO = new AccountQueryVO();
        assertEquals("Account-20171106-00001",cashBillBlService.getAccountList(accountQureyVO).get(0).getID());
    }

    @Test
    public void submit() throws Exception {
        assertEquals(ResultMessage.success,cashBillBlService.submit(new CashBillVO()));
    }

    @Test
    public void saveDraft() throws Exception {
        assertEquals(ResultMessage.success,cashBillBlService.saveDraft(new CashBillVO()));
    }

}
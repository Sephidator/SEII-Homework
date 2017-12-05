package main.java.businesslogicservice.financeblservice;

import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.financebill.CashBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface CashBillBlService {

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception;//返回商品列表

    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception;//返回账户列表

    public String submit(CashBillVO vo) throws Exception;//更新并提交现金费用单，持久化更新涉及的对象的数据，成功返回ID

    public void saveDraft(CashBillVO vo) throws Exception;//保存单据草稿

}

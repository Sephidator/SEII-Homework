package main.java.businesslogicservice.financeblservice;

import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.financebill.CashBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface CashBillBlService {

    ArrayList<CashBillVO> getCashBillList(BillQueryVO query) throws Exception;

    ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception;//返回账户列表

    String submit(CashBillVO vo) throws Exception;//更新并提交现金费用单，持久化更新涉及的对象的数据，成功返回ID

    void editCashBill(CashBillVO vo) throws Exception;//编辑现金费用单

}

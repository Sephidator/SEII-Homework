package main.java.businesslogicservice.financeblservice;

import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.financebill.PaymentBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public interface PaymentBillBlService {

    public ArrayList<ClientVO> getClientList(ClientQueryVO query) throws Exception;//返回客户列表

    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception;//返回账户列表

    public String submit(PaymentBillVO vo) throws Exception;//更新并提交付款单，持久化更新涉及的对象的数据，成功返回ID

    public void saveDraft(PaymentBillVO vo) throws Exception;//保存单据草稿
}

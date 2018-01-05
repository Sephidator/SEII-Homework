package main.java.businesslogicservice.financeblservice;

import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.financebill.ReceiptBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public interface ReceiptBillBlService {

    ArrayList<ReceiptBillVO> getReceiptBillList(BillQueryVO query) throws Exception;

    ArrayList<ClientVO> getClientList(ClientQueryVO query) throws Exception;//返回客户列表

    ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception;//返回账户列表

    String submit(ReceiptBillVO vo) throws Exception;//更新并提交收款单，持久化更新涉及的对象的数据，成功返回ID

    void editReceiptBill(ReceiptBillVO vo) throws Exception;//编辑收款单

}

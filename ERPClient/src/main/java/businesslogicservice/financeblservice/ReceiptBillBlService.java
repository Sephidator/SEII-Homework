package main.java.businesslogicservice.financeblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.financebill.ReceiptBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public interface ReceiptBillBlService {

    public String getID();//返回新的收款单单据编号

    public ArrayList<ClientVO> getClientList(ClientQueryVO query);//返回客户列表

    public ArrayList<AccountVO> getAccountList(AccountQueryVO query);//返回账户列表

    public ResultMessage submit(ReceiptBillVO vo);//更新并提交收款单，持久化更新涉及的对象的数据

    public ResultMessage saveDraft(ReceiptBillVO vo);//保存单据草稿

}

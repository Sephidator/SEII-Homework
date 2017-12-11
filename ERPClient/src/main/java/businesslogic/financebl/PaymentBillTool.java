package main.java.businesslogic.financebl;

import main.java.businesslogic.BillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.PaymentBillVO;

import java.util.ArrayList;

public interface PaymentBillTool extends BillTool{

    public void pass(BillVO bill) throws Exception;//更新客户应收应付信息，更改账户信息，修改单据状态

    public void reject(BillVO bill) throws Exception;//修改单据状态为不通过

    public ArrayList<PaymentBillVO> getPaymentBillList(BillQueryVO query) throws Exception;//取得收款单列表

    public String submit(PaymentBillVO vo) throws Exception;//红冲使用
}

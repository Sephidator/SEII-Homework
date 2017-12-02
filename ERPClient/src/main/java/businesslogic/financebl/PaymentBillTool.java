package main.java.businesslogic.financebl;

import main.java.businesslogic.BillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.PaymentBillVO;

import java.util.ArrayList;

public interface PaymentBillTool extends BillTool{

    public void pass(BillVO bill);//更新客户应收应付信息，更改账户信息，修改单据状态

    public void reject(BillVO bill);//修改单据状态为不通过

    public ArrayList<PaymentBillVO> getPaymentBillList(BillQueryVO query);//取得收款单列表
}

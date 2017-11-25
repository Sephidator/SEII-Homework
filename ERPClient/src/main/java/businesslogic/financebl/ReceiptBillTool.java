package main.java.businesslogic.financebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.financebill.ReceiptBillVO;

import java.util.ArrayList;

public interface ReceiptBillTool {

    public ResultMessage pass(ReceiptBillVO bill);//更新客户应收应付信息，更改账户信息，修改单据状态

    public ResultMessage reject(ReceiptBillVO bill);//修改单据状态为不通过

    public ArrayList<ReceiptBillVO> getReceiptBillList(BillQueryVO query);//取得收款单列表
}

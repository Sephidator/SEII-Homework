package businesslogic.financebl;

import businesslogic.blutility.ResultMessage;
import vo.bill.BillQueryVO;
import vo.bill.financebill.PaymentBillVO;

import java.util.ArrayList;

public interface PaymentBillTool {

    public ResultMessage pass(PaymentBillVO bill);//更新客户应收应付信息，更改账户信息，修改单据状态

    public ResultMessage reject(PaymentBillVO bill);//修改单据状态为不通过

    public ArrayList<PaymentBillVO> getPaymentBillList(BillQueryVO query);//取得收款单列表
}

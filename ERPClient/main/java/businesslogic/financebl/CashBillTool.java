package businesslogic.financebl;

import businesslogic.blutility.ResultMessage;
import vo.bill.BillQueryVO;
import vo.bill.financebill.CashBillVO;

import java.util.ArrayList;

public interface CashBillTool {

    public ResultMessage pass(CashBillVO bill);//更新更改账户信息，修改单据状态

    public ResultMessage reject(CashBillVO bill);//修改单据状态为不通过

    public ArrayList<CashBillVO> getCashBillList(BillQueryVO query);//取得现金费用单列表
}

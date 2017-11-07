package businesslogic.salebl;

import businesslogic.blutility.ResultMessage;
import vo.bill.BillQueryVO;
import vo.bill.salebill.SaleTradeBillVO;

import java.util.ArrayList;

public interface SaleTradeBillTool {
    public ResultMessage pass(SaleTradeBillVO bill);
    public ResultMessage reject(SaleTradeBillVO bill);
    public ArrayList<SaleTradeBillVO> getSaleTradeBillList(BillQueryVO query);
}

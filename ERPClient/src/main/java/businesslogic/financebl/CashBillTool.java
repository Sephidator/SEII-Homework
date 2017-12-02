package main.java.businesslogic.financebl;

import main.java.businesslogic.BillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.CashBillVO;

import java.util.ArrayList;

public interface CashBillTool extends BillTool{

    public void pass(BillVO bill);//更新更改账户信息，修改单据状态

    public void reject(BillVO bill);//修改单据状态为不通过

    public ArrayList<CashBillVO> getCashBillList(BillQueryVO query);//取得现金费用单列表
}

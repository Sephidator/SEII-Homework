package main.java.businesslogic;

import main.java.vo.bill.BillVO;

public interface BillTool {
    public void pass(BillVO billvo);
    public void reject(BillVO billvo);
}

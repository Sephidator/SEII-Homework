package main.java.businesslogic;

import main.java.vo.bill.BillVO;

public interface BillTool {
    public void pass(BillVO billvo) throws Exception;
    public void reject(BillVO billvo) throws Exception;
}

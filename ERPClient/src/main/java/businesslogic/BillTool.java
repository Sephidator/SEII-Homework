package main.java.businesslogic;

import main.java.vo.bill.BillVO;

public interface BillTool {
    void pass(BillVO billvo) throws Exception;
    void reject(BillVO billvo) throws Exception;
}

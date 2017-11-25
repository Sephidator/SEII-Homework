package main.java.businesslogicservice.approvalblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;

import java.util.ArrayList;

public interface ApprovalBlService {
    public ArrayList<BillVO> getBillList(BillQueryVO query);

    public ResultMessage pass(ArrayList<BillVO> vo);

    public ResultMessage reject(BillVO vo, String result);
}

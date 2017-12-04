package main.java.businesslogicservice.approvalblservice;

import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public interface ApprovalBlService {

    public ArrayList<BillVO> getBillList(BillQueryVO query);

    public void pass(BillVO vo, UserVO senderVO);

    public void reject(BillVO vo, String reason, UserVO senderVO);
}

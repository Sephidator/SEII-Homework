package main.java.businesslogicservice.approvalblservice;

import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public interface ApprovalBlService {

    ArrayList<BillVO> getBillList(BillQueryVO query) throws Exception;

    void pass(BillVO vo, UserVO senderVO) throws Exception;

    void reject(BillVO vo, String reason, UserVO senderVO) throws Exception;
}

package main.java.client_blservicestub.approvalblservicestub;

import main.java.businesslogicservice.approvalblservice.ApprovalBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class ApprovalBlServiceStub implements ApprovalBlService {

    @Override
    public ArrayList<BillVO> getBillList(BillQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public void pass(BillVO vo, UserVO senderVO) throws Exception {

    }

    @Override
    public void reject(BillVO vo, String reason, UserVO senderVO) throws Exception {

    }
}

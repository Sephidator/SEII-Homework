package main.java.businesslogic.approvalbl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.approvalblservice.ApprovalBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;

import java.util.ArrayList;

public class ApprovalBl implements ApprovalBlService {
    @Override
    public ArrayList<BillVO> getBillList(BillQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage pass(ArrayList<BillVO> vo) {
        return null;
    }

    @Override
    public ResultMessage reject(BillVO vo, String result) {
        return null;
    }
}

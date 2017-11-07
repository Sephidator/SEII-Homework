package businesslogic.approvalbl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.approvalblservice.ApprovalBlService;
import vo.bill.BillQueryVO;
import vo.bill.BillVO;

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

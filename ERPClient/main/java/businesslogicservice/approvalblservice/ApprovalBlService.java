package businesslogicservice.approvalblservice;

import businesslogic.blutility.ResultMessage;
import vo.bill.BillQueryVO;
import vo.bill.BillVO;

import java.util.ArrayList;

public interface ApprovalBlService {
    public ArrayList<BillVO> getBillList(BillQueryVO query);

    public ResultMessage pass(ArrayList<BillVO> vo);

    public ResultMessage fail(BillVO vo, String result);
}

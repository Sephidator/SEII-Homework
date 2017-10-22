package businesslogicservice.approvalblservice;

import businesslogic.blutility.ResultMessage;
import vo.BillVO;

import java.util.ArrayList;

public interface ApprovalBlService {
    ArrayList<BillVO> show();

    ArrayList<BillVO> filter(String type);

    ResultMessage pass(BillVO vo);

    ResultMessage fail(BillVO vo);

    ResultMessage quantities(ArrayList<BillVO> bo);
}

package client_blservicestub.approvalblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.approvalblservice.ApprovalBlService;
import vo.bill.BillQueryVO;
import vo.bill.BillVO;

import java.util.ArrayList;

public class ApprovalBlServiceStub implements ApprovalBlService {
    @Override
    public ArrayList<BillVO> getBillList(BillQueryVO query) {
        ArrayList<BillVO> list = new ArrayList<>();
        list.add(new BillVO());
        list.add(new BillVO());
        return list;
    }

    @Override
    public ResultMessage pass(ArrayList<BillVO> vo) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage fail(BillVO vo, String result) {
        return ResultMessage.success;
    }
}

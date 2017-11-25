package main.java.client_blservicestub.approvalblservicestub;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.approvalblservice.ApprovalBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;

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
    public ResultMessage reject(BillVO vo, String result) {
        return ResultMessage.success;
    }
}

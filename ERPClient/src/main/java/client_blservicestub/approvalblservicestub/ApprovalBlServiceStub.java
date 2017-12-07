//package main.java.client_blservicestub.approvalblservicestub;
//
//import main.java.businesslogicservice.approvalblservice.ApprovalBlService;
//import main.java.vo.bill.BillQueryVO;
//import main.java.vo.bill.BillVO;
//import main.java.vo.user.UserVO;
//
//import java.util.ArrayList;
//
//public class ApprovalBlServiceStub implements ApprovalBlService {
//    @Override
//    public ArrayList<BillVO> getBillList(BillQueryVO query) {
//        ArrayList<BillVO> list = new ArrayList<>();
//        BillVO billVO1 = new BillVO();//单据一
//        billVO1.setTime(query.start);
//        billVO1.setType(query.type);
//        billVO1.setState(query.state);
//
//        BillVO billVO2 = new BillVO();//单据二
//        billVO2.setTime(query.end);
//        billVO2.setType(query.type);
//        billVO2.setState(query.state);
//        list.add(billVO1);
//        list.add(billVO2);
//        return list;
//    }
//
//    @Override
//    public void pass(BillVO vo, UserVO senderVO) throws Exception {
//
//    }
//
//    @Override
//    public void reject(BillVO vo, String reason, UserVO senderVO) throws Exception {
//
//    }
//}

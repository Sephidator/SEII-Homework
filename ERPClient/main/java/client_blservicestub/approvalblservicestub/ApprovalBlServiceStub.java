package client_blservicestub.approvalblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.approvalblservice.ApprovalBlService;
import vo.BillVO;
import vo.PaymentBillVO;
import vo.ReceiptBillVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ApprovalBlServiceStub implements ApprovalBlService {
    ArrayList<BillVO> vos = new ArrayList<>();
    PaymentBillVO vo1 = new PaymentBillVO("FKD-20171004-12345", 0, new Date(), 456, "马冬梅", "合理", "张三", new HashMap<>());
    ReceiptBillVO vo2 = new ReceiptBillVO("SKD-20160324-23132", 1, new Date(), 3000, "王冬梅", "合理", "赵四", new HashMap<>());
    PaymentBillVO vo3 = new PaymentBillVO("FKD-20171004-47945", 1, new Date(), 735.2, "王冬梅", "合理", "张三", new HashMap<>());

    @Override
    public ArrayList<BillVO> show() {
        vos.clear();
        vos.add(vo2);
        vos.add(vo3);
        return vos;
    }

    @Override
    public ArrayList<BillVO> filter(String type) {
        vos.clear();

        if (type == "付款单")
            vos.add(vo3);
        else if (type == "收款单")
            vos.add(vo2);

        return vos;
    }

    @Override
    public ResultMessage pass(BillVO vo) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage fail(BillVO vo) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage quantities(ArrayList<BillVO> bo) {
        return ResultMessage.success;
    }
}

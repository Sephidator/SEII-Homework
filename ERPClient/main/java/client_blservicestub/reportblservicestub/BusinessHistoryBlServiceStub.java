package client_blservicestub.reportblservicestub;

import businesslogicservice.reportblservice.BusinessHistoryBlService;
import vo.BillVO;
import vo.PaymentBillVO;
import vo.ReceiptBillVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BusinessHistoryBlServiceStub implements BusinessHistoryBlService {
    ArrayList<BillVO> vos = new ArrayList<>();
    PaymentBillVO vo1 = new PaymentBillVO("FKD-20171004-12345", 1, new Date(), 456, "马冬梅", "合理", "张三", new HashMap<>());
    ReceiptBillVO vo2 = new ReceiptBillVO("SKD-20160324-23132", 1, new Date(), 3000, "王冬梅", "合理", "赵四", new HashMap<>());

    @Override
    public ArrayList<BillVO> filter(int state, Date start, Date end, String type, String client, String operator, String repository) {
        vos.clear();

        if (state == 1 && (operator == "马冬梅" || client == "张三"))
            vos.add(vo1);
        else if (state == 1 && (operator == "王冬梅" || client == "赵四"))
            vos.add(vo2);

        return vos;
    }
}

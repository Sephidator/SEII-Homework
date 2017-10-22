package client_blservicestub.reportblservicestub;

import businesslogicservice.reportblservice.BusinessConditionBlService;
import vo.PaymentBillVO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class BusinessConditionBlServiceStub implements BusinessConditionBlService {
    double[] result = new double[4];
    PaymentBillVO vo;

    public BusinessConditionBlServiceStub() {
        Date time = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            time = dateFormat.parse("2017-06-01");
        } catch (Exception e) {
            e.printStackTrace();
        }
        vo = new PaymentBillVO("FKD-20171004-12345", 1, time, 456, "马冬梅", "合理", "张三", new HashMap<>());
    }

    @Override
    public double[] filter(Date start, Date end, int state) {
        if (state == 1 && (start.getTime() <= vo.time.getTime()) && (vo.time.getTime() <= end.getTime())) {
            result[0] += 0;
            result[1] += 0;
            result[2] += 456;
            result[3] += -456;
        }

        return result;
    }
}

package stub_driver.Client.main.java;

import businesslogicservice.logblservice.LogBlService;
import vo.LogVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wangn on 2017.10.21.
 */
public class LogBlServiceStub implements LogBlService{

    @Override
    public ArrayList<LogVO> getLog(Date from, Date to) {
        ArrayList<LogVO> logVOArrayList = new ArrayList<LogVO>();
        logVOArrayList.add(new LogVO("finance","addCashBill",new Date()));
        return logVOArrayList;
    }

    @Override
    public ResultMessage writeLog(String operator, String action, Date date) {
        return ResultMessage.Success;
    }
}

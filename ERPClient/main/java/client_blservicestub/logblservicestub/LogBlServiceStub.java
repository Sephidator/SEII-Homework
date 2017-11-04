package client_blservicestub.logblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.logblservice.LogBlService;
import vo.InitialVO;
import vo.LogVO;
import vo.UserVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wangn on 2017.10.21.
 */
public class LogBlServiceStub implements LogBlService{

    @Override
    public ArrayList<LogVO> getLog(Date from, Date to) {
        UserVO user=new UserVO(0, 23, true, "LiuQin", "ID", "123", null);

        ArrayList<LogVO> logVOArrayList = new ArrayList<LogVO>();
        logVOArrayList.add(new LogVO(user,null,null));
        return logVOArrayList;
    }

    @Override
    public ResultMessage writeLog(String operator, String action, Date date) {
        return ResultMessage.success;
    }
}

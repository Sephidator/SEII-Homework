package main.java.client_blservicestub.logblservicestub;

import main.java.businesslogicservice.logblservice.LogBlService;
import main.java.vo.log.LogQueryVO;
import main.java.vo.log.LogVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;

public class LogBlServiceStub implements LogBlService{

    @Override
    public ArrayList<LogVO> getLogList(LogQueryVO query) {
        ArrayList<LogVO> logVOArrayList = new ArrayList<LogVO>();
        LogVO logVO = new LogVO(new UserVO("财务人员",20,true,"王宁","Finance-20171106-00001","1234",true),"制定收款单",new Date());
        logVOArrayList.add(logVO);
        return logVOArrayList;
    }
}

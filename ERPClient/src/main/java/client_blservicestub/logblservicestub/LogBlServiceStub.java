package main.java.client_blservicestub.logblservicestub;

import main.java.businesslogicservice.logblservice.LogBlService;
import main.java.vo.log.LogQueryVO;
import main.java.vo.log.LogVO;

import java.util.ArrayList;

public class LogBlServiceStub implements LogBlService {

    @Override
    public ArrayList<LogVO> getLogList(LogQueryVO query) throws Exception {
        return new ArrayList<>();
    }
}

package main.java.businesslogicservice.logblservice;

import main.java.vo.log.LogQueryVO;
import main.java.vo.log.LogVO;

import java.util.ArrayList;

public interface LogBlService {

    ArrayList<LogVO> getLogList(LogQueryVO query) throws Exception;//返回指定时间区间内的操作日志

}

package businesslogicservice.logblservice;

import vo.log.LogQueryVO;
import vo.log.LogVO;

import java.util.ArrayList;

public interface LogBlService {

    public ArrayList<LogVO> getLogList(LogQueryVO query);//返回指定时间区间内的操作日志

}

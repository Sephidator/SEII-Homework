package businesslogicservice.logblservice;

import java.util.ArrayList;
import java.util.Date;
import vo.LogVO;

/**
 * 提供操作日志查看
 * Created by wangn on 2017.10.19.
 */
public interface LogBlService {

    /******************************/
    public enum ResultMessage{True, False, Success};
    /*****************************/

    /*返回时间区间内的操作日志*/
    public ArrayList<LogVO> getLog(Date from, Date to);

    /*记录操作日志，成功返回true*/
    public ResultMessage writeLog(String operator, String action, Date date);
}

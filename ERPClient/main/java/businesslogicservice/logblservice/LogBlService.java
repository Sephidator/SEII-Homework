package businesslogicservice.logblservice;

import java.util.ArrayList;
import java.util.Date;
import vo.LogVO;

/**
 * �ṩ������־�鿴
 * Created by wangn on 2017.10.19.
 */
public interface LogBlService {

    /******************************/
    public enum ResultMessage{True, False, Success};
    /*****************************/

    /*����ʱ�������ڵĲ�����־*/
    public ArrayList<LogVO> getLog(Date from, Date to);

    /*��¼������־���ɹ�����true*/
    public ResultMessage writeLog(String operator, String action, Date date);
}

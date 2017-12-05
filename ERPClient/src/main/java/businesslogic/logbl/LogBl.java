package main.java.businesslogic.logbl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.logblservice.LogBlService;
import main.java.vo.log.LogQueryVO;
import main.java.vo.log.LogVO;

import java.util.ArrayList;

public class LogBl implements LogBlService,LogTool{
    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:07
     * @para: [log] 
     * @function: 
     */
    public String addLog(LogVO log)throws Exception {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:07
     * @para: [query] 
     * @function: 
     */
    public ArrayList<LogVO> getLogList(LogQueryVO query)throws Exception {
        return null;
    }
}

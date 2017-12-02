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
    public ResultMessage addLog(LogVO log) {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:07
     * @para: [query] 
     * @function: 
     */
    public ArrayList<LogVO> getLogList(LogQueryVO query) {
        return null;
    }
}

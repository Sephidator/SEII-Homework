package main.java.businesslogic.logbl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.logblservice.LogBlService;
import main.java.vo.log.LogQueryVO;
import main.java.vo.log.LogVO;

import java.util.ArrayList;

public class LogBl implements LogBlService,LogTool{
    @Override
    public ResultMessage addLog(LogVO log) {
        return null;
    }

    @Override
    public ArrayList<LogVO> getLogList(LogQueryVO query) {
        return null;
    }
}

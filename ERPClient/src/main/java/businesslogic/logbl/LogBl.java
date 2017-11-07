package businesslogic.logbl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.logblservice.LogBlService;
import vo.log.LogQueryVO;
import vo.log.LogVO;

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

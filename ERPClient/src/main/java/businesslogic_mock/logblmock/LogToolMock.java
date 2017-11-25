package main.java.businesslogic_mock.logblmock;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.logbl.LogTool;
import main.java.vo.log.LogVO;

public class LogToolMock implements LogTool{
    @Override
    public ResultMessage addLog(LogVO log) {
        return ResultMessage.success;
    }
}

package businesslogic_mock.logblmock;

import businesslogic.blutility.ResultMessage;
import businesslogic.logbl.LogTool;
import vo.log.LogVO;

public class LogToolMock implements LogTool{
    @Override
    public ResultMessage addLog(LogVO log) {
        return ResultMessage.success;
    }
}

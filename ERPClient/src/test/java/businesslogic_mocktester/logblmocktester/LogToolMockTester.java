package businesslogic_mocktester.logblmocktester;

import businesslogic.blutility.ResultMessage;
import businesslogic.logbl.LogTool;
import businesslogic_mock.logblmock.LogToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogToolMockTester {
    LogTool logTool = new LogToolMock();
    @Test
    public void addLog() throws Exception {
        assertEquals(ResultMessage.success,logTool.addLog(null));
    }

}
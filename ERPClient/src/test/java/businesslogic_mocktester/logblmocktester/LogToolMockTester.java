package test.java.businesslogic_mocktester.logblmocktester;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.logbl.LogTool;
import main.java.businesslogic_mock.logblmock.LogToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogToolMockTester {
    LogTool logTool = new LogToolMock();
    @Test
    public void addLog() throws Exception {
        assertEquals(ResultMessage.success,logTool.addLog(null));
    }

}
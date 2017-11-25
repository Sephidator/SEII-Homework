package test.java.businesslogic_mocktester.messageblmocktester;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.messagebl.MessageTool;
import main.java.businesslogic_mock.messageblmock.MessageBlMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageBlMockTester {
    MessageTool tool=new MessageBlMock();

    @Test
    public void pass() throws Exception {
        assertEquals(ResultMessage.success,tool.addMessage(null));
    }
}
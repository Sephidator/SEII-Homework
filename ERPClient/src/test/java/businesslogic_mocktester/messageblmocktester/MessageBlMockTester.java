package businesslogic_mocktester.messageblmocktester;

import businesslogic.blutility.ResultMessage;
import businesslogic.messagebl.MessageTool;
import businesslogic_mock.messageblmock.MessageBlMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageBlMockTester {
    MessageTool tool=new MessageBlMock();

    @Test
    public void pass() throws Exception {
        assertEquals(ResultMessage.success,tool.addMessage(null));
    }
}
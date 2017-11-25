package main.java.businesslogic_mock.messageblmock;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.messagebl.MessageTool;
import main.java.vo.message.MessageVO;

public class MessageBlMock implements MessageTool{
    @Override
    public ResultMessage addMessage(MessageVO message) {
        return ResultMessage.success;
    }
}

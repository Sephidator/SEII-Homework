package businesslogic_mock.messageblmock;

import businesslogic.blutility.ResultMessage;
import businesslogic.messagebl.MessageTool;
import vo.message.MessageVO;

public class MessageBlMock implements MessageTool{
    @Override
    public ResultMessage addMessage(MessageVO message) {
        return ResultMessage.success;
    }
}

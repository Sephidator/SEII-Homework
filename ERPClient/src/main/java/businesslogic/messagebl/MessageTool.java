package businesslogic.messagebl;

import businesslogic.blutility.ResultMessage;
import vo.message.MessageVO;

public interface MessageTool {
    public ResultMessage addMessage(MessageVO message);
}

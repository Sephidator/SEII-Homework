package main.java.businesslogic.messagebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.message.MessageVO;

public interface MessageTool {
    public ResultMessage addMessage(MessageVO message);
}

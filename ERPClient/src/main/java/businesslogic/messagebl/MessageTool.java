package main.java.businesslogic.messagebl;

import main.java.vo.message.MessageVO;

public interface MessageTool {
    void addMessage(MessageVO message)throws Exception;
}

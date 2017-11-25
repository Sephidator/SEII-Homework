package main.java.businesslogic.messagebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.messageblservice.MessageBlService;
import main.java.vo.message.MessageVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class MessageBl implements MessageBlService,MessageTool{
    @Override
    public ArrayList<MessageVO> getMessageList(UserVO receiver) {
        return null;
    }

    @Override
    public ResultMessage addMessage(MessageVO message) {
        return null;
    }
}

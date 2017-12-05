package main.java.businesslogic.messagebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.messageblservice.MessageBlService;
import main.java.vo.message.MessageVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class MessageBl implements MessageBlService,MessageTool{
    @Override
    public ArrayList<MessageVO> getMessageList(UserVO receiver)throws Exception {
        return null;
    }

    @Override
    public void addMessage(MessageVO message)throws Exception {
    }
}

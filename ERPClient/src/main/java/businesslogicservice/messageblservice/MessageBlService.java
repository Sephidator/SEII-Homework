package main.java.businesslogicservice.messageblservice;

import main.java.vo.message.MessageVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public interface MessageBlService {
    ArrayList<MessageVO> getMessageList(UserVO receiver) throws Exception;

    void deleteMessage(String receiverID, int number) throws Exception;
}

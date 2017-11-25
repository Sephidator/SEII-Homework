package main.java.client_blservicestub.messageblservicestub;

import main.java.businesslogicservice.messageblservice.MessageBlService;
import main.java.vo.message.MessageVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class MessageBlServiceStub implements MessageBlService{
    @Override
    public ArrayList<MessageVO> getMessageList(UserVO receiver) {
        ArrayList<MessageVO> list=new ArrayList<>();
        list.add(new MessageVO());
        return list;
    }
}

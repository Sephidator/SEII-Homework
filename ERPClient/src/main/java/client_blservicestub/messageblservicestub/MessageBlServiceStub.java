package client_blservicestub.messageblservicestub;

import businesslogicservice.messageblservice.MessageBlService;
import vo.message.MessageVO;
import vo.user.UserVO;

import java.util.ArrayList;

public class MessageBlServiceStub implements MessageBlService{
    @Override
    public ArrayList<MessageVO> getMessageList(UserVO receiver) {
        ArrayList<MessageVO> list=new ArrayList<>();
        list.add(new MessageVO());
        return list;
    }
}

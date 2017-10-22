package client_blservicestub.systemfunctionblservicestub;

import businesslogicservice.systemfunctionicservice.MessageBLService;
import vo.ClientVO;
import vo.MessageVO;
import vo.UserVO;

import java.util.ArrayList;

public class MessageBlServiceStub implements MessageBLService{

    @Override
    public MessageVO getMessageList(UserVO user) {
        ArrayList<String> list=new ArrayList<>();
        list.add("message");
        return new MessageVO(user,user,list);
    }
}

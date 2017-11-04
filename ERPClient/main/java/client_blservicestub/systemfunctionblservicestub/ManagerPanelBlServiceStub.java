package client_blservicestub.systemfunctionblservicestub;

import businesslogicservice.systemfunctionicservice.ManagerPanelBlService;
import vo.MessageVO;
import vo.UserVO;

import java.util.ArrayList;

public class ManagerPanelBlServiceStub implements ManagerPanelBlService {

    @Override
    public MessageVO getMessage(UserVO user) {
        String message="message";
        return new MessageVO(user,user,message);
    }
}

package client_blservicestub.systemfunctionblservicestub;

import businesslogicservice.systemfunctionicservice.ManagerPanelBlService;
import vo.message.MessageVO;
import vo.user.UserVO;

public class ManagerPanelBlServiceStub implements ManagerPanelBlService {

    @Override
    public MessageVO getMessage(UserVO user) {
        String message="message";
        return new MessageVO(user,user,message);
    }
}

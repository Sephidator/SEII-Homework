package client_blservicestub.systemfunctionblservicestub;

import businesslogicservice.systemfunctionicservice.AdministratorPanelBlService;
import vo.message.MessageVO;
import vo.user.UserVO;

public class AdministratorPanelBlServiceStub implements AdministratorPanelBlService {

    @Override
    public MessageVO getMessage(UserVO user) {
        String message="message";
        return new MessageVO(user,user, message);
    }
}

package client_blservicestub.systemfunctionblservicestub;

import businesslogicservice.systemfunctionicservice.FinancePanelBlService;
import vo.message.MessageVO;
import vo.user.UserVO;

public class FinancePanelBlServiceStub implements FinancePanelBlService {

    @Override
    public MessageVO getMessage(UserVO user) {
        String message="message";
        return new MessageVO(user,user,message);
    }
}

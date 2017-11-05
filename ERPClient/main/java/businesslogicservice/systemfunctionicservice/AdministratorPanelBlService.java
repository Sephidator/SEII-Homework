package businesslogicservice.systemfunctionicservice;

import vo.message.MessageVO;
import vo.user.UserVO;

public interface AdministratorPanelBlService {
    public MessageVO getMessage(UserVO receiver);
}

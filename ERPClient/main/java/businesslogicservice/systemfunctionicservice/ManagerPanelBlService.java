package businesslogicservice.systemfunctionicservice;

import vo.message.MessageVO;
import vo.user.UserVO;

public interface ManagerPanelBlService {
    public MessageVO getMessage(UserVO receiver);
}

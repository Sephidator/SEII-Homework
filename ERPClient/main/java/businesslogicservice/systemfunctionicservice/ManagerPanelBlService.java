package businesslogicservice.systemfunctionicservice;

import vo.MessageVO;
import vo.UserVO;

public interface ManagerPanelBlService {
    public MessageVO getMessage(UserVO receiver);
}

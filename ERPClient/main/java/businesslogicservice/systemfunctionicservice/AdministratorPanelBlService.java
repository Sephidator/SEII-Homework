package businesslogicservice.systemfunctionicservice;

import vo.MessageVO;
import vo.UserVO;

public interface AdministratorPanelBlService {
    public MessageVO getMessage(UserVO receiver);
}

package businesslogicservice.systemfunctionicservice;

import vo.message.MessageVO;
import vo.user.UserVO;

public interface FinancePanelBlService {
    public MessageVO getMessage(UserVO receiver);
}

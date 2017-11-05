package businesslogicservice.systemfunctionicservice;

import vo.message.MessageVO;
import vo.user.UserVO;

public interface PurchaseSalePanelBlService {
    public MessageVO getMessage(UserVO receiver);
}

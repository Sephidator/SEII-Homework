package businesslogicservice.systemfunctionicservice;

import vo.message.MessageVO;
import vo.user.UserVO;

public interface InventoryPanelBlService {
    public MessageVO getMessage(UserVO receiver);
}

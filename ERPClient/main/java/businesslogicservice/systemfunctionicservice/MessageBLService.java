package businesslogicservice.systemfunctionicservice;

import vo.ClientVO;
import vo.MessageVO;
import vo.UserVO;

import java.util.ArrayList;

public interface MessageBLService {
    public MessageVO getMessageList(UserVO user);
}

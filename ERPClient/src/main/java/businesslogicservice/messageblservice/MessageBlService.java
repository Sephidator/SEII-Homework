package businesslogicservice.messageblservice;

import vo.message.MessageVO;
import vo.user.UserVO;

import java.util.ArrayList;

public interface MessageBlService {
    public ArrayList<MessageVO> getMessageList(UserVO receiver);
}

package businesslogic.messagebl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.messageblservice.MessageBlService;
import vo.message.MessageVO;
import vo.user.UserVO;

import java.util.ArrayList;

public class MessageBl implements MessageBlService,MessageTool{
    @Override
    public ArrayList<MessageVO> getMessageList(UserVO receiver) {
        return null;
    }

    @Override
    public ResultMessage addMessage(MessageVO message) {
        return null;
    }
}

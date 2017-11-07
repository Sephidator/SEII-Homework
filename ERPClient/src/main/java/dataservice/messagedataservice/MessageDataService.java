package dataservice.messagedataservice;

import businesslogic.blutility.ResultMessage;
import po.message.MessagePO;
import po.user.UserPO;

import java.util.ArrayList;

public interface MessageDataService {
    public ArrayList<MessagePO> find(UserPO receiver);
    public ResultMessage insert(MessagePO message);
}

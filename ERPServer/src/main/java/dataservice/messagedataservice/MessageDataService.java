package main.java.dataservice.messagedataservice;

import main.java.data.datautility.ResultMessage;
import main.java.po.message.MessagePO;
import main.java.po.user.UserPO;

import java.util.ArrayList;

public interface MessageDataService {
    public ArrayList<MessagePO> find(UserPO receiver);
    public ResultMessage insert(MessagePO message);
}

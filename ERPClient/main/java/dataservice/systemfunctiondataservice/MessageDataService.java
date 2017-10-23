package dataservice.systemfunctiondataservice;

import po.MessagePO;
import po.UserPO;

public interface MessageDataService {
    public MessagePO getMessage(UserPO receiver);
}

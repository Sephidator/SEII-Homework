package server_dataservicestub.systemfunctiondataservicestub;

import dataservice.systemfunctiondataservice.MessageDataService;
import po.MessagePO;
import po.UserPO;

import java.util.ArrayList;

public class MessageDataServiceStub implements MessageDataService{
    @Override
    public MessagePO getMessage(UserPO user) {
        String list="message";
        return new MessagePO("cx","cx",list);
    }
}

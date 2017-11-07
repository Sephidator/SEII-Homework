package server_dataservicestub.messagedataservicestub;

import data.datautility.ResultMessage;
import dataservice.messagedataservice.MessageDataService;
import po.message.MessagePO;
import po.user.UserPO;

import java.util.ArrayList;

public class MessageDataServiceStub implements MessageDataService{
    @Override
    public ArrayList<MessagePO> find(UserPO receiver) {
        ArrayList<MessagePO> list=new ArrayList<>();
        list.add(new MessagePO());
        return list;
    }

    @Override
    public ResultMessage insert(MessagePO message) {
        return ResultMessage.success;
    }
}

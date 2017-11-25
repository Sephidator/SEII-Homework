package main.java.server_dataservicestub.messagedataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.messagedataservice.MessageDataService;
import main.java.po.message.MessagePO;
import main.java.po.user.UserPO;

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

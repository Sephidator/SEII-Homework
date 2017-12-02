package main.java.server_dataservicestub.messagedataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.messagedataservice.MessageDataService;
import main.java.po.message.MessagePO;
import main.java.po.user.UserPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class MessageDataServiceStub implements MessageDataService {

    @Override
    public ArrayList<MessagePO> find(UserPO receiver) throws RemoteException {
        ArrayList<MessagePO> list = new ArrayList<>();
        list.add(new MessagePO());
        return list;
    }

    @Override
    public ResultMessage insert(MessagePO message) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public String getMessageID() throws RemoteException {
        return "Message-20170910-12345";
    }
}

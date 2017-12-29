package main.java.server_dataservicestub.messagedataservicestub;

import main.java.dataservice.messagedataservice.MessageDataService;
import main.java.po.message.MessagePO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class MessageDataServiceStub implements MessageDataService {

    @Override
    public ArrayList<MessagePO> finds(String receiverID) {
        ArrayList<MessagePO> list = new ArrayList<>();
        list.add(new MessagePO());
        return list;
    }

    @Override
    public void insert(MessagePO message) {

    }

    @Override
    public void delete(String receiverID, int number) {

    }

}

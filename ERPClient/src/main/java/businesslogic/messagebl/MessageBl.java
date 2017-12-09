package main.java.businesslogic.messagebl;

import main.java.businesslogicservice.messageblservice.MessageBlService;
import main.java.data_stub.messagedataservicestub.MessageDataServiceStub;
import main.java.dataservice.messagedataservice.MessageDataService;
import main.java.po.message.MessagePO;
import main.java.vo.message.MessageVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class MessageBl implements MessageBlService,MessageTool{

    /**
     * @version: 1
     * @date: 2017.12.09 23:10
     * @para: [receiver]
     * @function: 每一位用户都会调用这个方法获取消息
     */
    @Override
    public ArrayList<MessageVO> getMessageList(UserVO receiver)throws Exception {
        /*dataService*/
        //MessageDataService messageDataService = (MessageDataService) Naming.lookup("rmi://localhost:");
        /*dataServiceStub*/
        MessageDataService messageDataService = new MessageDataServiceStub();
        ArrayList<MessagePO> messagePOS = messageDataService.finds(receiver.getID());

        ArrayList<MessageVO> messageVOS = new ArrayList<>();
        for(MessagePO messagePO : messagePOS)
            messageVOS.add(new MessageVO(messagePO));

        return messageVOS;
    }

    @Override
    public void addMessage(MessageVO message)throws Exception {
        /*dataService*/
        //MessageDataService messageDataService = (MessageDataService) Naming.lookup("rmi://localhost:");
        /*dataServiceStub*/
        MessageDataService messageDataService = new MessageDataServiceStub();
        messageDataService.insert(message.getMessagePO());
    }
}

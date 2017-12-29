package main.java.businesslogic.messagebl;

import main.java.businesslogicservice.messageblservice.MessageBlService;
import main.java.datafactory.messagedatafactory.MessageDataFactory;
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
        MessageDataService messageDataService = MessageDataFactory.getService();

        ArrayList<MessagePO> messagePOS = messageDataService.finds(receiver.getID());

        ArrayList<MessageVO> messageVOS = new ArrayList<>();
        for(MessagePO messagePO : messagePOS)
            messageVOS.add(new MessageVO(messagePO));

        return messageVOS;
    }

    @Override
    public void addMessage(MessageVO message)throws Exception {
        /*dataService*/
        MessageDataService messageDataService = MessageDataFactory.getService();
        messageDataService.insert(message.getMessagePO());
    }

    /**
     * @version: 1
     * @date: 2017.12.09 23:10
     * @para: [receiverID, number]
     * @function: 删除ID为receiverID某个用户的前number条系统消息
     */
    @Override
    public void deleteMessage(String receiverID,int number)throws Exception {
        /*dataService*/
        MessageDataService messageDataService = MessageDataFactory.getService();
        messageDataService.delete(receiverID,number);
    }
}

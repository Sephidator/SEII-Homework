package main.java.vo.message;

import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.po.message.MessagePO;
import main.java.vo.user.UserVO;

import java.util.Date;

public class MessageVO {
    private UserVO receiver;
    private UserVO sender;
    private String message;
    private Date time;

    public MessageVO() {
        receiver = new UserVO();
        sender = new UserVO();
        message = "";
        time=new Date();
    }

    public MessageVO(UserVO receiver, UserVO sender, String message) {
        this.receiver = receiver;
        this.sender = sender;
        this.message = message;
        time = new Date();
    }

    public UserVO getReceiver() {
        return receiver;
    }

    public UserVO getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }
    
    public void setReceiver(UserVO receiver) {
        this.receiver = receiver;
    }

    public void setSender(UserVO sender) {
        this.sender = sender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    /*get MessagePO*/
    public MessagePO getMessagePO() throws Exception{
        MessagePO messagePO = new MessagePO();
        
        messagePO.setMessage(message);
        messagePO.setReceiverID(receiver.getID());
        messagePO.setSenderID(sender.getID());
        messagePO.setTime(time);

        return messagePO;
    }

    /*从MessageVO得到MessageVO*/
    public MessageVO(MessagePO messagePO)throws Exception{
        message=messagePO.getMessage();
        time=messagePO.getTime();

        UserTool userTool = new UserBl();
        sender=userTool.find(messagePO.getSenderID());
        receiver=userTool.find(messagePO.getReceiverID());
    }
}


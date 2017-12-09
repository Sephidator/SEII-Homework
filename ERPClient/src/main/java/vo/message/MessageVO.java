package main.java.vo.message;

import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.po.message.MessagePO;
import main.java.vo.user.UserVO;

public class MessageVO {
    private UserVO receiver;
    private UserVO sender;
    private String message;

    public MessageVO() {
        this.receiver = new UserVO();
        this.sender = new UserVO();
        this.message = "";
    }

    public MessageVO(UserVO receiver, UserVO sender, String message) {
        this.receiver = receiver;
        this.sender = sender;
        this.message = message;
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

    public void setReceiver(UserVO receiver) {
        this.receiver = receiver;
    }

    public void setSender(UserVO sender) {
        this.sender = sender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*get MessagePO*/
    public MessagePO getMessagePO()throws Exception{
        MessagePO messagePO = new MessagePO();
        messagePO.setMessage(this.getMessage());
        messagePO.setReceiverID(this.getReceiver().getID());
        messagePO.setSenderID(this.getSender().getID());

        return messagePO;
    }

    /*从MessageVO得到MessageVO*/
    public MessageVO(MessagePO messagePO)throws Exception{
        this.setMessage(messagePO.getMessage());

        UserTool userTool = new UserBl();
        this.setSender(userTool.find(messagePO.getSenderID()));
        this.setReceiver(userTool.find(messagePO.getReceiverID()));
    }
}


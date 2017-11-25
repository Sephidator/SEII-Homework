package main.java.vo.message;

import main.java.vo.user.UserVO;

public class MessageVO {
    private UserVO receiver;
    private UserVO sender;
    private String message;

    public MessageVO() {
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
}


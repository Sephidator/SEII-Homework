package vo;

import java.util.ArrayList;

public class MessageVO {
    private UserVO receiver;
    private UserVO sender;
    private String message;

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
}


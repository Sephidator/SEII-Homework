package vo;

import java.util.ArrayList;

public class MessageVO {
    private UserVO receiver;
    private UserVO sender;
    private ArrayList<String> messageList;

    public MessageVO(UserVO receiver, UserVO sender, ArrayList<String> messageList) {
        this.receiver = receiver;
        this.sender = sender;
        this.messageList = messageList;
    }

    public UserVO getReceiver() {
        return receiver;
    }

    public UserVO getSender() {
        return sender;
    }

    public ArrayList<String> getMessageList() {
        return messageList;
    }
}


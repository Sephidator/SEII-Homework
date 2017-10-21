package po;

import java.util.ArrayList;

public class MessagePO {
    private String receiverID;
    private String senderID;
    private ArrayList<String> messageList;

    public MessagePO(String receiverID, String senderID, ArrayList<String> messageList) {
        this.receiverID = receiverID;
        this.senderID = senderID;
        this.messageList = messageList;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public String getSenderID() {
        return senderID;
    }

    public ArrayList<String> getMessageList() {
        return messageList;
    }
}

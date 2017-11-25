package main.java.po.message;

public class MessagePO {
    private String receiverID;
    private String senderID;
    private String message;

    public MessagePO() {
    }

    public MessagePO(String receiverID, String senderID, String message) {
        this.receiverID = receiverID;
        this.senderID = senderID;
        this.message = message;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public String getSenderID() {
        return senderID;
    }

    public String getMessage() {
        return message;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


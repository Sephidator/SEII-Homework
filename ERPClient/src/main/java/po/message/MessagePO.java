package main.java.po.message;

import java.io.Serializable;
import java.util.Date;

public class MessagePO implements Serializable {
    private String receiverID;
    private String senderID;
    private String message;
    private Date time;

    public MessagePO() {
        time = new Date();
    }

    public MessagePO(String receiverID, String senderID, String message, Date time) {
        this.receiverID = receiverID;
        this.senderID = senderID;
        this.message = message;
        this.time = time;
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

    public Date getTime() {
        return time;
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

    public void setTime(Date time) {
        this.time = time;
    }

}
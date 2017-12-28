package main.java.po.message;

import main.java.po.PO;

import java.util.Date;

public class MessagePO extends PO {
    private String receiverID;
    private String senderID;
    private String message;
    private Date time;

    public MessagePO() {
        time=new Date();
        visible=true;
    }

    public MessagePO(String receiverID, String senderID, String message, Date time, boolean visible) {
        this.receiverID = receiverID;
        this.senderID = senderID;
        this.message = message;
        this.time=time;
        this.visible=visible;
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
        this.time=time;
    }

}
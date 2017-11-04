package po;

        import java.util.ArrayList;

public class MessagePO {
    private String receiverID;
    private String senderID;
    private String message;

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
}

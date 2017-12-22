package main.java.businesslogicfactory.messageblfactory;

import main.java.businesslogic.messagebl.MessageBl;
import main.java.businesslogicservice.messageblservice.MessageBlService;

public class MessageBlFactory {
    public static MessageBlService getService(){
        MessageBlService messageBlService = new MessageBl();
        return messageBlService;
    }
}

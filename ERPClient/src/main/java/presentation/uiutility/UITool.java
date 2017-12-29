package main.java.presentation.uiutility;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import main.java.vo.message.MessageVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UITool {
    public static ButtonType showAlert(Alert.AlertType type, String title, String headerText, String contentText){
        Alert alert=new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
        return alert.getResult();
    }

    public static void showMessage(TextArea messageArea,ArrayList<MessageVO> messageList){
        String separator=System.lineSeparator()+"------------------------------"+System.lineSeparator()+System.lineSeparator();
        String text="";

        for(MessageVO message:messageList){
            text+=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(message.getTime())+System.lineSeparator();
            text+=message.getMessage()+separator;
        }
        messageArea.setText(text);
    }
}

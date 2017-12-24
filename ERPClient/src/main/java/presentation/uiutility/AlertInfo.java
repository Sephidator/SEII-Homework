package main.java.presentation.uiutility;

import javafx.scene.control.Alert;

public class AlertInfo {
    public static void showAlert(Alert.AlertType type,String title,String headerText,String contentText){
        Alert alert=new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}

package main.java.presentation.uiutility;

import javafx.scene.control.Alert;

public class CheckInput {
    public static boolean isPositiveNumber(String str){
        try{
            Double number=Double.parseDouble(str);
            if(number<=0){
                throw new NumberFormatException();
            }

            return true;
        }catch (NumberFormatException e){
            AlertInfo.showAlert(Alert.AlertType.ERROR,
                    "Invalid input","输入不正确","请输入正数");
            return false;
        }
    }
}

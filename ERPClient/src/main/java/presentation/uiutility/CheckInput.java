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
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid input");
            alert.setHeaderText("输入不正确");
            alert.setContentText("请输入正数");
            alert.showAndWait();
            return false;
        }
    }
}

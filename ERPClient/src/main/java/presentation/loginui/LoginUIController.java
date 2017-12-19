package main.java.presentation.loginui;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.*;
import main.java.vo.user.UserVO;

import java.io.IOException;

public class LoginUIController {

    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXTextField passwordField;
    @FXML
    private Button login;
    @FXML
    private Button exit;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Called when the user clickes on the Login button
     * */
    @FXML
    private void handleLogin(){
        System.out.println(usernameField.getText()==null);
        System.out.println(usernameField.getText().equals(""));

        UserVO user=new UserVO("宋抟","进货销售人员","JN123", "password", 23,true);
        if(usernameField.getText().equals("1")){
            stage.close();
            Stage newStage=new Stage();
            newStage.setTitle("灯具进销存管理系统");
            RootUIController root=RootUIController.initRoot(newStage,user);
            root.showLogoutButton(true);
            PurchaseSalePanelUIController.init(root);
        }
        else if(usernameField.getText().equals("2")){
            stage.close();
            Stage newStage=new Stage();
            newStage.setTitle("灯具进销存管理系统");
            RootUIController root=RootUIController.initRoot(newStage,user);
            root.showLogoutButton(true);
            InventoryPanelUIController.init(root);
        }
        else if(usernameField.getText().equals("3")){
            stage.close();
            Stage newStage=new Stage();
            newStage.setTitle("灯具进销存管理系统");
            RootUIController root=RootUIController.initRoot(newStage,user);
            root.showLogoutButton(true);
            FinancePanelUIController.init(root);
        }
        else if(usernameField.getText().equals("4")){
            stage.close();
            Stage newStage=new Stage();
            newStage.setTitle("灯具进销存管理系统");
            RootUIController root=RootUIController.initRoot(newStage,user);
            root.showLogoutButton(true);
            ManagerPanelUIController.init(root);
        }
        else if(usernameField.getText().equals("5")){
            stage.close();
            Stage newStage=new Stage();
            newStage.setTitle("灯具进销存管理系统");
            RootUIController root=RootUIController.initRoot(newStage,user);
            root.showLogoutButton(true);
            AdministratorPanelUIController.init(root);
        }
    }

    @FXML
    private void handleExit(){
        // 这里还有一个登出操作没有写
        System.exit(0);
    }

    public static void init(Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/loginui/LoginUI.fxml"));
            Scene scene=new Scene(loader.load());
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            LoginUIController controller=loader.getController();
            controller.setStage(stage);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

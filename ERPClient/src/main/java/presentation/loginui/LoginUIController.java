package main.java.presentation.loginui;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.InventoryPanelUIController;
import main.java.presentation.messageui.PurchaseSalePanelUIController;
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
        UserVO user=new UserVO("宋抟","进货销售人员","JN123", "password", 23,true);
        if(usernameField.getText().equals("1")){
            RootUIController root=RootUIController.initRoot(stage,user);
            root.showLogoutButton(true);
            PurchaseSalePanelUIController.init(root);
        }
        else if(usernameField.getText().equals("2")){
            RootUIController root=RootUIController.initRoot(stage,user);
            root.showLogoutButton(true);
            InventoryPanelUIController.init(root);
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

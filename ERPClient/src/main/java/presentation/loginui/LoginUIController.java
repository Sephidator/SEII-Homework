package main.java.presentation.loginui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.presentation.messageui.PurchaseSalePanelUIController;
import sun.applet.Main;

import java.io.IOException;

public class LoginUIController {

    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXTextField passwordField;
    @FXML
    private JFXButton login;
    @FXML
    private JFXButton exit;


    private Stage stage;

    /**
     * The constructor
     * be called before the initialize() method
     * */
    public LoginUIController() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Initialize the controller class.
     * be automatically called after the fxml file has been loaded.
     * */
    public void initialize(Stage stage){
        this.stage=stage;
        usernameField.setText("");
        passwordField.setText("");
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Called when the user clickes on the Login button
     * */
    @FXML
    private void handleLogin(){
        PurchaseSalePanelUIController.init(stage);
    }

    /**
     * Called when the user clickes on the Login button
     * */
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

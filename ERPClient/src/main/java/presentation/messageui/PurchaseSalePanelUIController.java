package main.java.presentation.messageui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.presentation.loginui.LoginUIController;

import java.io.IOException;


public class PurchaseSalePanelUIController {
    @FXML
    private JFXButton logout;
    @FXML
    private JFXButton refreshSystemMessage;
    @FXML
    private JFXButton button1;
    @FXML
    private ImageView image1;

    private LoginUIController loginUIController;
    private Stage stage;
    /**
     * The constructor
     * be called before the initialize() method
     * */
    public PurchaseSalePanelUIController() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Initialize the controller class.
     * be automatically called after the fxml file has been loaded.
     * */
    public void initialize(){
        //establishPurchaseTradeBillImage.fitWidthProperty().bind(establishPurchaseTradeBillButton.widthProperty());
        //establishPurchaseTradeBillImage.fitHeightProperty().bind(establishPurchaseTradeBillButton.heightProperty());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleLogout(){
        LoginUIController.init(stage);
    }

    public static void init(Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/messageui/PurchaseSalePanelUI.fxml"));
            Scene scene=new Scene(loader.load());
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();

            PurchaseSalePanelUIController controller=loader.getController();
            controller.setStage(stage);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

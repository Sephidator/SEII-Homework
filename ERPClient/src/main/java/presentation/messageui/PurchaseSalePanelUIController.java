package main.java.presentation.messageui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import main.java.MainApp;
import main.java.presentation.clientui.ClientUIController;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.uiutility.MyUIController;


public class PurchaseSalePanelUIController extends MyUIController{

    @FXML
    private void manageClient(){
        root.showLogoutButton(false);
        ClientUIController.init(root);
    }

    public void instanceInit(RootUIController root){
        init(root);
    }

    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/messageui/PurchaseSalePanelUI.fxml"));
            root.setCenterPane(loader.load());

            PurchaseSalePanelUIController controller=loader.getController();
            root.setMainPaneController(controller);
            root.showLogoutButton(true);
            controller.setRoot(root);
            controller.setReturnPaneController(null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

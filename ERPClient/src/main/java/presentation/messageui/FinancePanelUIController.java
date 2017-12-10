package main.java.presentation.messageui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import main.java.MainApp;
import main.java.presentation.clientui.ClientUIController;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.purchaseui.PurchaseBillUIController;
import main.java.presentation.saleui.SaleBillUIController;
import main.java.presentation.uiutility.CenterUIController;


public class FinancePanelUIController extends CenterUIController {


    // 界面之中会用到的方法******************************************

    /*
    @FXML
    private void handlePurchaseBill(){
        root.showLogoutButton(false);
        PurchaseBillUIController.init(root);
    }

    @FXML
    private void handleSaleBill(){
        root.showLogoutButton(false);
        SaleBillUIController.init(root);
    }

    @FXML
    private void handleClient(){
        root.showLogoutButton(false);
        ClientUIController.init(root);
    }
    */

    // 加载文件和界面的方法******************************************

    public void instanceInit(RootUIController root){
        init(root);
    }

    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/messageui/FinancePanelUI.fxml"));
            root.setCenterPane(loader.load());

            FinancePanelUIController controller=loader.getController();
            root.showLogoutButton(true);
            controller.setRoot(root);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

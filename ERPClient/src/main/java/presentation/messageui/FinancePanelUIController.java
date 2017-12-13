package main.java.presentation.messageui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import main.java.MainApp;
import main.java.presentation.accountui.AccountUIController;
import main.java.presentation.clientui.ClientUIController;
import main.java.presentation.financeui.FinanceBillUIController;
import main.java.presentation.logui.LogUIController;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.purchaseui.PurchaseBillUIController;
import main.java.presentation.reportui.BusinessConditionUIController;
import main.java.presentation.reportui.BusinessHistoryUIController;
import main.java.presentation.reportui.SaleDetailUIController;
import main.java.presentation.saleui.SaleBillUIController;
import main.java.presentation.uiutility.CenterUIController;


public class FinancePanelUIController extends CenterUIController {


    // 界面之中会用到的方法******************************************


    @FXML
    private void handleAccount(){
        root.showLogoutButton(false);
        AccountUIController.init(root);
    }

    @FXML
    private void handleFinanceBill(){
        root.showLogoutButton(false);
        FinanceBillUIController.init(root);
    }

    @FXML
    private void handleLog(){
        root.showLogoutButton(false);
        LogUIController.init(root);
    }

    @FXML
    private void handleSaleDetail(){
        root.showLogoutButton(false);
        SaleDetailUIController.init(root);
    }

    @FXML
    private void handleBusinessHistory(){
        root.showLogoutButton(false);
        BusinessHistoryUIController.init(root);
    }

    @FXML
    private void handleBusinessCondition(){
        root.showLogoutButton(false);
        BusinessConditionUIController.init(root);
    }

    @FXML
    private void handleInitial(){
    }

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

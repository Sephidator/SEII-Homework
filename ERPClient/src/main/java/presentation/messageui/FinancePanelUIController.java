package main.java.presentation.messageui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import main.java.MainApp;
import main.java.businesslogicfactory.messageblfactory.MessageBlFactory;
import main.java.businesslogicservice.messageblservice.MessageBlService;
import main.java.exception.DataException;
import main.java.presentation.accountui.AccountUIController;
import main.java.presentation.clientui.ClientUIController;
import main.java.presentation.financeui.FinanceBillUIController;
import main.java.presentation.initialui.InitialUIController;
import main.java.presentation.logui.LogUIController;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.purchaseui.PurchaseBillUIController;
import main.java.presentation.reportui.BusinessConditionUIController;
import main.java.presentation.reportui.BusinessHistoryUIController;
import main.java.presentation.reportui.SaleDetailUIController;
import main.java.presentation.saleui.SaleBillUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.message.MessageVO;

import java.util.ArrayList;


public class FinancePanelUIController extends CenterUIController {
    private MessageBlService service;
    @FXML
    private TextArea messageArea;

    // 设置controller数据的方法*****************************************

    public void setService(MessageBlService service){
        this.service=service;
        refreshMessage();
    }

    private void refreshMessage(){
        try{
            ArrayList<MessageVO> messageList=service.getMessageList(root.getOperator());
            String text="";

            for(MessageVO message:messageList){
                text+="系统消息："+System.lineSeparator();
                text+=message.getMessage()+System.lineSeparator();
                text+=System.lineSeparator();
            }
            messageArea.setText(text);

        }catch(DataException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("获取系统信息失败");
            alert.setContentText("数据库错误");
            alert.showAndWait();
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("获取系统信息失败");
            alert.setContentText("RMI连接错误");
            alert.showAndWait();
        }
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleMessage(){
        refreshMessage();
    }

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
        LogUIController.init(root,true);
    }

    @FXML
    private void handleSaleDetail(){
        root.showLogoutButton(false);
        SaleDetailUIController.init(root,true);
    }

    @FXML
    private void handleBusinessHistory(){
        root.showLogoutButton(false);
        BusinessHistoryUIController.init(root,true);
    }

    @FXML
    private void handleBusinessCondition(){
        root.showLogoutButton(false);
        BusinessConditionUIController.init(root,true);
    }

    @FXML
    private void handleInitial(){
        root.showLogoutButton(false);
        InitialUIController.init(root);
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
            controller.setService(MessageBlFactory.getService());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

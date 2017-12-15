package main.java.presentation.uiutility;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.businesslogicservice.approvalblservice.ApprovalBlService;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.CashItemVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class ApprovalRejectUIController {
    private Stage dialogStage;
    private ApprovalBlService service;
    private BillVO bill;
    private UserVO manager;

    @FXML
    private TextArea reason;

    // 加载文件后调用的方法******************************************

    public void initialize(){
    }

    // 设置controller数据的方法*****************************************

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage=dialogStage;
    }

    public void setService(ApprovalBlService service) {
        this.service=service;
    }

    public void setBill(BillVO bill) {
        this.bill=bill;
    }

    public void setManager(UserVO manager) {
        this.manager=manager;
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleConfirm(){
        //service.reject(bill,reason.getText(),manager);
        dialogStage.close();
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */

    public static void init(ApprovalBlService service, BillVO bill, UserVO manager, Stage stage){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/uiutility/ApprovalRejectUI.fxml"));

            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("审批界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            ApprovalRejectUIController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setService(service);
            controller.setBill(bill);
            controller.setManager(manager);

            dialogStage.showAndWait();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

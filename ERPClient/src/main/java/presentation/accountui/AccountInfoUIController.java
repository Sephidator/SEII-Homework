package main.java.presentation.accountui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.businesslogicservice.accountblservice.AccountBlService;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.account.AccountVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class AccountInfoUIController extends InfoUIController{
    private AccountVO account;
    private AccountBlService accountBlService;

    @FXML
    private TextField ID; // 客户编号
    @FXML
    private TextField name; // 客户姓名
    @FXML
    private TextField bankAccount; // 客户电话
    @FXML
    private TextField remaining; // 客户地址
    
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;

    // 加载文件后调用的方法******************************************

    /**
     * 设置各个选择框的信息
     * */
    public void initialize(){
    }

    // 设置controller数据的方法*****************************************

    public void setAccount(AccountVO account) {
        this.account = account;
        ID.setText(account.getID());
        name.setText(account.getName());
        bankAccount.setText(account.getBankAccount());
        remaining.setText(String.valueOf(account.getRemaining()));
    }

    public void setAccountBlService(AccountBlService accountBlService) {
        this.accountBlService = accountBlService;
    }

    /**
     * 根据数字来设置按钮的文字
     * 按钮被点击时，根据不同的文字执行不同的功能
     * 好吧这个控制耦合有点蠢，但是做的工作少一点
     * 1对应添加客户；2对应编辑客户界面；3对应查看客户界面
     * */
    public void setPaneFunction(int command){
        if(command==1){
            confirm.setText("添加");
        }
        else if(command==2){
            confirm.setText("编辑");
        }
        else if(command==3){
            confirm.setText("确定");
        }
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleConfirm(){
        String text=confirm.getText();
        /*
        if(text.equals("确认添加")){
            accountBlService.addAccount(account);
        }
        else if(text.equals("确认编辑")){
            accountBlService.editAccount(account);
        }
        else{
            stage.close();
        }
        */
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(AccountBlService service,AccountVO account, int command,Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/accountui/AccountInfoUI.fxml"));

            // Create the dialog stage
            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("账户信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            AccountInfoUIController controller=loader.getController();
            controller.setAccountBlService(service);
            controller.setAccount(account);
            controller.setDialogStage(dialogStage);
            controller.setPaneFunction(command);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
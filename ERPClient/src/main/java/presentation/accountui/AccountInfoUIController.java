package main.java.presentation.accountui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.businesslogicservice.accountblservice.AccountBlService;
import main.java.exception.DataException;
import main.java.exception.ExistException;
import main.java.exception.NotExistException;
import main.java.presentation.uiutility.UITool;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.account.AccountVO;


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
            remaining.setEditable(false);
        }
        else if(command==3){
            confirm.setText("确定");
            ID.setEditable(false);
            name.setEditable(false);
            bankAccount.setEditable(false);
            remaining.setEditable(false);
        }
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleConfirm(){
        if(isInputValid()){
            String text=confirm.getText();

            try{
                if(text.equals("添加")){
                    String accountID=accountBlService.addAccount(account);
                    String accountName=account.getName();

                    UITool.showAlert(Alert.AlertType.INFORMATION,
                            "Success","添加账户成功",
                            "账户ID："+accountID+System.lineSeparator()+"名字："+accountName);
                }
                else if(text.equals("编辑")){
                    accountBlService.editAccount(account);
                    String accountID=account.getID();
                    String accountName=account.getName();

                    UITool.showAlert(Alert.AlertType.INFORMATION,
                            "Success","编辑账户成功",
                            "账户ID："+accountID+System.lineSeparator()+"名字："+accountName);
                }

                dialogStage.close();
            }catch(DataException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"账户失败","数据库错误");
            }catch(NotExistException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"账户失败","账户不存在");
            }catch(ExistException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"账户失败","账号和已有账户重复");
            }catch(Exception e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"账户失败","RMI连接错误");
            }
        }
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    /**
     * 检查账户信息的输入是否完整且合法
     * 完整且合法返回true
     * */
    private boolean isInputValid(){

        String errorMessage = "";

        if (name.getText().length()==0) {
            errorMessage+=("未选择账户名称。"+System.lineSeparator());
        }
        if (bankAccount.getText().length()==0) {
            errorMessage+=("未输入银行账户。"+System.lineSeparator());
        }
        if (remaining.getText().length()==0) {
            errorMessage+=("未输入账户余额。"+System.lineSeparator());
        }
        else {
            try {
                double i=Double.parseDouble(remaining.getText());
                if(i<0)
                    throw new NumberFormatException();
            } catch(NumberFormatException e) {
                errorMessage+=("账户余额必须是非负数。"+System.lineSeparator());
            }
        }

        if(errorMessage.length()==0){
            account.setName(name.getText());
            account.setBankAccount(bankAccount.getText());
            account.setRemaining(Double.parseDouble(remaining.getText()));

            return true;
        } else {
            // Show the error message.
            UITool.showAlert(Alert.AlertType.ERROR,
                    "账户信息错误","请检查账户信息的输入",errorMessage);
            return false;
        }
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

            // Show the dialog and wait until the account closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
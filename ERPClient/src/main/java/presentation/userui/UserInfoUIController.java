package main.java.presentation.userui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.exception.DataException;
import main.java.exception.ExistException;
import main.java.exception.NotExistException;
import main.java.businesslogicservice.userblservice.UserBlService;
import main.java.presentation.uiutility.AlertInfo;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.user.UserVO;

public class UserInfoUIController extends InfoUIController{
    private UserVO user;
    private UserBlService userBlService;

    @FXML
    private TextField ID; // 用户编号
    @FXML
    private TextField type; // 用户类别
    @FXML
    private TextField jobName; // 账号名
    @FXML
    private TextField password; // 密码
    @FXML
    private TextField name; // 姓名
    @FXML
    private TextField age; // 年龄
    @FXML
    private TextField top; // 最高权限

    @FXML
    private ChoiceBox<String> typeChoiceBox;
    @FXML
    private ChoiceBox<String> topChoiceBox;
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;

    // 加载文件后调用的方法******************************************

    /**
     * 设置各个选择框的信息
     * */
    public void initialize(){
        String[] typeList=new String[]{"库存管理人员","进货销售人员","财务人员","总经理","管理员"};
        typeChoiceBox.setItems(FXCollections.observableArrayList("库存管理人员","进货销售人员","财务人员","总经理","管理员"));
        typeChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
            type.setText(typeList[newValue.intValue()]);
            user.setType(typeList[newValue.intValue()]);
        });

        topChoiceBox.setItems(FXCollections.observableArrayList("是","否"));
        topChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
            System.out.println(newValue.intValue()==0);
            user.setTop(newValue.intValue()==0);
            top.setText((user.isTop())?"是":"否");
        });

    }

    // 设置controller数据的方法*****************************************

    public void setUser(UserVO user) {
        this.user = user;
        ID.setText(user.getID());
        type.setText(user.getType());
        jobName.setText(user.getJobName());
        password.setText(user.getPassword());
        name.setText(user.getName());
        age.setText(String.valueOf(user.getAge()));
        top.setText((user.isTop())?"是":"否");
    }

    public void setUserBlService(UserBlService userBlService) {
        this.userBlService = userBlService;
    }

    /**
     * 根据数字来设置按钮的文字
     * 按钮被点击时，根据不同的文字执行不同的功能
     * 1对应添加用户；2对应编辑用户；3对应查看用户
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

            ID.setEditable(false);
            type.setEditable(false);
            jobName.setEditable(false);
            password.setEditable(false);
            name.setEditable(false);
            age.setEditable(false);
            top.setEditable(false);
            typeChoiceBox.setDisable(true);
            topChoiceBox.setDisable(true);
        }
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleConfirm(){
        if(isInputValid()){
            String text=confirm.getText();

            try{
                if(text.equals("添加")){
                    String userID=userBlService.addUser(user);
                    String userName=user.getName();

                    AlertInfo.showAlert(Alert.AlertType.INFORMATION,
                            "Success","添加用户成功",
                            "用户ID："+userID+System.lineSeparator()+"名字："+userName);
                }
                else if(text.equals("编辑")){
                    userBlService.editUser(user);
                    String userID=user.getID();
                    String userName=user.getName();

                    AlertInfo.showAlert(Alert.AlertType.INFORMATION,
                            "Success","编辑用户成功",
                            "用户ID："+userID+System.lineSeparator()+"名字："+userName);
                }

                dialogStage.close();
            }catch(DataException e){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"用户失败", "数据库错误");
            }catch(NotExistException e){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"用户失败","用户不存在");
            }catch(ExistException e){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"用户失败","账号和已有用户重复");
            }catch(Exception e){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"用户失败","RMI连接错误");
            }
        }
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    /**
     * 检查用户信息的输入是否完整且合法
     * 完整且合法返回true
     * */
    private boolean isInputValid(){

        String errorMessage = "";

        if (type.getText().length()==0) {
            errorMessage+=("未选择用户类型。"+System.lineSeparator());
        }
        if (jobName.getText().length()==0) {
            errorMessage+=("未输入用户账号名。"+System.lineSeparator());
        }
        if (password.getText().length()==0) {
            errorMessage+=("未输入用户密码。"+System.lineSeparator());
        }
        if (name.getText().length()==0) {
            errorMessage+=("未输入用户姓名。"+System.lineSeparator());
        }
        if (top.getText().length()==0) {
            errorMessage+=("未输入用户权限。"+System.lineSeparator());
        }
        if (age.getText().length()==0) {
            errorMessage+=("未输入用户年龄。"+System.lineSeparator());
        }
        else {
            try {
                int i=Integer.parseInt(age.getText());
                if(i<=0)
                    throw new NumberFormatException();
            } catch(NumberFormatException e) {
                errorMessage+=("用户年龄必须是正整数。"+System.lineSeparator());
            }
        }

        if(errorMessage.length()==0){
            user.setType(type.getText());
            user.setJobName(jobName.getText());
            user.setPassword(password.getText());
            user.setName(name.getText());
            user.setAge(Integer.parseInt(age.getText()));
            user.setTop(top.getText().equals("是"));

            return true;
        } else {
            // Show the error message.
            AlertInfo.showAlert(Alert.AlertType.ERROR,
                    "用户信息错误","请检查用户信息的输入",errorMessage);
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(UserBlService service,UserVO user, int command,Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/userui/UserInfoUI.fxml"));

            // Create the dialog stage
            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("用户信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            UserInfoUIController controller=loader.getController();
            controller.setUserBlService(service);
            controller.setUser(user);
            controller.setDialogStage(dialogStage);
            controller.setPaneFunction(command);


            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
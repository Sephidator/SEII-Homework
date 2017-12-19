package main.java.presentation.userui;

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
import main.java.businesslogicservice.userblservice.UserBlService;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.user.UserVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

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
        ID.setText(user.getName());
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
     * 好吧这个控制耦合有点蠢，但是做的工作少一点
     * 1对应添加客户；2对应编辑客户界面；3对应查看客户界面
     * */
    public void setPaneFunction(int command){
        if(command==1){
            confirm.setText("确认添加");
        }
        else if(command==2){
            confirm.setText("确认编辑");
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
        if(text.equals("添加")){
            userBlService.addUser(user);
        }
        else if(text.equals("编辑")){
            userBlService.editUser(user);
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
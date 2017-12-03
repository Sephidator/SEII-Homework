package main.java.presentation.clientui;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.businesslogicservice.clientblservice.ClientBlService;
import main.java.vo.client.ClientVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class ClientInfoUIController {
    private ClientVO client;
    private ClientUIController clientUIController;
    private ClientBlService clientBlService;
    private ArrayList<UserVO> salesmanList;
    private Stage stage;

    @FXML
    private TextField ID; // 客户编号
    @FXML
    private TextField category; // 客户类别：进货商、销售商
    @FXML
    private TextField level; // 客户级别：1-5（vip）
    @FXML
    private TextField name; // 客户姓名
    @FXML
    private TextField phone; // 客户电话
    @FXML
    private TextField address; // 客户地址
    @FXML
    private TextField post; // 客户邮编
    @FXML
    private TextField email; // 客户电子邮箱
    @FXML
    private TextField receivable; // 客户应收
    @FXML
    private TextField payable; // 客户应付
    @FXML
    private TextField receivableLimit;// 客户应收额度
    @FXML
    private TextField salesman; // 默认业务员
    @FXML
    private ChoiceBox<Integer> levelChoiceBox;
    @FXML
    private ChoiceBox<String> categoryChoiceBox;
    @FXML
    private ChoiceBox<String> salesmanChoiceBox;
    @FXML
    private JFXButton confirm;
    @FXML
    private JFXButton cancel;

    // 加载文件后调用的方法******************************************

    /**
     * 设置各个选择框的信息
     * */
    public void initialize(){
        levelChoiceBox= new ChoiceBox<Integer>(FXCollections.observableArrayList(1,2,3,4,5));
        levelChoiceBox.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    public void changed(ObservableValue ov, Number value, Number new_value) {
                        level.setText(new_value.toString());
                    }
                });

        categoryChoiceBox= new ChoiceBox<String>(FXCollections.observableArrayList("供应商","销售商"));
        categoryChoiceBox.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    public void changed(ObservableValue ov, Number value, Number new_value) {
                        category.setText(salesmanList.get(new_value.intValue()).getName());
                    }
                });

        salesmanChoiceBox= new ChoiceBox<String>();
        salesmanChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
            salesman.setText(salesmanList.get(newValue.intValue()).getName());
        });
    }

    // 设置controller数据的方法*****************************************

    public void setClient(ClientVO client) {
        this.client = client;
    }

    public void setClientBlService(ClientBlService clientBlService) {
        this.clientBlService = clientBlService;
    }

    public void setStage(Stage stage) {
        this.stage=stage;
    }

    // 界面之中会用到的方法******************************************

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

    @FXML
    private void handleConfirm(){
        String text=confirm.getText();
        /*
        if(text.equals("确认添加")){
            clientBlService.addClient(client);
        }
        else if(text.equals("确认编辑")){
            clientBlService.editClient(client);
        }
        else{
            stage.close();
        }
        */
    }

    @FXML
    private void handleCancel(){
        stage.close();
    }

    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(ClientBlService service,ClientVO client, int command){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/clientui/ClientInfoUI.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(loader.load()));

            ClientInfoUIController controller=loader.getController();
            controller.setClientBlService(service);
            controller.setClient(client);
            controller.setStage(stage);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
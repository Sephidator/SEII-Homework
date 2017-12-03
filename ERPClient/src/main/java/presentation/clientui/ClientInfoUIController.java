package main.java.presentation.clientui;


import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.businesslogicservice.clientblservice.ClientBlService;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.PurchaseSalePanelUIController;
import main.java.presentation.uiutility.MyUIController;
import main.java.vo.client.ClientVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class ClientInfoUIController{
    private ClientVO client;
    private ClientUIController clientUIController;
    private ClientBlService clientBlService;
    private ArrayList<UserVO> salesmanList;

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

    public ClientVO getClient() {
        return client;
    }

    public void setClient(ClientVO client) {
        this.client = client;
    }

    public ClientUIController getClientUIController() {
        return clientUIController;
    }

    public void setClientUIController(ClientUIController clientUIController) {
        this.clientUIController = clientUIController;
    }

    public ClientBlService getClientBlService() {
        return clientBlService;
    }

    public void setClientBlService(ClientBlService clientBlService) {
        this.clientBlService = clientBlService;
    }

    public static void init(ClientBlService service,ClientVO client){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/clientui/ClientInfoUI.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(loader.load()));

            ClientInfoUIController controller=loader.getController();
            controller.setClientBlService(service);
            controller.setClient(client);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
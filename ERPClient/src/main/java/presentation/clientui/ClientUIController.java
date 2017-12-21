package main.java.presentation.clientui;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.java.MainApp;
import main.java.businesslogicfactory.clientblfactory.ClientBlFactory;
import main.java.businesslogicservice.clientblservice.ClientBlService;
import main.java.exception.DataException;
import main.java.exception.NotExistException;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.PurchaseSalePanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.user.UserQueryVO;

import java.util.ArrayList;

public class ClientUIController extends CenterUIController {
    private ClientBlService clientBlService;

    private ObservableList<ClientVO> clientObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<ClientVO> clientTableView;
    @FXML
    private TableColumn<ClientVO,String> clientIDColumn;
    @FXML
    private TableColumn<ClientVO,String> clientNameColumn;
    @FXML
    private TableColumn<ClientVO,String> clientLevelColumn;
    @FXML
    private TableColumn<ClientVO,String> clientCategoryColumn;
    @FXML
    private TableColumn<ClientVO,String> clientPhoneColumn;
    @FXML
    private TableColumn<ClientVO,String> clientAddressColumn;

    @FXML
    private TextField searchInfo;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        clientIDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));
        clientNameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getName()));
        clientLevelColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getLevel())));
        clientCategoryColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getCategory()));
        clientPhoneColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getPhone()));
        clientAddressColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getAddress()));
    }

    // 设置controller数据的方法*****************************************

    public void setClientBlService(ClientBlService clientBlService) {
        this.clientBlService = clientBlService;
        refresh(null);
    }

    /**
     * 刷新界面，取得所有用户的列表，并显示在tableview中
     * */
    private void refresh(ClientQueryVO query){
        try {
            ArrayList<ClientVO> clientList = clientBlService.getClientList(query);
            showClientList(clientList);
        }catch(DataException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("查找客户失败");
            alert.setContentText("数据库错误");
            alert.showAndWait();
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("查找客户失败");
            alert.setContentText("RMI连接错误");
            alert.showAndWait();
        }
    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showClientList(ArrayList<ClientVO> clientList){
        clientObservableList.removeAll();
        clientObservableList.setAll(clientList);
        clientTableView.setItems(clientObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleSearch(){
        String text=searchInfo.getText();
        if(text.equals("")){
            refresh(null);
        }
        else{
            ClientQueryVO query=new ClientQueryVO(text);
            refresh(query);
        }
    }

    @FXML
    private void handleAddClient(){
        ClientInfoUIController.init(clientBlService,new ClientVO(),1,root.getStage());
        refresh(null);
    }

    @FXML
    private void handleDeleteClient(){
        if(isClientSelected()){
            try {
                String ID = clientTableView.getSelectionModel().getSelectedItem().getID();
                String name = clientTableView.getSelectionModel().getSelectedItem().getName();
                clientBlService.deleteClient(ID);

                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("删除客户成功");
                alert.setContentText("客户ID："+ID+System.lineSeparator()+"名字："+name);
                alert.showAndWait();
            }catch(DataException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("删除客户失败");
                alert.setContentText("数据库错误");
                alert.showAndWait();
            }catch(NotExistException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("删除客户失败");
                alert.setContentText("客户不存在");
                alert.showAndWait();
            }catch(Exception e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("删除客户失败");
                alert.setContentText("RMI连接错误");
                alert.showAndWait();
            }
            refresh(null);
        }
    }

    @FXML
    private void handleEditClient(){
        if(isClientSelected()){
            ClientInfoUIController.init(clientBlService,clientTableView.getSelectionModel().getSelectedItem(),2,root.getStage());
        }
        refresh(null);
    }

    @FXML
    private void handleCheckClient() {
        if(isClientSelected()){
            ClientInfoUIController.init(clientBlService,clientTableView.getSelectionModel().getSelectedItem(),3,root.getStage());
        }
    }

    private boolean isClientSelected(){
        int selectedIndex=clientTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            // Nothing selected
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("未选中客户");
            alert.setContentText("请在表中选择客户");
            alert.showAndWait();
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    /**
     * 初始化方法，调用init方法
     * 之所以有这个方法是为了多态而提供的
     * */
    public void instanceInit(RootUIController root){
        init(root);
    }

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/clientui/ClientUI.fxml"));
            root.setCenterPane(loader.load());

            ClientUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setClientBlService(ClientBlFactory.getService());

            root.setReturnPaneController(new PurchaseSalePanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

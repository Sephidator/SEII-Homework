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
import main.java.MainApp;
import main.java.businesslogicservice.clientblservice.ClientBlService;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.PurchaseSalePanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.client.ClientVO;

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
        clientAddressColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));
    }

    // 设置controller数据的方法*****************************************

    public void setClientBlService(ClientBlService clientBlService) {
        this.clientBlService = clientBlService;
        //ArrayList<ClientVO> clientList=clientBlService.getClientList(null);
        //showClientList(clientList);
    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showClientList(ArrayList<ClientVO> clientList){
        clientObservableList.removeAll();

        for(int i=0;i<clientList.size();i++){
            clientObservableList.add(clientList.get(i));
        }
        clientTableView.setItems(clientObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleAddClient(){
        ClientInfoUIController.init(clientBlService,new ClientVO(),1,root.getStage());
    }

    @FXML
    private void handleDeleteClient(){
        int selectedIndex=clientTableView.getSelectionModel().getSelectedIndex();
        if(isClientSelected()){
            clientTableView.getItems().remove(selectedIndex);
        }
    }

    @FXML
    private void handleEditClient(){
        if(isClientSelected()){
            ClientInfoUIController.init(clientBlService,clientTableView.getSelectionModel().getSelectedItem(),2,root.getStage());
        }
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
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table");
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
            controller.setClientBlService(null);

            ClientVO c1=new ClientVO("类别：经销商", 3, "名字：陈骁",
                    "电话：123", "地址：南京大学", "邮编123", "邮件：123",
            0, 0, 20, null);
            c1.setID("123");
            ClientVO c2=new ClientVO("类别：经销商", 4, "名字：陈骁2",
                    "电话：123", "地址：南京大学", "邮编123", "邮件：123",
                    0, 0, 20, null);
            c2.setID("123");

            ArrayList<ClientVO> list=new ArrayList<>();
            list.add(c1);
            list.add(c2);
            controller.showClientList(list);

            root.setReturnPaneController(new PurchaseSalePanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

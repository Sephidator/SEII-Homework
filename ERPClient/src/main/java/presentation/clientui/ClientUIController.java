package main.java.presentation.clientui;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.MainApp;
import main.java.businesslogicservice.clientblservice.ClientBlService;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.PurchaseSalePanelUIController;
import main.java.presentation.uiutility.MyUIController;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public class ClientUIController extends MyUIController{
    private ClientBlService clientBlService;

    @FXML
    private JFXButton search;
    @FXML
    private JFXButton addClient;
    @FXML
    private JFXButton deleteClient;
    @FXML
    private JFXButton EditClient;
    @FXML
    private JFXButton checkClient;

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

    public void initialize(){
        clientIDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));
        clientNameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getName()));
        clientLevelColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getLevel())));
        clientCategoryColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getCategory()));
        clientPhoneColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getPhone()));
        clientAddressColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));
    }

    public void setClientBlService(ClientBlService clientBlService) {
        this.clientBlService = clientBlService;
    }

    public ClientBlService getClientBlService() {
        return clientBlService;
    }

    private void showClientList(ArrayList<ClientVO> clientList){
        clientObservableList.removeAll();

        for(int i=0;i<clientList.size();i++){
            clientObservableList.add(clientList.get(i));
        }
        clientTableView.setItems(clientObservableList);
    }

    public void instanceInit(RootUIController root){
        init(root);
    }

    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/clientui/ClientUI.fxml"));
            root.setCenterPane(loader.load());

            ClientUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setReturnPaneController(new PurchaseSalePanelUIController());
            controller.setClientBlService(null);

            ClientVO c1=new ClientVO("ID:123","类别：经销商", 3, "名字：陈骁",
                    "电话：123", "地址：南京大学", "邮编123", "邮件：123",
            0, 0, 20, null);
            ClientVO c2=new ClientVO("ID:124","类别：经销商", 4, "名字：陈骁2",
                    "电话：123", "地址：南京大学", "邮编123", "邮件：123",
                    0, 0, 20, null);
            ArrayList<ClientVO> list=new ArrayList<>();
            list.add(c1);
            list.add(c2);
            controller.showClientList(list);

            root.setMainPaneController(controller);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

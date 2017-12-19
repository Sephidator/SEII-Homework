package main.java.presentation.userui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.MainApp;
import main.java.businesslogicservice.userblservice.UserBlService;
import main.java.presentation.userui.UserInfoUIController;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.PurchaseSalePanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class UserUIController extends CenterUIController {
    private UserBlService userBlService;

    private ObservableList<UserVO> userObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<UserVO> userTableView;
    @FXML
    private TableColumn<UserVO,String> userIDColumn;
    @FXML
    private TableColumn<UserVO,String> userNameColumn;
    @FXML
    private TableColumn<UserVO,String> userTypeColumn;
    @FXML
    private TableColumn<UserVO,String> userTopColumn;


    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        userIDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));
        userNameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getName()));
        userTypeColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getType()));
        userTopColumn.setCellValueFactory(cellData->new SimpleStringProperty((cellData.getValue().isTop())?"是":"否"));
    }

    // 设置controller数据的方法*****************************************

    public void setUserBlService(UserBlService userBlService) {
        this.userBlService = userBlService;
        //ArrayList<UserVO> userList=userBlService.getUserList(null);
        //showUserList(userList);
    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showUserList(ArrayList<UserVO> userList){
        userObservableList.removeAll();
        userObservableList.setAll(userList);
        userTableView.setItems(userObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleAddUser(){
        UserInfoUIController.init(userBlService,new UserVO(),1,root.getStage());
    }

    @FXML
    private void handleDeleteUser(){
        int selectedIndex=userTableView.getSelectionModel().getSelectedIndex();
        if(isUserSelected()){
            userTableView.getItems().remove(selectedIndex);
        }
    }

    @FXML
    private void handleEditUser(){
        if(isUserSelected()){
            UserInfoUIController.init(userBlService,userTableView.getSelectionModel().getSelectedItem(),2,root.getStage());
        }
    }

    @FXML
    private void handleCheckUser() {
        if(isUserSelected()){
            UserInfoUIController.init(userBlService,userTableView.getSelectionModel().getSelectedItem(),3,root.getStage());
        }
    }

    private boolean isUserSelected(){
        int selectedIndex=userTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            // Nothing selected
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("未选中用户");
            alert.setContentText("请在表中选择用户");
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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/userui/UserUI.fxml"));
            root.setCenterPane(loader.load());

            UserUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setUserBlService(null);

            root.setReturnPaneController(new PurchaseSalePanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

package main.java.presentation.userui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import main.java.MainApp;
import main.java.exception.DataException;
import main.java.exception.NotExistException;
import main.java.businesslogicfactory.userblfactory.UserBlFactory;
import main.java.businesslogicservice.userblservice.UserBlService;
import main.java.presentation.messageui.AdministratorPanelUIController;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.user.UserQueryVO;
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

    @FXML
    private TextField searchInfo;

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
        refresh(null);
    }

    /**
     * 刷新界面，取得所有用户的列表，并显示在tableview中
     * */
    private void refresh(UserQueryVO query){
        try {
            ArrayList<UserVO> userList = userBlService.getUserList(query);
            showUserList(userList);
        }catch(DataException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("查找用户失败");
            alert.setContentText("数据库连接错误");
            alert.showAndWait();
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("查找用户失败");
            alert.setContentText("RMI连接错误");
            alert.showAndWait();
        }
    }

    /**
     * 取得用户列表并修改ObservableList的信息
     * */
    private void showUserList(ArrayList<UserVO> userList){
        userObservableList.removeAll();
        userObservableList.setAll(userList);
        userTableView.setItems(userObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleSearch(){
        String text=searchInfo.getText();
        if(text.equals("")){
            refresh(null);
        }
        else{
            UserQueryVO query=new UserQueryVO(text,text);
            refresh(query);
        }
    }

    @FXML
    private void handleAddUser(){
        UserInfoUIController.init(userBlService,new UserVO(),1,root.getStage());
        refresh(null);
    }

    @FXML
    private void handleDeleteUser(){
        if(isUserSelected()){
            try {
                String ID = userTableView.getSelectionModel().getSelectedItem().getID();
                String name = userTableView.getSelectionModel().getSelectedItem().getName();
                UserBlFactory.getService().deleteUser(ID);

                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCCESS");
                alert.setHeaderText("删除用户成功");
                alert.setContentText("用户ID："+ID+System.lineSeparator()+"名字："+name);
                alert.showAndWait();
            }catch(DataException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("删除用户失败");
                alert.setContentText("数据库错误");
                alert.showAndWait();
            }catch(NotExistException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("删除用户失败");
                alert.setContentText("用户不存在");
                alert.showAndWait();
            }catch(Exception e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("删除用户失败");
                alert.setContentText("RMI连接错误");
                alert.showAndWait();
            }
            refresh(null);
        }
    }

    @FXML
    private void handleEditUser(){
        if(isUserSelected()){
            UserInfoUIController.init(userBlService,userTableView.getSelectionModel().getSelectedItem(),2,root.getStage());
        }
        refresh(null);
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
            controller.setUserBlService(UserBlFactory.getService());

            root.setReturnPaneController(new AdministratorPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

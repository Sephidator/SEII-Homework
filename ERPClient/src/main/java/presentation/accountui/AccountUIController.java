package main.java.presentation.accountui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import main.java.MainApp;
import main.java.businesslogicfactory.accountblfactory.AccountBlFactory;
import main.java.businesslogicservice.accountblservice.AccountBlService;
import main.java.exception.DataException;
import main.java.exception.NotExistException;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.FinancePanelUIController;
import main.java.presentation.uiutility.UITool;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;

import java.util.ArrayList;

public class AccountUIController extends CenterUIController {
    private AccountBlService accountBlService;

    private ObservableList<AccountVO> accountObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<AccountVO> accountTableView;
    @FXML
    private TableColumn<AccountVO,String> IDColumn;
    @FXML
    private TableColumn<AccountVO,String> nameColumn;
    @FXML
    private TableColumn<AccountVO,String> bankAccountColumn;
    @FXML
    private TableColumn<AccountVO,String> remainingColumn;

    @FXML
    private TextField searchInfo;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        IDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));
        nameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getName()));
        bankAccountColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getBankAccount()));
        remainingColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getRemaining())));
    }

    // 设置controller数据的方法*****************************************

    public void setAccountBlService(AccountBlService accountBlService) {
        this.accountBlService = accountBlService;
    }

    /**
     * 刷新界面，取得所有账户的列表，并显示在tableview中
     * */
    private void refresh(AccountQueryVO query){
        try {
            ArrayList<AccountVO> accountList = accountBlService.getAccountList(query);
            showAccountList(accountList);
        }catch(DataException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找账户失败","数据库错误");
        }catch(Exception e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找账户失败","RMI连接错误");
        }
    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showAccountList(ArrayList<AccountVO> accountList){
        accountObservableList.clear();
        accountObservableList.setAll(accountList);
        accountTableView.setItems(accountObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleSearch(){
        String text=searchInfo.getText();
        if(text.equals("")){
            refresh(null);
        }
        else{
            AccountQueryVO query=new AccountQueryVO(text,text);
            refresh(query);
        }
    }

    @FXML
    private void handleAddAccount(){
        if(!root.getOperator().isTop()){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","权限错误","只有最高权限可以添加账户");
        }
        else{
            AccountInfoUIController.init(accountBlService,new AccountVO(),1,root.getStage());
            refresh(null);
        }
    }

    @FXML
    private void handleDeleteAccount(){
        if(!root.getOperator().isTop()){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","权限错误","只有最高权限可以删除账户");
        }
        else{
            if(isAccountSelected()){
                ButtonType buttonType=UITool.showAlert(Alert.AlertType.CONFIRMATION,
                        "确认", "是否删除账户？","此操作无法撤回");
                if(buttonType.equals(ButtonType.OK)){
                    try {
                        String ID = accountTableView.getSelectionModel().getSelectedItem().getID();
                        String name = accountTableView.getSelectionModel().getSelectedItem().getName();
                        accountBlService.deleteAccount(ID);

                        UITool.showAlert(Alert.AlertType.INFORMATION,
                                "Success","删除账户成功",
                                "账户ID："+ID+System.lineSeparator()+"名字："+name);
                    }catch(DataException e){
                        UITool.showAlert(Alert.AlertType.ERROR,
                                "Error","删除账户失败","数据库错误");
                    }catch(NotExistException e){
                        UITool.showAlert(Alert.AlertType.ERROR,
                                "Error","删除账户失败","账户不存在");
                    }catch(Exception e){
                        UITool.showAlert(Alert.AlertType.ERROR,
                                "Error","删除账户失败","RMI连接错误");
                    }
                    refresh(null);
                }
            }
        }
    }

    @FXML
    private void handleEditAccount(){
        if(!root.getOperator().isTop()){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","权限错误","只有最高权限可以编辑账户");
        }
        else{
            if(isAccountSelected()){
                AccountInfoUIController.init(accountBlService,accountTableView.getSelectionModel().getSelectedItem(),2,root.getStage());
            }
            refresh(null);
        }
    }

    @FXML
    private void handleCheckAccount() {
        if(!root.getOperator().isTop()){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","权限错误","只有最高权限可以查看账户");
        }
        else{
            if(isAccountSelected()){
                AccountInfoUIController.init(accountBlService,accountTableView.getSelectionModel().getSelectedItem(),3,root.getStage());
            }
        }
    }

    private boolean isAccountSelected(){
        int selectedIndex=accountTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            // Nothing selected
            UITool.showAlert(Alert.AlertType.ERROR,
                    "No Selection","未选中账户","请在表中选择账户");
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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/accountui/AccountUI.fxml"));
            root.setCenterPane(loader.load());

            AccountUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setAccountBlService(AccountBlFactory.getService());
            controller.refresh(null);
            root.setReturnPaneController(new FinancePanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

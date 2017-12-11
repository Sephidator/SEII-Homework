package main.java.presentation.accountui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.MainApp;
import main.java.businesslogicservice.accountblservice.AccountBlService;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.FinancePanelUIController;
import main.java.presentation.messageui.PurchaseSalePanelUIController;
import main.java.presentation.uiutility.CenterUIController;
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
        //ArrayList<AccountVO> accountList=accountBlService.getAccountList(null);
        //showAccountList(accountList);
    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showAccountList(ArrayList<AccountVO> accountList){
        accountObservableList.removeAll();

        for(int i=0;i<accountList.size();i++){
            accountObservableList.add(accountList.get(i));
        }
        accountTableView.setItems(accountObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleAddAccount(){
        AccountInfoUIController.init(accountBlService,new AccountVO(),1,root.getStage());
    }

    @FXML
    private void handleDeleteAccount(){
        int selectedIndex=accountTableView.getSelectionModel().getSelectedIndex();
        if(isAccountSelected()){
            accountTableView.getItems().remove(selectedIndex);
        }
    }

    @FXML
    private void handleEditAccount(){
        if(isAccountSelected()){
            AccountInfoUIController.init(accountBlService,accountTableView.getSelectionModel().getSelectedItem(),2,root.getStage());
        }
    }

    @FXML
    private void handleCheckAccount() {
        if(isAccountSelected()){
           AccountInfoUIController.init(accountBlService,accountTableView.getSelectionModel().getSelectedItem(),3,root.getStage());
        }
    }

    private boolean isAccountSelected(){
        int selectedIndex=accountTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            // Nothing selected
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("未选中账户");
            alert.setContentText("请在表中选择账户");
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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/accountui/AccountUI.fxml"));
            root.setCenterPane(loader.load());

            AccountUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setAccountBlService(null);

            AccountVO c1=new AccountVO("23234324","账户A",10000);
            c1.setID("123");
            AccountVO c2=new AccountVO("23233424","账户B",10000);
            c2.setID("234");

            ArrayList<AccountVO> list=new ArrayList<>();
            list.add(c1);
            list.add(c2);
            controller.showAccountList(list);

            root.setReturnPaneController(new FinancePanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

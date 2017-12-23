package main.java.presentation.initialui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.businesslogicfactory.goodssortblfactory.GoodsSortBlFactory;
import main.java.businesslogicservice.initialblservice.InitialBlService;
import main.java.businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;
import main.java.exception.DataException;
import main.java.presentation.uiutility.AddGoodsUIController;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.initial.InitialVO;

import java.util.ArrayList;

public class InitialInfoUIController extends InfoUIController {
    private InitialBlService service;
    private InitialVO initial;

    private ObservableList<GoodsVO> goodsObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<GoodsVO> goodsTableView;
    @FXML
    private TableColumn<GoodsVO,String> goodsNameColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsSortColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsModelColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsCostColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsRetailColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsLatestCostColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsLatestRetailColumn;

    private ObservableList<ClientVO> clientObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<ClientVO> clientTableView;
    @FXML
    private TableColumn<ClientVO,String> clientNameColumn;
    @FXML
    private TableColumn<ClientVO,String> clientCategoryColumn;
    @FXML
    private TableColumn<ClientVO,String> clientPhoneColumn;
    @FXML
    private TableColumn<ClientVO,String> clientReceivableColumn;
    @FXML
    private TableColumn<ClientVO,String> clientPayableColumn;

    private ObservableList<AccountVO> accountObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<AccountVO> accountTableView;
    @FXML
    private TableColumn<AccountVO,String> bankAccountColumn;
    @FXML
    private TableColumn<AccountVO,String> accountNameColumn;
    @FXML
    private TableColumn<AccountVO,String> accountRemainingColumn;

    @FXML
    private TextField year;
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;


    // 加载文件后调用的方法******************************************

    public void initialize(){
        goodsNameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getName()));
        goodsNameColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(GoodsSortBlFactory.getService().find(cellData.getValue().getGoodsSortID()).getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
        goodsModelColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getModel()));
        goodsCostColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getCost())));
        goodsRetailColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getRetail())));
        goodsLatestCostColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getLatestCost())));
        goodsLatestRetailColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getLatestRetail())));

        clientNameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getName()));
        clientCategoryColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getCategory()));
        clientPhoneColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getPhone()));
        clientReceivableColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getReceivable())));
        clientPayableColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getPayable())));

        accountNameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getName()));
        bankAccountColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getBankAccount()));

    }

    // 设置controller数据的方法*****************************************


    public void setService(InitialBlService service) {
        this.service=service;
    }

    public void setInitial(InitialVO initial) {
        this.initial=initial;
        year.setText(String.valueOf(initial.getYear()));
    }

    /**
     * 根据数字来设置按钮的文字
     * 按钮被点击时，根据不同的文字执行不同的功能
     * 好吧这个控制耦合有点蠢，但是做的工作少一点
     * 1对应新建单据；2对应编辑单据；3对应查看单据
     * */
    public void setPaneFunction(int command){
        if(command==1){
            confirm.setText("期初建账");
            cancel.setText("取消");
        }
        else if(command==3){
            confirm.setText("确定");
            cancel.setText("取消");
        }
    }

    /**
     * 取得商品列表并修改ObservableList的信息
     * */
    private void showGoodsList(ArrayList<GoodsVO> goodsList){
        goodsTableView.getItems().clear();
        goodsObservableList.setAll(goodsList);
        goodsTableView.setItems(goodsObservableList);
    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showClientList(ArrayList<ClientVO> clientList){
        clientTableView.getItems().clear();
        clientObservableList.setAll(clientList);
        clientTableView.setItems(clientObservableList);
    }

    /**
     * 取得账户列表并修改ObservableList的信息
     * */
    private void showAccountList(ArrayList<AccountVO> accountList){
        accountTableView.getItems().clear();
        accountObservableList.setAll(accountList);
        accountTableView.setItems(accountObservableList);
    }


    // 界面之中会用到的方法******************************************

    @FXML
    private void handleConfirm(){
        String text=confirm.getText();

        if(text.equals("期初建账")){
            //
        }

        dialogStage.close();

    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }


    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(InitialBlService service,InitialVO initial, int command,Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/initialui/InitialInfoUI.fxml"));

            // Create the dialog stage
            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("期初信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            InitialInfoUIController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setService(service);
            controller.setInitial(initial);
            controller.showGoodsList(new ArrayList<>());
            controller.showClientList(new ArrayList<>());
            controller.showAccountList(new ArrayList<>());
            controller.setPaneFunction(command);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


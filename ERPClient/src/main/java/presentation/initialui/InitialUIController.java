package main.java.presentation.initialui;

import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import main.java.MainApp;
import main.java.businesslogicservice.initialblservice.InitialBlService;
import main.java.businesslogicservice.reportblservice.SaleDetailBlService;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.FinancePanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.initial.InitialVO;
import main.java.vo.report.SaleDetailQueryVO;
import main.java.vo.report.SaleRecordVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InitialUIController extends CenterUIController {
    private InitialBlService service;

    private ObservableList<InitialVO> initialObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<InitialVO> initialTableView;
    @FXML
    private TableColumn<InitialVO,String> yearColumn;
    @FXML
    private TableColumn<InitialVO,String> goodsNumberColumn;
    @FXML
    private TableColumn<InitialVO,String> clientNumberColumn;
    @FXML
    private TableColumn<InitialVO,String> accountNumberColumn;

    @FXML
    private ChoiceBox<String> yearSelector;
    @FXML
    private TextField yearField;
    @FXML
    private Button search;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        yearColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getYear())));
        goodsNumberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getGoodsList().size())));
        clientNumberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getClientList().size())));
        accountNumberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getAccountList().size())));

        String[] list={"无","2017","2016","2015","2014","2013","2012","2011","2010"};
        yearSelector.setItems(FXCollections.observableArrayList(list));
        yearSelector.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
            String text=list[newValue.intValue()];
            yearField.setText(text.equals("无")?"":text);
        });

    }

    // 设置controller数据的方法*****************************************

    public void setInitialBlService(InitialBlService service) {
        this.service = service;
        //ArrayList<SaleRecordVO> saleRecordList=saleRecordBlService.getSaleRecordList(null);
        //showSaleRecordList(saleRecordList);
    }

    /**
     * 取得期初信息列表并修改ObservableList的信息
     * */
    private void showInitialList(ArrayList<InitialVO> initialList){
        initialObservableList.removeAll();

        for(int i=0;i<initialList.size();i++){
            initialObservableList.add(initialList.get(i));
        }
        initialTableView.setItems(initialObservableList);
    }

    // 界面之中会用到的方法******************************************


    @FXML
    private void handleSearch(){
    }

    @FXML
    private void establishInitial(){
        InitialVO initial=new InitialVO();
        initial.setYear(2017);

        InitialInfoUIController.init(null,initial,1,root.getStage());
    }

    @FXML
    private void checkInitial(){
        if(isInitialSelected()){
            InitialVO initial=initialTableView.getSelectionModel().getSelectedItem();
            InitialInfoUIController.init(null,initial,1,root.getStage());
        }
    }


    private boolean isInitialSelected(){
        int selectedIndex = initialTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("未选择期初账目");
            alert.setContentText("请在表中选择期初账目");
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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/initialui/InitialUI.fxml"));
            root.setCenterPane(loader.load());

            InitialUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setInitialBlService(null);

            //controller.showInitialList(list);

            root.setReturnPaneController(new FinancePanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
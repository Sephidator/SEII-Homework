package main.java.presentation.reportui;

import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import main.java.MainApp;
import main.java.businesslogicfactory.reportblfactory.SaleDetailBlFactory;
import main.java.businesslogicservice.reportblservice.SaleDetailBlService;
import main.java.exception.DataException;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.FinancePanelUIController;
import main.java.presentation.messageui.ManagerPanelUIController;
import main.java.presentation.messageui.PurchaseSalePanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.presentation.uiutility.UITool;
import main.java.vo.report.SaleDetailQueryVO;
import main.java.vo.report.SaleRecordVO;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class SaleDetailUIController extends CenterUIController {
    private SaleDetailBlService service;

    private ObservableList<SaleRecordVO> saleRecordObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<SaleRecordVO> saleRecordTableView;
    @FXML
    private TableColumn<SaleRecordVO,String> timeColumn;
    @FXML
    private TableColumn<SaleRecordVO,String> goodsNameColumn;
    @FXML
    private TableColumn<SaleRecordVO,String> goodsModelColumn;
    @FXML
    private TableColumn<SaleRecordVO,String> goodsNumberColumn;
    @FXML
    private TableColumn<SaleRecordVO,String> goodsPriceColumn;
    @FXML
    private TableColumn<SaleRecordVO,String> goodsAmountColumn;

    @FXML
    private ChoiceBox<String> conditionSelector;
    @FXML
    private JFXDatePicker start;
    @FXML
    private JFXDatePicker end;
    @FXML
    private TextField inputInfo;
    @FXML
    private Button search;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        timeColumn.setCellValueFactory(cellData->new SimpleStringProperty(new SimpleDateFormat("yyyy-MM-dd-EE").format((cellData.getValue().time))));
        goodsNameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goodsItem.goods.getName()));
        goodsModelColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goodsItem.goods.getModel()));
        goodsNumberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().goodsItem.number)));
        goodsPriceColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().goodsItem.price)));
        goodsAmountColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().goodsItem.number*cellData.getValue().goodsItem.price)));


        String[] conditionList=new String[]{"所有单据","时间","商品名","客户","业务员"};
        conditionSelector.setItems(FXCollections.observableArrayList("所有单据","时间","商品名","客户","业务员"));
        conditionSelector.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
            showInputField(conditionList[newValue.intValue()]);
        });

    }

    // 设置controller数据的方法*****************************************

    public void setSaleRecordBlService(SaleDetailBlService service) {
        this.service = service;
    }

    public void refresh(SaleDetailQueryVO query){
        try{
            ArrayList<SaleRecordVO> saleRecordList=service.getSaleRecordList(query);
            showSaleRecordList(saleRecordList);
        }catch(DataException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找销售明细失败","数据库错误");
        }catch(Exception e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找销售明细失败","RMI连接错误");
        }
    }

    private void showInputField(String condition){
        start.setValue(null);
        end.setValue(null);
        inputInfo.setText("");

        if(condition.equals("所有单据")){
            start.setVisible(false);
            end.setVisible(false);
            inputInfo.setVisible(false);
            search.setVisible(false);
            refresh(null);
        }
        else if(condition.equals("时间")){
            start.setVisible(true);
            end.setVisible(true);
            inputInfo.setVisible(false);
            search.setVisible(true);
        }
        else{
            start.setVisible(false);
            end.setVisible(false);
            inputInfo.setVisible(true);
            search.setVisible(true);
            inputInfo.setPromptText("请输入"+condition);
        }
    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showSaleRecordList(ArrayList<SaleRecordVO> saleRecordList){
        saleRecordObservableList.removeAll();
        saleRecordObservableList.setAll(saleRecordList);
        saleRecordTableView.setItems(saleRecordObservableList);
    }

    // 界面之中会用到的方法******************************************


    @FXML
    private void handleSearch(){
        if(conditionSelector.getValue().equals("时间")){
            if(isValidTime()){
                Date startTime=Date.from(start.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                Date endTime=Date.from(end.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                try {
                    endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end.getEditor().getText() + " 23:59:59");
                }catch(Exception e){}

                SaleDetailQueryVO query=new SaleDetailQueryVO(startTime,endTime,null,null,null);
                refresh(query);
            }
        }
        else if(conditionSelector.getValue().equals("商品名")){
            SaleDetailQueryVO query=new SaleDetailQueryVO(null,null,inputInfo.getText(),null,null);
            refresh(query);
        }
        else if(conditionSelector.getValue().equals("客户")){
            SaleDetailQueryVO query=new SaleDetailQueryVO(null,null,null,inputInfo.getText(),null);
            refresh(query);
        }
        else if(conditionSelector.getValue().equals("业务员")){
            SaleDetailQueryVO query=new SaleDetailQueryVO(null,null,null,null,inputInfo.getText());
            refresh(query);
        }
        else if(conditionSelector.getValue().equals("业务员")){
            SaleDetailQueryVO query=new SaleDetailQueryVO(null,null,null,null,inputInfo.getText());
            refresh(query);
        }
    }

    private boolean isValidTime(){
        String errorMessage = "";

        if (start.getValue()==null) {
            errorMessage += ("未输入起始日期。"+System.lineSeparator());
        }
        if (end.getValue()==null) {
            errorMessage+=("未输入结束日期。"+System.lineSeparator());
        }

        if(errorMessage.length()>0){
            UITool.showAlert(Alert.AlertType.ERROR, "不正确","请确认筛选条件",errorMessage);
            return false;
        }

        Date startTime=Date.from(start.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date endTime=Date.from(end.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        try {
            endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end.getEditor().getText() + " 23:59:59");
        }catch(Exception e){}

        if(endTime.before(startTime)){
            errorMessage+=("结束时间早于起始时间。"+System.lineSeparator());
        }

        if(errorMessage.length()>0){
            UITool.showAlert(Alert.AlertType.ERROR, "不正确","请确认筛选条件",errorMessage);
            return false;
        }

        return true;
    }

    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(RootUIController root, boolean isFinance){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/reportui/SaleDetailUI.fxml"));
            root.setCenterPane(loader.load());

            SaleDetailUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setSaleRecordBlService(SaleDetailBlFactory.getService());
            controller.refresh(null);

            root.setReturnPaneController(isFinance?new FinancePanelUIController():new ManagerPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

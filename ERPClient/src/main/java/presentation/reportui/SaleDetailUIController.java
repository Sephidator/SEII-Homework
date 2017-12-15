package main.java.presentation.reportui;

import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import main.java.MainApp;
import main.java.businesslogicservice.reportblservice.SaleDetailBlService;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.FinancePanelUIController;
import main.java.presentation.messageui.ManagerPanelUIController;
import main.java.presentation.messageui.PurchaseSalePanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.report.SaleDetailQueryVO;
import main.java.vo.report.SaleRecordVO;

import java.text.SimpleDateFormat;
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
        goodsNameColumn.setCellValueFactory(cellData->new SimpleStringProperty("name"));
        goodsModelColumn.setCellValueFactory(cellData->new SimpleStringProperty("model"));
        goodsNumberColumn.setCellValueFactory(cellData->new SimpleStringProperty("3"));
        goodsPriceColumn.setCellValueFactory(cellData->new SimpleStringProperty("20"));
        goodsAmountColumn.setCellValueFactory(cellData->new SimpleStringProperty("60"));


        String[] conditionList=new String[]{"所有单据","时间","商品名","客户","业务员"};
        conditionSelector.setItems(FXCollections.observableArrayList("所有单据","时间","商品名","客户","业务员"));
        conditionSelector.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
            showInputField(conditionList[newValue.intValue()]);
        });

    }

    // 设置controller数据的方法*****************************************

    public void setSaleRecordBlService(SaleDetailBlService service) {
        this.service = service;
        //ArrayList<SaleRecordVO> saleRecordList=saleRecordBlService.getSaleRecordList(null);
        //showSaleRecordList(saleRecordList);
    }

    private void showInputField(String condition){
        start.getEditor().setText("");
        end.getEditor().setText("");
        inputInfo.setText("");

        if(condition.equals("所有单据")){
            start.setVisible(false);
            end.setVisible(false);
            inputInfo.setVisible(false);
            search.setVisible(false);
        }
        else{
            if(condition.equals("时间")){
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
            }
        }
    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showSaleRecordList(ArrayList<SaleRecordVO> saleRecordList){
        saleRecordObservableList.removeAll();

        for(int i=0;i<saleRecordList.size();i++){
            saleRecordObservableList.add(saleRecordList.get(i));
        }
        saleRecordTableView.setItems(saleRecordObservableList);
    }

    // 界面之中会用到的方法******************************************


    @FXML
    private void handleSearch(){
        if(conditionSelector.getValue().equals("时间")){
            if(isValidTime()){
                try{
                    Date startTime=new SimpleDateFormat("yyyy-MM-dd").parse(start.getEditor().getText());
                    Date endTime=new SimpleDateFormat("yyyy-MM-dd").parse(start.getEditor().getText());

                    SaleDetailQueryVO query=new SaleDetailQueryVO(startTime,endTime,null,null,null);
                    //ArrayList<SaleRecordVO> saleRecordList=service.getSaleRecordList(query);
                    //showSaleRecordList(saleRecordList);
                    System.out.println("选择了时间");
                }catch(Exception e){}
            }
        }
        else if(conditionSelector.getValue().equals("商品名")){
            SaleDetailQueryVO query=new SaleDetailQueryVO(null,null,inputInfo.getText(),null,null);
            System.out.println("选择了商品名");
        }
        else if(conditionSelector.getValue().equals("客户")){
            SaleDetailQueryVO query=new SaleDetailQueryVO(null,null,null,inputInfo.getText(),null);
            System.out.println("选择了客户");
        }
        else if(conditionSelector.getValue().equals("业务员")){
            SaleDetailQueryVO query=new SaleDetailQueryVO(null,null,null,null,inputInfo.getText());
            System.out.println("选择了业务员");
        }
    }

    private boolean isValidTime(){
        String errorMessage = "";

        try{
            if (start.getEditor().getText()==null || start.getEditor().getText().length()==0) {
                errorMessage += ("未输入起始日期。"+System.lineSeparator());
            }
            if (end.getEditor().getText()==null || end.getEditor().getText().length()==0) {
                errorMessage+=("未输入结束日期。"+System.lineSeparator());
            }

            if(errorMessage.length()>0)
                throw new Exception();

            Date startTime=new SimpleDateFormat("yyyy-MM-dd").parse(start.getEditor().getText());
            Date endTime=new SimpleDateFormat("yyyy-MM-dd").parse(end.getEditor().getText());


            if(endTime.before(startTime)){
                errorMessage+=("结束时间早于起始时间。"+System.lineSeparator());
            }

            if(errorMessage.length()>0)
                throw new Exception();

            return true;
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("不正确");
            alert.setHeaderText("请确认筛选条件");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }

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
            controller.setSaleRecordBlService(null);
            controller.showInputField("所有单据");

            //controller.showSaleRecordList(list);

            root.setReturnPaneController(isFinance?new FinancePanelUIController():new ManagerPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

package main.java.presentation.reportui;

import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import main.java.MainApp;
import main.java.businesslogicservice.reportblservice.BusinessHistoryBlService;
import main.java.businesslogicservice.reportblservice.SaleDetailBlService;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.FinancePanelUIController;
import main.java.presentation.messageui.ManagerPanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.bill.BillVO;
import main.java.vo.report.BusinessHistoryQueryVO;
import main.java.vo.report.SaleDetailQueryVO;
import main.java.vo.report.SaleRecordVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BusinessHistoryUIController extends CenterUIController {
    private BusinessHistoryBlService service;

    private ObservableList<BillVO> billObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<BillVO> billTableView;
    @FXML
    private TableColumn<BillVO,String> IDColumn;
    @FXML
    private TableColumn<BillVO,String> typeColumn;
    @FXML
    private TableColumn<BillVO,String> operatorColumn;

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
    @FXML
    private Button reverse;
    @FXML
    private Button reverseAndCopy;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        IDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));
        typeColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getType()));
        operatorColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getOperator().getName()));

        String[] conditionList=new String[]{"所有单据","时间","单据类型","客户","操作员"};
        conditionSelector.setItems(FXCollections.observableArrayList("所有单据","时间","单据类型","客户","操作员"));
        conditionSelector.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
            showInputField(conditionList[newValue.intValue()]);
        });

    }

    // 设置controller数据的方法*****************************************

    public void setButtonDisable(){
        reverse.setDisable(true);
        reverseAndCopy.setDisable(true);
    }

    public void setBusinessHistoryBlService(BusinessHistoryBlService service) {
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
    private void showBillList(ArrayList<BillVO> billList){
        billObservableList.removeAll();

        for(int i=0;i<billList.size();i++){
            billObservableList.add(billList.get(i));
        }
        billTableView.setItems(billObservableList);
    }

    // 界面之中会用到的方法******************************************


    @FXML
    private void handleSearch(){
        if(conditionSelector.getValue().equals("时间")){
            if(isValidTime()){
                try{
                    Date startTime=new SimpleDateFormat("yyyy-MM-dd").parse(start.getEditor().getText());
                    Date endTime=new SimpleDateFormat("yyyy-MM-dd").parse(start.getEditor().getText());

                    BusinessHistoryQueryVO query=new BusinessHistoryQueryVO(startTime,endTime,null,null,null);
                    //ArrayList<SaleRecordVO> saleRecordList=service.getSaleRecordList(query);
                    //showSaleRecordList(saleRecordList);
                    System.out.println("选择了时间");
                }catch(Exception e){}
            }
        }
        else if(conditionSelector.getValue().equals("单据类型")){
            BusinessHistoryQueryVO query=new BusinessHistoryQueryVO(null,null,inputInfo.getText(),null,null);
            System.out.println("选择了单据类型");
        }
        else if(conditionSelector.getValue().equals("客户")){
            BusinessHistoryQueryVO query=new BusinessHistoryQueryVO(null,null,null,inputInfo.getText(),null);
            System.out.println("选择了客户");
        }
        else if(conditionSelector.getValue().equals("操作员")){
            BusinessHistoryQueryVO query=new BusinessHistoryQueryVO(null,null,null,null,inputInfo.getText());
            System.out.println("选择了业务员");
        }
    }

    @FXML
    private void  handleReverse(){

    }

    @FXML
    private void  handleReverseAndCopy(){

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

    @FXML
    private void handleCheckBill(){
        if(isBillSelected()){
            BillVO bill=billTableView.getSelectionModel().getSelectedItem();
            bill.getInfoUIController().showInfo(bill,root.getStage());
        }
    }

    private boolean isBillSelected(){
        int selectedIndex=billTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            // Nothing selected
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("未选择单据");
            alert.setContentText("请选择要编辑的单据");
            alert.showAndWait();
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(RootUIController root,boolean isFinance){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/reportui/BusinessHistoryUI.fxml"));
            root.setCenterPane(loader.load());

            BusinessHistoryUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setBusinessHistoryBlService(null);
            controller.showInputField("所有单据");
            //controller.showSaleRecordList(list);

            if(isFinance){
                root.setReturnPaneController(new FinancePanelUIController());
            }
            else{
                controller.setButtonDisable();
                root.setReturnPaneController(new ManagerPanelUIController());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

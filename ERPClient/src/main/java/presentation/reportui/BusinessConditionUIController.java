package main.java.presentation.reportui;

import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.util.Pair;
import main.java.MainApp;
import main.java.businesslogicservice.reportblservice.BusinessConditionBlService;
import main.java.businesslogicservice.reportblservice.SaleDetailBlService;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.FinancePanelUIController;
import main.java.presentation.messageui.ManagerPanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.report.SaleDetailQueryVO;
import main.java.vo.report.SaleRecordVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BusinessConditionUIController extends CenterUIController {
    private BusinessConditionBlService service;

    private ObservableList<Pair<String,Double>> businessConditionObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<Pair<String,Double>> businessConditionTableView;
    @FXML
    private TableColumn<Pair<String,Double>,String> nameColumn;
    @FXML
    private TableColumn<Pair<String,Double>,String> amountColumn;

    @FXML
    private JFXDatePicker start;
    @FXML
    private JFXDatePicker end;
    @FXML
    private Button search;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        nameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getKey()));
        amountColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getValue())));
    }

    // 设置controller数据的方法*****************************************

    public void setBusinessConditionBlService(BusinessConditionBlService service) {
        this.service = service;
        //ArrayList<SaleRecordVO> saleRecordList=saleRecordBlService.getSaleRecordList(null);
        showBusinessConditionList(null);
    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showBusinessConditionList(ArrayList<Double> amountList){
        businessConditionObservableList.removeAll();

        if(amountList==null||amountList.size()<8){
            businessConditionObservableList.add(new Pair("销售收入",0));
            businessConditionObservableList.add(new Pair("商品类收入",0));
            businessConditionObservableList.add(new Pair("折让金额",0));
            businessConditionObservableList.add(new Pair("折让后总收入",0));
            businessConditionObservableList.add(new Pair("销售成本",0));
            businessConditionObservableList.add(new Pair("商品类支出",0));
            businessConditionObservableList.add(new Pair("总支出",0));
            businessConditionObservableList.add(new Pair("利润",0));
        }
        else{
            businessConditionObservableList.add(new Pair("销售收入",amountList.get(0)));
            businessConditionObservableList.add(new Pair("商品类收入",amountList.get(1)));
            businessConditionObservableList.add(new Pair("折让金额",amountList.get(2)));
            businessConditionObservableList.add(new Pair("折让后总收入",amountList.get(3)));
            businessConditionObservableList.add(new Pair("销售成本",amountList.get(4)));
            businessConditionObservableList.add(new Pair("商品类支出",amountList.get(5)));
            businessConditionObservableList.add(new Pair("总支出",amountList.get(6)));
            businessConditionObservableList.add(new Pair("利润",amountList.get(7)));
        }

        businessConditionTableView.setItems(businessConditionObservableList);
    }

    // 界面之中会用到的方法******************************************


    @FXML
    private void handleSearch(){
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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/reportui/BusinessConditionUI.fxml"));
            root.setCenterPane(loader.load());

            BusinessConditionUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setBusinessConditionBlService(null);

            //controller.showSaleRecordList(list);

            root.setReturnPaneController(isFinance?new FinancePanelUIController():new ManagerPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

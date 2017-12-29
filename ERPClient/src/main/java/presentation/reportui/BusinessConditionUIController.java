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
import main.java.businesslogicfactory.reportblfactory.BusinessConditionBlFactory;
import main.java.businesslogicservice.reportblservice.BusinessConditionBlService;
import main.java.businesslogicservice.reportblservice.SaleDetailBlService;
import main.java.exception.DataException;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.FinancePanelUIController;
import main.java.presentation.messageui.ManagerPanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.presentation.uiutility.UITool;
import main.java.vo.report.BusinessConditionQueryVO;
import main.java.vo.report.SaleDetailQueryVO;
import main.java.vo.report.SaleRecordVO;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
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
    }

    private void refresh(BusinessConditionQueryVO query){
        try{
            ArrayList<Double> amountList=service.getCondition(query);
            showBusinessConditionList(amountList);
        }catch(DataException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找经营历程表失败","数据库错误");
        }catch(Exception e){
            e.printStackTrace();
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找经营历程表失败","RMI连接错误");
        }
    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showBusinessConditionList(ArrayList<Double> amountList){
        businessConditionObservableList.clear();

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
            Date startTime=Date.from(start.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            Date endTime=Date.from(end.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            try {
                endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end.getEditor().getText() + " 23:59:59");
            }catch(Exception e){}

            BusinessConditionQueryVO query=new BusinessConditionQueryVO(startTime,endTime);
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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/reportui/BusinessConditionUI.fxml"));
            root.setCenterPane(loader.load());

            BusinessConditionUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setBusinessConditionBlService(BusinessConditionBlFactory.getService());

            root.setReturnPaneController(isFinance?new FinancePanelUIController():new ManagerPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

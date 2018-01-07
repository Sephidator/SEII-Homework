package main.java.presentation.logui;

import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.MainApp;
import main.java.businesslogicfactory.logblfactory.LogBlFactory;
import main.java.businesslogicservice.logblservice.LogBlService;
import main.java.exception.DataException;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.FinancePanelUIController;
import main.java.presentation.messageui.ManagerPanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.presentation.uiutility.UITool;
import main.java.vo.log.LogQueryVO;
import main.java.vo.log.LogVO;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class LogUIController extends CenterUIController {
    private LogBlService logBlService;

    private ObservableList<LogVO> logObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<LogVO> logTableView;
    @FXML
    private TableColumn<LogVO,String> logTimeColumn;
    @FXML
    private TableColumn<LogVO,String> logOperatorColumn;
    @FXML
    private TableColumn<LogVO,String> logActionColumn;

    @FXML
    private JFXDatePicker start;
    @FXML
    private JFXDatePicker end;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        logTimeColumn.setCellValueFactory(cellData->new SimpleStringProperty(new SimpleDateFormat("yyyy-MM-dd-EE").format(cellData.getValue().getTime())));
        logOperatorColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getOperator().getType()+":"+cellData.getValue().getOperator().getName()));
        logActionColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getAction())));
    }

    // 设置controller数据的方法*****************************************

    public void setLogBlService(LogBlService logBlService) {
        this.logBlService = logBlService;
    }

    public void refresh(LogQueryVO query){
        try{
            ArrayList<LogVO> logList=logBlService.getLogList(query);
            showLogList(logList);
        }catch(DataException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找日志失败","数据库错误");
        }catch(Exception e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找日志失败","RMI连接错误");
        }
    }

    private void showLogList(ArrayList<LogVO> logList){
        logObservableList.clear();
        logObservableList.setAll(logList);
        logTableView.setItems(logObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleSearch(){
        if(start.getValue()==null && end.getValue()==null){
            refresh(null);
        }
        else if(start.getValue()==null || end.getValue()==null){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "时间不合法","起始或结束时间空缺。","请重新选择起始和结束时间");
            start.setValue(null);
            end.setValue(null);
        }
        else if((start.getValue().isAfter(end.getValue()))){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "时间不合法","起始时间不得晚于结束时间。","请重新选择起始和结束时间");
            start.setValue(null);
            end.setValue(null);
        }
        else{
            Date startTime=Date.from(start.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            Date endTime=Date.from(end.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            try {
                endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end.getEditor().getText() + " 23:59:59");
            }catch(Exception e){
                e.printStackTrace();
            }
            LogQueryVO query=new LogQueryVO(startTime,endTime);
            refresh(query);
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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/logui/LogUI.fxml"));
            root.setCenterPane(loader.load());

            LogUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setLogBlService(LogBlFactory.getService());
            controller.refresh(null);

            root.setReturnPaneController(isFinance?new FinancePanelUIController():new ManagerPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

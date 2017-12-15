package main.java.presentation.logui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.MainApp;
import main.java.businesslogicservice.logblservice.LogBlService;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.FinancePanelUIController;
import main.java.presentation.messageui.ManagerPanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.log.LogVO;

import java.text.SimpleDateFormat;
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

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        logTimeColumn.setCellValueFactory(cellData->new SimpleStringProperty(
                new SimpleDateFormat("yyyy-MM-dd-EE").format(cellData.getValue().getTime())));
        logOperatorColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getOperator().getType()+":"+cellData.getValue().getOperator().getName()));
        logActionColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getAction())));
    }

    // 设置controller数据的方法*****************************************

    public void setLogBlService(LogBlService logBlService) {
        this.logBlService = logBlService;
        //ArrayList<LogVO> logList=logBlService.getLogList(null);
        //showLogList(logList);
    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showLogList(ArrayList<LogVO> logList){
        logObservableList.removeAll();

        for(int i=0;i<logList.size();i++){
            logObservableList.add(logList.get(i));
        }
        logTableView.setItems(logObservableList);
    }

    // 界面之中会用到的方法******************************************

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
            controller.setLogBlService(null);

            Date date= new SimpleDateFormat("yyyy-MM-dd").parse("2017-1-12");
            System.out.println(String.valueOf(date));
            LogVO c1=new LogVO(root.getOperator(),"制定进货单",date);
            LogVO c2=new LogVO(root.getOperator(),"制定销售单",date);

            ArrayList<LogVO> list=new ArrayList<>();
            list.add(c1);
            list.add(c2);
            controller.showLogList(list);


            root.setReturnPaneController(isFinance?new FinancePanelUIController():new ManagerPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

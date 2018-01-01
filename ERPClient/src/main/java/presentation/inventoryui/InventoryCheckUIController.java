package main.java.presentation.inventoryui;

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
import main.java.businesslogicfactory.inventoryblfactory.InventoryCheckBlFactory;
import main.java.businesslogicservice.inventoryblservice.InventoryCheckBlService;
import main.java.exception.DataException;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.InventoryPanelUIController;
import main.java.presentation.uiutility.UITool;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.goods.InventoryCheckItemVO;
import main.java.vo.report.BusinessConditionQueryVO;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class InventoryCheckUIController extends CenterUIController {
    private InventoryCheckBlService service;
    private Date startTime;
    private Date endTime;

    private ObservableList<InventoryCheckItemVO> checkObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<InventoryCheckItemVO> checkTableView;
    @FXML
    private TableColumn<InventoryCheckItemVO,String> IDColumn;
    @FXML
    private TableColumn<InventoryCheckItemVO,String> nameColumn;
    @FXML
    private TableColumn<InventoryCheckItemVO,String> purchaseNumberColumn;
    @FXML
    private TableColumn<InventoryCheckItemVO,String> purchaseAmountColumn;
    @FXML
    private TableColumn<InventoryCheckItemVO,String> saleNumberColumn;
    @FXML
    private TableColumn<InventoryCheckItemVO,String> saleAmountColumn;
    @FXML
    private TableColumn<InventoryCheckItemVO,String> goodsNumberColumn;

    @FXML
    private JFXDatePicker start;
    @FXML
    private JFXDatePicker end;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        IDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getID()));
        nameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getName()));
        purchaseNumberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().purchaseNumber)));
        purchaseAmountColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().purchaseAmount)));
        saleNumberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().saleNumber)));
        saleAmountColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().saleAmount)));
        goodsNumberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().goods.getNumber())));

        try {
            String day=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(day + " 00:00:00");
            endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(day + " 23:59:59");
        }catch(Exception e){}

        start.setValue(startTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        end.setValue(endTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    // 设置controller数据的方法*****************************************

    public void setService(InventoryCheckBlService service) {
        this.service = service;
    }

    public void refresh(){
        try{
            ArrayList<InventoryCheckItemVO> checkList=service.getInventoryCheck(startTime,endTime);
            showCheckList(checkList);
        }catch(DataException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","库存查看失败","数据库错误");
        }catch(Exception e){
            e.printStackTrace();
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","库存查看失败","RMI连接错误");
        }

    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showCheckList(ArrayList<InventoryCheckItemVO> checkList){
        checkObservableList.clear();
        checkObservableList.setAll(checkList);
        checkTableView.setItems(checkObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleSearch(){
        if(isValidTime()){
            startTime=Date.from(start.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            endTime=Date.from(end.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            try {
                endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end.getEditor().getText() + " 23:59:59");
            }catch(Exception e){}

            refresh();
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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/inventoryui/InventoryCheckUI.fxml"));
            root.setCenterPane(loader.load());

            InventoryCheckUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setService(InventoryCheckBlFactory.getService());
            controller.refresh();

            root.setReturnPaneController(new InventoryPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

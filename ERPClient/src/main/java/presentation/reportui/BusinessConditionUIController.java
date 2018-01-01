package main.java.presentation.reportui;

import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
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
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BusinessConditionUIController extends CenterUIController {
    private BusinessConditionBlService service;
    private Date startTime;
    private Date endTime;

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

        try {
            String day=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(day + " 00:00:00");
            endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(day + " 23:59:59");
        }catch(Exception e){}

        start.setValue(startTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        end.setValue(endTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    // 设置controller数据的方法*****************************************

    public void setBusinessConditionBlService(BusinessConditionBlService service) {
        this.service = service;
    }

    private void refresh(){
        BusinessConditionQueryVO query=new BusinessConditionQueryVO(startTime,endTime);
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
    private void handleExport(){
        try {
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            String startDay=sdf.format(startTime);
            String endDay=sdf.format(endTime);
            String fileName="经营情况表（"+startDay+"至"+endDay+"）";

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("First Sheet");

            HSSFRow rowHead = sheet.createRow((short)0);
            rowHead.createCell(0).setCellValue("名称");
            rowHead.createCell(1).setCellValue("金额");

            for(int i=0;i<businessConditionObservableList.size();i++){
                HSSFRow row = sheet.createRow((short)(i+1));
                row.createCell(0).setCellValue(nameColumn.getCellData(i));
                row.createCell(1).setCellValue(amountColumn.getCellData(i));
            }

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName(fileName);
            File file = fileChooser.showSaveDialog(root.getStage());

            if(file!=null){
                FileOutputStream fileOut = new FileOutputStream(file);
                workbook.write(fileOut);
                fileOut.close();

                UITool.showAlert(Alert.AlertType.INFORMATION,
                        "Success","导出经营情况表成功",
                        "文件路径："+file.getAbsolutePath());
            }

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }

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
            controller.refresh();

            root.setReturnPaneController(isFinance?new FinancePanelUIController():new ManagerPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

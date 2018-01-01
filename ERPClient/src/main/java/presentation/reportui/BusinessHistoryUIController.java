package main.java.presentation.reportui;

import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import main.java.MainApp;
import main.java.businesslogic.financebl.PaymentBillBl;
import main.java.businesslogicfactory.financeblfactory.CashBillBlFactory;
import main.java.businesslogicfactory.financeblfactory.PaymentBillBlFactory;
import main.java.businesslogicfactory.financeblfactory.ReceiptBillBlFactory;
import main.java.businesslogicfactory.reportblfactory.BusinessHistoryBlFactory;
import main.java.businesslogicservice.financeblservice.PaymentBillBlService;
import main.java.businesslogicservice.reportblservice.BusinessHistoryBlService;
import main.java.businesslogicservice.reportblservice.BusinessHistoryBlService;
import main.java.exception.DataException;
import main.java.exception.FullException;
import main.java.presentation.financeui.CashBillUIController;
import main.java.presentation.financeui.PaymentBillUIController;
import main.java.presentation.financeui.ReceiptBillUIController;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.FinancePanelUIController;
import main.java.presentation.messageui.ManagerPanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.presentation.uiutility.UITool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.CashBillVO;
import main.java.vo.bill.financebill.PaymentBillVO;
import main.java.vo.bill.financebill.ReceiptBillVO;
import main.java.vo.report.BusinessHistoryQueryVO;
import main.java.vo.report.BusinessHistoryQueryVO;
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

    public void refresh(BusinessHistoryQueryVO query){
        try{
            ArrayList<BillVO> billList=service.getBillList(query);
            showBillList(billList);
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
    private void showBillList(ArrayList<BillVO> billList){
        billObservableList.clear();
        billObservableList.setAll(billList);
        billTableView.setItems(billObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleExport(){
        try {
            String fileName="经营历程表";

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("First Sheet");

            HSSFRow rowHead = sheet.createRow((short)0);
            rowHead.createCell(0).setCellValue("单据编号");
            rowHead.createCell(1).setCellValue("类型");
            rowHead.createCell(2).setCellValue("操作员");

            for(int i=0;i<billObservableList.size();i++){
                HSSFRow row = sheet.createRow((short)(i+1));
                row.createCell(0).setCellValue(IDColumn.getCellData(i));
                row.createCell(1).setCellValue(typeColumn.getCellData(i));
                row.createCell(2).setCellValue(operatorColumn.getCellData(i));
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
                        "Success","导出经营历程表成功",
                        "文件路径："+file.getAbsolutePath());
            }

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }

    @FXML
    private void handleSearch(){
        if(conditionSelector.getValue().equals("时间")){
            if(isValidTime()){
                Date startTime=Date.from(start.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                Date endTime=Date.from(end.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                try {
                    endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end.getEditor().getText() + " 23:59:59");
                }catch(Exception e){}

                BusinessHistoryQueryVO query=new BusinessHistoryQueryVO(startTime,endTime,null,null,null);
                refresh(query);
            }
        }
        else if(conditionSelector.getValue().equals("单据类型")){
            BusinessHistoryQueryVO query=new BusinessHistoryQueryVO(null,null,inputInfo.getText(),null,null);
            refresh(query);
        }
        else if(conditionSelector.getValue().equals("客户")){
            BusinessHistoryQueryVO query=new BusinessHistoryQueryVO(null,null,null,inputInfo.getText(),null);
            refresh(query);
        }
        else if(conditionSelector.getValue().equals("操作员")){
            BusinessHistoryQueryVO query=new BusinessHistoryQueryVO(null,null,null,null,inputInfo.getText());
            refresh(query);
        }
    }

    @FXML
    private void  handleReverse(){
        if(isBillSelected()){
            BillVO bill=billTableView.getSelectionModel().getSelectedItem();
            if(bill.getType().equals("收款单")){
                try {
                    String billID = service.reverseReceiptBill((ReceiptBillVO) bill, root.getOperator());
                    UITool.showAlert(Alert.AlertType.INFORMATION,
                            "Success", "红冲收款单成功", "红冲单据ID：" + billID);
                }catch(DataException e){
                    UITool.showAlert(Alert.AlertType.ERROR,
                            "Error","红冲收款单失败", "数据库错误");
                }catch(FullException e){
                    UITool.showAlert(Alert.AlertType.ERROR,
                            "Error","红冲收款单失败", "超过单日单据上限（99999张）");
                }catch(Exception e){
                    UITool.showAlert(Alert.AlertType.ERROR,
                            "Error","红冲收款单失败", "RMI连接错误");
                }
            }
            else if(bill.getType().equals("付款单")){
                try {
                    String billID = service.reversePaymentBill((PaymentBillVO) bill, root.getOperator());
                    UITool.showAlert(Alert.AlertType.INFORMATION,
                            "Success", "红冲付款单成功", "红冲单据ID：" + billID);
                }catch(DataException e){
                    UITool.showAlert(Alert.AlertType.ERROR,
                            "Error","红冲付款单失败", "数据库错误");
                }catch(FullException e){
                    UITool.showAlert(Alert.AlertType.ERROR,
                            "Error","红冲付款单失败", "超过单日单据上限（99999张）");
                }catch(Exception e){
                    UITool.showAlert(Alert.AlertType.ERROR,
                            "Error","红冲付款单失败", "RMI连接错误");
                }
            }
            else if(bill.getType().equals("现金费用单")){
                try {
                    String billID = service.reverseCashBill((CashBillVO) bill, root.getOperator());
                    UITool.showAlert(Alert.AlertType.INFORMATION,
                            "Success", "红冲现金费用单成功", "红冲单据ID：" + billID);
                }catch(DataException e){
                    UITool.showAlert(Alert.AlertType.ERROR,
                            "Error","红冲现金费用单失败", "数据库错误");
                }catch(FullException e){
                    UITool.showAlert(Alert.AlertType.ERROR,
                            "Error","红冲现金费用单失败", "超过单日单据上限（99999张）");
                }catch(Exception e){
                    UITool.showAlert(Alert.AlertType.ERROR,
                            "Error","红冲现金费用单失败", "RMI连接错误");
                }
            }
            else{
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error","红冲单据失败", "只能对财务单据进行红冲");
            }
        }
    }

    @FXML
    private void  handleReverseAndCopy(){
        if(isBillSelected()){
            BillVO bill=billTableView.getSelectionModel().getSelectedItem();
            if(bill.getType().equals("收款单")){
                handleReverse();
                ReceiptBillVO receiptBillVO=(ReceiptBillVO)bill;

                ReceiptBillVO newBill = new ReceiptBillVO();
                newBill.setOperator(root.getOperator());
                newBill.setClient(receiptBillVO.getClient());
                newBill.setTransList(receiptBillVO.getTransList());
                newBill.setComment(receiptBillVO.getComment());
                newBill.setTime(receiptBillVO.getTime());
                newBill.setTotal(receiptBillVO.getTotal());
                newBill.setState("待审批");

                ReceiptBillUIController.init(ReceiptBillBlFactory.getService(),newBill,1,root.getStage());
            }
            else if(bill.getType().equals("付款单")){
                handleReverse();
                PaymentBillVO paymentBillVO=(PaymentBillVO)bill;

                PaymentBillVO newBill = new PaymentBillVO();
                newBill.setOperator(root.getOperator());
                newBill.setClient(paymentBillVO.getClient());
                newBill.setTransList(paymentBillVO.getTransList());
                newBill.setComment(paymentBillVO.getComment());
                newBill.setTime(new Date());
                newBill.setTotal(paymentBillVO.getTotal());
                newBill.setState("待审批");

                PaymentBillUIController.init(PaymentBillBlFactory.getService(),newBill,1,root.getStage());
            }
            else if(bill.getType().equals("现金费用单")){
                handleReverse();
                CashBillVO cashBillVO=(CashBillVO)bill;

                CashBillVO newBill = new CashBillVO();
                newBill.setOperator(root.getOperator());
                newBill.setAccount(cashBillVO.getAccount());
                newBill.setItemList(cashBillVO.getItemList());
                newBill.setComment(cashBillVO.getComment());
                newBill.setTime(new Date());
                newBill.setTotal(cashBillVO.getTotal());
                newBill.setState("待审批");

                CashBillUIController.init(CashBillBlFactory.getService(),newBill,1,root.getStage());
            }
            else{
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error","红冲单据失败", "只能对财务单据进行红冲");
            }
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
            UITool.showAlert(Alert.AlertType.ERROR,
                    "No Selection","未选择单据","请选择要编辑的单据");
            return false;
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
    public static void init(RootUIController root,boolean isFinance){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/reportui/BusinessHistoryUI.fxml"));
            root.setCenterPane(loader.load());

            BusinessHistoryUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setBusinessHistoryBlService(BusinessHistoryBlFactory.getService());
            controller.refresh(null);

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

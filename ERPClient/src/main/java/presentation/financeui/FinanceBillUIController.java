package main.java.presentation.financeui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.MainApp;
import main.java.businesslogicfactory.financeblfactory.CashBillBlFactory;
import main.java.businesslogicfactory.financeblfactory.PaymentBillBlFactory;
import main.java.businesslogicfactory.financeblfactory.ReceiptBillBlFactory;
import main.java.businesslogicservice.financeblservice.CashBillBlService;
import main.java.businesslogicservice.financeblservice.PaymentBillBlService;
import main.java.businesslogicservice.financeblservice.ReceiptBillBlService;
import main.java.exception.DataException;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.FinancePanelUIController;
import main.java.presentation.uiutility.UITool;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.financebill.*;

import java.util.ArrayList;

public class FinanceBillUIController extends CenterUIController {
    private ReceiptBillBlService receiptBillBlService;
    private PaymentBillBlService paymentBillBlService;
    private CashBillBlService cashBillBlService;

    private ObservableList<FinanceBillVO> financeBillObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<FinanceBillVO> financeBillTableView;
    @FXML
    private TableColumn<FinanceBillVO,String> IDColumn;
    @FXML
    private TableColumn<FinanceBillVO,String> typeColumn;
    @FXML
    private TableColumn<FinanceBillVO,String> stateColumn;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        IDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));
        typeColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getType()));
        stateColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getState()));
    }

    // 设置controller数据的方法*****************************************

    public void setReceiptBillBlService(ReceiptBillBlService receiptBillBlService) {
        this.receiptBillBlService = receiptBillBlService;
    }

    public void setPaymentBillBlService(PaymentBillBlService paymentBillBlService) {
        this.paymentBillBlService = paymentBillBlService;
    }

    public void setCashBillBlService(CashBillBlService cashBillBlService) {
        this.cashBillBlService = cashBillBlService;
    }

    public void refresh(){
        try{
            ArrayList<FinanceBillVO> financeBillList=new ArrayList<>();
            BillQueryVO query=new BillQueryVO("草稿",null,null,"收款单",root.getOperator().getID(),null);

            query.type="收款单";
            ArrayList<ReceiptBillVO> receiptBillList1=receiptBillBlService.getReceiptBillList(query);
            query.type="付款单";
            ArrayList<PaymentBillVO> paymentBillList1=paymentBillBlService.getPaymentBillList(query);
            query.type="现金费用单";
            ArrayList<CashBillVO> cashBillList1=cashBillBlService.getCashBillList(query);

            query.state="审批不通过";
            query.type="收款单";
            ArrayList<ReceiptBillVO> receiptBillList2=receiptBillBlService.getReceiptBillList(query);
            query.type="付款单";
            ArrayList<PaymentBillVO> paymentBillList2=paymentBillBlService.getPaymentBillList(query);
            query.type="现金费用单";
            ArrayList<CashBillVO> cashBillList2=cashBillBlService.getCashBillList(query);

            financeBillList.addAll(receiptBillList1);
            financeBillList.addAll(receiptBillList2);
            financeBillList.addAll(paymentBillList1);
            financeBillList.addAll(paymentBillList2);
            financeBillList.addAll(cashBillList1);
            financeBillList.addAll(cashBillList2);

            showFinanceBillList(financeBillList);

        }catch(DataException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找单据失败","数据库错误");
        }catch(Exception e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找单据失败","RMI连接错误");
        }
    }

    private void showFinanceBillList(ArrayList<FinanceBillVO> financeBillList){
        financeBillObservableList.clear();
        financeBillObservableList.setAll(financeBillList);
        financeBillTableView.setItems(financeBillObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void addReceiptBill(){
        ReceiptBillVO bill=new ReceiptBillVO();
        bill.setOperator(root.getOperator());
        ReceiptBillUIController.init(receiptBillBlService,bill,1,root.getStage());
        refresh();
    }

    @FXML
    private void addPaymentBill(){
        PaymentBillVO bill=new PaymentBillVO();
        bill.setOperator(root.getOperator());
        PaymentBillUIController.init(paymentBillBlService,bill,1,root.getStage());
        refresh();
    }

    @FXML
    private void addCashBill(){
        CashBillVO bill=new CashBillVO();
        bill.setOperator(root.getOperator());
        CashBillUIController.init(cashBillBlService,bill,1,root.getStage());
        refresh();
    }

    @FXML
    private void editFinanceBill(){
        if(isBillSelected()){
            FinanceBillVO bill=financeBillTableView.getSelectionModel().getSelectedItem();
            if(bill.getType().equals("付款单")){
                PaymentBillVO paymentBill=(PaymentBillVO)financeBillTableView.getSelectionModel().getSelectedItem();
                PaymentBillUIController.init(paymentBillBlService,paymentBill,2,root.getStage());
            }
            else if(bill.getType().equals("收款单")){
                ReceiptBillVO receiptBill=(ReceiptBillVO)bill;
                ReceiptBillUIController.init(receiptBillBlService,receiptBill,2,root.getStage());
            }
            else if(bill.getType().equals("现金费用单")){
                CashBillVO cashBill=(CashBillVO)bill;
                CashBillUIController.init(cashBillBlService,cashBill,2,root.getStage());
            }
            refresh();
        }
    }

    private boolean isBillSelected(){
        int selectedIndex=financeBillTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            // Nothing selected
            UITool.showAlert(Alert.AlertType.ERROR,
                    "No Selection","未选择单据","请选择要编辑的单据");
            return false;
        }
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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/financeui/FinanceBillUI.fxml"));
            root.setCenterPane(loader.load());

            FinanceBillUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setReceiptBillBlService(ReceiptBillBlFactory.getService());
            controller.setPaymentBillBlService(PaymentBillBlFactory.getService());
            controller.setCashBillBlService(CashBillBlFactory.getService());
            controller.refresh();

            root.setReturnPaneController(new FinancePanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

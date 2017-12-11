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
import main.java.businesslogicservice.financeblservice.CashBillBlService;
import main.java.businesslogicservice.financeblservice.PaymentBillBlService;
import main.java.businesslogicservice.financeblservice.ReceiptBillBlService;
import main.java.businesslogicservice.purchaseblservice.PurchaseRefundBillBlService;
import main.java.businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.FinancePanelUIController;
import main.java.presentation.messageui.PurchaseSalePanelUIController;
import main.java.presentation.purchaseui.PurchaseRefundBillUIController;
import main.java.presentation.purchaseui.PurchaseTradeBillUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.financebill.FinanceBillVO;
import main.java.vo.bill.financebill.PaymentBillVO;
import main.java.vo.bill.financebill.ReceiptBillVO;
import main.java.vo.bill.financebill.TransItemVO;
import main.java.vo.bill.purchasebill.PurchaseBillVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.GoodsVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    private void showFinanceBillList(ArrayList<FinanceBillVO> financeBillList){
        financeBillObservableList.removeAll();

        for(int i=0;i<financeBillList.size();i++){
            financeBillObservableList.add(financeBillList.get(i));
        }
        financeBillTableView.setItems(financeBillObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void addReceiptBill(){
        /*
        PurchaseTradeBillVO bill=new PurchaseTradeBillVO();
        bill.setOperator(root.getOperator());
        bill.setPurchaseList(new ArrayList<>());
        bill.setType("收款单");
        PurchaseTradeBillUIController.init(null,bill,1,root.getStage());
        */
    }

    @FXML
    private void addPaymentBill(){
        System.out.println("不是null");
        PurchaseRefundBillVO bill=new PurchaseRefundBillVO();
        bill.setOperator(root.getOperator());
        bill.setPurchaseList(new ArrayList<>());
        bill.setType("付款单");
        PurchaseRefundBillUIController.init(null,bill,1,root.getStage());
    }

    @FXML
    private void addCashBill(){
        /*
        System.out.println("不是null");
        PurchaseRefundBillVO bill=new PurchaseRefundBillVO();
        bill.setOperator(root.getOperator());
        bill.setPurchaseList(new ArrayList<>());
        bill.setType("付款单");
        PurchaseRefundBillUIController.init(null,bill,1,root.getStage());
        */
    }

    @FXML
    private void editFinanceBill(){
        /*
        if(isBillSelected()){
            if(purchaseBillTableView.getSelectionModel().getSelectedItem().getType().equals("进货单")){
                PurchaseTradeBillVO bill=(PurchaseTradeBillVO) purchaseBillTableView.getSelectionModel().getSelectedItem();
                PurchaseTradeBillUIController.init(null,bill,2,root.getStage());
            }
            else if(purchaseBillTableView.getSelectionModel().getSelectedItem().getType().equals("进货退货单")){
                PurchaseRefundBillVO bill=(PurchaseRefundBillVO) purchaseBillTableView.getSelectionModel().getSelectedItem();
                PurchaseRefundBillUIController.init(null,bill,2,root.getStage());
            }
        }
        */
    }

    private boolean isBillSelected(){
        int selectedIndex=financeBillTableView.getSelectionModel().getSelectedIndex();
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
            controller.setReceiptBillBlService(null);
            controller.setPaymentBillBlService(null);
            controller.setCashBillBlService(null);

            Date date= new SimpleDateFormat("yyyy-MM-dd").parse("2017-1-12");
            ClientVO client=new ClientVO("类别：经销商", 4, "名字：陈骁2",
                    "电话：123", "地址：南京大学", "邮编123", "邮件：123",
                    0, 0, 20, null);
            AccountVO account = new AccountVO("12344","账户A",10000);

            TransItemVO item=new TransItemVO(account,3,"无备注");
            ArrayList<TransItemVO> list=new ArrayList<>();
            list.add(item);


            ReceiptBillVO bill1=new ReceiptBillVO("草稿",date,root.getOperator(),"备注", 100,client, list);
            PaymentBillVO bill2=new PaymentBillVO("审批不通过",date,root.getOperator(),"备注", 100, client, list);
            bill1.setID("123");
            bill2.setID("233");
            ArrayList<FinanceBillVO> plist=new ArrayList<>();
            plist.add(bill1);
            plist.add(bill2);
            controller.showFinanceBillList(plist);

            root.setReturnPaneController(new FinancePanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

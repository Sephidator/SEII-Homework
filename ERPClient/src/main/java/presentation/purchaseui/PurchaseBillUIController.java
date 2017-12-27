package main.java.presentation.purchaseui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.MainApp;
import main.java.businesslogicfactory.purchaseblfactory.PurchaseRefundBillBlFactory;
import main.java.businesslogicfactory.purchaseblfactory.PurchaseTradeBillBlFactory;
import main.java.businesslogicservice.purchaseblservice.PurchaseRefundBillBlService;
import main.java.businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;
import main.java.exception.DataException;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.PurchaseSalePanelUIController;
import main.java.presentation.uiutility.UITool;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseBillVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;

import java.util.ArrayList;

public class PurchaseBillUIController extends CenterUIController {
    private PurchaseTradeBillBlService purchaseTradeBillBlService;
    private PurchaseRefundBillBlService purchaseRefundBillBlService;

    private ObservableList<PurchaseBillVO> purchaseBillObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<PurchaseBillVO> purchaseBillTableView;
    @FXML
    private TableColumn<PurchaseBillVO,String> IDColumn;
    @FXML
    private TableColumn<PurchaseBillVO,String> typeColumn;
    @FXML
    private TableColumn<PurchaseBillVO,String> stateColumn;

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

    public void setPurchaseTradeBillBlService(PurchaseTradeBillBlService purchaseTradeBillBlService) {
        this.purchaseTradeBillBlService = purchaseTradeBillBlService;
    }

    public void setPurchaseRefundBillBlService(PurchaseRefundBillBlService purchaseRefundBillBlService) {
        this.purchaseRefundBillBlService = purchaseRefundBillBlService;
    }

    public void refresh(){
        try{
            ArrayList<PurchaseBillVO> purchaseBillList=new ArrayList<>();
            BillQueryVO query=new BillQueryVO("草稿",null,null,"进货单",root.getOperator().getID(),null);

            query.type="进货单";
            ArrayList<PurchaseTradeBillVO> purchaseTradeBillList1=purchaseTradeBillBlService.getPurchaseTradeBillList(query);
            query.type="进货退货单";
            ArrayList<PurchaseRefundBillVO> purchaseRefundBillList1=purchaseRefundBillBlService.getPurchaseRefundBillList(query);

            query.state="审批不通过";
            query.type="进货单";
            ArrayList<PurchaseTradeBillVO> purchaseTradeBillList2=purchaseTradeBillBlService.getPurchaseTradeBillList(query);
            query.type="进货退货单";
            ArrayList<PurchaseRefundBillVO> purchaseRefundBillList2=purchaseRefundBillBlService.getPurchaseRefundBillList(query);

            purchaseBillList.addAll(purchaseTradeBillList1);
            purchaseBillList.addAll(purchaseRefundBillList1);
            purchaseBillList.addAll(purchaseTradeBillList2);
            purchaseBillList.addAll(purchaseRefundBillList2);

            showPurchaseBillList(purchaseBillList);

        }catch(DataException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找单据失败","数据库错误");
        }catch(Exception e){
            e.printStackTrace();
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找单据失败","RMI连接错误");
        }
    }

    private void showPurchaseBillList(ArrayList<PurchaseBillVO> purchaseBillList){
        purchaseBillObservableList.removeAll();
        purchaseBillObservableList.setAll(purchaseBillList);
        purchaseBillTableView.setItems(purchaseBillObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void addPurchaseTradeBill(){
        PurchaseTradeBillVO bill=new PurchaseTradeBillVO();
        bill.setOperator(root.getOperator());
        PurchaseTradeBillUIController.init(purchaseTradeBillBlService,bill,1,root.getStage());
        refresh();
    }

    @FXML
    private void addPurchaseRefundBill(){
        PurchaseRefundBillVO bill=new PurchaseRefundBillVO();
        bill.setOperator(root.getOperator());
        PurchaseRefundBillUIController.init(purchaseRefundBillBlService,bill,1,root.getStage());
        refresh();
    }

    @FXML
    private void editPurchaseBill(){
        if(isBillSelected()){
            PurchaseBillVO bill=purchaseBillTableView.getSelectionModel().getSelectedItem();
            if(bill.getType().equals("进货单")){
                PurchaseTradeBillVO purchaseTradeBill=(PurchaseTradeBillVO) bill;
                PurchaseTradeBillUIController.init(purchaseTradeBillBlService,purchaseTradeBill,2,root.getStage());
            }
            else if(bill.getType().equals("进货退货单")){
                PurchaseRefundBillVO purchaseRefundBill=(PurchaseRefundBillVO) bill;
                PurchaseRefundBillUIController.init(purchaseRefundBillBlService,purchaseRefundBill,2,root.getStage());
            }
            refresh();
        }
    }

    private boolean isBillSelected(){
        int selectedIndex=purchaseBillTableView.getSelectionModel().getSelectedIndex();
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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/purchaseui/PurchaseBillUI.fxml"));
            root.setCenterPane(loader.load());

            PurchaseBillUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setPurchaseTradeBillBlService(PurchaseTradeBillBlFactory.getService());
            controller.setPurchaseRefundBillBlService(PurchaseRefundBillBlFactory.getService());
            controller.refresh();

            root.setReturnPaneController(new PurchaseSalePanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

package main.java.presentation.saleui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.MainApp;
import main.java.businesslogicfactory.saleblfactory.SaleRefundBillBlFactory;
import main.java.businesslogicfactory.saleblfactory.SaleTradeBillBlFactory;
import main.java.businesslogicservice.saleblservice.SaleRefundBillBlService;
import main.java.businesslogicservice.saleblservice.SaleTradeBillBlService;
import main.java.exception.DataException;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.PurchaseSalePanelUIController;
import main.java.presentation.uiutility.UITool;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.salebill.SaleBillVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;

import java.util.ArrayList;

public class SaleBillUIController extends CenterUIController {
    private SaleTradeBillBlService saleTradeBillBlService;
    private SaleRefundBillBlService saleRefundBillBlService;

    private ObservableList<SaleBillVO> saleBillObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<SaleBillVO> saleBillTableView;
    @FXML
    private TableColumn<SaleBillVO,String> IDColumn;
    @FXML
    private TableColumn<SaleBillVO,String> typeColumn;
    @FXML
    private TableColumn<SaleBillVO,String> stateColumn;

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

    public void setSaleTradeBillBlService(SaleTradeBillBlService saleTradeBillBlService) {
        this.saleTradeBillBlService = saleTradeBillBlService;
    }

    public void setSaleRefundBillBlService(SaleRefundBillBlService saleRefundBillBlService) {
        this.saleRefundBillBlService = saleRefundBillBlService;
    }

    public void refresh(){
        try{
            ArrayList<SaleBillVO> saleBillList=new ArrayList<>();
            BillQueryVO query=new BillQueryVO("草稿",null,null,"销售单",root.getOperator().getID(),null);

            query.type="销售单";
            ArrayList<SaleTradeBillVO> saleTradeBillList1=saleTradeBillBlService.getSaleTradeBillList(query);
            query.type="销售退货单";
            ArrayList<SaleRefundBillVO> saleRefundBillList1=saleRefundBillBlService.getSaleRefundBillList(query);

            query.state="审批不通过";
            query.type="销售单";
            ArrayList<SaleTradeBillVO> saleTradeBillList2=saleTradeBillBlService.getSaleTradeBillList(query);
            query.type="销售退货单";
            ArrayList<SaleRefundBillVO> saleRefundBillList2=saleRefundBillBlService.getSaleRefundBillList(query);

            saleBillList.addAll(saleTradeBillList1);
            saleBillList.addAll(saleRefundBillList1);
            saleBillList.addAll(saleTradeBillList2);
            saleBillList.addAll(saleRefundBillList2);

            showSaleBillList(saleBillList);

        }catch(DataException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找单据失败","数据库错误");
        }catch(Exception e){
            e.printStackTrace();
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找单据失败","RMI连接错误");
        }
    }


    private void showSaleBillList(ArrayList<SaleBillVO> saleBillList){
        saleBillObservableList.removeAll();
        saleBillObservableList.setAll(saleBillList);
        saleBillTableView.setItems(saleBillObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void addSaleTradeBill(){
        SaleTradeBillVO bill=new SaleTradeBillVO();
        bill.setOperator(root.getOperator());
        SaleTradeBillUIController.init(saleTradeBillBlService,bill,1,root.getStage());
        refresh();
    }

    @FXML
    private void addSaleRefundBill(){
        SaleRefundBillVO bill=new SaleRefundBillVO();
        bill.setOperator(root.getOperator());
        SaleRefundBillUIController.init(saleRefundBillBlService,bill,1,root.getStage());
        refresh();
    }

    @FXML
    private void editSaleBill(){
        if(isBillSelected()){
            SaleBillVO bill=saleBillTableView.getSelectionModel().getSelectedItem();
            if(bill.getType().equals("销售单")){
                SaleTradeBillVO purchaseTradeBill=(SaleTradeBillVO) bill;
                SaleTradeBillUIController.init(saleTradeBillBlService,purchaseTradeBill,2,root.getStage());
            }
            else if(bill.getType().equals("销售退货单")){
                SaleRefundBillVO purchaseRefundBill=(SaleRefundBillVO) bill;
                SaleRefundBillUIController.init(saleRefundBillBlService,purchaseRefundBill,2,root.getStage());
            }
            refresh();
        }
    }

    private boolean isBillSelected(){
        int selectedIndex=saleBillTableView.getSelectionModel().getSelectedIndex();
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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/saleui/SaleBillUI.fxml"));
            root.setCenterPane(loader.load());

            SaleBillUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setSaleTradeBillBlService(SaleTradeBillBlFactory.getService());
            controller.setSaleRefundBillBlService(SaleRefundBillBlFactory.getService());
            controller.refresh();

            root.setReturnPaneController(new PurchaseSalePanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

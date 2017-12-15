package main.java.presentation.approvalui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import main.java.MainApp;
import main.java.businesslogicservice.approvalblservice.ApprovalBlService;
import main.java.businesslogicservice.reportblservice.BusinessHistoryBlService;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.ManagerPanelUIController;
import main.java.presentation.uiutility.ApprovalRejectUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;

import java.util.ArrayList;

public class ApprovalUIController extends CenterUIController {
    private ApprovalBlService service;

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
    private ChoiceBox<String> typeSelector;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        billTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        IDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));
        typeColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getType()));
        operatorColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getOperator().getName()));


        String[] typeList=new String[]{"所有单据","库存类单据","进货类单据","销售类单据","财务类单据"};
        typeSelector.setItems(FXCollections.observableArrayList("所有单据","库存类单据","进货类单据","销售类单据","财务类单据"));
        typeSelector.getSelectionModel().selectedIndexProperty().addListener((ov, oldValue, newValue)->{
            getBillList(typeList[newValue.intValue()]);
        });
    }

    // 设置controller数据的方法*****************************************

    public void setApprovalBlService(ApprovalBlService service) {
        this.service = service;
        //showBillList(service.getBillList(null));
    }

    private void getBillList(String type){

        if(type.equals("所有单据")){
        }
        else if(type.equals("库存类单据")){

        }
        else if(type.equals("进货类单据")){

        }
        else if(type.equals("销售类单据")){

        }
        else if(type.equals("财务类单据")){

        }
    }


    /**
     * 取得客户列表并修改ObservableList的信息
     **/
    private void showBillList(ArrayList<BillVO> billList){
        billTableView.getItems().clear();
        billObservableList.setAll(billList);
        billTableView.setItems(billObservableList);
    }


    // 界面之中会用到的方法******************************************

    @FXML
    private void  handlePass(){
        if(isBillSelected()){
            for(BillVO bill:billTableView.getSelectionModel().getSelectedItems()){
                //bill.getTool().pass(bill);
            }
        }
    }

    @FXML
    private void  handleReject(){
        if(isBillSelected()){
            if(billTableView.getSelectionModel().getSelectedItems().size()>1){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Selection");
                alert.setHeaderText("请重新选择");
                alert.setContentText("只能选择一张单据");
                alert.showAndWait();
            }
            else{
                BillVO bill=billTableView.getSelectionModel().getSelectedItem();
                ApprovalRejectUIController.init(service,bill, root.getOperator(),root.getStage());
            }
        }
    }

    @FXML
    private void handleCheckBill(){
        if(isBillSelected()){
            if(billTableView.getSelectionModel().getSelectedItems().size()>1){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Selection");
                alert.setHeaderText("请重新选择");
                alert.setContentText("只能选择一张单据");
                alert.showAndWait();
            }
            else{
                BillVO bill=billTableView.getSelectionModel().getSelectedItem();
                bill.getInfoUIController().showInfo(bill,root.getStage());
            }
        }
    }

    private boolean isBillSelected(){
        int selectedIndex=billTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            // Nothing selected
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("未选择单据");
            alert.setContentText("请选择要查看的单据");
            alert.showAndWait();
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/approvalui/ApprovalUI.fxml"));
            root.setCenterPane(loader.load());

            ApprovalUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setApprovalBlService(null);


            PurchaseTradeBillVO bill1=new PurchaseTradeBillVO();
            bill1.setID("123");
            bill1.setOperator(root.getOperator());

            InventoryGiftBillVO bill2=new InventoryGiftBillVO();
            bill2.setID("233");
            bill2.setOperator(root.getOperator());

            PurchaseTradeBillVO bill3=new PurchaseTradeBillVO();
            bill3.setID("123");
            bill3.setOperator(root.getOperator());

            InventoryGiftBillVO bill4=new InventoryGiftBillVO();
            bill4.setID("233");
            bill4.setOperator(root.getOperator());

            ArrayList<BillVO> list=new ArrayList<>();
            list.add(bill1);
            list.add(bill2);
            list.add(bill3);
            list.add(bill4);

            controller.showBillList(list);

            root.setReturnPaneController(new ManagerPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

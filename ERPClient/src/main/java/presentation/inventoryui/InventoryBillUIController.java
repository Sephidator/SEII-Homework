package main.java.presentation.inventoryui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.MainApp;
import main.java.businesslogicfactory.inventoryblfactory.InventoryGiftBillBlFactory;
import main.java.businesslogicfactory.inventoryblfactory.InventoryLossOverBillBlFactory;
import main.java.businesslogicservice.inventoryblservice.InventoryGiftBillBlService;
import main.java.businesslogicservice.inventoryblservice.InventoryLossOverBillBlService;
import main.java.exception.DataException;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.InventoryPanelUIController;
import main.java.presentation.uiutility.UITool;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryBillVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;
import main.java.vo.bill.inventorybill.InventoryLossOverBillVO;

import java.util.ArrayList;

public class InventoryBillUIController extends CenterUIController {
    private InventoryLossOverBillBlService inventoryLossOverBillBlService;
    private InventoryGiftBillBlService inventoryGiftBillBlService;

    private ObservableList<InventoryBillVO> inventoryBillObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<InventoryBillVO> inventoryBillTableView;
    @FXML
    private TableColumn<InventoryBillVO,String> IDColumn;
    @FXML
    private TableColumn<InventoryBillVO,String> typeColumn;
    @FXML
    private TableColumn<InventoryBillVO,String> stateColumn;

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

    public void setInventoryLossOverBillBlService(InventoryLossOverBillBlService inventoryLossOverBillBlService) {
        this.inventoryLossOverBillBlService=inventoryLossOverBillBlService;
    }

    public void setInventoryGiftBillBlService(InventoryGiftBillBlService inventoryGiftBillBlService) {
        this.inventoryGiftBillBlService=inventoryGiftBillBlService;
    }

    public void refresh(){
        try{
            ArrayList<InventoryBillVO> inventoryBillList=new ArrayList<>();
            BillQueryVO query=new BillQueryVO("草稿",null,null,"库存溢损单",root.getOperator().getID(),null);

            query.type="库存溢损单";
            ArrayList<InventoryLossOverBillVO> lossOverBillList1=inventoryLossOverBillBlService.getInventoryLossOverBillList(query);
            query.type="库存赠送单";
            ArrayList<InventoryGiftBillVO> giftBillList1=inventoryGiftBillBlService.getInventoryGiftBillList(query);

            query.state="审批不通过";
            query.type="库存溢损单";
            ArrayList<InventoryLossOverBillVO> lossOverBillList2=inventoryLossOverBillBlService.getInventoryLossOverBillList(query);
            query.type="库存赠送单";
            ArrayList<InventoryGiftBillVO> giftBillList2=inventoryGiftBillBlService.getInventoryGiftBillList(query);

            inventoryBillList.addAll(lossOverBillList1);
            inventoryBillList.addAll(lossOverBillList2);
            inventoryBillList.addAll(giftBillList1);
            inventoryBillList.addAll(giftBillList2);

            showInventoryBillList(inventoryBillList);
        }catch(DataException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找单据失败","数据库错误");
        }catch(Exception e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找单据失败","RMI连接错误");
        }
    }

    private void showInventoryBillList(ArrayList<InventoryBillVO> inventoryBillList){
        inventoryBillObservableList.removeAll();
        inventoryBillObservableList.setAll(inventoryBillList);
        inventoryBillTableView.setItems(inventoryBillObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void addInventoryLossOverBill(){
        InventoryLossOverBillVO bill=new InventoryLossOverBillVO();
        bill.setOperator(root.getOperator());
        InventoryLossOverBillUIController.init(inventoryLossOverBillBlService,bill,1,root.getStage());
        refresh();
    }

    @FXML
    private void addInventoryGiftBill(){
        InventoryGiftBillVO bill=new InventoryGiftBillVO();
        bill.setOperator(root.getOperator());
        InventoryGiftBillUIController.init(inventoryGiftBillBlService,bill,1,root.getStage());
        refresh();
    }

    @FXML
    private void editInventoryBill(){
        if(isBillSelected()){
            InventoryBillVO bill=inventoryBillTableView.getSelectionModel().getSelectedItem();
            if(bill.getType().equals("库存溢损单")){
                InventoryLossOverBillVO lossOverBill=(InventoryLossOverBillVO) inventoryBillTableView.getSelectionModel().getSelectedItem();
                InventoryLossOverBillUIController.init(inventoryLossOverBillBlService,lossOverBill,2,root.getStage());
            }
            else if(inventoryBillTableView.getSelectionModel().getSelectedItem().getType().equals("库存赠送单")){
                InventoryGiftBillVO giftBill=(InventoryGiftBillVO) inventoryBillTableView.getSelectionModel().getSelectedItem();
                InventoryGiftBillUIController.init(inventoryGiftBillBlService,giftBill,2,root.getStage());
            }
            refresh();
        }
    }

    private boolean isBillSelected(){
        int selectedIndex=inventoryBillTableView.getSelectionModel().getSelectedIndex();
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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/inventoryui/InventoryBillUI.fxml"));
            root.setCenterPane(loader.load());

            InventoryBillUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setInventoryLossOverBillBlService(InventoryLossOverBillBlFactory.getService());
            controller.setInventoryGiftBillBlService(InventoryGiftBillBlFactory.getService());
            controller.refresh();

            root.setReturnPaneController(new InventoryPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

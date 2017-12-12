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
import main.java.businesslogicservice.inventoryblservice.InventoryGiftBillBlService;
import main.java.businesslogicservice.inventoryblservice.InventoryLossOverBillBlService;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.InventoryPanelUIController;
import main.java.presentation.uiutility.CenterUIController;
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

    private void showInventoryBillList(ArrayList<InventoryBillVO> inventoryBillList){
        inventoryBillObservableList.removeAll();

        for(int i=0;i<inventoryBillList.size();i++){
            inventoryBillObservableList.add(inventoryBillList.get(i));
        }
        inventoryBillTableView.setItems(inventoryBillObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void addInventoryLossOverBill(){
        InventoryLossOverBillVO bill=new InventoryLossOverBillVO();
        bill.setOperator(root.getOperator());
        InventoryLossOverBillUIController.init(null,bill,1,root.getStage());
    }

    @FXML
    private void addInventoryGiftBill(){
        InventoryGiftBillVO bill=new InventoryGiftBillVO();
        bill.setOperator(root.getOperator());
        System.out.println(bill.getGiftList()==null);
        InventoryGiftBillUIController.init(null,bill,1,root.getStage());
    }

    @FXML
    private void editInventoryBill(){
        /*
        if(isBillSelected()){
            if(inventoryBillTableView.getSelectionModel().getSelectedItem().getType().equals("进货单")){
                InventoryLossOverBillVO bill=(InventoryLossOverBillVO) inventoryBillTableView.getSelectionModel().getSelectedItem();
                InventoryLossOverBillUIController.init(null,bill,2,root.getStage());
            }
            else if(inventoryBillTableView.getSelectionModel().getSelectedItem().getType().equals("进货退货单")){
                InventoryGiftBillVO bill=(InventoryGiftBillVO) inventoryBillTableView.getSelectionModel().getSelectedItem();
                InventoryGiftBillUIController.init(null,bill,2,root.getStage());
            }
        }
        */
    }

    private boolean isBillSelected(){
        int selectedIndex=inventoryBillTableView.getSelectionModel().getSelectedIndex();
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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/inventoryui/InventoryBillUI.fxml"));
            root.setCenterPane(loader.load());

            InventoryBillUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setInventoryLossOverBillBlService(null);
            controller.setInventoryGiftBillBlService(null);


            InventoryLossOverBillVO bill1=new InventoryLossOverBillVO();
            InventoryGiftBillVO bill2=new InventoryGiftBillVO();

            bill1.setID("123");
            bill2.setID("123");
            ArrayList<InventoryBillVO> plist=new ArrayList<>();
            plist.add(bill1);
            plist.add(bill2);
            controller.showInventoryBillList(plist);

            root.setReturnPaneController(new InventoryPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

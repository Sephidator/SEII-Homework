package main.java.presentation.purchaseui;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.MainApp;
import main.java.businesslogicservice.purchaseblservice.PurchaseRefundBillBlService;
import main.java.businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.PurchaseSalePanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.bill.purchasebill.PurchaseBillVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.GoodsVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    private void showPurchaseBillList(ArrayList<PurchaseBillVO> purchaseBillList){
        purchaseBillObservableList.removeAll();

        for(int i=0;i<purchaseBillList.size();i++){
            purchaseBillObservableList.add(purchaseBillList.get(i));
        }
        purchaseBillTableView.setItems(purchaseBillObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void addPurchaseTradeBill(){
        PurchaseTradeBillVO bill=new PurchaseTradeBillVO();
        bill.setOperator(root.getOperator());
        bill.setPurchaseList(new ArrayList<>());
        bill.setType("进货单");
        PurchaseTradeBillUIController.init(null,bill,1,root.getStage());
    }

    @FXML
    private void addPurchaseRefundBill(){
        System.out.println("不是null");
        PurchaseRefundBillVO bill=new PurchaseRefundBillVO();
        bill.setOperator(root.getOperator());
        bill.setPurchaseList(new ArrayList<>());
        bill.setType("进货退货单");
        PurchaseRefundBillUIController.init(null,bill,1,root.getStage());
    }

    @FXML
    private void editPurchaseBill(){
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
    }

    private boolean isBillSelected(){
        int selectedIndex=purchaseBillTableView.getSelectionModel().getSelectedIndex();
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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/purchaseui/PurchaseBillUI.fxml"));
            root.setCenterPane(loader.load());

            PurchaseBillUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setPurchaseTradeBillBlService(null);
            controller.setPurchaseRefundBillBlService(null);

            Date date= new SimpleDateFormat("yyyy-MM-dd").parse("2017-1-12");
            ClientVO client=new ClientVO("类别：经销商", 4, "名字：陈骁2",
                    "电话：123", "地址：南京大学", "邮编123", "邮件：123",
                    0, 0, 20, null);
            GoodsVO g=new GoodsVO();
            g.setID("123");
            g.setName("电灯");
            g.setModel("大号");
            g.setCost(5);
            g.setComment("备注");
            GoodsItemVO item=new GoodsItemVO(g,3);
            ArrayList<GoodsItemVO> list=new ArrayList<>();
            list.add(item);
            PurchaseTradeBillVO bill1=new PurchaseTradeBillVO("草稿",date,root.getOperator(),"备注", client, list,100);
            PurchaseRefundBillVO bill2=new PurchaseRefundBillVO("审批不通过",date,root.getOperator(),"备注", client, list,100);
            bill1.setID("123");
            bill2.setID("123");
            ArrayList<PurchaseBillVO> plist=new ArrayList<>();
            plist.add(bill1);
            plist.add(bill2);
            controller.showPurchaseBillList(plist);

            root.setReturnPaneController(new PurchaseSalePanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

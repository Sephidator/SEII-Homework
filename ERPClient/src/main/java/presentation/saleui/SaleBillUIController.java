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
import main.java.businesslogicservice.saleblservice.SaleRefundBillBlService;
import main.java.businesslogicservice.saleblservice.SaleTradeBillBlService;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.PurchaseSalePanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.bill.salebill.SaleBillVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.GoodsVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    private void showSaleBillList(ArrayList<SaleBillVO> saleBillList){
        saleBillObservableList.removeAll();

        for(int i=0;i<saleBillList.size();i++){
            saleBillObservableList.add(saleBillList.get(i));
        }
        saleBillTableView.setItems(saleBillObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void addSaleTradeBill(){
        SaleTradeBillVO bill=new SaleTradeBillVO();
        bill.setOperator(root.getOperator());
        bill.setSaleList(new ArrayList<>());
        bill.setType("销售单");
        SaleTradeBillUIController.init(null,bill,1,root.getStage());
    }

    @FXML
    private void addSaleRefundBill(){
        SaleRefundBillVO bill=new SaleRefundBillVO();
        bill.setOperator(root.getOperator());
        bill.setSaleList(new ArrayList<>());
        bill.setType("销售退货单");
        SaleRefundBillUIController.init(null,bill,1,root.getStage());
    }

    @FXML
    private void editSaleBill(){
        /*
        if(isBillSelected()){
            if(saleBillTableView.getSelectionModel().getSelectedItem().getType().equals("进货单")){
                SaleTradeBillVO bill=(SaleTradeBillVO) saleBillTableView.getSelectionModel().getSelectedItem();
                SaleTradeBillUIController.init(null,bill,2,root.getStage());
            }
            else if(saleBillTableView.getSelectionModel().getSelectedItem().getType().equals("进货退货单")){
                SaleRefundBillVO bill=(SaleRefundBillVO) saleBillTableView.getSelectionModel().getSelectedItem();
                SaleRefundBillUIController.init(null,bill,2,root.getStage());
            }
        }
        */
    }

    private boolean isBillSelected(){
        int selectedIndex=saleBillTableView.getSelectionModel().getSelectedIndex();
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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/saleui/SaleBillUI.fxml"));
            root.setCenterPane(loader.load());

            SaleBillUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setSaleTradeBillBlService(null);
            controller.setSaleRefundBillBlService(null);

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
            GoodsItemVO item=new GoodsItemVO(g,3,g.getCost());
            ArrayList<GoodsItemVO> list=new ArrayList<>();
            list.add(item);

            SaleTradeBillVO bill1=new SaleTradeBillVO("草稿",date,root.getOperator(),"备注", client,root.getOperator(), list,null, 100,100,100,100);
            SaleRefundBillVO bill2=new SaleRefundBillVO("审批不通过",date,root.getOperator(),"备注", client, root.getOperator(), list, 100);
            bill1.setID("123");
            bill2.setID("123");
            ArrayList<SaleBillVO> plist=new ArrayList<>();
            plist.add(bill1);
            plist.add(bill2);
            controller.showSaleBillList(plist);

            root.setReturnPaneController(new PurchaseSalePanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

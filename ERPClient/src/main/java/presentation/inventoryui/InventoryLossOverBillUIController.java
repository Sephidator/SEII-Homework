package main.java.presentation.inventoryui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.businesslogicservice.inventoryblservice.InventoryLossOverBillBlService;
import main.java.presentation.uiutility.AddGoodsUIController;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.inventorybill.InventoryLossOverBillVO;
import main.java.vo.bill.inventorybill.LossOverItemVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class InventoryLossOverBillUIController extends InfoUIController {
    private InventoryLossOverBillBlService service;
    private InventoryLossOverBillVO bill;
    private ArrayList<GoodsVO> goodsList;
    private ArrayList<LossOverItemVO> lossOverItemList;

    private ObservableList<LossOverItemVO> lossOverItemObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<LossOverItemVO> lossOverItemTableView;
    @FXML
    private TableColumn<LossOverItemVO,String> IDColumn;
    @FXML
    private TableColumn<LossOverItemVO,String> nameColumn;
    @FXML
    private TableColumn<LossOverItemVO,String> modelColumn;
    @FXML
    private TableColumn<LossOverItemVO,String> priceColumn;
    @FXML
    private TableColumn<LossOverItemVO,String> goodsNumberColumn;
    @FXML
    private TableColumn<LossOverItemVO,String> actualNumColumn;
    @FXML
    private TextField ID; // 单据编号
    @FXML
    private TextField type; // 单据类型
    @FXML
    private TextField operator; //操作员
    @FXML
    private TextArea comment; // 备注
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;


    // 加载文件后调用的方法******************************************

    public void initialize(){
        IDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getID()));
        nameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getName()));
        modelColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().goods.getModel())));
        goodsNumberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().goodsNumber)));
        priceColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().price)));
        actualNumColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().actualNumber)));
    }

    // 设置controller数据的方法*****************************************

    public void setBill(InventoryLossOverBillVO bill) {
        this.bill = bill;
        ID.setText(bill.getID());
        type.setText(bill.getType());
        operator.setText(bill.getOperator()==null?"":(bill.getOperator().getID()+":"+bill.getOperator().getName()));
        comment.setText(bill.getComment());
    }

    public void setService(InventoryLossOverBillBlService service) {
        this.service=service;
    }

    public void setGoodsList(ArrayList<GoodsVO> goodsList) {
        this.goodsList=goodsList;
    }

    public void setLossOverItemList(ArrayList<LossOverItemVO> lossOverItemList) {
        this.lossOverItemList=lossOverItemList;
        if(lossOverItemList==null){
            System.out.println("Null Exception");
        }
        else{
            System.out.println("Not null");
        }
        showLossOverItemList(lossOverItemList);
    }

    /**
     * 根据数字来设置按钮的文字
     * 按钮被点击时，根据不同的文字执行不同的功能
     * 好吧这个控制耦合有点蠢，但是做的工作少一点
     * 1对应新建单据；2对应编辑单据；3对应查看单据
     * */
    public void setPaneFunction(int command){
        if(command==1){
            confirm.setText("确认添加");
            cancel.setText("保存草稿");
        }
        else if(command==2){
            confirm.setText("确认编辑");
            cancel.setText("保存草稿");
        }
        else if(command==3){
            confirm.setText("确定");
            cancel.setText("取消");
        }
    }

    /**
     * 取得商品列表并修改ObservableList的信息
     * */
    private void showLossOverItemList(ArrayList<LossOverItemVO> lossOverItemList){
        if(lossOverItemList!=null){
            lossOverItemTableView.getItems().clear();
            lossOverItemObservableList.removeAll();

            for(int i=0;i<lossOverItemList.size();i++){
                lossOverItemObservableList.add(lossOverItemList.get(i));
            }
            lossOverItemTableView.setItems(lossOverItemObservableList);

            System.out.println("LossOverItemListSize: "+lossOverItemList.size());
        }
    }


    // 界面之中会用到的方法******************************************

    @FXML
    private void addGoods(){
        AddGoodsUIController.init(goodsList,dialogStage,lossOverItemList);
        System.out.println("添加商品了");
        showLossOverItemList(lossOverItemList);
    }

    @FXML
    private void deleteGoods(){
        if(isLossOverItemSelected()){
            int selectedIndex=lossOverItemTableView.getSelectionModel().getSelectedIndex();
            lossOverItemTableView.getItems().remove(selectedIndex);
        }
    }

    @FXML
    private void goodsNumberPlus(){
        if(isLossOverItemSelected()){
            int selectedIndex=lossOverItemTableView.getSelectionModel().getSelectedIndex();
            lossOverItemList.get(selectedIndex).actualNumber++;
            bill.setLossOverList(lossOverItemList);
            showLossOverItemList(lossOverItemList);
            lossOverItemTableView.getSelectionModel().select(selectedIndex);
        }
    }

    @FXML
    private void goodsNumberMinus(){
        if(isLossOverItemSelected()){
            int selectedIndex=lossOverItemTableView.getSelectionModel().getSelectedIndex();
            ArrayList<LossOverItemVO> lossOverItemList=bill.getLossOverList();
            lossOverItemList.get(selectedIndex).actualNumber--;
            if(lossOverItemList.get(selectedIndex).actualNumber==0){
                lossOverItemList.remove(selectedIndex);
                bill.setLossOverList(lossOverItemList);
                showLossOverItemList(lossOverItemList);
            }
            else{
                bill.setLossOverList(lossOverItemList);
                showLossOverItemList(lossOverItemList);
                lossOverItemTableView.getSelectionModel().select(selectedIndex);
            }
        }
    }

    @FXML
    private void handleConfirm(){
        String text=confirm.getText();
        /*
        if(text.equals("确认添加")){
            purchaseTradeBlService.addClient(client);
        }
        else if(text.equals("确认编辑")){
            purchaseTradeBillBlService.editClient(client);
        }
        else{
            stage.close();
        }
        */
    }

    @FXML
    private void handleCancel(){
        String text=cancel.getText();
        if(text.equals("保存草稿")){
            //service.saveDraft(bill);
            dialogStage.close();
        }
        else if(text.equals("取消")){
            dialogStage.close();
        }
    }

    private boolean isLossOverItemSelected(){
        int selectedIndex=lossOverItemTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            // Nothing selected
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("未选择商品");
            alert.setContentText("请在商品列表中选择商品");
            alert.showAndWait();
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    public void showInfo(BillVO bill, Stage stage){
        init(null,(InventoryLossOverBillVO)bill,3,stage);
    }

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(InventoryLossOverBillBlService service,InventoryLossOverBillVO bill, int command,Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/inventoryui/InventoryLossOverBillUI.fxml"));

            // Create the dialog stage
            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("库存溢损单信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            InventoryLossOverBillUIController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setService(service);
            controller.setBill(bill);

            //controller.setGoodsList(service.getGoodsList(null));
            ArrayList<GoodsVO> list=new ArrayList<GoodsVO>();
            GoodsVO goods1=new GoodsVO("电灯", null, "大号", 5, 20, 30, 20, 30, 40, "备注");
            goods1.setID("123");
            GoodsVO goods2=new GoodsVO("台灯", null, "小号", 5, 30, 60, 30, 60, 40, "备注");
            goods2.setID("233");
            list.add(goods1);
            list.add(goods2);

            controller.setGoodsList(list);
            controller.setLossOverItemList(bill.getLossOverList());
            controller.setPaneFunction(command);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


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
import main.java.businesslogicfactory.inventoryblfactory.InventoryLossOverBillBlFactory;
import main.java.businesslogicservice.inventoryblservice.InventoryLossOverBillBlService;
import main.java.exception.DataException;
import main.java.exception.FullException;
import main.java.presentation.uiutility.AddGoodsUIController;
import main.java.presentation.uiutility.AlertInfo;
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
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button plus;
    @FXML
    private Button minus;


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
        operator.setText(bill.getOperator().getName());
        comment.setText(bill.getComment());
        showLossOverItemList();
    }

    public void setService(InventoryLossOverBillBlService service) {
        this.service=service;
    }

    public void setGoodsList(ArrayList<GoodsVO> goodsList) {
        this.goodsList=goodsList;
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
            confirm.setText("提交编辑");
            cancel.setText("保存草稿");
        }
        else if(command==3){
            confirm.setText("确定");
            cancel.setText("取消");
            
            comment.setEditable(false);
            add.setDisable(true);
            delete.setDisable(true);
            minus.setDisable(true);
            plus.setDisable(true);
        }
    }

    /**
     * 取得商品列表并修改ObservableList的信息
     * */
    private void showLossOverItemList(){
        ArrayList<LossOverItemVO> lossOverItemList=bill.getLossOverList();
        lossOverItemTableView.getItems().clear();
        lossOverItemObservableList.setAll(lossOverItemList);
        lossOverItemTableView.setItems(lossOverItemObservableList);
    }


    // 界面之中会用到的方法******************************************

    @FXML
    private void addGoods(){
        AddGoodsUIController.init(goodsList,dialogStage,bill.getLossOverList());
        showLossOverItemList();
    }

    @FXML
    private void deleteGoods(){
        if(isLossOverItemSelected()){
            int selectedIndex=lossOverItemTableView.getSelectionModel().getSelectedIndex();
            bill.getLossOverList().remove(selectedIndex);
            showLossOverItemList();
        }
    }

    @FXML
    private void goodsNumberPlus(){
        if(isLossOverItemSelected()){
            int selectedIndex=lossOverItemTableView.getSelectionModel().getSelectedIndex();
            bill.getLossOverList().get(selectedIndex).actualNumber++;
            showLossOverItemList();
            lossOverItemTableView.getSelectionModel().select(selectedIndex);
        }
    }

    @FXML
    private void goodsNumberMinus(){
        if(isLossOverItemSelected()){
            int selectedIndex=lossOverItemTableView.getSelectionModel().getSelectedIndex();
            bill.getLossOverList().get(selectedIndex).actualNumber--;

            if(bill.getLossOverList().get(selectedIndex).actualNumber==0){
                bill.getLossOverList().remove(selectedIndex);
                showLossOverItemList();
            }
            else{
                showLossOverItemList();
                lossOverItemTableView.getSelectionModel().select(selectedIndex);
            }
        }
    }

    @FXML
    private void handleConfirm(){
        String text=confirm.getText();
        if(text.equals("确定")){
            dialogStage.close();
        }
        else if(isInputValid()){
            try{
                if(text.equals("确认添加")){
                    bill.setState("待审批");
                    String billID=service.submit(bill);
                    AlertInfo.showAlert(Alert.AlertType.INFORMATION,
                            "Success","提交库存溢损单成功", "单据ID："+billID);
                }
                else if(text.equals("提交编辑")){
                    bill.setState("待审批");
                    service.editInventoryLossOverBill(bill);
                    AlertInfo.showAlert(Alert.AlertType.INFORMATION,
                            "Success","编辑库存溢损单成功", "单据ID："+bill.getID());
                }

                dialogStage.close();
            }catch(DataException e){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"库存溢损单失败", "数据库错误");
            }catch(FullException e){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"库存溢损单失败", "超过单日单据上限（99999张）");
            }catch(Exception e){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"库存溢损单失败", "RMI连接错误");
            }
        }
    }

    @FXML
    private void handleCancel(){
        String text=cancel.getText();
        if(text.equals("保存草稿")){
            try{
                bill.setState("草稿");
                bill.setComment(comment.getText());

                String billID;
                if(ID.getText().length()==0){
                    billID=service.submit(bill);
                }
                else{
                    billID=bill.getID();
                    service.editInventoryLossOverBill(bill);
                }

                AlertInfo.showAlert(Alert.AlertType.INFORMATION,
                        "Success","已保存库存溢损单草稿", "单据ID："+billID);
                dialogStage.close();
            }catch(DataException e){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error","保存库存溢损单草稿失败", "数据库错误");
            }catch(FullException e){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error", "保存库存溢损单草稿失败", "超过单日单据上限（99999张）");
            }catch(Exception e){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error","保存库存溢损单草稿失败", "RMI连接错误");
            }
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
            AlertInfo.showAlert(Alert.AlertType.ERROR,
                    "No Selection","未选择商品","请在商品列表中选择商品");
            return false;
        }
    }

    /**
     * 检查单据信息的输入是否完整且合法
     * 完整且合法返回true
     * */
    private boolean isInputValid(){
        String errorMessage = "";
        
        if (lossOverItemObservableList==null||lossOverItemObservableList.size()==0) {
            errorMessage+=("库存溢损列表为空。"+System.lineSeparator());
        }

        if(errorMessage.length()==0){
            bill.setComment(comment.getText());
            return true;
        } else {
            AlertInfo.showAlert(Alert.AlertType.ERROR,
                    "单据信息错误", "请检查单据信息的输入", errorMessage);
            return false;
        }
    }
    
    // 加载文件和界面的方法******************************************

    public void showInfo(BillVO bill, Stage stage){
        init(InventoryLossOverBillBlFactory.getService(),(InventoryLossOverBillVO)bill,3,stage);
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
            controller.setGoodsList(service.getGoodsList(null));
            controller.setPaneFunction(command);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


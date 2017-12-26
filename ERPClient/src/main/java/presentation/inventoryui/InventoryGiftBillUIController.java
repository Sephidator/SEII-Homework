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
import main.java.businesslogicfactory.inventoryblfactory.InventoryGiftBillBlFactory;
import main.java.businesslogicservice.inventoryblservice.InventoryGiftBillBlService;
import main.java.exception.DataException;
import main.java.exception.FullException;
import main.java.presentation.uiutility.AddGoodsUIController;
import main.java.presentation.uiutility.AlertInfo;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GiftItemVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class InventoryGiftBillUIController extends InfoUIController {
    private InventoryGiftBillBlService service;
    private InventoryGiftBillVO bill;
    private ArrayList<GoodsVO> goodsList;

    private ObservableList<GiftItemVO> giftItemObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<GiftItemVO> giftItemTableView;
    @FXML
    private TableColumn<GiftItemVO,String> IDColumn;
    @FXML
    private TableColumn<GiftItemVO,String> nameColumn;
    @FXML
    private TableColumn<GiftItemVO,String> modelColumn;
    @FXML
    private TableColumn<GiftItemVO,String> numberColumn;

    @FXML
    private TextField ID; // 单据编号
    @FXML
    private TextField type; // 单据类型
    @FXML
    private TextField operator; //操作员
    @FXML
    private TextField client; //客户
    @FXML
    private TextArea comment; // 备注
    @FXML
    private ChoiceBox<String> clientChoiceBox;
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
        numberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().number)));
    }

    // 设置controller数据的方法*****************************************

    public void setBill(InventoryGiftBillVO bill) {
        this.bill = bill;
        ID.setText(bill.getID());
        type.setText(bill.getType());
        operator.setText(bill.getOperator().getName());
        client.setText(bill.getClient().getCategory()+" "+bill.getClient().getName());
        comment.setText(bill.getComment());
        showGiftItemList();
    }

    public void setService(InventoryGiftBillBlService service) {
        this.service=service;
        setClientList();
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
            confirm.setText("确认编辑");
            cancel.setText("保存草稿");
        }
        else if(command==3){
            confirm.setText("确定");
            cancel.setText("取消");

            comment.setEditable(false);
            clientChoiceBox.setDisable(true);
            add.setDisable(true);
            delete.setDisable(true);
            minus.setDisable(true);
            plus.setDisable(true);
        }
    }

    private void setClientList(){
        try{
            ArrayList<ClientVO> clientList= service.getClientList(null);

            ObservableList<String> list=FXCollections.observableArrayList();
            for(int i=0;i<clientList.size();i++){
                list.add(clientList.get(i).getCategory()+" "+clientList.get(i).getName());
            }
            clientChoiceBox.setItems(list);
            clientChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
                client.setText(list.get(newValue.intValue()));
                bill.setClient(clientList.get(newValue.intValue()));
            });
        }catch(DataException e){
            AlertInfo.showAlert(Alert.AlertType.ERROR,
                    "Error","查找客户失败","数据库错误");
        }catch(Exception e){
            AlertInfo.showAlert(Alert.AlertType.ERROR,
                    "Error","查找客户失败","RMI连接错误");
        }
    }

    /**
     * 取得商品列表并修改ObservableList的信息
     * */
    private void showGiftItemList(){
        ArrayList<GiftItemVO> giftItemList=bill.getGiftList();
        giftItemTableView.getItems().clear();
        giftItemObservableList.setAll(giftItemList);
        giftItemTableView.setItems(giftItemObservableList);
    }


    // 界面之中会用到的方法******************************************

    @FXML
    private void addGoods(){
        AddGoodsUIController.init(dialogStage,goodsList,bill.getGiftList());
        showGiftItemList();
    }

    @FXML
    private void deleteGoods(){
        if(isGiftItemSelected()){
            int selectedIndex=giftItemTableView.getSelectionModel().getSelectedIndex();
            bill.getGiftList().remove(selectedIndex);
            showGiftItemList();
        }
    }

    @FXML
    private void goodsNumberPlus(){
        if(isGiftItemSelected()){
            int selectedIndex=giftItemTableView.getSelectionModel().getSelectedIndex();
            bill.getGiftList().get(selectedIndex).number++;
            showGiftItemList();
            giftItemTableView.getSelectionModel().select(selectedIndex);
        }
    }

    @FXML
    private void goodsNumberMinus(){
        if(isGiftItemSelected()){
            int selectedIndex=giftItemTableView.getSelectionModel().getSelectedIndex();
            bill.getGiftList().get(selectedIndex).number--;

            if(bill.getGiftList().get(selectedIndex).number==0){
                bill.getGiftList().remove(selectedIndex);
                showGiftItemList();
            }
            else{
                showGiftItemList();
                giftItemTableView.getSelectionModel().select(selectedIndex);
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
                            "Success","提交库存赠送单成功", "单据ID："+billID);
                }
                else if(text.equals("提交编辑")){
                    bill.setState("待审批");
                    service.editInventoryGiftBill(bill);
                    AlertInfo.showAlert(Alert.AlertType.INFORMATION,
                            "Success","编辑库存赠送单成功", "单据ID："+bill.getID());
                }

                dialogStage.close();
            }catch(DataException e){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"库存赠送单失败", "数据库错误");
            }catch(FullException e){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"库存赠送单失败", "超过单日单据上限（99999张）");
            }catch(Exception e){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"库存赠送单失败", "RMI连接错误");
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
                    service.editInventoryGiftBill(bill);
                }

                AlertInfo.showAlert(Alert.AlertType.INFORMATION,
                        "Success","已保存库存赠送单草稿", "单据ID："+billID);
                dialogStage.close();
            }catch(DataException e){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error","保存库存赠送单草稿失败", "数据库错误");
            }catch(FullException e){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error", "保存库存赠送单草稿失败", "超过单日单据上限（99999张）");
            }catch(Exception e){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error","保存库存赠送单草稿失败", "RMI连接错误");
            }
            dialogStage.close();
        }
        else if(text.equals("取消")){
            dialogStage.close();
        }
    }

    private boolean isGiftItemSelected(){
        int selectedIndex=giftItemTableView.getSelectionModel().getSelectedIndex();
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

        if (client.getText().length()<=1) {
            errorMessage+=("未选择客户。"+System.lineSeparator());
        }
        if (giftItemObservableList==null||giftItemObservableList.size()==0) {
            errorMessage+=("库存赠送列表为空。"+System.lineSeparator());
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
        init(InventoryGiftBillBlFactory.getService(),(InventoryGiftBillVO)bill,3,stage);
    }

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(InventoryGiftBillBlService service,InventoryGiftBillVO bill, int command,Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/inventoryui/InventoryGiftBillUI.fxml"));

            // Create the dialog stage
            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("库存赠送单信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            InventoryGiftBillUIController controller=loader.getController();
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


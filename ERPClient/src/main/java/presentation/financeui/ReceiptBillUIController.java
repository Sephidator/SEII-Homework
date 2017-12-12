package main.java.presentation.financeui;

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
import main.java.businesslogicservice.financeblservice.ReceiptBillBlService;
import main.java.presentation.uiutility.AddAccountUIController;
import main.java.presentation.uiutility.AddGoodsUIController;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.ReceiptBillVO;
import main.java.vo.bill.financebill.TransItemVO;
import main.java.vo.goods.GiftItemVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class ReceiptBillUIController extends InfoUIController {
    private ReceiptBillBlService service;
    private ReceiptBillVO bill;
    private ArrayList<AccountVO> accountList;
    private ArrayList<TransItemVO> transItemList;

    private ObservableList<TransItemVO> transItemObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<TransItemVO> transItemTableView;
    @FXML
    private TableColumn<TransItemVO,String> bankAccountColumn;
    @FXML
    private TableColumn<TransItemVO,String> amountColumn;
    @FXML
    private TableColumn<TransItemVO,String> commentColumn;

    @FXML
    private TextField ID; // 单据编号
    @FXML
    private TextField type; // 单据类型
    @FXML
    private TextField operator; //操作员
    @FXML
    private TextArea comment; // 备注
    @FXML
    private TextField inputAmount; // 输入转账金额
    @FXML
    private TextField total; // 总价
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;

    // 加载文件后调用的方法******************************************

    public void initialize(){
        bankAccountColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().account.getBankAccount()));
        amountColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().transAmount)));
        commentColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().comment)));
    }

    // 设置controller数据的方法*****************************************

    public void setBill(ReceiptBillVO bill) {
        this.bill = bill;
        ID.setText(bill.getID());
        type.setText(bill.getType());
        operator.setText(bill.getOperator()==null?"":(bill.getOperator().getID()+":"+bill.getOperator().getName()));
        comment.setText(bill.getComment());
    }

    public void setService(ReceiptBillBlService service) {
        this.service=service;
    }

    public void setAccountList(ArrayList<AccountVO> accountList) {
        this.accountList=accountList;
    }

    public void setTransItemList(ArrayList<TransItemVO> transItemList) {
        this.transItemList=transItemList;
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
    private void showTransItemList(ArrayList<TransItemVO> transItemList){
        if(transItemList!=null){
            transItemTableView.getItems().clear();
            transItemObservableList.removeAll();

            for(int i=0;i<transItemList.size();i++){
                transItemObservableList.add(transItemList.get(i));
            }
            transItemTableView.setItems(transItemObservableList);

            System.out.println("TransItemListSize: "+transItemList.size());
        }
    }


    // 界面之中会用到的方法******************************************

    @FXML
    private void addAccount(){
        AddAccountUIController.init(accountList,transItemList,dialogStage);
        System.out.println("添加商品了");
        showTransItemList(transItemList);
    }

    @FXML
    private void deleteAccount(){
        if(isGiftItemSelected()){
            int selectedIndex=transItemTableView.getSelectionModel().getSelectedIndex();
            transItemTableView.getItems().remove(selectedIndex);
        }
    }

    @FXML
    private void handleCorrect(){
        if(isGiftItemSelected()){
            Double number;
            try{
                number=Double.parseDouble(inputAmount.getText());
                if(number<=0){
                    throw new NumberFormatException();
                }
            }catch (NumberFormatException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid input");
                alert.setHeaderText("输入不正确");
                alert.setContentText("请输入正数");
                alert.showAndWait();
                return;
            }

            int selectedIndex=transItemTableView.getSelectionModel().getSelectedIndex();
            transItemList.get(selectedIndex).transAmount=number;
            bill.setTransList(transItemList);
            showTransItemList(transItemList);
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

    private boolean isGiftItemSelected(){
        int selectedIndex=transItemTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            // Nothing selected
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("未选择账户");
            alert.setContentText("请在账户列表中选择账户");
            alert.showAndWait();
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    public void showInfo(BillVO bill, Stage stage){
        init(null,(ReceiptBillVO)bill,3,stage);
    }

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(ReceiptBillBlService service,ReceiptBillVO bill, int command,Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/financeui/ReceiptBillUI.fxml"));

            // Create the dialog stage
            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("收款单信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            ReceiptBillUIController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setService(service);
            controller.setBill(bill);

            //controller.setGoodsList(service.getGoodsList(null));
            ArrayList<AccountVO> list=new ArrayList<>();
            AccountVO a1=new AccountVO("1234567890","账户A",10000);
            AccountVO a2=new AccountVO("1000000000","账户B",23400);
            list.add(a1);
            list.add(a2);

            controller.setAccountList(list);
            controller.setTransItemList(bill.getTransList());
            controller.setPaneFunction(command);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


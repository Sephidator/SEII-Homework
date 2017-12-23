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
import main.java.businesslogicfactory.clientblfactory.ClientBlFactory;
import main.java.businesslogicfactory.userblfactory.UserBlFactory;
import main.java.businesslogicservice.financeblservice.PaymentBillBlService;
import main.java.exception.DataException;
import main.java.exception.FullException;
import main.java.exception.NotExistException;
import main.java.presentation.uiutility.AddAccountUIController;
import main.java.presentation.uiutility.CheckInput;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.PaymentBillVO;
import main.java.vo.bill.financebill.TransItemVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class PaymentBillUIController extends InfoUIController {
    private PaymentBillBlService service;
    private PaymentBillVO bill;
    private ArrayList<AccountVO> accountList;

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
    private TextField client; //客户
    @FXML
    private TextArea comment; // 备注
    @FXML
    private TextField inputAmount; // 输入转账金额
    @FXML
    private TextField total; // 总价
    @FXML
    private ChoiceBox<String> clientChoiceBox;
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

    public void setBill(PaymentBillVO bill) {
        this.bill = bill;
        ID.setText(bill.getID());
        type.setText(bill.getType());
        operator.setText(bill.getOperator().getName());
        client.setText(bill.getClient().getCategory()+" "+bill.getClient().getName());
        comment.setText(bill.getComment());
        total.setText(String.valueOf(bill.getTotal()));
        showTransItemList();
    }

    public void setService(PaymentBillBlService service) {
        this.service=service;
        setClientList();
    }

    public void setAccountList(ArrayList<AccountVO> accountList) {
        this.accountList=accountList;
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

    private void setClientList(){
        try{
            ArrayList<ClientVO> clientList= ClientBlFactory.getService().getClientList(null);

            ObservableList<String> list=FXCollections.observableArrayList();
            for(int i=0;i<clientList.size();i++){
                list.add(clientList.get(i).getCategory()+": "+clientList.get(i).getName());
            }
            clientChoiceBox.setItems(list);
            clientChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
                client.setText(clientList.get(newValue.intValue()).getCategory()+" "+clientList.get(newValue.intValue()).getName());
                bill.setClient(clientList.get(newValue.intValue()));
            });
        }catch(DataException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("查找客户失败");
            alert.setContentText("数据库错误");
            alert.showAndWait();
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("查找客户失败");
            alert.setContentText("RMI连接错误");
            alert.showAndWait();
        }
    }

    /**
     * 取得商品列表并修改ObservableList的信息
     * */
    private void showTransItemList(){
        ArrayList<TransItemVO> transItemList=bill.getTransList();
        transItemTableView.getItems().clear();
        transItemObservableList.setAll(transItemList);
        transItemTableView.setItems(transItemObservableList);
    }

    private void countTotal(){
        double totalAmount=0;
        for(TransItemVO item:bill.getTransList()){
            totalAmount+=item.transAmount;
        }
        total.setText(String.valueOf(totalAmount));
        bill.setTotal(totalAmount);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void addAccount(){
        AddAccountUIController.init(accountList,bill.getTransList(),dialogStage);
        showTransItemList();
        countTotal();
    }

    @FXML
    private void deleteAccount(){
        if(isAccountSelected()){
            int selectedIndex=transItemTableView.getSelectionModel().getSelectedIndex();
            bill.getTransList().remove(selectedIndex);
            showTransItemList();
            countTotal();
        }
    }

    @FXML
    private void handleCorrect(){
        if(isAccountSelected()){
            String str=inputAmount.getText();

            if(CheckInput.isPositiveNumber(str)){
                Double number=Double.parseDouble(str);
                int selectedIndex=transItemTableView.getSelectionModel().getSelectedIndex();

                if(number>bill.getTransList().get(selectedIndex).account.getRemaining()){
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid input");
                    alert.setHeaderText("输入不正确");
                    alert.setContentText("输入金额不得大于银行余额");
                    alert.showAndWait();
                    return;
                }
                else{
                    bill.getTransList().get(selectedIndex).transAmount=number;
                    inputAmount.setText("");
                    showTransItemList();
                    countTotal();
                }
            }
        }
    }

    @FXML
    private void handleConfirm(){
        if(isInputValid()){
            String text=confirm.getText();

            try{
                if(text.equals("添加")){
                    bill.setState("待审批");
                    String billID=service.submit(bill);

                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("提交付款单成功");
                    alert.setContentText("单据ID："+billID);
                    alert.showAndWait();
                }
                else if(text.equals("编辑")){
                    service.editPaymentBill(bill);

                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("编辑付款单成功");
                    alert.setContentText("单据ID："+bill.getID());
                    alert.showAndWait();
                }

                dialogStage.close();
            }catch(DataException e){
                e.printStackTrace();
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(text+"付款单失败");
                alert.setContentText("数据库错误");
                alert.showAndWait();
            }catch(FullException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(text+"付款单失败");
                alert.setContentText("超过单日单据上限（99999张）");
                alert.showAndWait();
            }catch(Exception e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(text+"付款单失败");
                alert.setContentText("RMI连接错误");
                alert.showAndWait();
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
                    service.editPaymentBill(bill);
                }

                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("已保存付款单草稿");
                alert.setContentText("单据ID："+billID);
                alert.showAndWait();

                dialogStage.close();
            }catch(DataException e){
                e.printStackTrace();
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("保存付款单草稿失败");
                alert.setContentText("数据库错误");
                alert.showAndWait();
            }catch(FullException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("保存付款单草稿失败");
                alert.setContentText("超过单日单据上限（99999张）");
                alert.showAndWait();
            }catch(Exception e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("保存付款单草稿失败");
                alert.setContentText("RMI连接错误");
                alert.showAndWait();
            }
            dialogStage.close();
        }
        else if(text.equals("取消")){
            dialogStage.close();
        }
    }

    private boolean isAccountSelected(){
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

    /**
     * 检查单据信息的输入是否完整且合法
     * 完整且合法返回true
     * */
    private boolean isInputValid(){
        String errorMessage = "";

        if (client.getText().length()==0) {
            errorMessage+=("未选择客户。"+System.lineSeparator());
        }
        if (Double.parseDouble(total.getText())==0) {
            errorMessage+=("未添加转账列表。"+System.lineSeparator());
        }

        if(errorMessage.length()==0){
            bill.setComment(comment.getText());
            return true;
        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("单据信息错误");
            alert.setHeaderText("请检查单据信息的输入");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    public void showInfo(BillVO bill, Stage stage){
        init(null,(PaymentBillVO)bill,3,stage);
    }

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(PaymentBillBlService service,PaymentBillVO bill, int command,Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/financeui/PaymentBillUI.fxml"));

            // Create the dialog stage
            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("收款单信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            PaymentBillUIController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setService(service);
            controller.setBill(bill);
            controller.setAccountList(service.getAccountList(null));
            controller.setPaneFunction(command);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


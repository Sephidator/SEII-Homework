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
import main.java.businesslogic.blutility.Arith;
import main.java.businesslogicfactory.accountblfactory.AccountBlFactory;
import main.java.businesslogicfactory.financeblfactory.CashBillBlFactory;
import main.java.businesslogicservice.financeblservice.CashBillBlService;
import main.java.exception.DataException;
import main.java.exception.FullException;
import main.java.presentation.uiutility.AddCashItemUIController;
import main.java.presentation.uiutility.UITool;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.CashBillVO;
import main.java.vo.bill.financebill.CashItemVO;

import java.util.ArrayList;

public class CashBillUIController extends InfoUIController {
    private CashBillBlService service;
    private CashBillVO bill;

    private ObservableList<CashItemVO> cashItemObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<CashItemVO> cashItemTableView;
    @FXML
    private TableColumn<CashItemVO,String> nameColumn;
    @FXML
    private TableColumn<CashItemVO,String> amountColumn;
    @FXML
    private TableColumn<CashItemVO,String> commentColumn;
    @FXML
    private TextField ID; // 单据编号
    @FXML
    private TextField type; // 单据类型
    @FXML
    private TextField operator; //操作员
    @FXML
    private TextField account; // 公司账户
    @FXML
    private TextField total; // 总价
    @FXML
    private TextArea comment; // 备注
    @FXML
    private ChoiceBox<String> accountChoiceBox;
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;
    @FXML
    private Button addCashItem;
    @FXML
    private Button deleteCashItem;
    
    // 加载文件后调用的方法******************************************

    public void initialize(){
        nameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().ItemName));
        amountColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().amount)));
        commentColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().comment));
    }

    // 设置controller数据的方法*****************************************

    public void setBill(CashBillVO bill) {
        this.bill = bill;
        ID.setText(bill.getID());
        type.setText(bill.getType());
        operator.setText(bill.getOperator().getName());
        account.setText(bill.getAccount().getName());
        comment.setText(bill.getComment());
        total.setText(String.valueOf(bill.getTotal()));
        showCashItemList();
    }

    public void setService(CashBillBlService service) {
        this.service=service;
        setAccountList();
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
            addCashItem.setDisable(true);
            deleteCashItem.setDisable(true);
        }
    }

    private void setAccountList(){
        try{
            ArrayList<AccountVO> accountList= AccountBlFactory.getService().getAccountList(null);

            ObservableList<String> list=FXCollections.observableArrayList();
            for(int i=0;i<accountList.size();i++){
                list.add(accountList.get(i).getName()+": 余额"+accountList.get(i).getRemaining()+"元");
            }
            accountChoiceBox.setItems(list);
            accountChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
                account.setText(accountList.get(newValue.intValue()).getName());
                bill.setAccount(accountList.get(newValue.intValue()));
            });
        }catch(DataException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找银行账户失败","数据库错误");
        }catch(Exception e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找银行账户失败","RMI连接错误");
        }
    }

    /**
     * 取得商品列表并修改ObservableList的信息
     * */
    private void showCashItemList(){
        ArrayList<CashItemVO> cashItemList=bill.getItemList();
        cashItemObservableList.clear();
        cashItemObservableList.setAll(cashItemList);
        cashItemTableView.setItems(cashItemObservableList);
    }

    private void countTotal(){
        double totalAmount=0;
        for(CashItemVO item:bill.getItemList()){
            totalAmount = Arith.add(totalAmount, item.amount);
        }
        total.setText(String.valueOf(totalAmount));
        bill.setTotal(totalAmount);
    }


    // 界面之中会用到的方法******************************************

    @FXML
    private void handleAddCashItem(){
        AddCashItemUIController.init(bill.getItemList(),dialogStage);
        showCashItemList();
        countTotal();
    }

    @FXML
    private void handleDeleteCashItem(){
        if(isCashItemSelected()){
            int selectedIndex=cashItemTableView.getSelectionModel().getSelectedIndex();
            bill.getItemList().remove(selectedIndex);
            showCashItemList();
            countTotal();
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
                    UITool.showAlert(Alert.AlertType.INFORMATION,
                            "Success","提交现金费用单成功", "单据ID："+billID);
                }
                else if(text.equals("提交编辑")){
                    bill.setState("待审批");
                    service.editCashBill(bill);
                    UITool.showAlert(Alert.AlertType.INFORMATION,
                            "Success","编辑现金费用单成功", "单据ID："+bill.getID());
                }

                dialogStage.close();
            }catch(DataException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"现金费用单失败", "数据库错误");
            }catch(FullException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"现金费用单失败", "超过单日单据上限（99999张）");
            }catch(Exception e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"现金费用单失败", "RMI连接错误");
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
                    service.editCashBill(bill);
                }

                UITool.showAlert(Alert.AlertType.INFORMATION,
                        "Success","已保存现金费用单草稿", "单据ID："+billID);
                dialogStage.close();
            }catch(DataException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error","保存现金费用单草稿失败", "数据库错误");
            }catch(FullException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error", "保存现金费用单草稿失败", "超过单日单据上限（99999张）");
            }catch(Exception e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error","保存现金费用单草稿失败", "RMI连接错误");
            }
            dialogStage.close();
        }
        else if(text.equals("取消")){
            dialogStage.close();
        }
    }

    private boolean isCashItemSelected(){
        int selectedIndex=cashItemTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            // Nothing selected
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("未选择条目");
            alert.setContentText("请在条目清单中选择条目");
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

        if (account.getText().length()==0) {
            errorMessage+=("未选择账户。"+System.lineSeparator());
        }
        if (cashItemObservableList==null||cashItemObservableList.size()==0) {
            errorMessage+=("未添加条目清单。"+System.lineSeparator());
        }

        if(errorMessage.length()==0){
            bill.setComment(comment.getText());
            return true;
        } else {
            UITool.showAlert(Alert.AlertType.ERROR,
                    "单据信息错误", "请检查单据信息的输入", errorMessage);
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    public void showInfo(BillVO bill, Stage stage){
        init(CashBillBlFactory.getService(),(CashBillVO)bill,3,stage);
    }

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(CashBillBlService service,CashBillVO bill, int command,Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/financeui/CashBillUI.fxml"));

            // Create the dialog stage
            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("现金费用单信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            CashBillUIController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setService(service);
            controller.setBill(bill);
            controller.setPaneFunction(command);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


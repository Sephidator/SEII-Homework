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
import main.java.businesslogicservice.financeblservice.CashBillBlService;
import main.java.presentation.uiutility.AddCashItemUIController;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.CashBillVO;
import main.java.vo.bill.financebill.CashItemVO;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public class CashBillUIController extends InfoUIController {
    private CashBillBlService service;
    private CashBillVO bill;
    private ArrayList<CashItemVO> cashItemList;

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
    private TextField bankAccount; // 总价
    @FXML
    private TextField total; // 总价
    @FXML
    private TextArea comment; // 备注
    @FXML
    private ChoiceBox<String> bankAccountChoiceBox;
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;


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
        total.setText(String.valueOf(bill.getTotal()));
        comment.setText(bill.getComment());
        operator.setText(bill.getOperator().getID()+":"+bill.getOperator().getName());
    }

    public void setService(CashBillBlService service) {
        this.service=service;

        AccountVO a1 = new AccountVO("12345678","账户A",1000);
        AccountVO a2 = new AccountVO("12389678","账户B",10000);

        ArrayList<AccountVO> accountList=new ArrayList<>();
        accountList.add(a1);
        accountList.add(a2);
        ObservableList<String> list=FXCollections.observableArrayList();
        for(int i=0;i<accountList.size();i++){
            list.add(accountList.get(i).getName()+":"+accountList.get(i).getRemaining()+"元");
        }
        bankAccountChoiceBox.setItems(list);
        bankAccountChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
            bankAccount.setText(accountList.get(newValue.intValue()).getName()+":"+accountList.get(newValue.intValue()).getRemaining()+"元");
            bill.setAccount(accountList.get(newValue.intValue()));
        });
    }

    public void setCashItemList(ArrayList<CashItemVO> cashItemList) {
        this.cashItemList=cashItemList;
        showCashItemList(cashItemList);
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
    private void showCashItemList(ArrayList<CashItemVO> cashItemList){
        if(cashItemList!=null){
            cashItemTableView.getItems().clear();
            cashItemObservableList.removeAll();

            for(int i=0;i<cashItemList.size();i++){
                cashItemObservableList.add(cashItemList.get(i));
            }
            cashItemTableView.setItems(cashItemObservableList);

            System.out.println("CashItemListSize: "+cashItemList.size());
        }
    }


    // 界面之中会用到的方法******************************************

    @FXML
    private void addCashItem(){
        AddCashItemUIController.init(cashItemList,dialogStage);
        showCashItemList(cashItemList);
    }

    @FXML
    private void deleteCashItem(){
        if(isCashItemSelected()){
            int selectedIndex=cashItemTableView.getSelectionModel().getSelectedIndex();
            cashItemTableView.getItems().remove(selectedIndex);
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

    // 加载文件和界面的方法******************************************

    public void showInfo(BillVO bill, Stage stage){
        init(null,(CashBillVO)bill,3,stage);
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
            dialogStage.setTitle("进货单信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            CashBillUIController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setService(service);
            controller.setBill(bill);

            ArrayList<CashItemVO> list=new ArrayList<>();
            CashItemVO cash1=new CashItemVO("条目1",100,"无备注");
            CashItemVO cash2=new CashItemVO("条目2",50,"备注");

            list.add(cash1);
            list.add(cash2);

            controller.setCashItemList(list);
            //controller.setCashItemList(bill.getItemList());
            controller.setPaneFunction(command);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


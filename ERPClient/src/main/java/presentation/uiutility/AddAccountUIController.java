package main.java.presentation.uiutility;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.financebill.TransItemVO;
import main.java.vo.bill.inventorybill.LossOverItemVO;
import main.java.vo.goods.GiftItemVO;

import java.util.ArrayList;

public class AddAccountUIController {
    protected Stage dialogStage;
    private ArrayList<AccountVO> accountList;
    private ArrayList<TransItemVO> transItemList;
    
    private ObservableList<AccountVO> accountObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<AccountVO> accountTableView;
    @FXML
    private TableColumn<AccountVO,String> bankAccountColumn;
    @FXML
    private TableColumn<AccountVO,String> nameColumn;
    @FXML
    private TableColumn<AccountVO,String> remainingColumn;

    // 加载文件后调用的方法******************************************

    public void initialize(){
        bankAccountColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getBankAccount()));
        nameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getName()));
        remainingColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getRemaining())));
    }

    // 设置controller数据的方法*****************************************

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage=dialogStage;
    }

    public void setAccountList(ArrayList<AccountVO> accountList) {
        this.accountList=accountList;
        showAccountList(accountList);
    }

    public void setTransItemList(ArrayList<TransItemVO> transItemList) {
        this.transItemList=transItemList;
    }

    // 界面之中会用到的方法******************************************

    /**
     * 取得商品列表并修改ObservableList的信息
     * */
    private void showAccountList(ArrayList<AccountVO> accountList){
        if(accountList!=null){
            accountObservableList.removeAll();

            for(int i=0;i<accountList.size();i++){
                accountObservableList.add(accountList.get(i));
            }
            accountTableView.setItems(accountObservableList);
        }
    }

    @FXML
    private void handleConfirm(){
        if(isAccountSelected()){
            if(transItemList!=null){
                int index=accountTableView.getSelectionModel().getSelectedIndex();
                TransItemVO transItem=new TransItemVO(accountTableView.getItems().get(index),100, "无备注");
                transItemList.add(transItem);
            }
        }
        dialogStage.close();
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    private boolean isAccountSelected(){
        int selectedIndex=accountTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("未选择账户");
            alert.setContentText("请在账户列表中选择商品");
            alert.showAndWait();
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */

    public static void init(ArrayList<AccountVO> goodsList,ArrayList<TransItemVO> transItemList,Stage stage){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/uiutility/AddAccountUI.fxml"));

            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("添加商品界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            AddAccountUIController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAccountList(goodsList);
            controller.setTransItemList(transItemList);

            dialogStage.showAndWait();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

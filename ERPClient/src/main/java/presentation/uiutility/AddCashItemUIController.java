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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.financebill.CashItemVO;
import main.java.vo.bill.financebill.TransItemVO;

import java.util.ArrayList;

public class AddCashItemUIController {
    protected Stage dialogStage;
    private ArrayList<CashItemVO> cashItemList;

    @FXML
    private TextField name;
    @FXML
    private TextField amount;
    @FXML
    private TextField comment;

    // 加载文件后调用的方法******************************************

    public void initialize(){
    }

    // 设置controller数据的方法*****************************************

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage=dialogStage;
    }

    public void setCashItemList(ArrayList<CashItemVO> cashItemList) {
        this.cashItemList=cashItemList;
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleConfirm(){
        if(isInputValid()){
            CashItemVO cashItemVO=new CashItemVO(name.getText(),Double.parseDouble(amount.getText()),comment.getText());
            cashItemList.add(cashItemVO);
        }
        dialogStage.close();
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    private boolean isInputValid(){
        String errorMessage = "";

        if (name.getText()==null || name.getText().length()==0) {
            errorMessage+=("未输入条目名！"+System.lineSeparator());
        }
        if (amount.getText()==null || amount.getText().length()==0) {
            errorMessage+=("未输入金额!"+System.lineSeparator());
        }
        else{
            if(!CheckInput.isPositiveNumber(amount.getText())){
                errorMessage+=("金额必须是正数!"+System.lineSeparator());
            }
        }

        if(errorMessage.length()==0){
            return true;
        } else {
            // Show the error message.
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("不正确的输入");
            alert.setHeaderText("请确认输入");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */

    public static void init(ArrayList<CashItemVO> cashItemList,Stage stage){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/uiutility/AddCashItemUI.fxml"));

            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("添加条目界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            AddCashItemUIController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCashItemList(cashItemList);

            dialogStage.showAndWait();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

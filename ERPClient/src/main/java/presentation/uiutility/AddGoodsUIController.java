package main.java.presentation.uiutility;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.vo.bill.BillVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class AddGoodsUIController {
    protected Stage dialogStage;
    private ArrayList<GoodsVO> goodsList;
    private ArrayList<GoodsItemVO> goodsItemList;

    private ObservableList<GoodsVO> goodsObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<GoodsVO> goodsTableView;
    @FXML
    private TableColumn<GoodsVO,String> IDColumn;
    @FXML
    private TableColumn<GoodsVO,String> nameColumn;
    @FXML
    private TableColumn<GoodsVO,String> modelColumn;
    @FXML
    private TableColumn<GoodsVO,String> numberColumn;
    @FXML
    private TableColumn<GoodsVO,String> priceColumn;
    @FXML
    private TableColumn<GoodsVO,String> commentColumn;

    // 加载文件后调用的方法******************************************

    public void initialize(){
        IDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));
        nameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getName()));
        modelColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getModel())));
        numberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getNumber())));
        priceColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getCost())));
        commentColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getComment()));
    }

    // 设置controller数据的方法*****************************************

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage=dialogStage;
    }

    public void setGoodsList(ArrayList<GoodsVO> goodsList) {
        this.goodsList=goodsList;
        showGoodsList(goodsList);
    }

    public void setGoodsItemList(ArrayList<GoodsItemVO> goodsItemList) {
        this.goodsItemList=goodsItemList;
    }

    // 界面之中会用到的方法******************************************

    /**
     * 取得商品列表并修改ObservableList的信息
     * */
    private void showGoodsList(ArrayList<GoodsVO> goodsList){
        if(goodsList!=null){
            goodsObservableList.removeAll();

            for(int i=0;i<goodsList.size();i++){
                goodsObservableList.add(goodsList.get(i));
            }
            goodsTableView.setItems(goodsObservableList);
        }
    }

    @FXML
    private void handleConfirm(){
        if(isGoodsSelected()){
            GoodsItemVO goodsItem=new GoodsItemVO(goodsTableView.getSelectionModel().getSelectedItem(),1);
            goodsItemList.add(goodsItem);
        }
        dialogStage.close();
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    private boolean isGoodsSelected(){
        int selectedIndex=goodsTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("未选择商品");
            alert.setContentText("请在商品列表中选择商品");
            alert.showAndWait();
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(ArrayList<GoodsVO> goodsList,ArrayList<GoodsItemVO> goodsItemList,Stage stage){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/uiutility/AddGoodsUI.fxml"));

            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("添加商品界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            AddGoodsUIController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setGoodsList(goodsList);
            controller.setGoodsItemList(goodsItemList);

            dialogStage.showAndWait();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

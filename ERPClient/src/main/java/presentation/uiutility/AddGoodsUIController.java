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
import main.java.vo.bill.inventorybill.LossOverItemVO;
import main.java.vo.goods.GiftItemVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class AddGoodsUIController {
    protected Stage dialogStage;
    private ArrayList<GoodsVO> goodsList;
    private ArrayList<GoodsItemVO> goodsItemList;
    private ArrayList<LossOverItemVO> lossOverItemList;
    private ArrayList<GiftItemVO> giftItemList;

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

    public void setPriceColumn(boolean priceIsCost) {
        if(!priceIsCost){
            priceColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getRetail())));
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage=dialogStage;
    }

    public void setGoodsList(ArrayList<GoodsVO> goodsList) {
        this.goodsList=goodsList;
        showGoodsList();
    }

    public void setGoodsItemList(ArrayList<GoodsItemVO> goodsItemList) {
        this.goodsItemList=goodsItemList;
    }

    public void setLossOverItemList(ArrayList<LossOverItemVO> lossOverItemList) {
        this.lossOverItemList=lossOverItemList;
    }

    public void setGiftItemList(ArrayList<GiftItemVO> giftItemList) {
        this.giftItemList=giftItemList;
    }

    // 界面之中会用到的方法******************************************

    /**
     * 取得商品列表并修改ObservableList的信息
     * */
    private void showGoodsList(){
        goodsObservableList.removeAll();
        goodsObservableList.setAll(goodsList);
        goodsTableView.setItems(goodsObservableList);
    }

    @FXML
    private void handleConfirm(){
        if(isGoodsSelected()){
            if(goodsItemList!=null){
                int index=goodsTableView.getSelectionModel().getSelectedIndex();
                GoodsItemVO goodsItem=new GoodsItemVO(goodsTableView.getItems().get(index),1,Double.parseDouble(priceColumn.getCellData(index)));

                for(int i=0;i<goodsItemList.size();i++){
                    if(goodsItemList.get(i).goods.getID().equals(goodsItem.goods.getID())){
                        dialogStage.close();
                        return;
                    }
                }
                goodsItemList.add(goodsItem);
            }
            if(lossOverItemList!=null){
                GoodsVO goods=goodsTableView.getSelectionModel().getSelectedItem();
                LossOverItemVO lossOverItem=new LossOverItemVO(goods,goods.getRetail(),goods.getNumber(),goods.getNumber());

                for(int i=0;i<lossOverItemList.size();i++){
                    if(lossOverItemList.get(i).goods.getID().equals(lossOverItem.goods.getID())){
                        dialogStage.close();
                        return;
                    }
                }
                lossOverItemList.add(lossOverItem);
            }
            if(giftItemList!=null){
                GoodsVO goods=goodsTableView.getSelectionModel().getSelectedItem();
                GiftItemVO giftItem=new GiftItemVO(goods,1,goods.getCost());

                for(int i=0;i<giftItemList.size();i++){
                    if(giftItemList.get(i).goods.getID().equals(giftItem.goods.getID())){
                        dialogStage.close();
                        return;
                    }
                }
                giftItemList.add(giftItem);
            }
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
            UITool.showAlert(Alert.AlertType.ERROR,
                    "No Selection","未选择商品","请在商品列表中选择商品");
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */

    public static void init(ArrayList<GoodsVO> goodsList,ArrayList<GoodsItemVO> goodsItemList,Stage stage,boolean priceIsCost){
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
            controller.setPriceColumn(priceIsCost);
            controller.setDialogStage(dialogStage);
            controller.setGoodsList(goodsList);
            controller.setGoodsItemList(goodsItemList);

            dialogStage.showAndWait();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void init(ArrayList<GoodsVO> goodsList,Stage stage,ArrayList<LossOverItemVO> lossOverItemList){
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
            controller.setLossOverItemList(lossOverItemList);

            dialogStage.showAndWait();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void init(Stage stage,ArrayList<GoodsVO> goodsList,ArrayList<GiftItemVO> giftItemList){
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
            controller.setGiftItemList(giftItemList);

            dialogStage.showAndWait();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

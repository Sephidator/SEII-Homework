package main.java.presentation.promotionui;

import com.jfoenix.controls.JFXDatePicker;
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
import main.java.businesslogicservice.promotionblservice.PromotionBlService;
import main.java.presentation.uiutility.AddGoodsUIController;
import main.java.presentation.uiutility.CheckInput;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.promotion.PromotionGoodsVO;
import main.java.vo.promotion.PromotionGoodsVO;
import main.java.vo.promotion.PromotionVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PromotionGoodsUIController extends InfoUIController {
    private PromotionBlService service;
    private PromotionGoodsVO promotion;
    private ArrayList<GoodsVO> goodsList;
    private ArrayList<GoodsItemVO> goodsItemList;

    private ObservableList<GoodsItemVO> goodsItemObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<GoodsItemVO> goodsItemTableView;
    @FXML
    private TableColumn<GoodsItemVO,String> IDColumn;
    @FXML
    private TableColumn<GoodsItemVO,String> nameColumn;
    @FXML
    private TableColumn<GoodsItemVO,String> modelColumn;
    @FXML
    private TableColumn<GoodsItemVO,String> numberColumn;

    @FXML
    private TextField ID; // 单据编号
    @FXML
    private JFXDatePicker start; //起始时间
    @FXML
    private JFXDatePicker end; //结束时间
    @FXML
    private TextField discount; // 折让金额
    
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;


    // 加载文件后调用的方法******************************************

    public void initialize(){
        IDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getID()));
        nameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getName()));
        modelColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().goods.getModel())));
        numberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().number)));
    }

    // 设置controller数据的方法*****************************************

    public void setPromotion(PromotionGoodsVO promotion) {
        this.promotion=promotion;
        ID.setText(promotion.getID());
        start.getEditor().setText(new SimpleDateFormat("yyyy-MM-dd").format(promotion.getStart()));
        end.getEditor().setText(new SimpleDateFormat("yyyy-MM-dd").format(promotion.getEnd()));
        discount.setText(String.valueOf(promotion.getDiscount()));
    }

    public void setService(PromotionBlService service) {
        this.service=service;
    }

    public void setGoodsList(ArrayList<GoodsVO> goodsList) {
        this.goodsList=goodsList;
    }

    public void setGoodsItemList(ArrayList<GoodsItemVO> goodsItemList) {
        this.goodsItemList=goodsItemList;
        showGoodsItemList(goodsItemList);
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
    private void showGoodsItemList(ArrayList<GoodsItemVO> goodsItemList){
        if(goodsItemList!=null){
            goodsItemTableView.getItems().clear();
            goodsItemObservableList.setAll(goodsItemList);
            goodsItemTableView.setItems(goodsItemObservableList);
        }
    }


    // 界面之中会用到的方法******************************************

    @FXML
    private void addGoods(){
        AddGoodsUIController.init(goodsList,goodsItemList,dialogStage,false);
        showGoodsItemList(goodsItemList);
        promotion.setGoodsList(goodsItemList);
    }

    @FXML
    private void deleteGoods(){
        if(isGoodsItemSelected()){
            int selectedIndex=goodsItemTableView.getSelectionModel().getSelectedIndex();
            goodsItemTableView.getItems().remove(selectedIndex);
            goodsItemList.remove(selectedIndex);
            promotion.setGoodsList(goodsItemList);
        }
    }

    @FXML
    private void goodsNumberPlus(){
        if(isGoodsItemSelected()){
            int selectedIndex=goodsItemTableView.getSelectionModel().getSelectedIndex();
            if(goodsItemList.get(selectedIndex).number<goodsItemList.get(selectedIndex).goods.getNumber()){
                goodsItemList.get(selectedIndex).number++;
                promotion.setGoodsList(goodsItemList);
                showGoodsItemList(goodsItemList);
                goodsItemTableView.getSelectionModel().select(selectedIndex);
            }
        }
    }

    @FXML
    private void goodsNumberMinus(){
        if(isGoodsItemSelected()){
            int selectedIndex=goodsItemTableView.getSelectionModel().getSelectedIndex();
            ArrayList<GoodsItemVO> goodsItemList=promotion.getGoodsList();
            goodsItemList.get(selectedIndex).number--;
            if(goodsItemList.get(selectedIndex).number==0){
                goodsItemList.remove(selectedIndex);
                promotion.setGoodsList(goodsItemList);
                showGoodsItemList(goodsItemList);
            }
            else{
                promotion.setGoodsList(goodsItemList);
                showGoodsItemList(goodsItemList);
                goodsItemTableView.getSelectionModel().select(selectedIndex);
            }
        }
    }

    @FXML
    private void handleDiscountCorrect(){
        String str=discount.getText();

        if(CheckInput.isPositiveNumber(str)){
            Double number=Double.parseDouble(str);
            promotion.setDiscount(number);

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("折让设置成功");
            alert.setContentText("促销策略的折让为："+str);
            alert.showAndWait();
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

    private boolean isGoodsItemSelected(){
        int selectedIndex=goodsItemTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            // Nothing selected
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("未选择商品");
            alert.setContentText("请在商品列表中选择商品");
            alert.showAndWait();
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    public void showInfo(PromotionVO promotion, Stage stage){
        init(null,(PromotionGoodsVO)promotion,3,stage);
    }

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(PromotionBlService service,PromotionGoodsVO promotion, int command,Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/promotionui/PromotionGoodsUI.fxml"));

            // Create the dialog stage
            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("商品促销策略");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            PromotionGoodsUIController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setService(service);
            controller.setPromotion(promotion);

            //controller.setGoodsList(service.getGoodsList(null));
            ArrayList<GoodsVO> list=new ArrayList<GoodsVO>();
            GoodsVO goods1=new GoodsVO("电灯", null, "大号", 5, 20, 30, 20, 30, 40, "备注");
            goods1.setID("123");
            GoodsVO goods2=new GoodsVO("台灯", null, "小号", 5, 30, 60, 30, 60, 40, "备注");
            goods2.setID("233");
            list.add(goods1);
            list.add(goods2);

            controller.setGoodsList(list);
            controller.setGoodsItemList(promotion.getGoodsList());
            controller.setPaneFunction(command);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


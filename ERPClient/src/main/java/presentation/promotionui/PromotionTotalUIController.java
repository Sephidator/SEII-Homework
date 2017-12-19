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
import main.java.vo.goods.GiftItemVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.promotion.PromotionTotalVO;
import main.java.vo.promotion.PromotionVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PromotionTotalUIController extends InfoUIController {
    private PromotionBlService service;
    private PromotionTotalVO promotion;
    private ArrayList<GoodsVO> goodsList;
    private ArrayList<GiftItemVO> giftItemList;

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
    private JFXDatePicker start; //起始时间
    @FXML
    private JFXDatePicker end; //结束时间
    @FXML
    private TextField total; // 总价
    @FXML
    private TextField voucher; // 代金券金额

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

    public void setPromotion(PromotionTotalVO promotion) {
        this.promotion=promotion;
        ID.setText(promotion.getID());
        start.getEditor().setText(new SimpleDateFormat("yyyy-MM-dd").format(promotion.getStart()));
        end.getEditor().setText(new SimpleDateFormat("yyyy-MM-dd").format(promotion.getEnd()));
        total.setText(String.valueOf(promotion.getTotal()));
        voucher.setText(String.valueOf(promotion.getVoucher()));
    }

    public void setService(PromotionBlService service) {
        this.service=service;
    }

    public void setGoodsList(ArrayList<GoodsVO> goodsList) {
        this.goodsList=goodsList;
    }

    public void setGiftItemList(ArrayList<GiftItemVO> giftItemList) {
        this.giftItemList=giftItemList;
        showGiftItemList(giftItemList);
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
        }
        else if(command==2){
            confirm.setText("确认编辑");
        }
        else if(command==3){
            confirm.setText("确定");
        }
        cancel.setText("取消");
    }

    /**
     * 取得商品列表并修改ObservableList的信息
     * */
    private void showGiftItemList(ArrayList<GiftItemVO> giftItemList){
        if(giftItemList!=null){
            giftItemTableView.getItems().clear();
            giftItemObservableList.setAll(giftItemList);
            giftItemTableView.setItems(giftItemObservableList);
        }
    }


    // 界面之中会用到的方法******************************************

    @FXML
    private void addGoods(){
        AddGoodsUIController.init(dialogStage,goodsList,giftItemList);
        showGiftItemList(giftItemList);
        promotion.setGiftList(giftItemList);
    }

    @FXML
    private void deleteGoods(){
        if(isGiftItemSelected()){
            int selectedIndex=giftItemTableView.getSelectionModel().getSelectedIndex();
            giftItemTableView.getItems().remove(selectedIndex);
            giftItemList.remove(selectedIndex);
            promotion.setGiftList(giftItemList);
        }
    }

    @FXML
    private void goodsNumberPlus(){
        if(isGiftItemSelected()){
            int selectedIndex=giftItemTableView.getSelectionModel().getSelectedIndex();
            giftItemList.get(selectedIndex).number++;
            promotion.setGiftList(giftItemList);
            showGiftItemList(giftItemList);
            giftItemTableView.getSelectionModel().select(selectedIndex);
        }
    }

    @FXML
    private void goodsNumberMinus(){
        if(isGiftItemSelected()){
            int selectedIndex=giftItemTableView.getSelectionModel().getSelectedIndex();
            ArrayList<GiftItemVO> giftItemList=promotion.getGiftList();
            giftItemList.get(selectedIndex).number--;
            if(giftItemList.get(selectedIndex).number==0){
                giftItemList.remove(selectedIndex);
                promotion.setGiftList(giftItemList);
                showGiftItemList(giftItemList);
            }
            else{
                promotion.setGiftList(giftItemList);
                showGiftItemList(giftItemList);
                giftItemTableView.getSelectionModel().select(selectedIndex);
            }
        }
    }

    @FXML
    private void handleTotalCorrect(){
        String str=total.getText();

        if(CheckInput.isPositiveNumber(str)){
            Double number=Double.parseDouble(str);
            promotion.setTotal(number);

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("折让设置成功");
            alert.setContentText("促销策略的折让为："+str);
            alert.showAndWait();
        }
    }

    @FXML
    private void handleVoucherCorrect(){
        String str=voucher.getText();

        if(CheckInput.isPositiveNumber(str)){
            Double number=Double.parseDouble(str);
            promotion.setVoucher(number);

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("代金券金额设置成功");
            alert.setContentText("促销策略发放的代金券金额为："+str);
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
        dialogStage.close();
    }

    private boolean isGiftItemSelected(){
        int selectedIndex=giftItemTableView.getSelectionModel().getSelectedIndex();
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
        init(null,(PromotionTotalVO)promotion,3,stage);
    }

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(PromotionBlService service,PromotionTotalVO promotion, int command,Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/promotionui/PromotionTotalUI.fxml"));

            // Create the dialog stage
            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("总价促销策略");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            PromotionTotalUIController controller=loader.getController();
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
            controller.setGiftItemList(promotion.getGiftList());
            controller.setPaneFunction(command);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


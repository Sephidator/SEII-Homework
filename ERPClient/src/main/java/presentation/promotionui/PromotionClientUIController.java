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
import main.java.exception.DataException;
import main.java.exception.ExistException;
import main.java.exception.NotExistException;
import main.java.presentation.uiutility.AddGoodsUIController;
import main.java.presentation.uiutility.UITool;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.goods.GiftItemVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.promotion.PromotionClientVO;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class PromotionClientUIController extends InfoUIController {
    private PromotionBlService service;
    private PromotionClientVO promotion;
    private ArrayList<GoodsVO> goodsList;

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
    private TextField ID; // 促销策略编号
    @FXML
    private TextField name; // 促销策略名称
    @FXML
    private TextField level; // 客户等级
    @FXML
    private JFXDatePicker start; //起始时间
    @FXML
    private JFXDatePicker end; //结束时间
    @FXML
    private TextField discount; // 折让金额
    @FXML
    private TextField voucher; // 代金券金额

    @FXML
    private Button confirm;
    @FXML
    private Button cancel;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button plus;
    @FXML
    private Button minus;


    // 加载文件后调用的方法******************************************

    public void initialize(){
        IDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getID()));
        nameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getName()));
        modelColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().goods.getModel())));
        numberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().number)));
    }

    // 设置controller数据的方法*****************************************

    public void setPromotion(PromotionClientVO promotion) {
        this.promotion=promotion;
        ID.setText(promotion.getID());
        name.setText(promotion.getName());
        level.setText(promotion.getClientLevel()==0?"":String.valueOf(promotion.getClientLevel()));
        discount.setText(String.valueOf(promotion.getDiscount()));
        voucher.setText(String.valueOf(promotion.getVoucher()));

        start.setValue(promotion.getStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        end.setValue(promotion.getEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        showGiftItemList();
    }

    public void setService(PromotionBlService service) {
        this.service=service;
    }

    public void setGoodsList(ArrayList<GoodsVO> goodsList) {
        this.goodsList=goodsList;
    }


    /**
     * 根据数字来设置按钮的文字
     * 按钮被点击时，根据不同的文字执行不同的功能
     * 好吧这个控制耦合有点蠢，但是做的工作少一点
     * 1对应新建单据；2对应编辑单据；3对应查看单据
     * */
    public void setPaneFunction(int command){
        if(command==1){
            confirm.setText("添加");
        }
        else if(command==2){
            confirm.setText("编辑");
        }
        else if(command==3){
            confirm.setText("确定");

            ID.setEditable(false);
            name.setEditable(false);
            discount.setEditable(false);
            voucher.setEditable(false);
            level.setEditable(false);

            start.setOnMouseClicked(e -> start.hide());
            end.setOnMouseClicked(e -> end.hide());

            add.setDisable(true);
            delete.setDisable(true);
            minus.setDisable(true);
            plus.setDisable(true);
        }
    }

    /**
     * 取得商品列表并修改ObservableList的信息
     * */
    private void showGiftItemList(){
        ArrayList<GiftItemVO> giftItemList=promotion.getGiftList();
        giftItemObservableList.clear();
        giftItemObservableList.setAll(giftItemList);
        giftItemTableView.setItems(giftItemObservableList);
    }


    // 界面之中会用到的方法******************************************

    @FXML
    private void addGoods(){
        AddGoodsUIController.init(dialogStage,goodsList,promotion.getGiftList());
        showGiftItemList();
    }

    @FXML
    private void deleteGoods(){
        if(isGiftItemSelected()){
            int selectedIndex=giftItemTableView.getSelectionModel().getSelectedIndex();
            promotion.getGiftList().remove(selectedIndex);
            showGiftItemList();
        }
    }

    @FXML
    private void goodsNumberPlus(){
        if(isGiftItemSelected()){
            int selectedIndex=giftItemTableView.getSelectionModel().getSelectedIndex();
            promotion.getGiftList().get(selectedIndex).number++;
            showGiftItemList();
            giftItemTableView.getSelectionModel().select(selectedIndex);
        }
    }

    @FXML
    private void goodsNumberMinus(){
        if(isGiftItemSelected()){
            int selectedIndex=giftItemTableView.getSelectionModel().getSelectedIndex();
            promotion.getGiftList().get(selectedIndex).number--;

            if(promotion.getGiftList().get(selectedIndex).number==0){
                promotion.getGiftList().remove(selectedIndex);
                showGiftItemList();
            }
            else{
                showGiftItemList();
                giftItemTableView.getSelectionModel().select(selectedIndex);
            }
        }
    }

    @FXML
    private void handleConfirm(){
        if(isInputValid()){
            String text=confirm.getText();

            try{
                if(text.equals("添加")){
                    String promotionID=service.addPromotion(promotion);
                    String promotionName=promotion.getName();

                    UITool.showAlert(Alert.AlertType.INFORMATION,
                            "Success","添加促销策略成功",
                            "促销策略ID："+promotionID+System.lineSeparator()+"名字："+promotionName);
                }
                else if(text.equals("编辑")){
                    service.editPromotion(promotion);
                    String promotionID=promotion.getID();
                    String promotionName=promotion.getName();

                    UITool.showAlert(Alert.AlertType.INFORMATION,
                            "Success","添加促销策略成功",
                            "促销策略ID："+promotionID+System.lineSeparator()+"名字："+promotionName);
                }

                dialogStage.close();
            }catch(DataException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"促销策略失败", "数据库错误");
            }catch(NotExistException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"促销策略失败","促销策略不存在");
            }catch(ExistException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"促销策略失败","名称和已有促销策略重复");
            }catch(Exception e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"促销策略失败","RMI连接错误");
            }
        }
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
            UITool.showAlert(Alert.AlertType.ERROR,
                    "No Selection","未选择商品","请在商品列表中选择商品");
            return false;
        }
    }

    /**
     * 检查促销策略信息的输入是否完整且合法
     * 完整且合法返回true
     * */
    private boolean isInputValid(){
        String errorMessage = "";

        if(name.getText().length()==0) {
            errorMessage+=("未输入促销策略名称。"+System.lineSeparator());
        }

        if(level.getText().length()==0){
            errorMessage+=("未输入客户等级。"+System.lineSeparator());
        }
        else{
            try {
                int i=Integer.parseInt(level.getText());
                if(i<1||i>5)
                    throw new NumberFormatException();
            } catch(NumberFormatException e) {
                errorMessage+=("客户等级必须在1~5之间。"+System.lineSeparator());
            }
        }

        if(discount.getText().length()==0){
            errorMessage+=("未输入总价。"+System.lineSeparator());
        }
        else{
            try {
                double i=Double.parseDouble(discount.getText());
                if(i<0)
                    throw new NumberFormatException();
            } catch(NumberFormatException e) {
                errorMessage+=("折让金额必须是非负数。"+System.lineSeparator());
            }
        }

        if(voucher.getText().length()==0){
            errorMessage+=("未输入发放代金券金额。"+System.lineSeparator());
        }
        else{
            try {
                double i=Double.parseDouble(voucher.getText());
                if(i<0)
                    throw new NumberFormatException();
            } catch(NumberFormatException e) {
                errorMessage+=("代金券金额必须是非负数。"+System.lineSeparator());
            }
        }

        if((start.getValue().isAfter(end.getValue()))){
            errorMessage+=("起始时间不得晚于结束时间。"+System.lineSeparator());
        }

        if(errorMessage.length()==0){
            promotion.setName(name.getText());
            promotion.setClientLevel(Integer.parseInt(level.getText()));
            promotion.setDiscount(Double.parseDouble(discount.getText()));
            promotion.setVoucher(Double.parseDouble(voucher.getText()));

            Date startTime=Date.from(start.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            Date endTime=Date.from(end.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            try {
                endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end.getEditor().getText() + " 23:59:59");
            }catch(Exception e){
                e.printStackTrace();
            }

            promotion.setStart(startTime);
            promotion.setEnd(endTime);

            return true;
        } else {
            UITool.showAlert(Alert.AlertType.ERROR,
                    "促销策略信息错误", "请检查促销策略信息的输入", errorMessage);
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(PromotionBlService service,PromotionClientVO promotion, int command,Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/promotionui/PromotionClientUI.fxml"));

            // Create the dialog stage
            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("客户促销策略");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            PromotionClientUIController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setService(service);
            controller.setPromotion(promotion);
            controller.setGoodsList(service.getGoodsList(null));
            controller.setPaneFunction(command);

            Date now=new Date();
            if(command==2 && now.after(promotion.getStart())){
                UITool.showAlert(Alert.AlertType.INFORMATION,
                        "无法编辑","不允许修改促销策略","促销策略已经开始实施");
                controller.setPaneFunction(3);
            }

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


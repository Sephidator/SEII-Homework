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
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.promotion.PromotionGoodsVO;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class PromotionGoodsUIController extends InfoUIController {
    private PromotionBlService service;
    private PromotionGoodsVO promotion;
    private ArrayList<GoodsVO> goodsList;

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
    private TextField ID; // 促销策略编号
    @FXML
    private TextField name; // 促销策略名称
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

    public void setPromotion(PromotionGoodsVO promotion) {
        this.promotion=promotion;
        ID.setText(promotion.getID());
        name.setText(promotion.getName());
        discount.setText(String.valueOf(promotion.getDiscount()));

        start.setValue(promotion.getStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        end.setValue(promotion.getEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        showGoodsItemList();
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
     * 1对应新建促销策略；2对应编辑促销策略；3对应查看促销策略
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
    private void showGoodsItemList(){
        ArrayList<GoodsItemVO> goodsItemList=promotion.getGoodsList();
        goodsItemTableView.getItems().clear();
        goodsItemObservableList.setAll(goodsItemList);
        goodsItemTableView.setItems(goodsItemObservableList);
    }


    // 界面之中会用到的方法******************************************

    @FXML
    private void addGoods(){
        AddGoodsUIController.init(goodsList,promotion.getGoodsList(),dialogStage,false);
        showGoodsItemList();
    }

    @FXML
    private void deleteGoods(){
        if(isGoodsItemSelected()){
            int selectedIndex=goodsItemTableView.getSelectionModel().getSelectedIndex();
            promotion.getGoodsList().remove(selectedIndex);
            showGoodsItemList();
        }
    }

    @FXML
    private void goodsNumberPlus(){
        if(isGoodsItemSelected()){
            int selectedIndex=goodsItemTableView.getSelectionModel().getSelectedIndex();
            promotion.getGoodsList().get(selectedIndex).number++;
            showGoodsItemList();
            goodsItemTableView.getSelectionModel().select(selectedIndex);
        }
    }

    @FXML
    private void goodsNumberMinus(){
        if(isGoodsItemSelected()){
            int selectedIndex=goodsItemTableView.getSelectionModel().getSelectedIndex();
            promotion.getGoodsList().get(selectedIndex).number--;

            if(promotion.getGoodsList().get(selectedIndex).number==0){
                promotion.getGoodsList().remove(selectedIndex);
                showGoodsItemList();
            }
            else{
                showGoodsItemList();
                goodsItemTableView.getSelectionModel().select(selectedIndex);
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
                        "Error",text+"促销策略失败","账号和已有促销策略重复");
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

    private boolean isGoodsItemSelected(){
        int selectedIndex=goodsItemTableView.getSelectionModel().getSelectedIndex();
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

        if(discount.getText().length()==0){
            errorMessage+=("未输入折让金额。"+System.lineSeparator());
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

        if((start.getValue().isAfter(end.getValue()))){
            errorMessage+=("起始时间不得晚于结束时间。"+System.lineSeparator());
        }

        if (goodsItemObservableList==null||goodsItemObservableList.size()==0) {
            errorMessage+=("未添加组合商品。"+System.lineSeparator());
        }

        if(errorMessage.length()==0){
            promotion.setName(name.getText());
            promotion.setDiscount(Double.parseDouble(discount.getText()));

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
            controller.setGoodsList(service.getGoodsList(null));
            controller.setPaneFunction(command);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


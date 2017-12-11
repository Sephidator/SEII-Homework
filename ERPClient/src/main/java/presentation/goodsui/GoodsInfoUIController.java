package main.java.presentation.goodsui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.businesslogicservice.goodsblservice.GoodsBlService;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.goods.GoodsVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class GoodsInfoUIController extends InfoUIController{
    private GoodsVO goods;
    private GoodsBlService goodsBlService;

    @FXML
    private TextField ID; // 商品编号
    @FXML
    private TextField name; // 商品名称
    @FXML
    private TextField sort; // 商品分类
    @FXML
    private TextField model; // 商品型号
    @FXML
    private TextField number; // 商品数量
    @FXML
    private TextField alarmNum; // 报警数量
    @FXML
    private TextField cost; // 进价
    @FXML
    private TextField retail; // 零售价
    @FXML
    private TextField latestCost; // 最近进价
    @FXML
    private TextField latestRetail; // 最近零售价
    @FXML
    private TextArea comment;// 商品备注

    @FXML
    private Button confirm;
    @FXML
    private Button cancel;

    // 加载文件后调用的方法******************************************

    /**
     * 设置各个选择框的信息
     * */
    public void initialize(){
    }

    // 设置controller数据的方法*****************************************

    public void setGoods(GoodsVO goods) {
        this.goods = goods;
        ID.setText("123");
        name.setText(goods.getName());
        sort.setText("sort");
        model.setText("型号");
        number.setText(String.valueOf(goods.getNumber()));
        alarmNum.setText(String.valueOf(goods.getAlarmNum()));
        cost.setText(String.valueOf(goods.getCost()));
        retail.setText(String.valueOf(goods.getRetail()));
        latestCost.setText(String.valueOf(goods.getLatestCost()));
        latestRetail.setText(String.valueOf(goods.getLatestRetail()));
    }

    public void setGoodsBlService(GoodsBlService goodsBlService) {
        this.goodsBlService = goodsBlService;
    }

    /**
     * 根据数字来设置按钮的文字
     * 按钮被点击时，根据不同的文字执行不同的功能
     * 好吧这个控制耦合有点蠢，但是做的工作少一点
     * 1对应添加客户；2对应编辑客户界面；3对应查看客户界面
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
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleConfirm(){
        String text=confirm.getText();
        /*
        if(text.equals("添加")){
            goodsBlService.addGoods(goods);
        }
        else if(text.equals("编辑")){
            goodsBlService.editGoods(goods);
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

    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(GoodsBlService service,GoodsVO goods, int command,Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/goodsui/GoodsInfoUI.fxml"));

            // Create the dialog stage
            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("商品信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            GoodsInfoUIController controller=loader.getController();
            controller.setGoodsBlService(service);
            controller.setGoods(goods);
            controller.setDialogStage(dialogStage);
            controller.setPaneFunction(command);


            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
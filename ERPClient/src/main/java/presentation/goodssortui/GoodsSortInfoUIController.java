package main.java.presentation.goodssortui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.businesslogicservice.goodssortblservice.GoodsSortBlService;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.goods.GoodsSortVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class GoodsSortInfoUIController extends InfoUIController {
    private GoodsSortVO goodsSort;
    private GoodsSortBlService goodsSortBlService;

    @FXML
    private TextField ID; // 商品分类编号
    @FXML
    private TextField name; // 商品分类名称
    @FXML
    private TextField comment; // 备注
    @FXML
    private TextField father; // 父分类
    @FXML
    private TextField children; // 子分类
    @FXML
    private TextField goodsList; // 商品列表
    
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

    public void setGoodsSort(GoodsSortVO goodsSort) {
        this.goodsSort = goodsSort;
        ID.setText("123");
        name.setText(goodsSort.getName());
        comment.setText(goodsSort.getComment());
        father.setText("father"); //goodsSort.getFather().getName()

        String str="";
        for(GoodsSortVO child: goodsSort.getChildren()){
            str+=child.getName()+";";
        }
        children.setText(str);

        str="";
        for(GoodsVO goods:goodsSort.getGoods()){
            str+=goods.getName();
        }
        goodsList.setText(str);
    }

    public void setGoodsSortBlService(GoodsSortBlService goodsSortBlService) {
        this.goodsSortBlService = goodsSortBlService;
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
            goodsSortBlService.addGoodsSort(goodsSort);
        }
        else if(text.equals("编辑")){
            goodsSortBlService.editGoodsSort(goodsSort);
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
    public static void init(GoodsSortBlService service,GoodsSortVO goodsSort, int command,Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/goodssortui/GoodsSortInfoUI.fxml"));

            // Create the dialog stage
            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("客户信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            GoodsSortInfoUIController controller=loader.getController();
            controller.setGoodsSortBlService(service);
            controller.setGoodsSort(goodsSort);
            controller.setDialogStage(dialogStage);
            controller.setPaneFunction(command);


            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
package main.java.presentation.goodssortui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.businesslogicfactory.goodssortblfactory.GoodsSortBlFactory;
import main.java.businesslogicservice.goodssortblservice.GoodsSortBlService;
import main.java.exception.DataException;
import main.java.exception.ExistException;
import main.java.exception.NotExistException;
import main.java.exception.NotNullException;
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

        try {
            ID.setText(goodsSort.getID());
            name.setText(goodsSort.getName());
            comment.setText(goodsSort.getComment());
            if(goodsSort.getFatherID()==null){
                father.setText("");
            }
            else{
                father.setText(GoodsSortBlFactory.getService().find(goodsSort.getFatherID()).getName());
            }

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
        }catch(DataException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("查找商品分类失败");
            alert.setContentText("数据库错误");
            alert.showAndWait();
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("查找商品分类失败");
            alert.setContentText("RMI连接错误");
            alert.showAndWait();
        }
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
            confirm.setText("添加");
        }
        else if(command==2){
            confirm.setText("编辑");
        }
        else if(command==3){
            confirm.setText("确定");
            ID.setEditable(false);
            name.setEditable(false);
            father.setEditable(false);
            comment.setEditable(false);
            children.setEditable(false);
            goodsList.setEditable(false);
        }
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleConfirm(){
        if(isInputValid()){
            String text=confirm.getText();

            try{
                if(text.equals("添加")){
                    String sortID=goodsSortBlService.addGoodsSort(goodsSort);
                    String sortName=goodsSort.getName();

                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("添加商品分类成功");
                    alert.setContentText("商品分类ID："+sortID+System.lineSeparator()+"名字："+sortName);
                    alert.showAndWait();
                }
                else if(text.equals("编辑")){
                    goodsSortBlService.editGoodsSort(goodsSort);
                    String sortID=goodsSort.getID();
                    String sortName=goodsSort.getName();

                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("编辑商品分类成功");
                    alert.setContentText("商品分类ID："+sortID+System.lineSeparator()+"名字："+sortName);
                    alert.showAndWait();
                }

                dialogStage.close();
            }catch(DataException e){
                e.printStackTrace();
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(text+"商品分类失败");
                alert.setContentText("数据库错误");
                alert.showAndWait();
            }
            catch(ExistException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(text+"商品分类失败");
                alert.setContentText("和已存在的商品分类重名");
                alert.showAndWait();
            }catch(NotExistException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(text+"商品分类失败");
                alert.setContentText("商品分类不存在");
                alert.showAndWait();
            }
            catch(NotNullException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(text+"商品分类失败");
                alert.setContentText("父分类下有商品");
                alert.showAndWait();
            }catch(Exception e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(text+"商品分类失败");
                alert.setContentText("RMI连接错误");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    /**
     * 检查用户信息的输入是否完整且合法
     * 完整且合法返回true
     * */
    private boolean isInputValid(){
        String errorMessage = "";

        if (name.getText().length()==0) {
            errorMessage+=("未输入商品分类名。"+System.lineSeparator());
        }

        if(errorMessage.length()==0){
            goodsSort.setName(name.getText());
            goodsSort.setComment(comment.getText());
            return true;
        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("商品分类信息错误");
            alert.setHeaderText("请检查商品分类信息的输入");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
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
            dialogStage.setTitle("商品分类信息界面");
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
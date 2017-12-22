package main.java.presentation.goodsui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.java.MainApp;
import main.java.businesslogicfactory.goodsblfactory.GoodsBlFactory;
import main.java.businesslogicservice.goodsblservice.GoodsBlService;
import main.java.exception.DataException;
import main.java.exception.NotExistException;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.InventoryPanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class GoodsUIController extends CenterUIController {
    private GoodsBlService goodsBlService;

    private ObservableList<GoodsVO> goodsObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<GoodsVO> goodsTableView;
    @FXML
    private TableColumn<GoodsVO,String> goodsIDColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsNameColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsCostColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsRetailColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsNumberColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsCommentColumn;

    @FXML
    private TextField searchInfo;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        goodsIDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));
        goodsNameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getName()));
        goodsCostColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getCost())));
        goodsRetailColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getRetail())));
        goodsNumberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getNumber())));
        goodsCommentColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getComment()));
    }

    // 设置controller数据的方法*****************************************

    public void setGoodsBlService(GoodsBlService goodsBlService) {
        this.goodsBlService = goodsBlService;
        refresh(null);
    }

    /**
     * 刷新界面，取得所有商品的列表，并显示在tableview中
     * */
    private void refresh(GoodsQueryVO query){
        try {
            ArrayList<GoodsVO> goodsList = goodsBlService.getGoodsList(query);
            showGoodsList(goodsList);
        }catch(DataException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("查找商品失败");
            alert.setContentText("数据库错误");
            alert.showAndWait();
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("查找商品失败");
            alert.setContentText("RMI连接错误");
            alert.showAndWait();
        }
    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showGoodsList(ArrayList<GoodsVO> goodsList){
        goodsObservableList.removeAll();
        goodsObservableList.setAll(goodsList);
        goodsTableView.setItems(goodsObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleSearch(){
        String text=searchInfo.getText();
        if(text.equals("")){
            refresh(null);
        }
        else{
            GoodsQueryVO query=new GoodsQueryVO(text,text);
            refresh(query);
        }
    }

    @FXML
    private void handleDeleteGoods(){
        if(isGoodsSelected()){
            try {
                String ID = goodsTableView.getSelectionModel().getSelectedItem().getID();
                String name = goodsTableView.getSelectionModel().getSelectedItem().getName();
                goodsBlService.deleteGoods(ID);

                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("删除商品成功");
                alert.setContentText("商品ID："+ID+System.lineSeparator()+"名字："+name);
                alert.showAndWait();
            }catch(DataException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("删除商品失败");
                alert.setContentText("数据库错误");
                alert.showAndWait();
            }catch(NotExistException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("删除商品失败");
                alert.setContentText("商品不存在");
                alert.showAndWait();
            }catch(Exception e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("删除商品失败");
                alert.setContentText("RMI连接错误");
                alert.showAndWait();
            }
            refresh(null);
        }
    }

    @FXML
    private void handleEditGoods(){
        if(isGoodsSelected()){
            GoodsInfoUIController.init(goodsBlService,goodsTableView.getSelectionModel().getSelectedItem(),2,root.getStage());
        }
        refresh(null);
    }

    @FXML
    private void handleCheckGoods() {
        if(isGoodsSelected()){
            GoodsInfoUIController.init(goodsBlService,goodsTableView.getSelectionModel().getSelectedItem(),3,root.getStage());
        }
    }

    private boolean isGoodsSelected(){
        int selectedIndex=goodsTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            // Nothing selected
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("未选中商品");
            alert.setContentText("请在表中选择商品");
            alert.showAndWait();
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    /**
     * 初始化方法，调用init方法
     * 之所以有这个方法是为了多态而提供的
     * */
    public void instanceInit(RootUIController root){
        init(root);
    }

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/goodsui/GoodsUI.fxml"));
            root.setCenterPane(loader.load());

            GoodsUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setGoodsBlService(GoodsBlFactory.getService());

            root.setReturnPaneController(new InventoryPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

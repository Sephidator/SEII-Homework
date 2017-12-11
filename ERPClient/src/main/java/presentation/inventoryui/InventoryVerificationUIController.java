package main.java.presentation.inventoryui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.MainApp;
import main.java.businesslogicservice.goodsblservice.GoodsBlService;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.InventoryPanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class InventoryVerificationUIController extends CenterUIController {
    private GoodsBlService goodsBlService;

    private ObservableList<GoodsVO> goodsObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<GoodsVO> goodsTableView;
    @FXML
    private TableColumn<GoodsVO,String> goodsIDColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsNameColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsModelColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsCostColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsRetailColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsNumberColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsCommentColumn;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        goodsIDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));
        goodsNameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getName()));
        goodsModelColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getModel()));
        goodsCostColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getCost())));
        goodsRetailColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getRetail())));
        goodsNumberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getNumber())));
        goodsCommentColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getComment()));
    }

    // 设置controller数据的方法*****************************************

    public void setGoodsBlService(GoodsBlService goodsBlService) {
        this.goodsBlService = goodsBlService;
        //ArrayList<GoodsVO> goodsList=goodsBlService.getGoodsList(null);
        //showGoodsList(goodsList);
    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showGoodsList(ArrayList<GoodsVO> goodsList){
        goodsObservableList.removeAll();

        for(int i=0;i<goodsList.size();i++){
            goodsObservableList.add(goodsList.get(i));
        }
        goodsTableView.setItems(goodsObservableList);
    }

    // 界面之中会用到的方法******************************************

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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/inventoryui/InventoryVerificationUI.fxml"));
            root.setCenterPane(loader.load());

            InventoryVerificationUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setGoodsBlService(null);

            GoodsVO c1=new GoodsVO("台灯1", null, "",50,20,30,20,30,10,"备注");
            c1.setID("123");
            GoodsVO c2=new GoodsVO("台灯1", null, "",50,20,30,20,30,10,"备注");
            c2.setID("123");

            ArrayList<GoodsVO> list=new ArrayList<>();
            list.add(c1);
            list.add(c2);

            controller.showGoodsList(list);

            root.setReturnPaneController(new InventoryPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

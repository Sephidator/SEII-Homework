package main.java.presentation.promotionui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.MainApp;
import main.java.businesslogicservice.promotionblservice.PromotionBlService;
import main.java.exception.DataException;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.ManagerPanelUIController;
import main.java.presentation.messageui.PurchaseSalePanelUIController;
import main.java.presentation.uiutility.AlertInfo;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.promotion.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PromotionUIController extends CenterUIController {
    private PromotionBlService promotionBlService;

    private ObservableList<PromotionVO> promotionObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<PromotionVO> promotionTableView;
    @FXML
    private TableColumn<PromotionVO,String> promotionIDColumn;
    @FXML
    private TableColumn<PromotionVO,String> promotionStartColumn;
    @FXML
    private TableColumn<PromotionVO,String> promotionEndColumn;
    @FXML
    private TableColumn<PromotionVO,String> promotionTypeColumn;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        promotionIDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));
        promotionStartColumn.setCellValueFactory(cellData->new SimpleStringProperty(new SimpleDateFormat("yyyy-MM-dd-EE").format(cellData.getValue().getStart())));
        promotionEndColumn.setCellValueFactory(cellData->new SimpleStringProperty(new SimpleDateFormat("yyyy-MM-dd-EE").format(cellData.getValue().getEnd())));
        promotionTypeColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getType()));
    }

    // 设置controller数据的方法*****************************************

    public void setPromotionBlService(PromotionBlService promotionBlService) {
        this.promotionBlService = promotionBlService;
    }

    /**
     * 刷新界面，取得所有商品的列表，并显示在tableview中
     * */
    private void refresh(PromotionQueryVO query){
        try {
            ArrayList<PromotionVO> promotionList = promotionBlService.getPromotionList(query);
            showPromotionList(promotionList);
        }catch(DataException e){
            AlertInfo.showAlert(Alert.AlertType.ERROR,
                    "Error","查找促销策略失败","数据库错误");
        }catch(Exception e){
            AlertInfo.showAlert(Alert.AlertType.ERROR,
                    "Error","查找促销策略失败","RMI连接错误");
        }
    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showPromotionList(ArrayList<PromotionVO> promotionList){
        promotionObservableList.removeAll();
        promotionObservableList.setAll(promotionList);
        promotionTableView.setItems(promotionObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void AddPromotionClient(){
        PromotionClientUIController.init(promotionBlService,new PromotionClientVO(),1,root.getStage());
    }

    @FXML
    private void AddPromotionTotal(){
        PromotionTotalUIController.init(promotionBlService,new PromotionTotalVO(),1,root.getStage());
    }

    @FXML
    private void AddPromotionGoods(){
        PromotionGoodsUIController.init(promotionBlService,new PromotionGoodsVO(),1,root.getStage());
    }

    @FXML
    private void handleDeletePromotion(){
        int selectedIndex=promotionTableView.getSelectionModel().getSelectedIndex();
        if(isPromotionSelected()){
            promotionTableView.getItems().remove(selectedIndex);
        }
    }

    @FXML
    private void handleCheckPromotion() {
        if(isPromotionSelected()){
            //PromotionInfoUIController.init(promotionBlService,promotionTableView.getSelectionModel().getSelectedItem(),3,root.getStage());
        }
    }

    private boolean isPromotionSelected(){
        int selectedIndex=promotionTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            // Nothing selected
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("未选中促销策略");
            alert.setContentText("请在表中选择促销策略");
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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/promotionui/PromotionUI.fxml"));
            root.setCenterPane(loader.load());

            PromotionUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setPromotionBlService(null);

            PromotionClientVO c1=new PromotionClientVO();
            c1.setID("123");
            PromotionTotalVO c2=new PromotionTotalVO();
            c2.setID("233");

            ArrayList<PromotionVO> list=new ArrayList<>();
            list.add(c1);
            list.add(c2);
            controller.showPromotionList(list);

            root.setReturnPaneController(new ManagerPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

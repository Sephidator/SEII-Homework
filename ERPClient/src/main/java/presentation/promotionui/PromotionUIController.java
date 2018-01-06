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
import main.java.businesslogicfactory.promotionblfactory.PromotionBlFactory;
import main.java.businesslogicservice.promotionblservice.PromotionBlService;
import main.java.exception.DataException;
import main.java.exception.NotExistException;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.ManagerPanelUIController;
import main.java.presentation.uiutility.UITool;
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
    private TableColumn<PromotionVO,String> promotionNameColumn;
    @FXML
    private TableColumn<PromotionVO,String> promotionStartColumn;
    @FXML
    private TableColumn<PromotionVO,String> promotionEndColumn;
    @FXML
    private TableColumn<PromotionVO,String> promotionTypeColumn;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的促销策略信息以及显示方法
     * */
    public void initialize(){
        promotionIDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));
        promotionNameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getName()));
        promotionStartColumn.setCellValueFactory(cellData->new SimpleStringProperty(new SimpleDateFormat("yyyy-MM-dd").format(cellData.getValue().getStart())));
        promotionEndColumn.setCellValueFactory(cellData->new SimpleStringProperty(new SimpleDateFormat("yyyy-MM-dd").format(cellData.getValue().getEnd())));
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
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找促销策略失败","数据库错误");
        }catch(Exception e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找促销策略失败","RMI连接错误");
        }
    }

    /**
     * 取得促销策略列表并修改ObservableList的信息
     * */
    private void showPromotionList(ArrayList<PromotionVO> promotionList){
        promotionObservableList.clear();
        promotionObservableList.setAll(promotionList);
        promotionTableView.setItems(promotionObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void AddPromotionClient(){
        PromotionClientUIController.init(promotionBlService,new PromotionClientVO(),1,root.getStage());
        refresh(null);
    }

    @FXML
    private void AddPromotionTotal(){
        PromotionTotalUIController.init(promotionBlService,new PromotionTotalVO(),1,root.getStage());
        refresh(null);
    }

    @FXML
    private void AddPromotionGoods(){
        PromotionGoodsUIController.init(promotionBlService,new PromotionGoodsVO(),1,root.getStage());
        refresh(null);
    }

    @FXML
    private void handleDeletePromotion(){
        if(isPromotionSelected()){
            try {
                String ID = promotionTableView.getSelectionModel().getSelectedItem().getID();
                String name = promotionTableView.getSelectionModel().getSelectedItem().getName();
                promotionBlService.deletePromotion(ID);

                UITool.showAlert(Alert.AlertType.INFORMATION,
                        "Success","删除促销策略成功",
                        "促销策略ID："+ID+System.lineSeparator()+"名字："+name);
            }catch(DataException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error","删除促销策略失败","数据库错误");
            }catch(NotExistException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error","删除促销策略失败","促销策略不存在");
            }catch(Exception e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error","删除促销策略失败","RMI连接错误");
            }
            refresh(null);
        }
    }

    @FXML
    private void handleEditPromotion() {
        if(isPromotionSelected()){
            PromotionVO promotion=promotionTableView.getSelectionModel().getSelectedItem();
            if(promotion.getType().equals("客户促销策略")){
                PromotionClientVO promotionClient=(PromotionClientVO)promotion;
                PromotionClientUIController.init(promotionBlService,promotionClient,2,root.getStage());
            }
            else if(promotion.getType().equals("商品促销策略")){
                PromotionGoodsVO promotionGoods=(PromotionGoodsVO)promotion;
                PromotionGoodsUIController.init(promotionBlService,promotionGoods,2,root.getStage());
            }
            else if(promotion.getType().equals("总价促销策略")){
                PromotionTotalVO promotionTotal=(PromotionTotalVO)promotion;
                PromotionTotalUIController.init(promotionBlService,promotionTotal,2,root.getStage());
            }
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
            controller.setPromotionBlService(PromotionBlFactory.getService());
            controller.refresh(null);

            root.setReturnPaneController(new ManagerPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

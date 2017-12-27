package main.java.presentation.inventoryui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.MainApp;
import main.java.businesslogicservice.inventoryblservice.InventoryCheckBlService;
import main.java.exception.DataException;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.InventoryPanelUIController;
import main.java.presentation.uiutility.UITool;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.goods.InventoryCheckItemVO;

import java.util.ArrayList;

public class InventoryCheckUIController extends CenterUIController {
    private InventoryCheckBlService service;

    private ObservableList<InventoryCheckItemVO> checkObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<InventoryCheckItemVO> checkTableView;
    @FXML
    private TableColumn<InventoryCheckItemVO,String> IDColumn;
    @FXML
    private TableColumn<InventoryCheckItemVO,String> nameColumn;
    @FXML
    private TableColumn<InventoryCheckItemVO,String> purchaseNumberColumn;
    @FXML
    private TableColumn<InventoryCheckItemVO,String> purchaseAmountColumn;
    @FXML
    private TableColumn<InventoryCheckItemVO,String> saleNumberColumn;
    @FXML
    private TableColumn<InventoryCheckItemVO,String> saleAmountColumn;
    @FXML
    private TableColumn<InventoryCheckItemVO,String> goodsNumberColumn;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        IDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getID()));
        nameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getName()));
        purchaseNumberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().purchaseNumber)));
        purchaseAmountColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().purchaseAmount)));
        saleNumberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().saleNumber)));
        saleAmountColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().saleAmount)));
        goodsNumberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().goods.getNumber())));
    }

    // 设置controller数据的方法*****************************************

    public void setService(InventoryCheckBlService service) {
        this.service = service;
    }

    public void refresh(){
        try{
            ArrayList<InventoryCheckItemVO> checkList=service.getInventoryCheck(null,null);
            showGoodsList(checkList);
        }catch(DataException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找单据失败","数据库错误");
        }catch(Exception e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找单据失败","RMI连接错误");
        }

    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showGoodsList(ArrayList<InventoryCheckItemVO> checkList){
        checkObservableList.removeAll();
        checkObservableList.setAll(checkList);
        checkTableView.setItems(checkObservableList);
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

            InventoryCheckUIController controller=loader.getController();
            controller.setRoot(root);

            root.setReturnPaneController(new InventoryPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

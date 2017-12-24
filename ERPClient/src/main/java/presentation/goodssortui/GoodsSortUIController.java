package main.java.presentation.goodssortui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.util.Callback;
import main.java.MainApp;
import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodssortbl.GoodsSortBl;
import main.java.businesslogicfactory.goodsblfactory.GoodsBlFactory;
import main.java.businesslogicfactory.goodssortblfactory.GoodsSortBlFactory;
import main.java.businesslogicservice.goodsblservice.GoodsBlService;
import main.java.businesslogicservice.goodssortblservice.GoodsSortBlService;
import main.java.exception.DataException;
import main.java.exception.NotExistException;
import main.java.exception.NotNullException;
import main.java.presentation.goodsui.GoodsInfoUIController;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.InventoryPanelUIController;
import main.java.presentation.uiutility.AlertInfo;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.goods.GoodsSortVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class GoodsSortUIController extends CenterUIController {
    private GoodsSortBlService goodsSortBlService;
    private GoodsBlService goodsBlService;
    @FXML
    private TreeView goodsSortTreeView;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
    }

    // 设置controller数据的方法*****************************************

    public void setGoodsSortBlService(GoodsSortBlService goodsSortBlService) {
        this.goodsSortBlService=goodsSortBlService;
    }

    public void setGoodsBlService(GoodsBlService goodsBlService) {
        this.goodsBlService=goodsBlService;
    }


    private void refresh(){
        try{
            GoodsSortVO root= GoodsSortBlFactory.getService().getRoot();
            showGoodsSort(null,root);
        }catch(DataException e){
            AlertInfo.showAlert(Alert.AlertType.ERROR,
                    "Error","查找商品分类失败","数据库错误");
        }catch(Exception e){
            AlertInfo.showAlert(Alert.AlertType.ERROR,
                    "Error","查找商品分类失败","RMI连接错误");
        }
    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showGoodsSort(TreeItem fatherItem, GoodsSortVO newSort){
        TreeItem newItem= new TreeItem(newSort);
        newItem.setExpanded(true);

        if(fatherItem==null){
            goodsSortTreeView.setRoot(newItem);
        }
        else{
            fatherItem.getChildren().add(newItem);
        }

        for(GoodsSortVO child:newSort.getChildren()){
            showGoodsSort(newItem,child);
        }

        for(GoodsVO goods:newSort.getGoods()){
            TreeItem<GoodsVO> goodsTreeItem=new TreeItem(goods);
            goodsTreeItem.setExpanded(false);
            newItem.getChildren().add(goodsTreeItem);
        }
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleAddSort(){
        if(isGoodsSortSelected()){
            int selectedIndex=goodsSortTreeView.getSelectionModel().getSelectedIndex();
            TreeItem<GoodsSortVO> sortItem=goodsSortTreeView.getTreeItem(selectedIndex);
            GoodsSortVO sort=sortItem.getValue();

            if(sort.getGoods().size()!=0){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error","添加商品分类失败","父分类下有商品");
            }
            else{
                GoodsSortVO son=new GoodsSortVO();
                son.setFather(sort.getID());
                GoodsSortInfoUIController.init(goodsSortBlService,son,1,root.getStage());
                refresh();
            }
        }
    }

    @FXML
    private void handleDeleteSort(){
        if(isGoodsSortSelected()){
            int selectedIndex=goodsSortTreeView.getSelectionModel().getSelectedIndex();
            TreeItem<GoodsSortVO> sortItem=goodsSortTreeView.getTreeItem(selectedIndex);
            GoodsSortVO sort=sortItem.getValue();

            if(!sortItem.isLeaf()){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error","删除商品分类失败","商品分类下有子分类或商品");
            }
            else if(selectedIndex==0){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error","删除商品分类失败","不能删除总分类");
            }
            else{
                try {
                    String ID = sort.getID();
                    String name = sort.getName();
                    goodsSortBlService.deleteGoodsSort(ID);

                    AlertInfo.showAlert(Alert.AlertType.INFORMATION,
                            "Success","删除商品分类成功",
                            "分类ID："+ID+System.lineSeparator()+"名字："+name);
                }catch(DataException e){
                    AlertInfo.showAlert(Alert.AlertType.ERROR,
                            "Error","删除商品分类失败","数据库错误");
                }catch(NotExistException e){
                    AlertInfo.showAlert(Alert.AlertType.ERROR,
                            "Error","删除商品分类失败","商品分类不存在");
                }catch(NotNullException e){
                    AlertInfo.showAlert(Alert.AlertType.ERROR,
                            "Error","删除商品分类失败","商品分类下有子类或商品");
                }catch(Exception e){
                    AlertInfo.showAlert(Alert.AlertType.ERROR,
                            "Error","删除商品分类失败","RMI连接错误");
                }
                refresh();
            }
        }
    }

    @FXML
    private void handleEditSort(){
        if(isGoodsSortSelected()){
            int selectedIndex=goodsSortTreeView.getSelectionModel().getSelectedIndex();
            TreeItem<GoodsSortVO> sortItem=goodsSortTreeView.getTreeItem(selectedIndex);
            GoodsSortVO sort=sortItem.getValue();

            GoodsSortInfoUIController.init(goodsSortBlService,sort,2,root.getStage());
            refresh();
        }
    }

    @FXML
    private void handleAddGoods() {
        if(isGoodsSortSelected()){
            int selectedIndex=goodsSortTreeView.getSelectionModel().getSelectedIndex();
            TreeItem<GoodsSortVO> sortItem=goodsSortTreeView.getTreeItem(selectedIndex);
            GoodsSortVO sort=sortItem.getValue();

            if(sort.getChildren().size()!=0){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error","添加商品失败","商品分类下有子分类");
            }
            else{
                GoodsVO goods=new GoodsVO();
                goods.setGoodsSort(sort.getID());

                GoodsInfoUIController.init(goodsBlService,goods,1,root.getStage());
                refresh();
            }

        }
    }

    private boolean isGoodsSortSelected(){
        int selectedIndex=goodsSortTreeView.getSelectionModel().getSelectedIndex();
        if(selectedIndex<0){
            AlertInfo.showAlert(Alert.AlertType.ERROR,
                    "No Selection","未选中商品分类","请在表中选择商品分类");
            return false;
        }else{
            TreeItem item=goodsSortTreeView.getTreeItem(selectedIndex);
            if(!item.isExpanded()){
                AlertInfo.showAlert(Alert.AlertType.ERROR,
                        "Error","选中了商品信息","请在表中选择商品分类");
                return false;
            }
            return true;
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
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/goodssortui/GoodsSortUI.fxml"));
            root.setCenterPane(loader.load());

            GoodsSortUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setGoodsSortBlService(GoodsSortBlFactory.getService());
            controller.setGoodsBlService(GoodsBlFactory.getService());
            controller.refresh();
            root.setReturnPaneController(new InventoryPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

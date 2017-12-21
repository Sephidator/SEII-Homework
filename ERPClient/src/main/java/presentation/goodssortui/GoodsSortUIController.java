package main.java.presentation.goodssortui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.util.Callback;
import main.java.MainApp;
import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodssortbl.GoodsSortBl;
import main.java.businesslogicfactory.goodssortblfactory.GoodsSortBlFactory;
import main.java.businesslogicservice.goodsblservice.GoodsBlService;
import main.java.businesslogicservice.goodssortblservice.GoodsSortBlService;
import main.java.exception.DataException;
import main.java.exception.NotExistException;
import main.java.exception.NotNullException;
import main.java.presentation.goodsui.GoodsInfoUIController;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.InventoryPanelUIController;
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
        refresh();

    }

    public void setGoodsBlService(GoodsBlService goodsBlService) {
        this.goodsBlService=goodsBlService;
    }


    private void refresh(){
        try{
            GoodsSortVO root= GoodsSortBlFactory.getService().getRoot();
            showGoodsSort(null,root);
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
        if(isCorrectGoodsSortSelected()){
            int selectedIndex=goodsSortTreeView.getSelectionModel().getSelectedIndex();
            TreeItem<GoodsSortVO> sortItem=goodsSortTreeView.getTreeItem(selectedIndex);
            GoodsSortVO sort=sortItem.getValue();

            if(sort.getChildren().size()!=0){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("添加商品分类失败");
                alert.setContentText("该商品分类下有商品");
                alert.showAndWait();
            }
            else{
                GoodsSortVO son=new GoodsSortVO();
                son.setFather(sort);
                GoodsSortInfoUIController.init(goodsSortBlService,son,1,root.getStage());
                refresh();
            }
        }
    }

    @FXML
    private void handleDeleteSort(){
        if(isCorrectGoodsSortSelected()){
            try {
                int selectedIndex=goodsSortTreeView.getSelectionModel().getSelectedIndex();
                TreeItem<GoodsSortVO> sortItem=goodsSortTreeView.getTreeItem(selectedIndex);
                GoodsSortVO sort=sortItem.getValue();

                String ID = sort.getID();
                String name = sort.getName();
                goodsSortBlService.deleteGoodsSort(ID);

                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("删除商品分类成功");
                alert.setContentText("分类ID："+ID+System.lineSeparator()+"名字："+name);
                alert.showAndWait();
            }catch(DataException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("删除商品分类失败");
                alert.setContentText("数据库错误");
                alert.showAndWait();
            }catch(NotExistException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("删除商品分类失败");
                alert.setContentText("商品分类不存在");
                alert.showAndWait();
            }catch(NotNullException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("删除商品分类失败");
                alert.setContentText("商品分类下有子类或商品");
                alert.showAndWait();
            }catch(Exception e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("删除商品分类失败");
                alert.setContentText("RMI连接错误");
                alert.showAndWait();
            }
            refresh();
        }
    }

    @FXML
    private void handleEditSort(){
        int selectedIndex=goodsSortTreeView.getSelectionModel().getSelectedIndex();
        if(goodsSortTreeView.getTreeItem(selectedIndex).isExpanded()){
            TreeItem<GoodsSortVO> item=goodsSortTreeView.getTreeItem(selectedIndex);
            GoodsSortVO sort=item.getValue();
            GoodsSortInfoUIController.init(goodsSortBlService,sort,3,root.getStage());
        }
        else{
            TreeItem<GoodsVO> item=goodsSortTreeView.getTreeItem(selectedIndex);
            GoodsVO goods=item.getValue();
            GoodsInfoUIController.init(goodsBlService,goods,3,root.getStage());
        }
    }

    @FXML
    private void handleAddGoods() {
    }

    private boolean isCorrectGoodsSortSelected(){
        int selectedIndex=goodsSortTreeView.getSelectionModel().getSelectedIndex();
        if(selectedIndex<0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("未选中商品分类");
            alert.setContentText("请在表中选择商品分类");
            alert.showAndWait();
            return false;
        }else{
            TreeItem item=goodsSortTreeView.getTreeItem(selectedIndex);
            if(!item.isExpanded()){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("选中了商品信息");
                alert.setContentText("请在表中选择商品分类");
                alert.showAndWait();
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
            controller.setGoodsSortBlService(new GoodsSortBl());
            controller.setGoodsBlService(new GoodsBl());

            root.setReturnPaneController(new InventoryPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

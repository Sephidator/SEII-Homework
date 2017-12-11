package main.java.presentation.goodssortui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import main.java.MainApp;
import main.java.businesslogic.goodssortbl.GoodsSortBl;
import main.java.businesslogicservice.goodssortblservice.GoodsSortBlService;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.InventoryPanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.goods.GoodsSortVO;

import java.util.ArrayList;

public class GoodsSortUIController extends CenterUIController {
    private GoodsSortBlService goodsSortBlService;
    @FXML
    private TreeView goodsSortTreeView;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        goodsSortTreeView.setEditable(true);
        /*
        root = new TreeItem<String>("Root");
        root.setExpanded(true);
        goodsSortTreeView.setRoot(root);
        */
    }

    // 设置controller数据的方法*****************************************

    public void setGoodsSortBlService(GoodsSortBlService goodsSortBlService) {
        this.goodsSortBlService=goodsSortBlService;
        //ArrayList<GoodsSortVO> goodsSortList=goodsSortBlService.getGoodsSortList(null);

        GoodsSortVO c1=new GoodsSortVO();
        c1.setID("Sort12345");
        c1.setName("二极管1");

        GoodsSortVO c2=new GoodsSortVO();
        c2.setID("Sort98085");
        c2.setName("二极管2");

        ArrayList<GoodsSortVO> list=new ArrayList<>();
        list.add(c1);
        list.add(c2);

        GoodsSortVO b1=new GoodsSortVO();
        b1.setID("Sort12345");
        b1.setName("二极管");
        b1.setChildren(list);

        GoodsSortVO b2=new GoodsSortVO();
        b2.setID("Sort98085");
        b2.setName("白炽灯");

        ArrayList<GoodsSortVO> list2=new ArrayList<>();
        list2.add(b1);
        list2.add(b2);

        GoodsSortVO a=new GoodsSortVO();
        a.setID("Sort12345678");
        a.setName("灯具");
        a.setChildren(list2);

        showGoodsSort(null,a);
    }


    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showGoodsSort(TreeItem<String> fatherItem,GoodsSortVO newSort){
        TreeItem<String> newItem= new TreeItem<String>(newSort.getID()+": "+newSort.getName());

        if(fatherItem==null){
            goodsSortTreeView.setRoot(newItem);
        }
        else{
            fatherItem.getChildren().add(newItem);
        }

        if(newSort.getChildren()!=null && newSort.getChildren().size()>0){
            for(GoodsSortVO child:newSort.getChildren()){
                showGoodsSort(newItem,child);
            }
        }
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleAddSort(){
        GoodsSortInfoUIController.init(goodsSortBlService,new GoodsSortVO(),1,root.getStage());
    }

    @FXML
    private void handleDeleteSort(){
        int selectedIndex=goodsSortTreeView.getSelectionModel().getSelectedIndex();
        if(isGoodsSortSelected()){
           TreeItem<String> item=goodsSortTreeView.getTreeItem(selectedIndex);
           item.getParent().getChildren().remove(item);
        }
    }

    @FXML
    private void handleEditSort(){
        /*
        int selectedIndex=goodsSortTreeView.getSelectionModel().getSelectedIndex();
        TreeItem<String> item=goodsSortTreeView.getTreeItem(selectedIndex);
        */
        GoodsSortInfoUIController.init(goodsSortBlService,new GoodsSortVO(),3,root.getStage());
    }

    @FXML
    private void handleAddGoods() {
    }

    private boolean isGoodsSortSelected(){
        int selectedIndex=goodsSortTreeView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("未选中商品分类");
            alert.setContentText("请在表中选择商品分类");
            alert.showAndWait();
            return false;
        }
    }

    /*
    private boolean isClientSelected(){
    }
    */

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

            root.setReturnPaneController(new InventoryPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

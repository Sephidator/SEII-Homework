package main.java.presentation.goodssortui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import main.java.MainApp;
import main.java.businesslogic.goodssortbl.GoodsSortBl;
import main.java.businesslogicservice.clientblservice.ClientBlService;
import main.java.businesslogicservice.goodssortblservice.GoodsSortBLService;
import main.java.presentation.clientui.ClientInfoUIController;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.InventoryPanelUIController;
import main.java.presentation.messageui.PurchaseSalePanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsSortVO;

import java.util.ArrayList;

public class GoodsSortUIController extends CenterUIController {
    private GoodsSortBLService goodsSortBlService;
    @FXML
    private TreeView goodsSortTreeView;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        /*
        root = new TreeItem<String>("Root");
        root.setExpanded(true);
        goodsSortTreeView.setRoot(root);
        */
    }

    // 设置controller数据的方法*****************************************

    public void setGoodsSortBlService(GoodsSortBLService goodsSortBlService) {
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
    }

    @FXML
    private void handleDeleteSort(){
    }

    @FXML
    private void handleEditSort(){
    }

    @FXML
    private void handleAddGoods() {
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

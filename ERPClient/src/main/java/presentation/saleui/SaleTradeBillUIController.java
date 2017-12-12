package main.java.presentation.saleui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.businesslogicservice.saleblservice.SaleTradeBillBlService;
import main.java.presentation.uiutility.AddGoodsUIController;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GiftItemVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.promotion.PromotionVO;

import java.util.ArrayList;

public class SaleTradeBillUIController extends InfoUIController {
    private SaleTradeBillBlService service;
    private SaleTradeBillVO bill;
    private ArrayList<GoodsVO> goodsList;
    private ArrayList<GoodsItemVO> goodsItemList;
    private ArrayList<GiftItemVO> giftItemList;
    private PromotionVO promotion;

    private ObservableList<GoodsItemVO> goodsItemObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<GoodsItemVO> goodsItemTableView;
    @FXML
    private TableColumn<GoodsItemVO,String> IDColumn;
    @FXML
    private TableColumn<GoodsItemVO,String> nameColumn;
    @FXML
    private TableColumn<GoodsItemVO,String> modelColumn;
    @FXML
    private TableColumn<GoodsItemVO,String> numberColumn;
    @FXML
    private TableColumn<GoodsItemVO,String> priceColumn;
    @FXML
    private TableColumn<GoodsItemVO,String> amountColumn;

    private ObservableList<GiftItemVO> giftItemObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<GoodsItemVO> giftItemTableView;
    @FXML
    private TableColumn<GoodsItemVO,String> IDColumn2;
    @FXML
    private TableColumn<GoodsItemVO,String> nameColumn2;
    @FXML
    private TableColumn<GoodsItemVO,String> modelColumn2;
    @FXML
    private TableColumn<GoodsItemVO,String> numberColumn2;

    @FXML
    private TextField ID; // 单据编号
    @FXML
    private TextField type; // 单据类型
    @FXML
    private TextField client; // 客户
    @FXML
    private TextField operator; //操作员
    @FXML
    private TextField salesman; // 业务员
    @FXML
    private TextArea comment; // 备注
    @FXML
    private TextField promotionInfo; // 适用的促销策略
    @FXML
    private TextField totalBeforeDiscount; // 折让前总额
    @FXML
    private TextField discount; // 折让金额
    @FXML
    private TextField amountOfVoucher; // 代金卷金额
    @FXML
    private TextField totalAfterDiscount; // 折让后总额

    @FXML
    private ChoiceBox<String> clientChoiceBox;
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;


    // 加载文件后调用的方法******************************************

    public void initialize(){
        IDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getID()));
        nameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getName()));
        modelColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().goods.getModel())));
        numberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().number)));
        priceColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().price)));
        amountColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().number*cellData.getValue().price)));
    }

    // 设置controller数据的方法*****************************************

    public void setBill(SaleTradeBillVO bill) {
        this.bill = bill;
        ID.setText(bill.getID());
        type.setText(bill.getType());
        client.setText(bill.getClient()==null?"":(bill.getClient().getID()+":"+bill.getClient().getName()));
        operator.setText(bill.getOperator()==null?"":(bill.getOperator().getID()+":"+bill.getOperator().getName()));
        promotionInfo.setText("");//promotionInfo.setText(bill.getPromotion().getName());
        salesman.setText("salesman");//salesman.setText(bill.getPromotion().getName());
        comment.setText(bill.getComment());

        totalBeforeDiscount.setText(String.valueOf(bill.getTotalBeforeDiscount()));
        totalAfterDiscount.setText(String.valueOf(bill.getTotalAfterDiscount()));
        discount.setText(String.valueOf(bill.getDiscount()));
        amountOfVoucher.setText(String.valueOf(bill.getAmountOfVoucher()));
    }

    public void setService(SaleTradeBillBlService service) {
        this.service=service;
        // ArrayList<ClientVO> clientList=service.getClientList(null);

        ClientVO c1=new ClientVO("类别：经销商", 3, "名字：陈骁",
                "电话：123", "地址：南京大学", "邮编123", "邮件：123",
                0, 0, 20, null);
        c1.setID("123");
        ClientVO c2=new ClientVO("类别：经销商", 4, "名字：陈骁2",
                "电话：123", "地址：南京大学", "邮编123", "邮件：123",
                0, 0, 20, null);
        c2.setID("123");

        ArrayList<ClientVO> clientList=new ArrayList<>();
        clientList.add(c1);
        clientList.add(c2);
        ObservableList<String> list=FXCollections.observableArrayList();
        for(int i=0;i<clientList.size();i++){
            list.add(clientList.get(i).getID()+","+clientList.get(i).getName());
        }
        clientChoiceBox.setItems(list);
        clientChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
            client.setText(clientList.get(newValue.intValue()).getName());
            bill.setClient(clientList.get(newValue.intValue()));
        });
    }

    public void setGoodsList(ArrayList<GoodsVO> goodsList) {
        this.goodsList=goodsList;
    }

    public void setGoodsItemList(ArrayList<GoodsItemVO> goodsItemList) {
        this.goodsItemList=goodsItemList;
        if(goodsItemList==null){
            System.out.println("Null Exception");
        }
        else{
            System.out.println("Not null");
        }
        showGoodsItemList(goodsItemList);
    }

    public void setGiftItemList(ArrayList<GoodsItemVO> goodsItemList) {
        this.giftItemList=giftItemList;
        if(goodsItemList==null){
            System.out.println("Null Exception");
        }
        else{
            System.out.println("Not null");
        }
        showGoodsItemList(goodsItemList);
    }

    /**
     * 取得赠品列表并修改ObservableList的信息
     * */
    private void setPromotion(PromotionVO promotion){
        this.promotion=promotion;
    }

    /**
     * 根据数字来设置按钮的文字
     * 按钮被点击时，根据不同的文字执行不同的功能
     * 好吧这个控制耦合有点蠢，但是做的工作少一点
     * 1对应新建单据；2对应编辑单据；3对应查看单据
     * */
    public void setPaneFunction(int command){
        if(command==1){
            confirm.setText("确认添加");
            cancel.setText("保存草稿");
        }
        else if(command==2){
            confirm.setText("确认编辑");
            cancel.setText("保存草稿");
        }
        else if(command==3){
            confirm.setText("确定");
            cancel.setText("取消");
        }
    }

    /**
     * 取得商品列表并修改ObservableList的信息
     * */
    private void showGoodsItemList(ArrayList<GoodsItemVO> goodsItemList){
        if(goodsItemList!=null){
            goodsItemTableView.getItems().clear();
            goodsItemObservableList.removeAll();

            for(int i=0;i<goodsItemList.size();i++){
                goodsItemObservableList.add(goodsItemList.get(i));
            }
            goodsItemTableView.setItems(goodsItemObservableList);

            System.out.println("GoodsItemListSize: "+goodsItemList.size());
        }
    }

    /**
     * 取得赠品列表并修改ObservableList的信息
     * */
    private void showGiftItemList(ArrayList<GiftItemVO> giftItemList){
        /*
        if(goodsItemList!=null){
            giftItemTableView.getItems().clear();
            giftItemObservableList.removeAll();

            for(int i=0;i<giftItemList.size();i++){
                giftItemObservableList.add(giftItemList.get(i));
            }
            giftItemTableView.setItems(giftItemObservableList);

            System.out.println("GoodsItemListSize: "+giftItemList.size());
        }
        */
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void addGoods(){
        AddGoodsUIController.init(goodsList,goodsItemList,dialogStage,false);
        System.out.println("添加商品了");
        showGoodsItemList(goodsItemList);
    }

    @FXML
    private void deleteGoods(){
        if(isGoodsItemSelected()){
            int selectedIndex=goodsItemTableView.getSelectionModel().getSelectedIndex();
            goodsItemTableView.getItems().remove(selectedIndex);
        }
    }

    @FXML
    private void goodsNumberPlus(){
        if(isGoodsItemSelected()){
            int selectedIndex=goodsItemTableView.getSelectionModel().getSelectedIndex();
            if(goodsItemList.get(selectedIndex).number<goodsItemList.get(selectedIndex).goods.getNumber()){
                goodsItemList.get(selectedIndex).number++;
                bill.setSaleList(goodsItemList);
                showGoodsItemList(goodsItemList);
                goodsItemTableView.getSelectionModel().select(selectedIndex);
            }
        }
    }

    @FXML
    private void goodsNumberMinus(){
        if(isGoodsItemSelected()){
            int selectedIndex=goodsItemTableView.getSelectionModel().getSelectedIndex();
            ArrayList<GoodsItemVO> goodsItemList=bill.getSaleList();
            goodsItemList.get(selectedIndex).number--;
            if(goodsItemList.get(selectedIndex).number==0){
                goodsItemList.remove(selectedIndex);
                bill.setSaleList(goodsItemList);
                showGoodsItemList(goodsItemList);
            }
            else{
                bill.setSaleList(goodsItemList);
                showGoodsItemList(goodsItemList);
                goodsItemTableView.getSelectionModel().select(selectedIndex);
            }
        }
    }

    @FXML
    private void handleConfirm(){
        String text=confirm.getText();
        /*
        if(text.equals("确认添加")){
            saleTradeBlService.addClient(client);
        }
        else if(text.equals("确认编辑")){
            saleTradeBillBlService.editClient(client);
        }
        else{
            stage.close();
        }
        */
    }

    @FXML
    private void handleCancel(){
        String text=cancel.getText();
        if(text.equals("保存草稿")){
            //service.saveDraft(bill);
            dialogStage.close();
        }
        else if(text.equals("取消")){
            dialogStage.close();
        }
    }

    private boolean isGoodsItemSelected(){
        int selectedIndex=goodsItemTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            // Nothing selected
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText("未选择商品");
            alert.setContentText("请在进货商品列表中选择商品");
            alert.showAndWait();
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    public void showInfo(BillVO bill, Stage stage){
        init(null,(SaleTradeBillVO)bill,3,stage);
    }

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(SaleTradeBillBlService service,SaleTradeBillVO bill, int command,Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/saleui/SaleTradeBillUI.fxml"));

            // Create the dialog stage
            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("进货单信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            SaleTradeBillUIController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setService(service);
            controller.setBill(bill);

            //controller.setGoodsList(service.getGoodsList(null));
            ArrayList<GoodsVO> list=new ArrayList<GoodsVO>();
            GoodsVO goods1=new GoodsVO("电灯", null, "大号", 5, 20, 30, 20, 30, 40, "备注");
            goods1.setID("123");
            GoodsVO goods2=new GoodsVO("台灯", null, "小号", 5, 30, 60, 30, 60, 40, "备注");
            goods2.setID("233");
            list.add(goods1);
            list.add(goods2);

            controller.setGoodsList(list);
            controller.setGoodsItemList(bill.getSaleList());
            controller.setGiftItemList(null);
            controller.setPromotion(null);
            controller.setPaneFunction(command);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


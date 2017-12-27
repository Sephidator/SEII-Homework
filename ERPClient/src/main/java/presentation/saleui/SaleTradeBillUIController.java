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
import main.java.businesslogicfactory.saleblfactory.SaleTradeBillBlFactory;
import main.java.businesslogicservice.saleblservice.SaleTradeBillBlService;
import main.java.exception.DataException;
import main.java.exception.FullException;
import main.java.presentation.uiutility.AddGoodsUIController;
import main.java.presentation.uiutility.UITool;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GiftItemVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.promotion.PromotionVO;

import java.util.ArrayList;
import java.util.Date;

public class SaleTradeBillUIController extends InfoUIController {
    private SaleTradeBillBlService service;
    private SaleTradeBillVO bill;
    private ArrayList<GoodsVO> goodsList;

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
    private TableView<GiftItemVO> giftItemTableView;
    @FXML
    private TableColumn<GiftItemVO,String> IDColumn2;
    @FXML
    private TableColumn<GiftItemVO,String> nameColumn2;
    @FXML
    private TableColumn<GiftItemVO,String> modelColumn2;
    @FXML
    private TableColumn<GiftItemVO,String> numberColumn2;

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
    private TextField promotionDiscount; // 促销折让金额
    @FXML
    private TextField discount; // 折让金额
    @FXML
    private TextField useVoucher; // 使用代金卷金额
    @FXML
    private TextField totalAfterDiscount; // 折让后总额
    @FXML
    private TextField sendVoucher; // 发放代金券金额

    @FXML
    private ChoiceBox<String> clientChoiceBox;
    @FXML
    private ChoiceBox<PromotionVO> promotionChoiceBox;
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button plus;
    @FXML
    private Button minus;
    @FXML
    private Button correct;


    // 加载文件后调用的方法******************************************

    public void initialize(){
        IDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getID()));
        nameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getName()));
        modelColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().goods.getModel())));
        numberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().number)));
        priceColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().price)));
        amountColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().number*cellData.getValue().price)));

        IDColumn2.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getID()));
        nameColumn2.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getName()));
        modelColumn2.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().goods.getModel())));
        numberColumn2.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().number)));
    }

    // 设置controller数据的方法*****************************************

    public void setBill(SaleTradeBillVO bill) {
        this.bill = bill;
        ID.setText(bill.getID());
        type.setText(bill.getType());
        client.setText(bill.getClient().getCategory()+" "+bill.getClient().getName());
        operator.setText(bill.getOperator().getName());
        promotionInfo.setText(bill.getPromotion().getName());
        salesman.setText(bill.getSalesman().getName());
        comment.setText(bill.getComment());
        discount.setText(String.valueOf(bill.getDiscount()));
        useVoucher.setText(String.valueOf(bill.getAmountOfVoucher()));

        showGoodsItemList();
        countTotalAndGift();
    }

    public void setService(SaleTradeBillBlService service) {
        this.service=service;
        setSellerList();
        setPromotionList();
    }

    public void setGoodsList(ArrayList<GoodsVO> goodsList) {
        this.goodsList=goodsList;
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
            confirm.setText("提交编辑");
            cancel.setText("保存草稿");
        }
        else if(command==3){
            confirm.setText("确定");
            cancel.setText("取消");

            comment.setEditable(false);
            clientChoiceBox.setDisable(true);
            promotionChoiceBox.setDisable(true);
            add.setDisable(true);
            delete.setDisable(true);
            plus.setDisable(true);
            minus.setDisable(true);
            correct.setDisable(true);
        }
    }

    private void setSellerList(){
        try{
            ArrayList<ClientVO> clientList= service.getSellerList(null);
            ArrayList<ClientVO> sellerList=new ArrayList<>();
            for(ClientVO client:clientList){
                if(client.getCategory().equals("销售商")){
                    sellerList.add(client);
                }
            }

            ObservableList<String> list=FXCollections.observableArrayList();
            for(int i=0;i<sellerList.size();i++){
                list.add(sellerList.get(i).getCategory()+" "+sellerList.get(i).getName());
            }
            clientChoiceBox.setItems(list);
            clientChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
                client.setText(list.get(newValue.intValue()));
                bill.setClient(sellerList.get(newValue.intValue()));

                salesman.setText(sellerList.get(newValue.intValue()).getSalesman().getName());
                bill.setSalesman(sellerList.get(newValue.intValue()).getSalesman());
            });
        }catch(DataException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找客户失败","数据库错误");
        }catch(Exception e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找客户失败","RMI连接错误");
        }
    }

    /**
     * 取得促销策略列表并修改ObservableList的信息
     * */
    private void setPromotionList(){
        try{
            ArrayList<PromotionVO> allPromotionList=service.getPromotionList(null);
            ArrayList<PromotionVO> promotionList=new ArrayList<>();
            for(PromotionVO promotion:allPromotionList){
                Date now=new Date();
                if(now.after(promotion.getStart())&&now.before(promotion.getEnd())){
                    promotionList.add(promotion);
                }
            }

            ObservableList<PromotionVO> list=FXCollections.observableArrayList();
            list.setAll(promotionList);
            promotionChoiceBox.setItems(list);
            promotionChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
                promotionInfo.setText(promotionList.get(newValue.intValue()).getName());
                bill.setPromotion(promotionList.get(newValue.intValue()));
            });
        }catch(DataException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找促销策略失败","数据库错误");
        }catch(Exception e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找促销策略失败","RMI连接错误");
        }
    }

    /**
     * 取得商品列表并修改ObservableList的信息
     * */
    private void showGoodsItemList(){
        ArrayList<GoodsItemVO> goodsItemList=bill.getSaleList();
        goodsItemTableView.getItems().clear();
        goodsItemObservableList.setAll(goodsItemList);
        goodsItemTableView.setItems(goodsItemObservableList);
    }

    /**
     * 取得赠品列表并修改ObservableList的信息
     * */
    private void showGiftItemList(ArrayList<GiftItemVO> giftItemList){
        giftItemTableView.getItems().clear();
        giftItemObservableList.setAll(giftItemList);
        giftItemTableView.setItems(giftItemObservableList);
    }

    private void countTotalAndGift(){
        // 计算折让前总价

        double totalAmount=0;
        for(GoodsItemVO item:bill.getSaleList()){
            totalAmount+=item.number*item.price;
        }
        totalBeforeDiscount.setText(String.valueOf(totalAmount));
        bill.setTotalBeforeDiscount(totalAmount);

        // 计算促销策略折让

        Double promotionDiscountNum =
                bill.getPromotion().countPromotionDiscount
                        (bill.getSaleList(),bill.getClient(),totalAmount);
        promotionDiscount.setText(String.valueOf(promotionDiscountNum));

        // 计算发放代金券金额

        Double sendVoucherNum =
                bill.getPromotion().countVoucher
                        (bill.getSaleList(),bill.getClient(),totalAmount);
        sendVoucher.setText(String.valueOf(sendVoucherNum));

        // 计算并显示赠品列表

        ArrayList<GiftItemVO> giftList =
                bill.getPromotion().countGiftList
                    (bill.getSaleList(),bill.getClient(),bill.getTotalBeforeDiscount());
        showGiftItemList(giftList);

        // 计算折让后总价

        Double discountNum=Double.parseDouble(discount.getText());
        totalAmount-=discountNum;

        Double useVoucherNum=Double.parseDouble(useVoucher.getText());
        totalAmount-=useVoucherNum;

        totalAmount-=promotionDiscountNum;

        if(totalAmount<0){
            totalAmount=0;
        }

        totalAfterDiscount.setText(String.valueOf(totalAmount));
        bill.setTotalAfterDiscount(totalAmount);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void addGoods(){
        AddGoodsUIController.init(goodsList,bill.getSaleList(),dialogStage,false);
        showGoodsItemList();
        countTotalAndGift();
    }

    @FXML
    private void deleteGoods(){
        if(isGoodsItemSelected()){
            int selectedIndex=goodsItemTableView.getSelectionModel().getSelectedIndex();
            bill.getSaleList().remove(selectedIndex);
            showGoodsItemList();
            countTotalAndGift();
        }
    }

    @FXML
    private void goodsNumberPlus(){
        if(isGoodsItemSelected()){
            int selectedIndex=goodsItemTableView.getSelectionModel().getSelectedIndex();
            if(bill.getSaleList().get(selectedIndex).number<bill.getSaleList().get(selectedIndex).goods.getNumber()){
                bill.getSaleList().get(selectedIndex).number++;
                showGoodsItemList();
                countTotalAndGift();
                goodsItemTableView.getSelectionModel().select(selectedIndex);
            }
            else{
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error","增加商品数量失败","超过库存总数");
            }
        }
    }

    @FXML
    private void goodsNumberMinus(){
        if(isGoodsItemSelected()){
            int selectedIndex=goodsItemTableView.getSelectionModel().getSelectedIndex();
            bill.getSaleList().get(selectedIndex).number--;

            if(bill.getSaleList().get(selectedIndex).number==0){
                bill.getSaleList().remove(selectedIndex);
                showGoodsItemList();
                countTotalAndGift();
            }
            else{
                showGoodsItemList();
                countTotalAndGift();
                goodsItemTableView.getSelectionModel().select(selectedIndex);
            }
        }
    }

    @FXML
    private void handleCorrect(){
        String errorMessage = "";

        if (discount.getText().length()==0) {
            errorMessage+=("未输入折让金额。"+System.lineSeparator());
        }
        else{
            try{
                double d=Double.parseDouble(discount.getText());
                if(d<0){
                    throw new NumberFormatException();
                }
                else{
                    if(bill.getOperator().isTop() && d>5000){
                        errorMessage+=("销售经理的折让额度不得超过5000元。"+System.lineSeparator());
                    }
                    else if(bill.getOperator().isTop() && d>1000){
                        errorMessage+=("销售员的折让额度不得超过1000元。"+System.lineSeparator());
                    }
                }
            }catch(NumberFormatException e){
                errorMessage+=("折让金额必须是非负数"+System.lineSeparator());
            }
        }

        if (useVoucher.getText().length()==0) {
            errorMessage+=("未输入折让金额。"+System.lineSeparator());
        }
        else{
            try{
                double d=Double.parseDouble(discount.getText());
                if(d<0){
                    throw new NumberFormatException();
                }
            }catch(NumberFormatException e){
                errorMessage+=("使用代金券金额必须是非负数"+System.lineSeparator());
            }
        }

        if(errorMessage.length()==0){
            bill.setDiscount(Double.parseDouble(discount.getText()));
            bill.setAmountOfVoucher(Double.parseDouble(useVoucher.getText()));
            countTotalAndGift();
        } else {
            UITool.showAlert(Alert.AlertType.ERROR,
                    "单据信息错误", "请检查单据信息的输入", errorMessage);
        }
    }

    @FXML
    private void handleConfirm(){
        String text=confirm.getText();
        if(text.equals("确定")){
            dialogStage.close();
        }
        else if(isInputValid()){
            try{
                if(text.equals("确认添加")){
                    bill.setState("待审批");
                    String billID=service.submit(bill);
                    UITool.showAlert(Alert.AlertType.INFORMATION,
                            "Success","提交销售单成功", "单据ID："+billID);
                }
                else if(text.equals("提交编辑")){
                    bill.setState("待审批");
                    service.editSaleTradeBill(bill);
                    UITool.showAlert(Alert.AlertType.INFORMATION,
                            "Success","编辑销售单成功", "单据ID："+bill.getID());
                }

                dialogStage.close();
            }catch(DataException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"销售单失败", "数据库错误");
            }catch(FullException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"销售单失败", "超过单日单据上限（99999张）");
            }catch(Exception e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"销售单失败", "RMI连接错误");
            }
        }
    }

    @FXML
    private void handleCancel(){
        String text=cancel.getText();
        if(text.equals("保存草稿")){
            try{
                bill.setState("草稿");
                bill.setComment(comment.getText());

                String billID;
                if(ID.getText().length()==0){
                    billID=service.submit(bill);
                }
                else{
                    billID=bill.getID();
                    service.editSaleTradeBill(bill);
                }

                UITool.showAlert(Alert.AlertType.INFORMATION,
                        "Success","已保存销售单草稿", "单据ID："+billID);
                dialogStage.close();
            }catch(DataException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error","保存销售单草稿失败", "数据库错误");
            }catch(FullException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error", "保存销售单草稿失败", "超过单日单据上限（99999张）");
            }catch(Exception e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error","保存销售单草稿失败", "RMI连接错误");
            }
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
            UITool.showAlert(Alert.AlertType.ERROR,
                    "No Selection","未选择商品","请在进货商品列表中选择商品");
            return false;
        }
    }

    /**
     * 检查单据信息的输入是否完整且合法
     * 完整且合法返回true
     * */
    private boolean isInputValid(){
        String errorMessage = "";

        if (client.getText().length()<=1) {
            errorMessage+=("未选择客户。"+System.lineSeparator());
        }
        if (salesman.getText().length()==0) {
            errorMessage+=("未添加业务员。"+System.lineSeparator());
        }
        if (goodsItemObservableList==null||goodsItemObservableList.size()==0) {
            errorMessage+=("未添加出货商品清单。"+System.lineSeparator());
        }

        if(errorMessage.length()==0){
            bill.setComment(comment.getText());
            return true;
        } else {
            UITool.showAlert(Alert.AlertType.ERROR,
                    "单据信息错误", "请检查单据信息的输入", errorMessage);
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    public void showInfo(BillVO bill, Stage stage){
        init(SaleTradeBillBlFactory.getService(),(SaleTradeBillVO)bill,3,stage);
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
            dialogStage.setTitle("销售单信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            SaleTradeBillUIController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setService(service);
            controller.setBill(bill);
            controller.setGoodsList(service.getGoodsList(null));
            controller.setPaneFunction(command);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


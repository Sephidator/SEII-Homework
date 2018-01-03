package main.java.presentation.saleui;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.businesslogicfactory.saleblfactory.SaleRefundBillBlFactory;
import main.java.businesslogicservice.saleblservice.SaleRefundBillBlService;
import main.java.exception.DataException;
import main.java.exception.FullException;
import main.java.presentation.uiutility.AddGoodsUIController;
import main.java.arith.Arith;
import main.java.presentation.uiutility.UITool;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class SaleRefundBillUIController extends InfoUIController {
    private SaleRefundBillBlService service;
    private SaleRefundBillVO bill;
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
    @FXML
    private TextField ID; // 单据编号
    @FXML
    private TextField type; // 单据类型
    @FXML
    private TextField client; // 客户
    @FXML
    private TextField operator; //操作员
    @FXML
    private TextField salesman; //业务员
    @FXML
    private TextField total; // 总价
    @FXML
    private TextArea comment; // 备注
    @FXML
    private ChoiceBox<String> clientChoiceBox;
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


    // 加载文件后调用的方法******************************************

    public void initialize(){
        IDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getID()));
        nameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().goods.getName()));
        modelColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().goods.getModel())));
        numberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().number)));
        priceColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().price)));
        amountColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(Arith.mul(cellData.getValue().number, cellData.getValue().price))));

        goodsItemTableView.setEditable(true);
        numberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        numberColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<GoodsItemVO, String> t) -> {
                    try{
                        int n=Integer.parseInt(t.getNewValue());
                        if(n<=0){
                            throw new Exception();
                        }
                        else{
                            bill.getSaleList().get(t.getTablePosition().getRow()).number=n;
                            showGoodsItemList();
                            countTotal();
                        }
                    }catch(Exception e){
                        UITool.showAlert(Alert.AlertType.ERROR,
                                "Error","请检查输入","输入的商品数量必须为正数");
                        showGoodsItemList();
                        countTotal();
                    }
                });
    }

    // 设置controller数据的方法*****************************************

    public void setBill(SaleRefundBillVO bill) {
        this.bill = bill;
        ID.setText(bill.getID());
        type.setText(bill.getType());
        total.setText(String.valueOf(bill.getTotal()));
        comment.setText(bill.getComment());
        client.setText(bill.getClient().getCategory()+" "+bill.getClient().getName());
        operator.setText(bill.getOperator().getName());
        salesman.setText(bill.getSalesman().getName());
        showGoodsItemList();
    }

    public void setService(SaleRefundBillBlService service) {
        this.service=service;
        setSellerList();
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
            add.setDisable(true);
            delete.setDisable(true);
            plus.setDisable(true);
            minus.setDisable(true);
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
     * 取得商品列表并修改ObservableList的信息
     * */
    private void showGoodsItemList(){
        ArrayList<GoodsItemVO> goodsItemList=bill.getSaleList();
        goodsItemObservableList.clear();
        goodsItemObservableList.setAll(goodsItemList);
        goodsItemTableView.setItems(goodsItemObservableList);
    }

    private void countTotal(){
        double totalAmount=0;
        for(GoodsItemVO item:bill.getSaleList()){
            double amountForOne = Arith.mul(item.number, item.price);
            totalAmount = Arith.add(totalAmount, amountForOne);
        }
        total.setText(String.valueOf(totalAmount));
        bill.setTotal(totalAmount);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void addGoods(){
        AddGoodsUIController.init(goodsList,bill.getSaleList(),dialogStage,false);
        showGoodsItemList();
        countTotal();
    }

    @FXML
    private void deleteGoods(){
        if(isGoodsItemSelected()){
            int selectedIndex=goodsItemTableView.getSelectionModel().getSelectedIndex();
            bill.getSaleList().remove(selectedIndex);
            showGoodsItemList();
            countTotal();
        }
    }

    @FXML
    private void goodsNumberPlus(){
        if(isGoodsItemSelected()){
            int selectedIndex=goodsItemTableView.getSelectionModel().getSelectedIndex();
            bill.getSaleList().get(selectedIndex).number++;
            showGoodsItemList();
            countTotal();
            goodsItemTableView.getSelectionModel().select(selectedIndex);
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
                countTotal();
            }
            else{
                showGoodsItemList();
                countTotal();
                goodsItemTableView.getSelectionModel().select(selectedIndex);
            }
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
                            "Success","提交销售退货单成功", "单据ID："+billID);
                }
                else if(text.equals("提交编辑")){
                    bill.setState("待审批");
                    service.editSaleRefundBill(bill);
                    UITool.showAlert(Alert.AlertType.INFORMATION,
                            "Success","编辑销售退货单成功", "单据ID："+bill.getID());
                }

                dialogStage.close();
            }catch(DataException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"销售退货单失败", "数据库错误");
            }catch(FullException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"销售退货单失败", "超过单日单据上限（99999张）");
            }catch(Exception e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error",text+"销售退货单失败", "RMI连接错误");
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
                    service.editSaleRefundBill(bill);
                }

                UITool.showAlert(Alert.AlertType.INFORMATION,
                        "Success","已保存销售退货单草稿", "单据ID："+billID);
                dialogStage.close();
            }catch(DataException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error","保存销售退货单草稿失败", "数据库错误");
            }catch(FullException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error", "保存销售退货单草稿失败", "超过单日单据上限（99999张）");
            }catch(Exception e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error","保存销售退货单草稿失败", "RMI连接错误");
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
                    "No Selection","未选择商品", "请在退货商品列表中选择商品");
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
            errorMessage+=("未添加退货商品清单。"+System.lineSeparator());
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
        init(SaleRefundBillBlFactory.getService(),(SaleRefundBillVO)bill,3,stage);
    }

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(SaleRefundBillBlService service,SaleRefundBillVO bill, int command,Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/saleui/SaleRefundBillUI.fxml"));

            // Create the dialog stage
            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("销售退货单信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            SaleRefundBillUIController controller=loader.getController();
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

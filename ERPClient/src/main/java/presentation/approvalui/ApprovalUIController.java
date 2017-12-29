package main.java.presentation.approvalui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import main.java.MainApp;
import main.java.businesslogicfactory.approvalblfactory.ApprovalBlFactory;
import main.java.businesslogicservice.approvalblservice.ApprovalBlService;
import main.java.exception.DataException;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.ManagerPanelUIController;
import main.java.presentation.uiutility.UITool;
import main.java.presentation.uiutility.ApprovalRejectUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;

import java.util.ArrayList;

public class ApprovalUIController extends CenterUIController {
    private ApprovalBlService service;

    private ObservableList<BillVO> billObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<BillVO> billTableView;

    @FXML
    private TableColumn<BillVO,String> IDColumn;
    @FXML
    private TableColumn<BillVO,String> typeColumn;
    @FXML
    private TableColumn<BillVO,String> operatorColumn;

    @FXML
    private ChoiceBox<String> typeSelector;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        billTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        IDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));
        typeColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getType()));
        operatorColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getOperator().getName()));


        String[] typeList=new String[]{"所有单据","库存类单据","进货类单据","销售类单据","财务类单据"};
        typeSelector.setItems(FXCollections.observableArrayList("所有单据","库存类单据","进货类单据","销售类单据","财务类单据"));
        typeSelector.getSelectionModel().selectedIndexProperty().addListener((ov, oldValue, newValue)->{
            setBillList(typeList[newValue.intValue()]);
        });
    }

    // 设置controller数据的方法*****************************************

    public void setApprovalBlService(ApprovalBlService service) {
        this.service = service;
        typeSelector.setValue("所有单据");
    }

    private void setBillList(String type){
        ArrayList<BillVO> bills=new ArrayList<>();
        try{
            if(type.equals("所有单据")){
                bills.addAll(getInventoryBillList());
                bills.addAll(getPurchaseBillList());
                bills.addAll(getSaleBillList());
                bills.addAll(getFinanceBillList());
            }
            else if(type.equals("库存类单据")){
                bills.addAll(getInventoryBillList());
            }
            else if(type.equals("进货类单据")){
                bills.addAll(getPurchaseBillList());
            }
            else if(type.equals("销售类单据")){
                bills.addAll(getSaleBillList());
            }
            else if(type.equals("财务类单据")){
                bills.addAll(getFinanceBillList());
            }
            showBillList(bills);
        }catch(DataException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找单据失败","数据库错误");
        }catch(Exception e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","查找单据失败","RMI连接错误");
        }

    }


    private ArrayList<BillVO> getInventoryBillList() throws Exception{
        ArrayList<BillVO> inventoryBillList=new ArrayList<>();
        BillQueryVO query=new BillQueryVO("待审批",null,null,"库存溢损单",null,null);

        query.type="库存溢损单";
        ArrayList<BillVO> lossOverBillList=service.getBillList(query);
        query.type="库存赠送单";
        ArrayList<BillVO> giftBillList=service.getBillList(query);

        inventoryBillList.addAll(lossOverBillList);
        inventoryBillList.addAll(giftBillList);

        return inventoryBillList;
    }

    private ArrayList<BillVO> getPurchaseBillList() throws Exception{
        ArrayList<BillVO> purchaseBillList=new ArrayList<>();
        BillQueryVO query=new BillQueryVO("待审批",null,null,"进货单",null,null);

        query.type="进货单";
        ArrayList<BillVO> purchaseTradeBillList=service.getBillList(query);
        query.type="进货退货单";
        ArrayList<BillVO> purchaseRefundBillList=service.getBillList(query);

        purchaseBillList.addAll(purchaseTradeBillList);
        purchaseBillList.addAll(purchaseRefundBillList);

        return purchaseBillList;
    }

    private ArrayList<BillVO> getSaleBillList() throws Exception{
        ArrayList<BillVO> saleBillList=new ArrayList<>();
        BillQueryVO query=new BillQueryVO("待审批",null,null,"销售单",null,null);

        query.type="销售单";
        ArrayList<BillVO> saleTradeBillList=service.getBillList(query);
        query.type="销售退货单";
        ArrayList<BillVO> saleRefundBillList=service.getBillList(query);

        saleBillList.addAll(saleTradeBillList);
        saleBillList.addAll(saleRefundBillList);

        return saleBillList;
    }

    private ArrayList<BillVO> getFinanceBillList() throws Exception{
        ArrayList<BillVO> financeBillList=new ArrayList<>();
        BillQueryVO query=new BillQueryVO("待审批",null,null,"收款单",null,null);

        query.type="收款单";
        ArrayList<BillVO> receiptBillList=service.getBillList(query);
        query.type="付款单";
        ArrayList<BillVO> paymentBillList=service.getBillList(query);
        query.type="现金费用单";
        ArrayList<BillVO> cashBillList=service.getBillList(query);

        financeBillList.addAll(receiptBillList);
        financeBillList.addAll(paymentBillList);
        financeBillList.addAll(cashBillList);

        return financeBillList;
    }


    /**
     * 取得客户列表并修改ObservableList的信息
     **/
    private void showBillList(ArrayList<BillVO> billList){
        billObservableList.clear();
        billObservableList.setAll(billList);
        billTableView.setItems(billObservableList);
    }


    // 界面之中会用到的方法******************************************

    @FXML
    private void  handlePass(){
        if(isBillSelected()){
            try{
                for(BillVO bill:billTableView.getSelectionModel().getSelectedItems()){
                    service.pass(bill,root.getOperator());
                }
                setBillList(typeSelector.getValue());
            }catch(DataException e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error","查找单据失败","数据库错误");
            }catch(Exception e){
                UITool.showAlert(Alert.AlertType.ERROR,
                        "Error","查找单据失败","RMI连接错误");
            }
        }
    }

    @FXML
    private void  handleReject(){
        if(isBillSelected()){
            if(billTableView.getSelectionModel().getSelectedItems().size()>1){
                UITool.showAlert(Alert.AlertType.INFORMATION,
                        "Invalid Selection","请重新选择","审批不通过只能选择单张单据");
            }
            else{
                BillVO bill=billTableView.getSelectionModel().getSelectedItem();
                ApprovalRejectUIController.init(service,bill, root.getOperator(),root.getStage());
                setBillList(typeSelector.getValue());
            }
        }
    }

    @FXML
    private void handleCheckBill(){
        if(isBillSelected()){
            if(billTableView.getSelectionModel().getSelectedItems().size()>1){
                UITool.showAlert(Alert.AlertType.INFORMATION,
                        "Invalid Selection","请重新选择","只能选择单张单据查看");
            }
            else{
                BillVO bill=billTableView.getSelectionModel().getSelectedItem();
                bill.getInfoUIController().showInfo(bill,root.getStage());
                setBillList(typeSelector.getValue());
            }
        }
    }

    private boolean isBillSelected(){
        int selectedIndex=billTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            return true;
        }else{
            // Nothing selected
            UITool.showAlert(Alert.AlertType.ERROR,
                    "No Selection","未选择单据","请选择要操作的单据");
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/approvalui/ApprovalUI.fxml"));
            root.setCenterPane(loader.load());

            ApprovalUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setApprovalBlService(ApprovalBlFactory.getService());

            root.setReturnPaneController(new ManagerPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

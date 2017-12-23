package main.java.presentation.clientui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.businesslogicfactory.userblfactory.UserBlFactory;
import main.java.businesslogicservice.clientblservice.ClientBlService;
import main.java.exception.DataException;
import main.java.exception.NotExistException;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.client.ClientVO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class ClientInfoUIController extends InfoUIController{
    private ClientVO client;
    private ClientBlService clientBlService;

    @FXML
    private TextField ID; // 客户编号
    @FXML
    private TextField category; // 客户类别：进货商、销售商
    @FXML
    private TextField level; // 客户级别：1-5（vip）
    @FXML
    private TextField name; // 客户姓名
    @FXML
    private TextField phone; // 客户电话
    @FXML
    private TextField address; // 客户地址
    @FXML
    private TextField post; // 客户邮编
    @FXML
    private TextField email; // 客户电子邮箱
    @FXML
    private TextField receivable; // 客户应收
    @FXML
    private TextField payable; // 客户应付
    @FXML
    private TextField receivableLimit;// 客户应收额度
    @FXML
    private TextField salesman; // 默认业务员
    @FXML
    private ChoiceBox<Integer> levelChoiceBox;
    @FXML
    private ChoiceBox<String> categoryChoiceBox;
    @FXML
    private ChoiceBox<String> salesmanChoiceBox;
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;


    // 加载文件后调用的方法******************************************

    /**
     * 设置各个选择框的信息
     * */
    public void initialize(){
        levelChoiceBox.setItems(FXCollections.observableArrayList(1,2,3,4,5));
        levelChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
            level.setText(String.valueOf(newValue.intValue()+1));
            client.setLevel(newValue.intValue()+1);
        });


        String[] categoryList=new String[]{"供应商","销售商"};
        categoryChoiceBox.setItems(FXCollections.observableArrayList("供应商","销售商"));
        categoryChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
            category.setText(categoryList[newValue.intValue()]);
            client.setCategory(categoryList[newValue.intValue()]);
        });

    }

    // 设置controller数据的方法*****************************************

    public void setClient(ClientVO client) {
        this.client = client;
        ID.setText(client.getID());
        category.setText(client.getCategory());
        level.setText(client.getLevel()==0?"":String.valueOf(client.getLevel()));
        name.setText(client.getName());
        phone.setText(client.getPhone());
        address.setText(client.getAddress());
        post.setText(client.getPost());
        email.setText(client.getEmail());
        receivable.setText(String.valueOf(client.getReceivable()));
        payable.setText(String.valueOf(client.getPayable()));
        receivableLimit.setText(String.valueOf(client.getReceivableLimit()));
        salesman.setText(client.getSalesman().getName());
    }

    public void setClientBlService(ClientBlService clientBlService) {
        this.clientBlService = clientBlService;
        refreshSalesmanList();
    }

    private void refreshSalesmanList(){
        try{
            UserQueryVO query=new UserQueryVO(null,"进货销售人员");
            ArrayList<UserVO> salesmanList= UserBlFactory.getService().getUserList(query);

            ObservableList<String> list=FXCollections.observableArrayList();
            for(int i=0;i<salesmanList.size();i++){
                list.add(salesmanList.get(i).getType()+": "+salesmanList.get(i).getName());
            }
            salesmanChoiceBox.setItems(list);
            salesmanChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldValue,newValue)->{
                salesman.setText(salesmanList.get(newValue.intValue()).getName());
                client.setSalesman(salesmanList.get(newValue.intValue()));
            });
        }catch(DataException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("查找业务员失败");
            alert.setContentText("数据库错误");
            alert.showAndWait();
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("查找业务员失败");
            alert.setContentText("RMI连接错误");
            alert.showAndWait();
        }
    }

    /**
     * 根据数字来设置按钮的文字
     * 按钮被点击时，根据不同的文字执行不同的功能
     * 好吧这个控制耦合有点蠢，但是做的工作少一点
     * 1对应添加客户；2对应编辑客户界面；3对应查看客户界面
     * */
    public void setPaneFunction(int command){
        if(command==1){
            confirm.setText("添加");
        }
        else if(command==2){
            confirm.setText("编辑");
        }
        else if(command==3){
            confirm.setText("确定");
            ID.setEditable(false);
            name.setEditable(false);
            level.setEditable(false);
            category.setEditable(false);
            phone.setEditable(false);
            email.setEditable(false);
            address.setEditable(false);
            post.setEditable(false);
            receivable.setEditable(false);
            payable.setEditable(false);
            receivableLimit.setEditable(false);
            salesman.setEditable(false);

            categoryChoiceBox.setDisable(true);
            levelChoiceBox.setDisable(true);
            salesmanChoiceBox.setDisable(true);
        }
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleConfirm(){
        if(isInputValid()){
            String text=confirm.getText();

            try{
                if(text.equals("添加")){
                    String clientID=clientBlService.addClient(client);
                    String clientName=client.getName();

                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("添加客户成功");
                    alert.setContentText("客户ID："+clientID+System.lineSeparator()+"名字："+clientName);
                    alert.showAndWait();
                }
                else if(text.equals("编辑")){
                    clientBlService.editClient(client);
                    String clientID=client.getID();
                    String clientName=client.getName();

                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("编辑客户成功");
                    alert.setContentText("客户ID："+clientID+System.lineSeparator()+"名字："+clientName);
                    alert.showAndWait();
                }

                dialogStage.close();
            }catch(DataException e){
                e.printStackTrace();
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(text+"客户失败");
                alert.setContentText("数据库错误");
                alert.showAndWait();
            }catch(NotExistException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(text+"客户失败");
                alert.setContentText("客户不存在");
                alert.showAndWait();
            }catch(Exception e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(text+"客户失败");
                alert.setContentText("RMI连接错误");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    /**
     * 检查用户信息的输入是否完整且合法
     * 完整且合法返回true
     * */
    private boolean isInputValid(){
        String errorMessage = "";

        if (category.getText().length()==0) {
            errorMessage+=("未选择客户类型。"+System.lineSeparator());
        }
        if (level.getText().length()==0) {
            errorMessage+=("未输入客户等级。"+System.lineSeparator());
        }
        if (name.getText().length()==0) {
            errorMessage+=("未输入客户姓名。"+System.lineSeparator());
        }
        if (phone.getText().length()==0) {
            errorMessage+=("未输入联系方式。"+System.lineSeparator());
        }
        if (address.getText().length()==0) {
            errorMessage+=("未输入客户地址。"+System.lineSeparator());
        }
        if (post.getText().length()==0) {
            errorMessage+=("未输入客户邮编。"+System.lineSeparator());
        }
        if (email.getText().length()==0) {
            errorMessage+=("未输入客户邮箱。"+System.lineSeparator());
        }

        if (receivable.getText().length()==0) {
            errorMessage+=("未输入客户应收。"+System.lineSeparator());
        }
        else {
            try {
                double i=Double.parseDouble(receivable.getText());
                if(i<0)
                    throw new NumberFormatException();
            } catch(NumberFormatException e) {
                errorMessage+=("客户应收必须是非负数。"+System.lineSeparator());
            }
        }

        if (payable.getText().length()==0) {
            errorMessage+=("未输入客户应付。"+System.lineSeparator());
        }
        else {
            try {
                double i=Double.parseDouble(payable.getText());
                if(i<0)
                    throw new NumberFormatException();
            } catch(NumberFormatException e) {
                errorMessage+=("客户应付必须是非负数。"+System.lineSeparator());
            }
        }

        if (receivableLimit.getText().length()==0) {
            errorMessage+=("未输入客户应收额度。"+System.lineSeparator());
        }
        else {
            try {
                double i=Double.parseDouble(receivableLimit.getText());
                if(i<0)
                    throw new NumberFormatException();
            } catch(NumberFormatException e) {
                errorMessage+=("客户应收额度必须是非负数。"+System.lineSeparator());
            }
        }

        if (salesman.getText()==null) {
            errorMessage+=("未选择业务员。"+System.lineSeparator());
        }

        if(errorMessage.length()==0){
            client.setCategory(category.getText());
            client.setName(name.getText());
            client.setPhone(phone.getText());
            client.setAddress(address.getText());
            client.setPost(post.getText());
            client.setEmail(email.getText());
            client.setReceivable(Double.parseDouble(receivable.getText()));
            client.setPayable(Double.parseDouble(payable.getText()));
            client.setReceivableLimit(Double.parseDouble(receivableLimit.getText()));
            return true;
        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("客户信息错误");
            alert.setHeaderText("请检查客户信息的输入");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    // 加载文件和界面的方法******************************************

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(ClientBlService service,ClientVO client, int command,Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/clientui/ClientInfoUI.fxml"));

            // Create the dialog stage
            Stage dialogStage=new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("客户信息界面");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            dialogStage.setScene(new Scene(loader.load()));

            ClientInfoUIController controller=loader.getController();
            controller.setClientBlService(service);
            controller.setClient(client);
            controller.setDialogStage(dialogStage);
            controller.setPaneFunction(command);


            // Show the dialog and wait until the client closes it.
            dialogStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
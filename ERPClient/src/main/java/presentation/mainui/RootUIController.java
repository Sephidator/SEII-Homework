package main.java.presentation.mainui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.exception.DataException;
import main.java.businesslogicfactory.loginblfactory.LoginBlFactory;
import main.java.presentation.loginui.LoginUIController;
import main.java.presentation.uiutility.UITool;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.user.UserVO;

public class RootUIController {
    private Stage stage;
    private UserVO operator;
    private BorderPane rootPane;
    private CenterUIController returnPaneController;

    @FXML
    private Label name;
    @FXML
    private Label jobNumber;
    @FXML
    private Label type;
    @FXML
    private Button logout;
    @FXML
    private Button exit;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setRootPane(BorderPane rootPane) {
        this.rootPane = rootPane;
    }

    public void setOperator(UserVO operator) {
        this.operator = operator;
        name.setText(operator.getName());
        jobNumber.setText(operator.getJobName());
        type.setText(operator.getType());
    }

    public void setReturnPaneController(CenterUIController returnPaneController) {
        this.returnPaneController = returnPaneController;
    }

    public UserVO getOperator() {
        return operator;
    }

    public Stage getStage() {
        return stage;
    }

    public void setCenterPane(AnchorPane centerPane){
        rootPane.setCenter(centerPane);
    }


    public void showLogoutButton(boolean state){
        logout.setVisible(state);
        exit.setVisible(!state);
    }

    public void setClose(){
        stage.setOnCloseRequest(event-> logout());
    }

    private void logout(){
        try{
            LoginBlFactory.getService().logout(operator.getID());
        }catch(DataException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","登陆失败","数据库连接错误");
        }catch(Exception e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","登陆失败","RMI连接错误");
        }
    }

    @FXML
    private void handleLogout(){
        logout();
        stage.close();
        Stage newStage=new Stage();
        newStage.setTitle("灯具进销存管理系统");
        LoginUIController.init(newStage);
    }

    @FXML
    private void handleExit(){
        returnPaneController.instanceInit(this);
    }

    public static RootUIController initRoot(Stage stage, UserVO operator){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/mainui/RootUI.fxml"));
            BorderPane rootPane=loader.load();
            Scene scene=new Scene(rootPane);
            stage.setScene(scene);
            stage.show();

            RootUIController controller=loader.getController();
            controller.setStage(stage);
            controller.setRootPane(rootPane);
            controller.setOperator(operator);
            controller.setClose();

            return controller;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

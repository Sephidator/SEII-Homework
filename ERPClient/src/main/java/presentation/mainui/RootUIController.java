package main.java.presentation.mainui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.presentation.loginui.LoginUIController;
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
    private JFXButton logout;
    @FXML
    private JFXButton exit;

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

    @FXML
    private void handleLogout(){
        stage.close();
        LoginUIController.init(new Stage());
    }

    @FXML
    private void handleExit(){
        System.out.println("Button called");
        returnPaneController.instanceInit(this);
    }

    public static RootUIController initRoot(Stage stage, UserVO operator){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/mainui/RootUI.fxml"));
            BorderPane rootPane=loader.load();
            Scene scene=new Scene(rootPane);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();

            RootUIController controller=loader.getController();
            controller.setStage(stage);
            controller.setRootPane(rootPane);
            controller.setOperator(operator);

            return controller;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

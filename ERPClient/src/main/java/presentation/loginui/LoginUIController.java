package main.java.presentation.loginui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.java.MainApp;
import main.java.businesslogicfactory.loginblfactory.LoginBlFactory;
import main.java.exception.DataException;
import main.java.exception.LoginException;
import main.java.exception.NotExistException;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.*;
import main.java.presentation.uiutility.UITool;
import main.java.vo.user.UserVO;

import java.io.IOException;

public class LoginUIController {

    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private Button login;
    @FXML
    private Button exit;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Called when the user clickes on the Login button
     * */
    @FXML
    private void handleLogin(){
        try{
            UserVO user= LoginBlFactory.getService().login(usernameField.getText(),passwordField.getText());

            stage.close();
            Stage newStage=new Stage();
            newStage.setTitle("灯具进销存管理系统");
            RootUIController root=RootUIController.initRoot(newStage,user);
            root.showLogoutButton(true);

            if(user.getType().equals("进货销售人员")){
                PurchaseSalePanelUIController.init(root);
            }
            else if(user.getType().equals("库存管理人员")){
                InventoryPanelUIController.init(root);
            }
            else if(user.getType().equals("财务人员")){
                FinancePanelUIController.init(root);
            }
            else if(user.getType().equals("总经理")){
                ManagerPanelUIController.init(root);
            }
            else if(user.getType().equals("管理员")){
                AdministratorPanelUIController.init(root);
            }
        }catch(LoginException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","登陆失败","用户已登录");
        }catch(DataException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","登陆失败","数据库连接错误");
        }catch(NotExistException e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","登陆失败","用户名或密码错误");
        }catch(Exception e){
            UITool.showAlert(Alert.AlertType.ERROR,
                    "Error","登陆失败","RMI连接错误");
        }
    }

    @FXML
    private void handleExit(){
        // 这里还有一个登出操作没有写
        System.exit(0);
    }

    public static void init(Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/loginui/LoginUI.fxml"));
            Scene scene=new Scene(loader.load());
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            LoginUIController controller=loader.getController();
            controller.setStage(stage);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

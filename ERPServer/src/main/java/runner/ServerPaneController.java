package main.java.runner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ServerPaneController {
    @FXML
    private TextArea serverInfo;
    private Stage stage;

    public void initialize(){
        serverInfo.setText("Server Start");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setOnCloseRequest(event-> System.exit(0));
    }

    public static void init(Stage stage){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(ServerRunner.class.getResource("/main/java/runner/ServerPane.fxml"));
            Scene scene=new Scene(loader.load());
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            ServerPaneController controller=loader.getController();
            controller.setStage(stage);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
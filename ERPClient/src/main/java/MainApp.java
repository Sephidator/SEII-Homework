package main.java;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.presentation.loginui.LoginUIController;

import java.io.IOException;

public class MainApp extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage=stage;
        this.stage.setTitle("灯具进销存管理系统");

        getLoginPane();

        // 设置关闭界面时做什么
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            }
        });
    }

    public void getLoginPane(){
        LoginUIController.init(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package main.java;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.presentation.loginui.LoginUIController;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("灯具进销存管理系统");
        LoginUIController.init(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

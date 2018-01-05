package main.java.runner;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.data.DataHelper;

public class ServerRunner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            new DataHelper();
            new RemoteHelper();
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.setTitle("灯具进销存管理系统");
        ServerPaneController.init(stage);
    }
}

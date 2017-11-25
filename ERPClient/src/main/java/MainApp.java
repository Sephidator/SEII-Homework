import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private Scene scene;



    @Override
    public void start(Stage primaryStage) {
        this.primaryStage=primaryStage;
        this.primaryStage.setTitle("BF-IDE");

        initLoginPane();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            }
        });
    }

    public void initLoginPane(){
        getLoginPane();
    }

    public void getLoginPane(){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("LoginUI.fxml"));

            scene=new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch(IOException e){
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

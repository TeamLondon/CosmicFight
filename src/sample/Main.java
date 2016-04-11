package sample;

import controllers.StageManager;
import enums.Scenes;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception{
        init(primaryStage);
        primaryStage.show();
    }

    public void init(Stage stage) throws java.io.IOException{
        StageManager stageManager = new StageManager(stage);
        stageManager.setScene(Scenes.StartGameScene);
        stage.setTitle("Cosmic fight");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

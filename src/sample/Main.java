package sample;

import core.*;
import enums.Scenes;
import interfaces.Database;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception{
        init(primaryStage);
        primaryStage.show();
    }

    public void init(Stage stage) throws java.io.IOException{
        SimpleSceneBuilder sceneBuilder = new SimpleSceneBuilder();
        Database gameDatabase = new GameDatabase();
        MessageBox messageBox = new MessageBox();
        ConfirmBox confirmBox = new ConfirmBox();
        SimpleStageManager stageManager = new SimpleStageManager(stage, sceneBuilder, gameDatabase, messageBox, confirmBox);

        stageManager.setScene(Scenes.StartGameScene);
        stage.setTitle("Cosmic fight");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package controllers;

import core.Constants;
import enums.Scenes;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


import java.io.IOException;

public class StageManager {
    private Stage stage;

    private FXMLLoader myLoader;

    public StageManager(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scenes sceneType){
        Scene scene = null;
        switch (sceneType){
            case StartGameScene:
                scene = getStartScene();
                break;
            case InsertUsernameScene:
                scene = getInsertUsernameScene();
                break;
            case FirstLevelScene:
                try {
                    scene = getFirstLevelScene();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

        this.stage.setScene(scene);
    }

    private Scene getInsertUsernameScene() {
        Scene scene = null;
        this.myLoader = new FXMLLoader(getClass().getResource(Constants.InsertUsernameSceneResource));
        try {
            Parent loadScreen = this.myLoader.load();
            scene = new Scene(loadScreen);

            InsertUsernameController insertUsernameController = this.myLoader.getController();
            insertUsernameController.setStageManager(this);
        } catch (IOException e) {
            // Alert.show("Scene not loaded.");
        }
        return scene;
    }
    private Scene getStartScene() {
        Scene scene = null;
        this.myLoader = new FXMLLoader(getClass().getResource(Constants.StartGameSceneResource));
        try {
            Parent loadScreen = this.myLoader.load();
            scene = new Scene(loadScreen);
            StartGameController startGameController = this.myLoader.getController();
            startGameController.setStageManager(this);
        } catch (IOException e) {
            // Alert.show("Scene not loaded.");
        }
        return scene;
    }
    private Scene getFirstLevelScene() throws Exception {
        FirstLevelController controller = new FirstLevelController(this.stage);
        return controller.getCurrentScene();
    }

    public Stage getStage() {
        return stage;
    }
}

package core;

import controllers.*;
import interfaces.StageManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

import enums.Scenes;

public class SimpleSceneBuilder {
    private FXMLLoader myLoader;

    public SimpleSceneBuilder() {
    }

    public Scene build(Scenes sceneType, SimpleStageManager stageManager){
        Scene scene = null;
        switch (sceneType){
            case StartGameScene:
                scene = getStartScene(stageManager);
                break;
            case InsertUsernameScene:
                scene = getInsertUsernameScene(stageManager);
                break;
            case FirstLevelScene:
                scene = getFirstLevelScene(stageManager);
                break;
            case HighScoreScene:
                scene = getHighScoreScene(stageManager);
                break;
            case ExitGameScene:
                scene = getExitGameScene(stageManager);
                break;
            default:
                break;
        }

        return scene;
    }

    private Scene getExitGameScene(SimpleStageManager stageManager) {
        Scene scene = null;
        this.myLoader = new FXMLLoader(getClass().getResource(Constants.EndGameSceneResource));
        try {
            Parent loadScreen = this.myLoader.load();
            scene = new Scene(loadScreen);

            GameOverController gameOverController = this.myLoader.getController();
            gameOverController.setStageManager(stageManager);

        } catch (IOException e) {
            stageManager.getMessageBox().display("Exception", "Scene not loaded.");
            System.out.println(e.getMessage());
        }
        return scene;
    }

    private Scene getHighScoreScene(StageManager stageManager) {
        Scene scene = null;
        this.myLoader = new FXMLLoader(getClass().getResource(Constants.HighScoreSceneResource));
        try {
            Parent loadScreen = this.myLoader.load();
            scene = new Scene(loadScreen);

            HighScoreController highScoreController = this.myLoader.getController();
            highScoreController.setStageManager(stageManager);

        } catch (IOException e) {
            stageManager.getMessageBox().display("Exception", "Scene not loaded.");
            System.out.println(e.getMessage());
        }
        return scene;
    }

    private Scene getInsertUsernameScene(SimpleStageManager stageManager) {
        Scene scene = null;
        this.myLoader = new FXMLLoader(getClass().getResource(Constants.InsertUsernameSceneResource));
        try {
            Parent loadScreen = this.myLoader.load();
            scene = new Scene(loadScreen);

            InsertUsernameController insertUsernameController = this.myLoader.getController();
            insertUsernameController.setStageManager(stageManager);
        } catch (IOException e) {
            stageManager.getMessageBox().display("Exception", "Scene not loaded.");
        }
        return scene;
    }

    private Scene getStartScene(SimpleStageManager stageManager) {
        Scene scene = null;
        this.myLoader = new FXMLLoader(getClass().getResource(Constants.StartGameSceneResource));
        try {
            Parent loadScreen = this.myLoader.load();
            scene = new Scene(loadScreen);
            StartGameController startGameController = this.myLoader.getController();
            startGameController.setStageManager(stageManager);
        } catch (IOException e) {
            stageManager.getMessageBox().display("Exception", "Scene not loaded.");
        }
        return scene;
    }

    public Scene getFirstLevelScene(SimpleStageManager stageManager) {
        FirstLevelController controller = new FirstLevelController(stageManager);
        Scene scene = null;
        try {
            scene = controller.getCurrentScene();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return scene;
    }
}
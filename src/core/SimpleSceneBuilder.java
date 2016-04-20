package core;

import controllers.*;
import core.spawners.SimpleSpawner;
import enums.Scenes;
import gameObjects.staticGameObjects.SimpleHUD;
import interfaces.*;
import interfaces.core.Database;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import models.contracts.ConfirmBox;
import models.contracts.MessageBox;
import core.handlers.InputHandler;
import utilities.Constants;

import java.io.IOException;

public class SimpleSceneBuilder implements SceneBuilder {
    private FXMLLoader myLoader;

    private SimpleSpawner spawner;
    private InputHandler inputHandler;
    private SimpleHUD hud;
    private Database database;
    private MessageBox messageBox;
    private ConfirmBox confirmBox;

    public SimpleSceneBuilder(
            Database database,
            SimpleSpawner spawner,
            InputHandler inputHandler,
            ConfirmBox confirmBox,
            MessageBox messageBox,
            SimpleHUD hud) {
        this.database = database;
        this.messageBox = messageBox;
        this.confirmBox = confirmBox;
        this.spawner = spawner;
        this.inputHandler = inputHandler;
        this.hud = hud;
    }

    public Scene build(Scenes sceneType, StageManager stageManager) {
        Scene scene = null;
        switch (sceneType) {
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

    private Scene getExitGameScene(StageManager stageManager) {
        Scene scene = null;
        this.myLoader = new FXMLLoader(getClass().getResource(Constants.END_GAME_SCENE_RESOURCE));
        try {
            Parent loadScreen = this.myLoader.load();
            scene = new Scene(loadScreen);

            GameOverController gameOverController = this.myLoader.getController();
            gameOverController.initialize(stageManager, this.database, this.messageBox, this.confirmBox);

        } catch (IOException e) {
            this.messageBox.display("Exception", "Scene not loaded.");
            System.out.println(e.getMessage());
        }
        return scene;
    }

    private Scene getHighScoreScene(StageManager stageManager) {
        Scene scene = null;
        this.myLoader = new FXMLLoader(getClass().getResource(Constants.HIGH_SCORE_SCENE_RESOURCE));
        try {
            Parent loadScreen = this.myLoader.load();
            scene = new Scene(loadScreen);

            HighScoreController highScoreController = this.myLoader.getController();
            highScoreController.initialize(stageManager, this.database, this.messageBox, this.confirmBox);

        } catch (IOException e) {
            this.messageBox.display("Exception", "Scene not loaded.");
            System.out.println(e.getMessage());
        }
        return scene;
    }

    private Scene getInsertUsernameScene(StageManager stageManager) {
        Scene scene = null;
        this.myLoader = new FXMLLoader(getClass().getResource(Constants.INSERT_USERNAME_SCENE_RESOURCE));
        try {
            Parent loadScreen = this.myLoader.load();
            scene = new Scene(loadScreen);

            InsertUsernameController insertUsernameController = this.myLoader.getController();
            insertUsernameController.initialize(stageManager, this.database, this.messageBox, this.confirmBox);
        } catch (IOException e) {
            this.messageBox.display("Exception", "Scene not loaded.");
        }
        return scene;
    }

    private Scene getStartScene(StageManager stageManager) {
        Scene scene = null;
        this.myLoader = new FXMLLoader(getClass().getResource(Constants.START_GAME_SCENE_RESOURCE));
        try {
            Parent loadScreen = this.myLoader.load();
            scene = new Scene(loadScreen);
            StartGameController startGameController = this.myLoader.getController();
            startGameController.initialize(stageManager, this.database, this.messageBox, this.confirmBox);
        } catch (IOException e) {
            this.messageBox.display("Exception", "Scene not loaded.");
        }
        return scene;
    }

    private Scene getFirstLevelScene(StageManager stageManager) {
        FirstLevelController controller = new FirstLevelController(
                stageManager,
                this.database,
                this.spawner,
                this.inputHandler,
                this.messageBox,
                this.confirmBox,
                this.hud);
        Scene scene = null;
        try {
            scene = controller.getCurrentScene();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return scene;
    }
}
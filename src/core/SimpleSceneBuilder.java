package core;

import controllers.*;
import core.managers.SimpleStageManager;
import gameObjects.staticGameObjects.HUD;
import interfaces.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

import enums.Scenes;
import models.handlers.InputHandler;
import models.handlers.ObjectHandler;
import utilities.Constants;

public class SimpleSceneBuilder {
    private FXMLLoader myLoader;

    private Spawner spawner;
    private InputHandler inputHandler;
    private HUD hud;
    private Database database;
    private MessageBox messageBox;
    private ConfirmBox confirmBox;

    public SimpleSceneBuilder(
            Database database,
            Spawner spawner,
            InputHandler inputHandler,
            ConfirmBox confirmBox,
            MessageBox messageBox,
            HUD hud) {
        this.database = database;
        this.messageBox = messageBox;
        this.confirmBox = confirmBox;
        this.spawner = spawner;
        this.inputHandler = inputHandler;
        this.hud = hud;
    }

    public SimpleSceneBuilder(InputHandler inputHandler, Spawner spawner, HUD hud) {
        this.hud = hud;
        this.inputHandler = inputHandler;
        this.spawner = spawner;
    }

    public Scene build(Scenes sceneType, SimpleStageManager stageManager) {
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

    private Scene getExitGameScene(SimpleStageManager stageManager) {
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

    private Scene getInsertUsernameScene(SimpleStageManager stageManager) {
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

    private Scene getStartScene(SimpleStageManager stageManager) {
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

    public Scene getFirstLevelScene(SimpleStageManager stageManager) {
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
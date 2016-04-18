package core;

import controllers.*;
import core.managers.SimpleStageManager;
import gameObjects.staticGameObjects.HUD;
import interfaces.Player;
import interfaces.StageManager;
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

    private Player player;
    private Spawner spawner;
    private ObjectHandler objectHandler;
    private InputHandler inputHandler;
    private HUD hud;

    public SimpleSceneBuilder(Player player, Spawner spawner, ObjectHandler objectHandler, InputHandler inputHandler, HUD hud) {
        this.player = player;
        this.spawner = spawner;
        this.objectHandler = objectHandler;
        this.inputHandler = inputHandler;
        this.hud = hud;
    }

    public SimpleSceneBuilder(InputHandler inputHandler, ObjectHandler objectHandler, Spawner spawner,HUD hud) {
        this.hud = hud;
        this.inputHandler = inputHandler;
        this.objectHandler = objectHandler;
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
                scene = getFirstLevelScene(stageManager, stageManager.getDatabase().getPlayer(), this.inputHandler, this.objectHandler, this.hud, this.spawner);
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
            gameOverController.initialize(stageManager, stageManager.getDatabase(), stageManager.getMessageBox(), stageManager.getConfirmBox());

        } catch (IOException e) {
            stageManager.getMessageBox().display("Exception", "Scene not loaded.");
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
            highScoreController.initialize(stageManager, stageManager.getDatabase(), stageManager.getMessageBox(), stageManager.getConfirmBox());

        } catch (IOException e) {
            stageManager.getMessageBox().display("Exception", "Scene not loaded.");
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
            insertUsernameController.initialize(stageManager, stageManager.getDatabase(), stageManager.getMessageBox(), stageManager.getConfirmBox());
        } catch (IOException e) {
            stageManager.getMessageBox().display("Exception", "Scene not loaded.");
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
            startGameController.initialize(stageManager, stageManager.getDatabase(), stageManager.getMessageBox(), stageManager.getConfirmBox());
        } catch (IOException e) {
            stageManager.getMessageBox().display("Exception", "Scene not loaded.");
        }
        return scene;
    }

    public Scene getFirstLevelScene(
            SimpleStageManager stageManager,
            Player player,
            InputHandler inputHandler,
            ObjectHandler objectHandler,
            HUD hud,
            Spawner spawner) {
        FirstLevelController controller = new FirstLevelController(
                stageManager,
                stageManager.getDatabase(),
                stageManager.getMessageBox(),
                stageManager.getConfirmBox(),
                objectHandler,
                player,
                inputHandler,
                hud,
                spawner);
        Scene scene = null;
        try {
            scene = controller.getCurrentScene();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return scene;
    }
}
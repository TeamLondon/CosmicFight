package controllers;

import interfaces.SceneController;
import interfaces.StageController;
import javafx.scene.control.Button;
import enums.Scenes;
import core.Constants;

public class StartGameController implements SceneController, StageController {
    private SceneManager sceneManager;

    private StageManager stageManager;

    public StartGameController() {
    }

    public Button startGameButton;

    public Button highScoreButton;

    public Button exitGameButton;

    public void onStartGameButtonClick() {
        // this.sceneManager.setScene(Constants.InsertUsernameSceneID);
        this.stageManager.setScene(Scenes.InsertUsernameScene);
    }

    public void onHighScoreButtonClick() {
        this.sceneManager.setScene(Constants.HighScoreSceneID);
    }

    public void onExitGameButtonClick() {
        this.sceneManager.setScene(Constants.EndGameSceneID);
    }

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void setStageManager(StageManager stageManager) {
        this.stageManager = stageManager;
    }
}

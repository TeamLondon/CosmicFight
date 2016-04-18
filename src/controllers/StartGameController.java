package controllers;

import utilities.Constants;
import enums.Scenes;
import models.contracts.ConfirmBox;
import interfaces.core.Database;
import models.contracts.MessageBox;
import interfaces.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class StartGameController extends AbstractController {
    public StartGameController() {
    }

    public Button startGameButton;

    public Button highScoreButton;

    public Button exitGameButton;

    @Override
    public void initialize(StageManager stageManager, Database database, MessageBox messageBox, ConfirmBox confirmBox) {
        this.startGameButton.setStyle(Constants.BUTTON_STYLE);
        this.highScoreButton.setStyle(Constants.BUTTON_STYLE);
        this.exitGameButton.setStyle(Constants.BUTTON_STYLE);
        super.initialize(stageManager, database, messageBox, confirmBox);
    }

    public void onStartGameButtonClick() {
        this.getStageManager().setScene(Scenes.InsertUsernameScene);
    }

    public void onHighScoreButtonClick() {
        this.getStageManager().setScene(Scenes.HighScoreScene);
    }

    public void onExitGameButtonClick() {
        this.onCloseRequest();
    }

    @FXML
    public void onEnterKeyPress(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            if (e.getSource() == this.startGameButton) {
                this.onStartGameButtonClick();
            } else if (e.getSource() == this.highScoreButton) {
                this.onHighScoreButtonClick();
            } else if (e.getSource() == this.exitGameButton) {
                this.onExitGameButtonClick();
            }
        }
    }
}

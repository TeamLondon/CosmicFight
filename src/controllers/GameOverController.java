package controllers;

import utilities.Constants;
import enums.Scenes;
import interfaces.ConfirmBox;
import interfaces.Database;
import interfaces.MessageBox;
import interfaces.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameOverController extends AbstractController {
    @FXML
    private Label badLuck;
    @FXML
    private Label congratulationsYouWin;

    @FXML
    public Label score;

    @FXML
    private Button exitGame;
    @FXML
    private Button playNewGame;

    @Override
    public void initialize(StageManager stageManager, Database database, MessageBox messageBox, ConfirmBox confirmBox) {
        this.exitGame.setStyle(Constants.BUTTON_STYLE);
        this.playNewGame.setStyle(Constants.BUTTON_STYLE);
        super.initialize(stageManager, database, messageBox, confirmBox);
        boolean isAlive = this.getDatabase().getPlayer().getHitPoints() > 0;
        if (isAlive) {
            this.congratulationsYouWin.setVisible(true);
            this.score.textProperty().
                    set(this.getDatabase().getPlayer().getName() +
                    " score: " + this.getDatabase().getPlayer().getHighScore().getPlayerScore());
            this.score.setVisible(true);
        } else {
            this.badLuck.setVisible(true);
        }
    }

    public void playNewGameButton() {
        this.getDatabase().saveHighScoreInfo();
        this.getStageManager().setScene(Scenes.StartGameScene);
    }

    public void exitGameButton() {
        this.onCloseRequest();
    }

    @FXML
    public void onEnterKeyPress(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            if (e.getSource() == this.playNewGame) {
                this.playNewGameButton();
            }

            if (e.getSource() == this.exitGame) {
                this.exitGameButton();
            }
        }
    }
}

package controllers;

import core.Constants;
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
import javafx.scene.paint.Paint;

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
        boolean isAlive = this.getGameDatabase().getPlayer().getHitPoints() > 0;
        if (isAlive) {
            this.congratulationsYouWin.setVisible(true);
            this.score.textProperty().
                    set(this.getGameDatabase().getPlayer().getName() +
                    " score: " + this.getGameDatabase().getPlayer().getHighScore().getPlayerScore());
            this.score.setVisible(true);
        } else {
            this.badLuck.setVisible(true);
        }
    }

    public void playNewGameButton() {
        this.getGameDatabase().saveHighScoreInfo();
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

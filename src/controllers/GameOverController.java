package controllers;

import core.Constants;
import enums.Scenes;
import interfaces.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameOverController extends AbstractController{
    @FXML
    private Label badLuck;
    @FXML
    private Label congratulationsYouWin;

    @FXML
    private Button exitGame;
    @FXML
    private Button playNewGame;

    @Override
    @FXML
    public void setStageManager(StageManager stageManager) {
        this.exitGame.setStyle(Constants.BUTTON_STYLE);
        this.playNewGame.setStyle(Constants.BUTTON_STYLE);
        super.setStageManager(stageManager);
       boolean isAlive = this.getGameDatabase().getPlayer().getHitPoints() > 0;
       if(isAlive){
           this.badLuck.setVisible(false);
       }else{
           this.congratulationsYouWin.setVisible(false);
       }
    }

    public void playNewGameButton(){
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

package controllers;

import enums.Scenes;
import interfaces.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameOverController extends AbstractController{
    @FXML
    public Label badLuck;
    @FXML
    public Label congratulationsYouWin;

    @FXML
    public Button exitGame;
    @FXML
    public Button playNewGame;

    @Override
    @FXML
    public void setStageManager(StageManager stageManager) {
        super.setStageManager(stageManager);
        boolean isAlive = this.getGameDatabase().getPlayer().getHitPoints() > 0;
        if(isAlive){
            this.badLuck.setVisible(false);
        }else{
            this.congratulationsYouWin.setVisible(false);
        }
    }

    public void playNewGameButton(){
        this.getStageManager().setScene(Scenes.FirstLevelScene);
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

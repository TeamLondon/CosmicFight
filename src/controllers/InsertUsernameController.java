package controllers;

import core.Constants;
import enums.Scenes;
import gameObjects.dynamicGameObjects.player.GamePlayer;
import interfaces.Player;
import interfaces.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import utilityModels.GameHighScore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InsertUsernameController extends AbstractController {
    public TextField usernameTextField;

    public Button enterUsernameButton;

    @Override
    public void setStageManager(StageManager stageManager) {
        this.enterUsernameButton.setStyle(Constants.BUTTON_STYLE);
        super.setStageManager(stageManager);
    }

    public void onEnterUsernameButtonClick(){
        setPlayer();
    }

    @FXML
    public void onEnterUsernameKeyPressed(KeyEvent e)
    {
        if(e.getCode() == KeyCode.ENTER)
        {
            setPlayer();
        }
    }

    private void setPlayer() {
        String input = this.usernameTextField.getText();
        Pattern pattern = Pattern.compile("([a-zA-Z]+)[\\W]+([0-9]+)");
        String name = input;
        Player gamePlayer = new GamePlayer(100, 100 ,name);
        getStageManager().getDatabase().setPlayer(gamePlayer);
        this.getStageManager().getDatabase().addHighScore(gamePlayer.getHighScore());
        setNextScene();
    }

    private void setNextScene() {
        this.getStageManager().setScene(Scenes.FirstLevelScene);
    }
}

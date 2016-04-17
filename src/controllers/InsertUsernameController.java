package controllers;

import core.Constants;
import enums.Scenes;
import gameObjects.dynamicGameObjects.player.GamePlayer;
import interfaces.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.regex.Pattern;


public class InsertUsernameController extends AbstractController {
    public TextField usernameTextField;

    public Button enterUsernameButton;

    @Override
    public void initialize(StageManager stageManager, Database database, MessageBox messageBox, ConfirmBox confirmBox) {
        this.enterUsernameButton.setStyle(Constants.BUTTON_STYLE);
        super.initialize(stageManager, database, messageBox, confirmBox);
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

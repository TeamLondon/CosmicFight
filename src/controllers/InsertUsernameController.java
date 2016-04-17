package controllers;

import core.Constants;
import enums.Scenes;
import gameObjects.dynamicGameObjects.player.GamePlayer;
import interfaces.Player;
import interfaces.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import utilityModels.GameHighScore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InsertUsernameController extends AbstractController {
    @FXML
    private TextField usernameTextField;

    @FXML
    private Button enterUsernameButton;

    @FXML
    private Label usernameInfo;

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

    @FXML
    private void setPlayer() {
        String input = this.usernameTextField.getText();

        Pattern validationPattern = Pattern.compile("[\\w]{5,16}");
        Matcher validationMatcher = validationPattern.matcher(input);

        String name = "";
        if (validationMatcher.find()){
            name = input;
            Player gamePlayer = new GamePlayer(100, 100 ,name);

            this.getStageManager().getDatabase().setPlayer(gamePlayer);
            this.getStageManager().getDatabase().addHighScore(gamePlayer.getHighScore());
            setNextScene();

        } else{
            this.usernameTextField.clear();
            this.usernameInfo.setVisible(true);
        }
    }

    private void setNextScene() {
        this.getStageManager().setScene(Scenes.FirstLevelScene);
    }
}
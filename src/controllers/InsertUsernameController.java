package controllers;

import gameObjects.dynamicGameObjects.player.GamePlayer;
import interfaces.core.Database;
import models.contracts.ConfirmBox;
import models.contracts.MessageBox;
import utilities.Constants;
import enums.Scenes;
import interfaces.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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

    @FXML
    private void setPlayer() {
        String input = this.usernameTextField.getText();

        Pattern validationPattern = Pattern.compile("[\\w]{5,16}");
        Matcher validationMatcher = validationPattern.matcher(input);

        String name = "";
        if (validationMatcher.find()){
            name = input;
            this.getDatabase().setPlayer(new GamePlayer(name));
            this.getDatabase().addHighScore(this.getDatabase().getPlayer().getHighScore());
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
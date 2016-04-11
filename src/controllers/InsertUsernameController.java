package controllers;

import enums.Scenes;
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

    public void onEnterUsernameButtonClick(){
        setNextScene();
    }

    @FXML
    public void onEnterUsernameKeyPressed(KeyEvent e)
    {
        if(e.getCode() == KeyCode.ENTER)
        {
            String input = this.usernameTextField.getText();
            Pattern pattern = Pattern.compile("([a-zA-Z]+)[\\W]+([0-9]+)");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()){
                String name = matcher.group(1);
                Integer points = Integer.parseInt(matcher.group(2));
                this.getStageManager().getDatabase().addHighScore(new GameHighScore(name, points));
            }
            setNextScene();
        }
    }

    private void setNextScene() {
        this.getStageManager().setScene(Scenes.FirstLevelScene);
    }
}

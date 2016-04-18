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
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class HighScoreController extends AbstractController {
    public ScrollPane highScoreScrollPane;

    public Label highScoreResultsLabel;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Label highScoreLabel;

    @Override
    public void initialize(StageManager stageManager, Database database, MessageBox messageBox, ConfirmBox confirmBox) {
        this.mainMenuButton.setStyle(Constants.BUTTON_STYLE);
        super.initialize(stageManager, database, messageBox, confirmBox);
        this.initializeInfo();
    }

    public void onMenuButtonClick() {
        this.getStageManager().setScene(Scenes.StartGameScene);
    }

    private void initializeInfo() {
        String highScoreText = this.getGameDatabase().getHighScore();
        this.highScoreResultsLabel.setText(highScoreText);
    }

    @FXML
    public void onMenuButtonKeyPress(KeyEvent k) {
        if (k.getCode() == KeyCode.ENTER) {
            this.onMenuButtonClick();
        }
    }
}


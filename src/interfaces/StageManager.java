package interfaces;

import core.ConfirmBox;
import core.MessageBox;
import core.SimpleSceneBuilder;
import enums.Scenes;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface StageManager {
    SimpleSceneBuilder getSceneBuilder();

    Database getDatabase();

    Stage getStage();

    Scene getCurrentScene();

    ConfirmBox getConfirmBox();

    MessageBox getMessageBox();

    void setScene(Scenes sceneType);
}

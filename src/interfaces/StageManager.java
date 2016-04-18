package interfaces;

import models.outputBoxes.SimpleConfirmBox;
import models.outputBoxes.SimpleMessageBox;
import core.SimpleSceneBuilder;
import enums.Scenes;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface StageManager {
    SimpleSceneBuilder getSceneBuilder();
    Stage getStage();

    Scene getCurrentScene();

    void setScene(Scenes sceneType);
}

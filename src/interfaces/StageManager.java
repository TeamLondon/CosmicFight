package interfaces;

import enums.Scenes;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface StageManager {
    SceneBuilder getSceneBuilder();

    Stage getStage();

    Scene getCurrentScene();

    void setScene(Scenes sceneType);
}

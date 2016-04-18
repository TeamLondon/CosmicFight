package interfaces;
import enums.Scenes;
import javafx.scene.Scene;

public interface SceneBuilder {
    Scene build(Scenes sceneType, StageManager stageManager);
}

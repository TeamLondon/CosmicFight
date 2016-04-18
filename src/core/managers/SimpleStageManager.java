package core.managers;

import enums.Scenes;
import interfaces.SceneBuilder;
import interfaces.StageManager;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimpleStageManager  implements StageManager{
    private Scene currentScene;

    private Stage stage;

    private SceneBuilder sceneBuilder;

    public SimpleStageManager(Stage stage, SceneBuilder sceneBuilder) {
        this.stage = stage;
        this.sceneBuilder =sceneBuilder;
    }

    @Override
    public SceneBuilder getSceneBuilder() {
        return this.sceneBuilder;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setScene(Scenes sceneType) {
        Scene scene = sceneBuilder.build(sceneType, this);
        this.currentScene = scene;
        this.stage.setScene(scene);
    }
}

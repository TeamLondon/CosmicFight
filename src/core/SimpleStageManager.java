package core;

import enums.Scenes;
import interfaces.Database;
import interfaces.StageManager;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimpleStageManager  implements StageManager{
    private SimpleSceneBuilder sceneBuilder;

    private Scene currentScene;

    private MessageBox messageBox;

    private ConfirmBox confirmBox;

    private Stage stage;

    private Database gameDatabase;

    public SimpleStageManager(Stage stage, SimpleSceneBuilder sceneBuilder, Database gameDatabase, MessageBox messageBox, ConfirmBox confirmBox) {
        this.stage = stage;
        this.sceneBuilder = sceneBuilder;
        this.gameDatabase = gameDatabase;
        this.messageBox = messageBox;
        this.confirmBox = confirmBox;
    }

    public SimpleSceneBuilder getSceneBuilder() {
        return sceneBuilder;
    }

    @Override
    public Database getDatabase() {
        return this.gameDatabase;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    @Override
    public ConfirmBox getConfirmBox() {
        return this.confirmBox;
    }

    @Override
    public MessageBox getMessageBox() {
        return this.messageBox;
    }

    public void setScene(Scenes sceneType) {
        Scene scene = sceneBuilder.build(sceneType, this);
        this.currentScene = scene;
        this.stage.setScene(scene);
    }

}

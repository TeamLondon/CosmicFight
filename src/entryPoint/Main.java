package entryPoint;

import core.*;
import core.factories.UnitFactory;
import core.managers.PositionManager;
import core.managers.SimpleStageManager;
import data.GameDatabase;
import enums.Scenes;
import gameObjects.dynamicGameObjects.player.GamePlayer;
import gameObjects.staticGameObjects.HUD;
import interfaces.Database;
import interfaces.Player;
import javafx.application.Application;
import javafx.stage.Stage;
import models.handlers.InputHandler;
import models.handlers.ObjectHandler;
import models.outputBoxes.SimpleConfirmBox;
import models.outputBoxes.SimpleMessageBox;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        initialize(primaryStage);
        primaryStage.show();
    }

    public void initialize(Stage stage) throws java.io.IOException {
        UnitFactory unitFactory = new UnitFactory();
        PositionManager positionManager = new PositionManager();
        Spawner spawner = new Spawner(unitFactory, positionManager);

        ObjectHandler objectHandler = new ObjectHandler();
        InputHandler inputHandler = new InputHandler(objectHandler);

        HUD hud = new HUD();
        Database gameDatabase = new GameDatabase();
        SimpleMessageBox messageBox = new SimpleMessageBox();
        SimpleConfirmBox confirmBox = new SimpleConfirmBox();

        SimpleSceneBuilder sceneBuilder = new SimpleSceneBuilder(
                gameDatabase,
                spawner,
                inputHandler,
                confirmBox,
                messageBox,
                hud);

        SimpleStageManager stageManager = new SimpleStageManager(stage, sceneBuilder);

        stageManager.setScene(Scenes.StartGameScene);
        stage.setTitle("Cosmic fight");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

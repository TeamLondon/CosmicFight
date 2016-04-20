package entryPoint;

import core.*;
import core.factories.SimpleAttackFactory;
import core.factories.SimpleBonusFactory;
import core.factories.SimpleBossFactory;
import core.factories.SimpleUnitFactory;
import core.managers.PositionManager;
import core.managers.SimpleStageManager;
import core.spawners.SimpleSpawner;
import data.GameDatabase;
import enums.Scenes;
import gameObjects.staticGameObjects.SimpleHUD;
import interfaces.StageManager;
import interfaces.core.Database;
import interfaces.factories.AttacksFactory;
import interfaces.factories.BonusFactory;
import interfaces.factories.BossFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import core.handlers.InputHandler;
import core.handlers.ObjectHandler;
import models.outputBoxes.SimpleConfirmBox;
import models.outputBoxes.SimpleMessageBox;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        initialize(primaryStage);
        primaryStage.show();
    }

    public void initialize(Stage stage) throws java.io.IOException {

        ObjectHandler objectHandler = new ObjectHandler();
        InputHandler inputHandler = new InputHandler(objectHandler);

        SimpleUnitFactory unitFactory = new SimpleUnitFactory();
        PositionManager positionManager = new PositionManager();
        AttacksFactory attacksFactory = new SimpleAttackFactory();
        BonusFactory bonusFactory = new SimpleBonusFactory();
        BossFactory bossFactory = new SimpleBossFactory(objectHandler, attacksFactory);
        SimpleSpawner spawner = new SimpleSpawner(unitFactory, bossFactory, bonusFactory, positionManager);

        SimpleHUD hud = new SimpleHUD();
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

        StageManager stageManager = new SimpleStageManager(stage, sceneBuilder);

        stageManager.setScene(Scenes.StartGameScene);
        stage.setTitle("Cosmic fight");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package entryPoint;

import core.*;
import core.factories.SimpleAttackFactory;
import core.factories.SimpleBonusFactory;
import core.factories.SimpleUnitFactory;
import core.managers.PositionManager;
import core.managers.SimpleStageManager;
import data.GameDatabase;
import enums.Scenes;
import gameObjects.staticGameObjects.SimpleHUD;
import interfaces.core.Database;
import interfaces.factories.AttacksFactory;
import interfaces.factories.BonusFactory;
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
        SimpleUnitFactory unitFactory = new SimpleUnitFactory();
        PositionManager positionManager = new PositionManager();
        AttacksFactory attacksFactory = new SimpleAttackFactory();
        BonusFactory bonusFactory = new SimpleBonusFactory();
        Spawner spawner = new Spawner(unitFactory,attacksFactory, bonusFactory, positionManager);

        ObjectHandler objectHandler = new ObjectHandler();
        InputHandler inputHandler = new InputHandler(objectHandler);

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

        SimpleStageManager stageManager = new SimpleStageManager(stage, sceneBuilder);

        stageManager.setScene(Scenes.StartGameScene);
        stage.setTitle("Cosmic fight");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

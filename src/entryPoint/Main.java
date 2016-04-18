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

    public void start(Stage primaryStage) throws Exception{
        initialize(primaryStage);
        primaryStage.show();
    }

    public void initialize(Stage stage) throws java.io.IOException{
        Player player = new GamePlayer(100, 100);
        UnitFactory unitFactory = new UnitFactory();
        PositionManager positionManager = new PositionManager();
        Spawner spawner = new Spawner(unitFactory, positionManager);
        ObjectHandler objectHandler = new ObjectHandler(player);
        InputHandler inputHandler = new InputHandler(player,objectHandler);
        HUD hud = new HUD(player);
        SimpleSceneBuilder sceneBuilder = new SimpleSceneBuilder(player,spawner, objectHandler, inputHandler, hud);
        Database gameDatabase = new GameDatabase();
        gameDatabase.setPlayer(player);
        SimpleMessageBox messageBox = new SimpleMessageBox();
        SimpleConfirmBox confirmBox = new SimpleConfirmBox();
        SimpleStageManager stageManager = new SimpleStageManager(stage, sceneBuilder, gameDatabase, messageBox, confirmBox);

        stageManager.setScene(Scenes.StartGameScene);
        stage.setTitle("Cosmic fight");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

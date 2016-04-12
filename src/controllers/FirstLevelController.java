package controllers;

import core.*;
import enums.Scenes;
import gameObjects.dynamicGameObjects.enemies.FirstLevelBoss;
import gameObjects.dynamicGameObjects.player.GamePlayer;

import interfaces.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FirstLevelController extends AbstractController {
    private SimpleStageManager stageManager;
    private Scene scene;
    private Stage stage;

    private ObjectHandler handler;
    private Stage window;
    private Player player;
    private InputHandler keyInput;
    private UnitFactory factory;
    private ImageView backgroundImageView;
    private double backgroundScrollSpeed = 0.3;
    private Pane backgroundLayer;

    public FirstLevelController(SimpleStageManager stageManager) {
        this.stage = stageManager.getStage();
        this.stageManager = stageManager;
    }

    public void start() throws Exception {
        window = this.stage;
        StackPane layout = new StackPane();
        backgroundLayer = new Pane();
        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        window.setScene(scene);
        Canvas canvas = new Canvas(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        layout.getChildren().add(backgroundLayer);
        layout.getChildren().add(canvas);

        // background
        // --------------------------------
        backgroundImageView = new ImageView(getClass().getResource(Constants.BACKGROUND_PATH).toExternalForm());
        backgroundImageView.setFitWidth(Constants.WINDOW_WIDTH);
        // reposition the map. it is scrolling from bottom of the background to top of the background
        backgroundImageView.relocate(0, -backgroundImageView.getImage().getHeight() + Constants.WINDOW_HEIGHT);
        // add background to layer
        backgroundLayer.getChildren().add(backgroundImageView);

        //layout.setCursor(Cursor.NONE);
        handler = new ObjectHandler();
        factory = new UnitFactory();
        GraphicsContext gc = canvas.getGraphicsContext2D();


        player = stageManager.getDatabase().getPlayer();
        handler.addDynamicObject(player);
        handler.addDynamicObject(factory.createUnit(400, 0, "SlowEnemy"));
        //handler.addDynamicObject(factory.createUnit(300, 0, "SlowEnemy"));
        //handler.addDynamicObject(factory.createUnit(500, 0, "SlowEnemy"));
        //handler.addDynamicObject(factory.createUnit(450, 0, "SlowEnemy"));
        //handler.addDynamicObject(factory.createUnit(350, 0, "SlowEnemy"));//handler.addDynamicObject(factory.createUnit(200, 0, "ChaoticEnemy"));//handler.addDynamicObject(factory.createUnit(400, 0, "ChaoticEnemy"));
        //handler.addDynamicObject(factory.createUnit(500, 0, "RoundAsteroid"));
        handler.addDynamicObject(new FirstLevelBoss(100, 100));

        keyInput = new InputHandler(scene, player, handler);

        new AnimationTimer() {public void handle(long currentNanoTime) {
            update();
            draw(gc);
        }}.start();
    }

    public void draw(GraphicsContext gc) {
        // scroll background and calculate new position
        double y = backgroundImageView.getLayoutY() + backgroundScrollSpeed;
        // check bounds. we scroll upwards, so the y position is negative. once it's > 0 we have reached the end of the map and stop scrolling
        if( Double.compare( y, 0) >= 0) y = 0;
        // move background
        backgroundImageView.setLayoutY(y);

        gc.clearRect(0,0, Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
        handler.draw(gc);
    }

    public  void update() {
        handler.update();
        keyInput.refresh();
    }

    public void setStageManager(SimpleStageManager stageManager) {
        this.stageManager = stageManager;
    }
    private void setNextScene() {
        this.stageManager.setScene(Scenes.ExitGameScene);
    }
    public Scene getCurrentScene() throws Exception {
        this.start();
        return this.scene;
    }
}

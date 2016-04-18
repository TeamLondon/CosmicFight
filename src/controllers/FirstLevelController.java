package controllers;

import core.*;
import enums.Scenes;
import gameObjects.dynamicGameObjects.enemies.FirstLevelBoss;
import gameObjects.staticGameObjects.HUD;

import interfaces.*;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.handlers.InputHandler;
import models.handlers.ObjectHandler;
import utilities.Constants;

import java.util.Random;

public class FirstLevelController extends AbstractLevelController {
    private Scene scene;
    private Stage stage;
    private AnimationTimer timer;
    private ImageView backgroundImageView;
    private double backgroundScrollSpeed = 0.6;
    private Stage window;
    private Pane backgroundLayer;
    private StackPane layout;

    private boolean isBossSpawned = false;
    private FirstLevelBoss boss;

    public FirstLevelController(
            StageManager stageManager,
            Database gameDatabase,
            MessageBox messageBox,
            ConfirmBox confirmBox,
            ObjectHandler objectHandler,
            Player player,
            InputHandler inputHandler,
            HUD hud,
            Spawner spawner) {

        super(stageManager, gameDatabase, messageBox, confirmBox, objectHandler, player, inputHandler, hud, spawner);
        this.stage = stageManager.getStage();
        this.isBossSpawned = false;
    }

    public void start() throws Exception {
        GraphicsContext gc = initializeGraphicContext();
        setBackground();
        initialize();
        spawnBoss();


        timer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                update();
                draw(gc);
                // System.out.println(getObjectHandler().dynamicObjects.size());
            }
        };

        timer.start();
    }

    public Scene getCurrentScene() throws Exception {
        this.start();
        return this.scene;
    }

    public void initialize(){
        this.getInputHandler().setScene(this.scene);
        /*
        BUG: Player acts much faster when this method is called.
        this.getObjectHandler().setPlayer(this.getPlayer());
        */
        this.getObjectHandler().setPlayer(this.getPlayer());
        this.getInputHandler().setPlayer(this.getPlayer());
        this.getHud().setPlayer(this.getPlayer());
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setGlobalAlpha(1.0);
        graphicsContext.setGlobalBlendMode(BlendMode.SRC_OVER);
        // scroll background and calculate new position
        double distanceTravelled = backgroundImageView.getLayoutY();
        double y = backgroundImageView.getLayoutY() + backgroundScrollSpeed;
        // check bounds. we scroll upwards, so the y position is negative. once it's > 0 we have reached the end of the map and stop scrolling

        ////////////////////////////////////////////////
        if (Double.compare(y, 0) >= 0) {
            y = 0;
            if (!this.isBossSpawned) {
                spawnBoss();
            }
        }

        distanceTravelled = distanceTravelled * (-1);

        this.getSpawner().setDistance(30d);
        DynamicGameObject gameObject = this.getSpawner().spawn(distanceTravelled);
        if (gameObject != null) {
            this.getObjectHandler().addDynamicObject(gameObject);
        }

        ///////////////////////////////////////////////

        // move background
        this.backgroundImageView.setLayoutY(y);
        graphicsContext.clearRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.getObjectHandler().draw(graphicsContext);
        this.getHud().draw(graphicsContext);
    }

    public void update() {
        this.getObjectHandler().update();
        this.getInputHandler().refresh();
        if (this.isBossSpawned) {
            if (this.boss.isAlive()) {
                setNextScene();
                this.timer.stop();
            }
        }
        this.getHud().update();
    }

    private void spawnBoss() {
        Random random = new Random();
        this.boss = new FirstLevelBoss(random.nextInt(650) + 100, 50, this.getObjectHandler());
        this.getObjectHandler().addDynamicObject(boss);
        this.isBossSpawned = true;
    }

    private void setNextScene() {
        this.getStageManager().setScene(Scenes.ExitGameScene);
    }

    private void setBackground() {
        this.backgroundImageView = new ImageView(getClass().getResource(Constants.BACKGROUND_PATH).toExternalForm());
        this.backgroundImageView.setFitWidth(Constants.WINDOW_WIDTH);

        // reposition the map. it is scrolling from bottom of the background to top of the background
        this.backgroundImageView.relocate(0, -backgroundImageView.getImage().getHeight() + Constants.WINDOW_HEIGHT);

        // add background to layer
        this.backgroundLayer.getChildren().add(backgroundImageView);
    }

    private GraphicsContext initializeGraphicContext() {
        this.window = this.stage;
        this.layout = new StackPane();
        this.backgroundLayer = new Pane();
        this.scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.window.setScene(scene);
        Canvas canvas = new Canvas(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.layout.getChildren().add(backgroundLayer);
        this.layout.getChildren().add(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        return graphicsContext;
    }
}

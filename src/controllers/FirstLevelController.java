package controllers;

import core.*;
import enums.Scenes;
import gameObjects.dynamicGameObjects.enemies.FirstLevelBoss;
import gameObjects.dynamicGameObjects.player.GamePlayer;
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

import java.util.Random;

public class FirstLevelController extends AbstractController {
    private Scene scene;
    private Stage stage;
    private AnimationTimer timer;
    private ImageView backgroundImageView;
    private double backgroundScrollSpeed = 0.6;
    private Stage window;
    private Pane backgroundLayer;
    private StackPane layout;

    private ObjectHandler handler;
    private Player player;
    private InputHandler inputHandler;
    private FirstLevelBoss boss;
    private HUD hud;
    private Spawner spawner;

    private boolean isBossSpawned = false;

    public FirstLevelController(
            StageManager stageManager,
            Database gameDatabase,
            MessageBox messageBox,
            ConfirmBox confirmBox) {
        super.initialize(stageManager, gameDatabase, messageBox, confirmBox);

        this.stage = stageManager.getStage();
    }

    public void start() throws Exception {
        GraphicsContext gc = initializeGraphicContext();
        setBackground();
        initializeControllersAndPlayer();

        spawnBoss();


        timer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                update();
                draw(gc);
                System.out.println(handler.dynamicObjects.size());
            }
        };

        timer.start();
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
        GraphicsContext gc = canvas.getGraphicsContext2D();
        return gc;
    }

    private void initializeControllersAndPlayer() {
        this.player = this.getStageManager().getDatabase().getPlayer();

        this.handler = new ObjectHandler((GamePlayer) this.player);

        this.spawner = new Spawner(new UnitFactory(), new PositionManager());

        this.inputHandler = new InputHandler(this.scene, this.player, this.handler);

        this.handler.addDynamicObject(player);

        this.hud = new HUD(this.player);
    }

    public void initialize(Player player, ObjectHandler handler, Spawner spawner, InputHandler inputHandler, HUD hud) {
        this.player = player;
        this.handler = handler;
        this.spawner = spawner;
        this.inputHandler = inputHandler;
        this.inputHandler.setScene(this.scene);
        this.hud = hud;
    }

    private void setBackground() {
        backgroundImageView = new ImageView(getClass().getResource(Constants.BACKGROUND_PATH).toExternalForm());
        backgroundImageView.setFitWidth(Constants.WINDOW_WIDTH);
        // reposition the map. it is scrolling from bottom of the background to top of the background
        backgroundImageView.relocate(0, -backgroundImageView.getImage().getHeight() + Constants.WINDOW_HEIGHT);
        // add background to layer
        backgroundLayer.getChildren().add(backgroundImageView);
    }

    public void draw(GraphicsContext gc) {
        gc.setGlobalAlpha(1.0);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        // scroll background and calculate new position
        double distanceTravelled = backgroundImageView.getLayoutY();
        double y = backgroundImageView.getLayoutY() + backgroundScrollSpeed;
        // check bounds. we scroll upwards, so the y position is negative. once it's > 0 we have reached the end of the map and stop scrolling

        ////////////////////////////////////////////////
        if (Double.compare(y, 0) >= 0) {
            y = 0;
            if (isBossSpawned == false) {
                spawnBoss();
            }
        }

        distanceTravelled = distanceTravelled * (-1);

        spawner.setDistance(30d);
        DynamicGameObject gameObject = spawner.spawn(distanceTravelled);
        if (gameObject != null) {
            this.handler.addDynamicObject(gameObject);
        }

        ///////////////////////////////////////////////

        // move background
        backgroundImageView.setLayoutY(y);
        gc.clearRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        handler.draw(gc);
        hud.draw(gc);
    }

    public void update() {
        handler.update();
        inputHandler.refresh();
        if (isBossSpawned) {
            if (boss.getHitPoints() <= 0) {
                setNextScene();
                timer.stop();
            }
        }
        hud.update();
    }

    private void spawnBoss() {
        Random random = new Random();
        this.boss = new FirstLevelBoss(random.nextInt(650) + 100, 50, this.handler);
        this.handler.addDynamicObject(boss);
        isBossSpawned = true;
    }

    private void setNextScene() {
        this.getStageManager().setScene(Scenes.ExitGameScene);
    }

    public Scene getCurrentScene() throws Exception {
        this.start();
        return this.scene;
    }
}

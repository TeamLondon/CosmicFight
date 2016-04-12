package controllers;

import core.*;
import enums.Scenes;
import gameObjects.dynamicGameObjects.enemies.FirstLevelBoss;
import gameObjects.dynamicGameObjects.player.GamePlayer;
import interfaces.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Random;

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
    private double backgroundScrollSpeed = 0.6;
    private Pane backgroundLayer;

    private boolean isFirstSwarmSpawned = false;
    private boolean isSecondSwarmSpawned = false;
    private boolean isThirdSwarmSpawned = false;
    private boolean isBossSpawned = false;

    public FirstLevelController(SimpleStageManager stageManager) {
        this.stage = stageManager.getStage();
        this.stageManager = stageManager;

    }

    public void start() throws Exception {
        GraphicsContext gc = initialize();
        setBackground();
        initializeControllersAndPlayer();

        new AnimationTimer() {public void handle(long currentNanoTime) {
            update();
            draw(gc);
        }}.start();
    }

    private GraphicsContext initialize() {
        window = this.stage;
        StackPane layout = new StackPane();
        backgroundLayer = new Pane();
        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        window.setScene(scene);
        Canvas canvas = new Canvas(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        layout.getChildren().add(backgroundLayer);
        layout.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        return gc;
    }

    private void initializeControllersAndPlayer() {
        player = stageManager.getDatabase().getPlayer();
        handler = new ObjectHandler((GamePlayer) player);
        factory = new UnitFactory();
        keyInput = new InputHandler(scene, player, handler);
        handler.addDynamicObject(player);
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
        // scroll background and calculate new position
        double distanceTravelled = backgroundImageView.getLayoutY();
        double y = backgroundImageView.getLayoutY() + backgroundScrollSpeed;
        // check bounds. we scroll upwards, so the y position is negative. once it's > 0 we have reached the end of the map and stop scrolling

        ////////////////////////////////////////////////
        if(Double.compare( y, 0) >= 0) {
            y = 0;
        }

        boolean isInFirstSector = distanceTravelled > -1000 && distanceTravelled < -900;
        boolean isInSecondSector = distanceTravelled > -900 && distanceTravelled < -800;
        boolean isInThirdSector = distanceTravelled > -800 && distanceTravelled < -700;
        boolean isInFinalSector = y == 0;

        if (isInFirstSector && !isFirstSwarmSpawned) {
            spawnFirstSwarm();
            isFirstSwarmSpawned = true;
        }else if (isInSecondSector && !isSecondSwarmSpawned) {
            spawnSecondSwarm();
            isSecondSwarmSpawned = true;
        }else if (isInThirdSector && !isThirdSwarmSpawned) {
            spawnThirdSwarm();
            isThirdSwarmSpawned = true;
        }else if (isInFinalSector && !isBossSpawned) {
            spawnBoss();
            isBossSpawned = true;
        }

        ///////////////////////////////////////////////

        // move background
        backgroundImageView.setLayoutY(y);

        gc.clearRect(0,0, Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
        handler.draw(gc);
    }

    public  void update() {
        handler.update();
        keyInput.refresh();
    }

    private void spawnThirdSwarm() {
        Random random = new Random();
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
    }

    private void spawnSecondSwarm() {
        Random random = new Random();
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
    }

    private void spawnFirstSwarm() {
        Random random = new Random();
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(random.nextInt(650) + 100, 0, "SlowEnemy"));
    }

    private void spawnBoss() {
        Random random = new Random();
        handler.addDynamicObject(new FirstLevelBoss(random.nextInt(650) + 100, 0));
    }

    private void setNextScene() {
        this.stageManager.setScene(Scenes.ExitGameScene);
    }

    public Scene getCurrentScene() throws Exception {
        this.start();
        return this.scene;
    }
}

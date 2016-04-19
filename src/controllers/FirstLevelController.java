package controllers;

import enums.Scenes;
import gameObjects.dynamicGameObjects.enemies.FirstLevelBoss;
import gameObjects.staticGameObjects.SimpleHUD;
import interfaces.Spawner;
import interfaces.StageManager;
import interfaces.core.Database;
import interfaces.models.DynamicGameObject;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.contracts.ConfirmBox;
import models.contracts.MessageBox;
import models.handlers.InputHandler;
import utilities.Constants;

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
            Spawner spawner,
            InputHandler inputHandler,
            MessageBox messageBox,
            ConfirmBox confirmBox,
            SimpleHUD hud) {

        super(stageManager, gameDatabase, spawner, inputHandler, messageBox, confirmBox, hud);
        this.stage = stageManager.getStage();
        this.isBossSpawned = false;
    }

    public void start() throws Exception {
        GraphicsContext gc = initializeGraphicContext();
        setBackground();
        clear();
        initialize();

        timer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                update();
                draw(gc);
            }
        };

        timer.start();
    }

    private void clear() {
        this.getInputHandler().getObjectHandler().clear();
        this.getInputHandler().clear();
    }

    public Scene getCurrentScene() throws Exception {
        this.start();
        return this.scene;
    }

    public void initialize(){
        this.getInputHandler().setScene(this.scene);
        this.getInputHandler().setPlayer(this.getPlayer());
        this.getInputHandler().getObjectHandler().setPlayer(this.getPlayer());
        this.getSpawner().initialize();
        this.getSpawner().setDistance(20d);
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
        }

        distanceTravelled = distanceTravelled * (-1);
        DynamicGameObject gameObject = this.getSpawner().spawn(distanceTravelled);
        if (gameObject != null) {
            if (gameObject instanceof FirstLevelBoss){
                this.isBossSpawned = true;
                this.boss = (FirstLevelBoss) gameObject;
            }
            this.getInputHandler().getObjectHandler().addDynamicObject(gameObject);
        }

        ///////////////////////////////////////////////

        // move background
        this.backgroundImageView.setLayoutY(y);
        graphicsContext.clearRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.getInputHandler().getObjectHandler().draw(graphicsContext);
        this.getHud().draw(graphicsContext);
    }

    public void update() {
        this.getInputHandler().getObjectHandler().update();
        this.getInputHandler().refresh();
        if (this.isBossSpawned) {
            if (!this.boss.isAlive() || this.getPlayer().getHitPoints() <= 0) {
                setNextScene();
                this.timer.stop();
            }
        }
        
        this.getHud().update();
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

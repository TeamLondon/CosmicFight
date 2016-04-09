package sample;

import core.Constants;
import core.UnitFactory;
import core.InputHandler;
import core.ObjectHandler;
import gameObjects.AbstractDynamicGameObject;
import gameObjects.dynamicGameObjects.attacks.Bullet;
import gameObjects.dynamicGameObjects.player.GamePlayer;
import gameObjects.dynamicGameObjects.rocks.RoundAsteroid;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
<<<<<<< HEAD
    public static final int WIDTH = 800, HEIGHT = (WIDTH / 12) * 9;
    ObjectHandler handler;
    Stage window;
    GamePlayer player;
    InputHandler keyInput;
    EnemyFactory factory;
    Image background = new Image("/background.png");
=======
    private ObjectHandler handler;
    private Stage window;
    private GamePlayer player;
    private InputHandler keyInput;
    private UnitFactory factory;
    private ImageView backgroundImageView;
    private double backgroundScrollSpeed = 0.7;
    private Pane backgroundLayer;
>>>>>>> c54db4d77cb99173ca12205a58113f8d77fe3fb3

    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Cosmic Fight");
        StackPane layout = new StackPane();
        backgroundLayer = new Pane();
        Scene scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
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

        player = new GamePlayer(100, 100, "Asen");
        handler.addDynamicObject(player);
        handler.addDynamicObject(factory.createUnit(400, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(300, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(500, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(450, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createUnit(350, 0, "SlowEnemy"));
       //handler.addDynamicObject(factory.createUnit(200, 0, "ChaoticEnemy"));
       //handler.addDynamicObject(factory.createUnit(400, 0, "ChaoticEnemy"));
        handler.addDynamicObject(factory.createUnit(500, 0, "RoundAsteroid"));

        keyInput = new InputHandler(scene, player, handler);

        new AnimationTimer() {
           public void handle(long currentNanoTime) {
               update();
               draw(gc);
           }
       }.start();

        window.show();
    }

    public void draw(GraphicsContext gc) {
        // scroll background and calculate new position
        double y = backgroundImageView.getLayoutY() + backgroundScrollSpeed;
        // check bounds. we scroll upwards, so the y position is negative. once it's > 0 we have reached the end of the map and stop scrolling
        if( Double.compare( y, 0) >= 0) y = -1000;
        // move background
        backgroundImageView.setLayoutY(y);

        gc.clearRect(0,0, Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
        handler.draw(gc);
    }
    public void update() {
        handler.update();
        keyInput.refresh();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package sample;

import core.EnemyFactory;
import core.InputHandler;
import core.ObjectHandler;
import gameObjects.dynamicGameObjects.player.GamePlayer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main extends Application {
    public static final int WIDTH = 800, HEIGHT = (WIDTH / 12) * 9;
    ObjectHandler handler;
    Stage window;
    GamePlayer player;
    InputHandler keyInput;
    EnemyFactory factory;
    Image background = new Image("/background.png");

    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle( "Collect the Money Bags!" );

        StackPane layout = new StackPane();
        Scene scene = new Scene(layout, WIDTH, HEIGHT);
        window.setScene(scene);

        Canvas canvas = new Canvas( WIDTH, HEIGHT);
        layout.getChildren().add(canvas);
        //layout.setCursor(Cursor.NONE);



        BackgroundImage myBI= new BackgroundImage(new Image("/background.png"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        //then you set to your node
        layout.setBackground(new Background(myBI));


        handler = new ObjectHandler();
        factory = new EnemyFactory();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        player = new GamePlayer(100, 100, "Asen");
        handler.addDynamicObject(player);
        handler.addDynamicObject(factory.createEnemy(400, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createEnemy(300, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createEnemy(500, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createEnemy(450, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createEnemy(350, 0, "SlowEnemy"));
        handler.addDynamicObject(factory.createEnemy(200, 0, "ChaoticEnemy"));
        handler.addDynamicObject(factory.createEnemy(400, 0, "ChaoticEnemy"));


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
        gc.clearRect(0,0, 800,600);
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

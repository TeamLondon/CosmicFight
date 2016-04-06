package sample;

import core.ObjectHandler;
import gameObjects.dynamicGameObjects.player.GamePlayer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static final int WIDTH = 800, HEIGHT = (WIDTH / 12) * 9;
    ObjectHandler handler;

    Stage window;
    GamePlayer player;

    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle( "Collect the Money Bags!" );

        StackPane layout = new StackPane();
        Scene scene = new Scene(layout, WIDTH, HEIGHT);
        window.setScene(scene);

        Canvas canvas = new Canvas( WIDTH, HEIGHT);
        layout.getChildren().add(canvas);
        handler = new ObjectHandler();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //player = new Image("5.png");
        //player = new GamePlayer(100, 100, "Asen");
        handler.addDynamicObject(new GamePlayer(100, 100, "Asen"));
       new AnimationTimer() {
           public void handle(long currentNanoTime) {
               update();
               draw(gc);
           }
       }.start();

        window.show();
    }

    public void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, 800,600);
        handler.draw(gc);
    }
    public void update() {
        handler.update();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

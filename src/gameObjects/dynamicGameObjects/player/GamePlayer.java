package gameObjects.dynamicGameObjects.player;

import core.Constants;
import gameObjects.AbstractDynamicGameObject;
import interfaces.Attack;
import interfaces.DynamicGameObject;
import interfaces.HighScore;
import interfaces.Player;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import sample.Main;

public class GamePlayer extends AbstractDynamicGameObject implements Player{
    private String name;
    private Integer score = 0;
    private double health = 100;

    public GamePlayer(double x, double y, String name) {
        super(x, y);
        this.name = name;
        this.setImage(Constants.PLAYER_PATH);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public HighScore getHighScore() {
        return null;
    }
    public Integer getScore() {
        return score;
    }
    public Attack produceAttack() {
        return null;
    }
    public Double getHealth() {
        return health;
    }

    public void update() {
        x += velocityX;
        y += velocityY;
        y += 1;
    }
    public void draw(GraphicsContext gc) {

        //ImageView iv = new ImageView(new Image( "/5.png"));
        //iv.setRotate(40);
        //SnapshotParameters params = new SnapshotParameters();
        //params.setFill(Color.TRANSPARENT);
        //Image rotatedImage = iv.snapshot(params, null);
        x = clamp(x, 0, Constants.WINDOW_WIDTH - 50);
        y = clamp(y, 0, Constants.WINDOW_HEIGHT - 50);
        gc.drawImage(image, this.x, this.y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
    }

    private double clamp(double var, double min, double max) {
        if (var >= max) return var = max;
        else if(var <= min)return var = min;
        else return var;
    }

}

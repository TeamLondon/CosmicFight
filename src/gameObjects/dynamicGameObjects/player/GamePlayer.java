package gameObjects.dynamicGameObjects.player;

import core.Constants;
import gameObjects.AbstractDynamicGameObject;
import interfaces.HighScore;
import interfaces.Player;
import javafx.scene.canvas.GraphicsContext;

public class GamePlayer extends AbstractDynamicGameObject implements Player{
    private String name;
    private Integer score = 0;
    private double health = 100;
    private double fireRate = 0.4;
    private double damage = 5;

    public GamePlayer(double x, double y, String name) {
        super(x, y);
        this.name = name;
        this.setImage(Constants.PLAYER_PATH);
    }

    public void update() {
        super.update();
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

    public Double getHealth() {
        return health;
    }

    private double clamp(double var, double min, double max) {
        if (var >= max) return var = max;
        else if(var <= min)return var = min;
        else return var;
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double fireRate) {
        this.fireRate = fireRate;
    }

    public void applyDamage(double hitPoints) {

    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}

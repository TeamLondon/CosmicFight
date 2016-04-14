package gameObjects.dynamicGameObjects.player;

import core.Constants;
import gameObjects.AbstractDynamicGameObject;
import interfaces.HighScore;
import interfaces.Player;
import javafx.scene.canvas.GraphicsContext;
import utilityModels.GameHighScore;

public class GamePlayer extends AbstractDynamicGameObject implements Player{
    private String name;
    private double health = 100;
    private double fireRate = 0.4;
    private double damage = 5;
    private HighScore highScore;

    public GamePlayer(double x, double y, String name) {
        super(x, y);
        this.name = name;
        this.setImage(Constants.PLAYER_PATH);
        this.highScore = new GameHighScore(this.name, 0);
    }

    public void update() {
        super.update();
        y += 1;
        setHitPoints(getHitPoints() - 1);
    }

    public void draw(GraphicsContext gc) {
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
        return this.highScore;
    }

    public void addScore(int points) {
        this.getHighScore().setPlayerScore(this.getHighScore().getPlayerScore() + points);
    }

    public Double getHealth() {
        return this.health;
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

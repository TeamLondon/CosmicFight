package gameObjects.dynamicGameObjects.player;

import core.Constants;
import gameObjects.AbstractDynamicGameObject;
import interfaces.HighScore;
import interfaces.Player;
import javafx.scene.canvas.GraphicsContext;
import utilityModels.GameHighScore;

public class GamePlayer extends AbstractDynamicGameObject implements Player{
    private String name;
    private double fireRate;
    private double damage;
    private HighScore highScore;

    public GamePlayer(double x, double y, String name) {
        super(x, y);
        this.setName(name);
        this.setImage(Constants.PLAYER_PATH);
        this.setWidth(Constants.PLAYER_WIDTH);
        this.setHeight(Constants.PLAYER_HEIGHT);
        this.setFireRate(0.4);
        this.setDamage(5);
        this.highScore = new GameHighScore(this.name, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double fireRate) {
        this.fireRate = fireRate;
    }

    public HighScore getHighScore() {
        return this.highScore;
    }

    public void addScore(int points) {
        this.getHighScore().setPlayerScore(this.getHighScore().getPlayerScore() + points);
    }

    public void applyDamage(double hitPoints) {
        this.setHitPoints(this.getHitPoints() - hitPoints);
    }

    private double clamp(double var, double min, double max) {
        if (var >= max) return var = max;
        else if(var <= min)return var = min;
        else return var;
    }



    public void update() {
        super.update();
        double currentX = clamp(this.getX(), 0, Constants.WINDOW_WIDTH - this.getWidth());
        double currentY = clamp(this.getY(), 0, Constants.WINDOW_HEIGHT - this.getHeight());
        this.setPosition(currentX, currentY + 1);
    }

    public void draw(GraphicsContext gc) {
        super.draw(gc);
        gc.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}

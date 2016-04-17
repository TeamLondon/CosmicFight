package gameObjects.dynamicGameObjects.player;

import core.Constants;
import enums.Attacks;
import gameObjects.AbstractDynamicGameObject;
import interfaces.HighScore;
import interfaces.Player;
import javafx.scene.canvas.GraphicsContext;
import utilityModels.GameHighScore;

import java.util.ArrayList;
import java.util.List;

public class GamePlayer extends AbstractDynamicGameObject implements Player{
    private static final String DefaultName = "DefaultName";
    private String name;
    private double bulletCooldown;
    private double bombCooldown;
    private HighScore highScore;
    private List<Attacks> attacks = new ArrayList<>();
    private Attacks currentAttack;

    public GamePlayer(double x, double y, String name) {
        super(x, y);
        this.setName(name);
        this.setImage(Constants.PLAYER_PATH);
        this.setWidth(Constants.PLAYER_WIDTH);
        this.setHeight(Constants.PLAYER_HEIGHT);
        this.setBulletCooldown(0.0);
        this.setBombCooldown(0.0);
        this.currentAttack = Attacks.Bullet;
        this.addAttack(currentAttack);
        this.highScore = new GameHighScore(this.name, 0);
    }

    public GamePlayer(double x, double y) {
        this(x, y, DefaultName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBulletCooldown() {
        return bulletCooldown;
    }

    public void setBulletCooldown(double bulletCooldown) {
        this.bulletCooldown = bulletCooldown;
    }

    public void resetBullet() {
        this.setBulletCooldown(0.0);
    }

    public HighScore getHighScore() {
        return this.highScore;
    }

    public void changeAttack() {
        int index = attacks.indexOf(currentAttack);

        if (index == this.attacks.size() - 1) {
            this.currentAttack = this.attacks.get(0);
        }else {
            this.currentAttack = this.attacks.get(index + 1);
        }
    }

    public void addAttack(Attacks attack) {
        this.attacks.add(attack);
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

    public double getBombCooldown() {
        return bombCooldown;
    }

    public void setBombCooldown(double bombCooldown) {
        this.bombCooldown = bombCooldown;
    }

    public void resetBomb() {
        this.setBombCooldown(0.0);
    }

    public Attacks getCurrentAttack() {
        return currentAttack;
    }

    public List<Attacks> getAttacks() {
        return attacks;
    }

    public void update() {
        super.update();
        double currentX = clamp(this.getX(), 0, Constants.WINDOW_WIDTH - this.getWidth());
        double currentY = clamp(this.getY(), 0, Constants.WINDOW_HEIGHT - this.getHeight());
        this.setPosition(currentX, currentY + 1);
        this.setBulletCooldown(clamp(this.getBulletCooldown() + 0.13, 0.0, 0.4));
        this.setBombCooldown(clamp(this.getBombCooldown() + 0.1, 0.0, 20.0));
    }

    public void draw(GraphicsContext gc) {
        super.draw(gc);
        gc.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}

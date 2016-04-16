package gameObjects.dynamicGameObjects.enemies;

import core.Constants;
import gameObjects.AbstractDynamicGameObject;
import gameObjects.dynamicGameObjects.HealthBar;
import interfaces.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class ChaoticEnemy extends AbstractDynamicGameObject implements Enemy {
    private HealthBar healthBar;

    public ChaoticEnemy(double x, double y) {
        super(x, y);
        this.setImage(Constants.CHAOTIC_ENEMY_PATH);
        this.setWidth(Constants.CHAOTIC_ENEMY_WIDTH);
        this.setHeight(Constants.CHAOTIC_ENEMY_HEIGHT);
        this.healthBar = new HealthBar(this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getHitPoints(), Constants.SLOW_ENEMY_HEALTH_BAR_PATH);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.healthBar.draw(gc);
    }
    public void update() {
        super.update();
        this.setVelocity(((Math.random() * 40)) - 20, (Math.random() * 10));

        this.healthBar.setPosition(this.getX(), this.getY());
        this.healthBar.setHealth(this.getHitPoints());
        this.healthBar.update();
    }

    @Override
    public Integer getRewardPoints() {
        return 10;
    }
}

package gameObjects.dynamicGameObjects.enemies;

import interfaces.models.HealthBar;
import utilities.Constants;
import gameObjects.AbstractDynamicGameObject;
import gameObjects.dynamicGameObjects.healthBars.EnemyHealthBar;
import interfaces.models.Enemy;
import javafx.scene.canvas.GraphicsContext;

public class ChaoticEnemy extends AbstractDynamicGameObject implements Enemy {
    private HealthBar healthBar;

    public ChaoticEnemy(double x, double y) {
        super(x, y);
        this.setImage(Constants.CHAOTIC_ENEMY_PATH);
        this.setWidth(Constants.CHAOTIC_ENEMY_WIDTH);
        this.setHeight(Constants.CHAOTIC_ENEMY_HEIGHT);
        this.healthBar = new EnemyHealthBar(this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getHitPoints(), Constants.SLOW_ENEMY_HEALTH_BAR_PATH);
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.healthBar.draw(graphicsContext);
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

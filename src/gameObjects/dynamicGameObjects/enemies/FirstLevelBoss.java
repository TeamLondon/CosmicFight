package gameObjects.dynamicGameObjects.enemies;

import core.Constants;
import gameObjects.AbstractDynamicGameObject;
import gameObjects.dynamicGameObjects.HealthBar;
import interfaces.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class FirstLevelBoss extends AbstractDynamicGameObject implements Enemy{
    private HealthBar healthBar;

    public FirstLevelBoss(double x, double y) {
        super(x, y);
        this.setHitPoints(2000);
        this.setImage(Constants.BOSS_PATH);
        this.setWidth(Constants.BOSS_WIDTH);
        this.setHeight(Constants.BOSS_HEIGHT);
        this.healthBar = new HealthBar(this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getHitPoints(), Constants.BOSS_HEALTH_BAR_PATH);
        this.setVelocity(5, 0);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.healthBar.draw(gc);
    }
    public void update() {
        super.update();

        if (this.getX() < 0){
            this.setVelocity(5, 0);
        } else if (this.getX() > Constants.WINDOW_WIDTH - this.getWidth()) {
            this.setVelocity(-5, 0);
        }

        this.healthBar.setPosition(this.getX(), this.getY());
        this.healthBar.setHealth(this.getHitPoints());
        this.healthBar.update();
    }

    @Override
    public Integer getRewardPoints() {
        return 500;
    }
}

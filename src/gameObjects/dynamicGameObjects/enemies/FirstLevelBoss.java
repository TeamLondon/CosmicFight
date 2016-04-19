package gameObjects.dynamicGameObjects.enemies;

import enums.Attacks;
import gameObjects.AbstractDynamicGameObject;
import gameObjects.dynamicGameObjects.HealthBar;
import interfaces.factories.AttacksFactory;
import interfaces.models.Enemy;
import javafx.scene.canvas.GraphicsContext;
import models.handlers.ObjectHandler;
import utilities.Constants;

public class FirstLevelBoss extends AbstractDynamicGameObject implements Enemy {
    private HealthBar healthBar;
    private ObjectHandler objectHandler;
    private AttacksFactory attacksFactory;
    private double lastTime;

    public FirstLevelBoss(double x, double y, ObjectHandler handler, AttacksFactory attacksFactory) {
        super(x, y);
        this.setHitPoints(2000);
        this.setImage(Constants.BOSS_PATH);
        this.setWidth(Constants.BOSS_WIDTH);
        this.setHeight(Constants.BOSS_HEIGHT);
        this.setVelocity(5, 0);
        this.objectHandler = handler;
        this.attacksFactory = attacksFactory;
        this.healthBar = new HealthBar(this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getHitPoints(), Constants.BOSS_HEALTH_BAR_PATH);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.healthBar.draw(gc);
    }

    public void update() {
        double currentTime = System.currentTimeMillis();
        super.update();

        if (this.getX() < 0) {
            this.setVelocity(5, 0);
        } else if (this.getX() > Constants.WINDOW_WIDTH - this.getWidth()) {
            this.setVelocity(-5, 0);
        }

        if (currentTime - this.lastTime > 400.0) {
            this.objectHandler.addDynamicObject(
                    this.attacksFactory
                            .createAttack(Attacks.BossBullet, this.getX(), this.getY() + this.getHeight()));

            this.objectHandler.addDynamicObject(
                    this.attacksFactory
                            .createAttack(Attacks.BossBullet, this.getX() + this.getWidth(), this.getY() + this.getHeight()));

            this.lastTime = currentTime;
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

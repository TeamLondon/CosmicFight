package gameObjects.dynamicGameObjects.enemies;

import core.Constants;
import gameObjects.AbstractDynamicGameObject;
import gameObjects.dynamicGameObjects.HealthBar;
import gameObjects.dynamicGameObjects.Particle;
import gameObjects.dynamicGameObjects.effects.Emitter;
import gameObjects.dynamicGameObjects.effects.FireEmitter;
import interfaces.DynamicGameObject;
import interfaces.Enemy;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SlowEnemy extends AbstractDynamicGameObject implements Enemy{
    private HealthBar healthBar;
    private Emitter emitter = new FireEmitter();
    private List<Particle> particles = new ArrayList<>();

    public SlowEnemy(double x, double y) {
        super(x, y);
        this.setImage(Constants.SLOW_ENEMY_PATH);
        this.setWidth(Constants.SLOW_ENEMY_WIDTH);
        this.setHeight(Constants.SLOW_ENEMY_HEIGHT);
        this.healthBar = new HealthBar(this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getHitPoints(), Constants.SLOW_ENEMY_HEALTH_BAR_PATH);
        this.setVelocity(0, 3);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.healthBar.draw(gc);

        particles.addAll(emitter.emit(this.getX() + (this.getWidth() / 2) - 2, this.getY()));

        for (Iterator<Particle> it = particles.iterator(); it.hasNext(); ) {
            Particle p = it.next();
            p.update();

            if (!p.isAlive()) {
                it.remove();
                continue;
            }

            p.draw(gc);
        }
    }
    public void update() {
        super.update();
        this.healthBar.setPosition(this.getX(), this.getY());
        this.healthBar.setHealth(this.getHitPoints());
        this.healthBar.update();

    }

    @Override
    public Integer getRewardPoints() {
        return 5;
    }
}

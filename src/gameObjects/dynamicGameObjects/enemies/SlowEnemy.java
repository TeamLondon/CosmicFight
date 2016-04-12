package gameObjects.dynamicGameObjects.enemies;

import core.Constants;
import gameObjects.AbstractDynamicGameObject;
import gameObjects.dynamicGameObjects.HealthBar;
import interfaces.DynamicGameObject;
import interfaces.Enemy;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SlowEnemy extends AbstractDynamicGameObject implements Enemy{
    private HealthBar healthBar;

    public SlowEnemy(double x, double y) {
        super(x, y);
        this.image = new Image(Constants.SLOW_ENEMY_PATH);
        this.setWidth(Constants.SLOW_ENEMY_WIDTH);
        this.setHeight(Constants.SLOW_ENEMY_HEIGHT);
        this.healthBar = new HealthBar(this.getX(), this.getY() - 15, this.getWidth(), 5);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.healthBar.draw(gc);
    }
    public void update() {
        super.update();
        this.healthBar.setPosition(this.getX() + 1, this.getY() - 20);
        healthBar.setHealth(this.getHitPoints() / 2);
        this.healthBar.update();
        this.y+= 1;
    }
}

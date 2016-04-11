package gameObjects.dynamicGameObjects.enemies;

import core.Constants;
import gameObjects.AbstractDynamicGameObject;
import interfaces.DynamicGameObject;
import interfaces.Enemy;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SlowEnemy extends AbstractDynamicGameObject implements Enemy{

    public SlowEnemy(double x, double y) {
        super(x, y);
        this.image = new Image(Constants.SLOW_ENEMY_PATH);
        this.setWidth(Constants.SLOW_ENEMY_WIDTH);
        this.setHeight(Constants.SLOW_ENEMY_HEIGHT);
    }

    public Double getHealth() {
        return null;
    }
    public void draw(GraphicsContext gc) {
        gc.drawImage(image, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    public void update() {
        this.y+= 1;
    }
}

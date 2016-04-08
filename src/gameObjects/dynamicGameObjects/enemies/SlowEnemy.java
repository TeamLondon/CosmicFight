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
    }

    public Double getHealth() {
        return null;
    }
    public void draw(GraphicsContext gc) {
        gc.drawImage(image, this.x, this.y, Constants.SLOW_ENEMY_WIDTH, Constants.SLOW_ENEMY_HEIGHT);
    }
    public void update() {
        this.y+= 3;
    }
}

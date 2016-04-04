package gameObjects.dynamicGameObjects.enemies;

import gameObjects.AbstractDynamicGameObject;
import interfaces.DynamicGameObject;
import interfaces.Enemy;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SlowEnemy extends AbstractDynamicGameObject implements Enemy{

    public SlowEnemy(double x, double y) {
        super(x, y);
    }

    public Double getHealth() {
        return null;
    }

    public void draw(GraphicsContext graphicsContext) {

    }
    public void update() {

    }
}

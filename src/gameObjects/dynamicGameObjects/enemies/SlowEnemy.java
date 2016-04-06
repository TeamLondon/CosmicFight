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
        this.image = new Image("/enemy.png");
    }

    public Double getHealth() {
        return null;
    }
    public void draw(GraphicsContext gc) {
        gc.drawImage(image, this.x, this.y, 50, 50);
    }
    public void update() {
        this.y+= 3;
    }
}

package gameObjects.dynamicGameObjects.obstacles;

import gameObjects.AbstractDynamicGameObject;
import interfaces.models.Unit;
import javafx.scene.canvas.GraphicsContext;

public abstract class Asteroid extends AbstractDynamicGameObject implements Unit {

    protected Asteroid(double x, double y) {
        super(x, y);
    }

    public void draw(GraphicsContext gc) {
        super.draw(gc);
        gc.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    public void update() {
        super.update();
    }
}

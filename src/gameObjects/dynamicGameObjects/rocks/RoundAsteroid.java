package gameObjects.dynamicGameObjects.rocks;

import core.Constants;
import gameObjects.AbstractDynamicGameObject;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class RoundAsteroid extends AbstractDynamicGameObject{

    public RoundAsteroid(double x, double y) {
        super(x, y);
        this.setImage(Constants.ROUND_ASTEROID_PATH);
        this.setWidth(Constants.ROUND_ASTEROID_WIDTH);
        this.setHeight(Constants.ROUND_ASTEROID_HEIGHT);
        this.setVelocity(0, 8);
    }

    public void draw(GraphicsContext gc) {
        super.draw(gc);
        gc.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    public void update() {
        super.update();
    }
}

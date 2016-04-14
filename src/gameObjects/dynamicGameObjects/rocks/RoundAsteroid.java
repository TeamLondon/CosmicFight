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
        this.image = new Image(Constants.ROUND_ASTEROID_PATH);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(this.image, this.x, this.y, Constants.ROUND_ASTEROID_WIDTH, Constants.ROUND_ASTEROID_HEIGHT);
    }
    public void update() {
        this.y += 8;
    }
}

package gameObjects.dynamicGameObjects.rocks;

import core.Constants;
import gameObjects.AbstractDynamicGameObject;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class RoundAsteroid extends AbstractDynamicGameObject{

    private int rotation = 1;

    public RoundAsteroid(double x, double y) {
        super(x, y);
        this.image = new Image(Constants.ROUND_ASTEROID_PATH);
    }

    public void draw(GraphicsContext gc) {
        ImageView iv = new ImageView(this.image);
        iv.setRotate(rotation);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null);

        gc.drawImage(rotatedImage, this.x, this.y, Constants.ROUND_ASTEROID_WIDTH, Constants.ROUND_ASTEROID_HEIGHT);
        this.rotation += 2;
    }

    public void update() {
        this.y += 2;
    }
}

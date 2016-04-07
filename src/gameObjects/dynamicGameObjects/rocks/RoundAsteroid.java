package gameObjects.dynamicGameObjects.rocks;

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
        this.image = new Image("/rocks/Asteroids/Asteroids_256x256_008.png");
    }

    public void draw(GraphicsContext gc) {
        ImageView iv = new ImageView(this.image);
        iv.setRotate(rotation);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null);

        gc.drawImage(rotatedImage, this.x, this.y, 64, 64);
        this.rotation += 5;
    }

    public void update() {
        this.y += 2;
    }
}

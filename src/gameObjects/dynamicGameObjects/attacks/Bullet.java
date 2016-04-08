package gameObjects.dynamicGameObjects.attacks;

import core.Constants;
import gameObjects.AbstractDynamicGameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.image.Image;

import java.awt.*;

public class Bullet extends AbstractDynamicGameObject{

    public Bullet(double x, double y) {
        super(x, y);
        this.image = new Image(Constants.BULLET_PATH);
        this.width = Constants.BULLET_WIDTH;
        this.height = Constants.BULLET_HEIGHT;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, x + 18, y, this.width, this.height);
    }

    public void update() {
        y -= 10;
    }
}

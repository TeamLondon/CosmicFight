package gameObjects.dynamicGameObjects.attacks;

import gameObjects.AbstractDynamicGameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.image.Image;

import java.awt.*;

public class Bullet extends AbstractDynamicGameObject{

    public Bullet(double x, double y) {
        super(x, y);
        this.image = new Image("/bullet.png");
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image, x, y, 15, 15);
    }

    @Override
    public void update() {
        y -= 15;
    }
}

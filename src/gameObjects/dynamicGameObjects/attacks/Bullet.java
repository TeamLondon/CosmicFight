package gameObjects.dynamicGameObjects.attacks;

import core.Constants;
import gameObjects.AbstractDynamicGameObject;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.image.Image;

import java.awt.*;

public class Bullet extends AbstractDynamicGameObject{

    public Bullet(double x, double y) {
        super(x, y);
        this.setImage(Constants.BULLET_PATH);
        this.setWidth(Constants.BULLET_WIDTH);
        this.setHeight(Constants.BULLET_HEIGHT);
        this.setVelocity(0, -10);
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.getX(), this.getY(), this.getWidth() + 20, this.getHeight() + 20);
    }



    public void draw(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.getX() + 18, this.getY(), this.getWidth(), this.getHeight());
    }

    public void update() {
        super.update();
    }
}

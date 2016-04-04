package gameObjects;

import interfaces.DynamicGameObject;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class AbstractDynamicGameObject implements DynamicGameObject{
    public Image image;
    public double x;
    public double y;
    public double velocityX = 0;
    public double velocityY = 0;
    public double width = image.getWidth();
    public double height = image.getHeight();

    public AbstractDynamicGameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }
    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
    }
    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.x,this.y,width,height);
    }
    public boolean isIntersecting(DynamicGameObject otherDynamicObject) {
        return false;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
    }
    public void setImage(String imagePath) {
        Image i = new Image(imagePath);
        setImage(i);
    }
}

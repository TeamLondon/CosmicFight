package gameObjects;

import interfaces.StaticGameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class AbstractStaticGameObject implements StaticGameObject{
    private Image image;
    private double x;
    private double y;
    private double width;
    private double height;


    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}

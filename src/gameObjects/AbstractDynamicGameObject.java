package gameObjects;

import interfaces.models.DynamicGameObject;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class AbstractDynamicGameObject implements DynamicGameObject {
    private Image image;
    private double x;
    private double y;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private double hitPoints;
    // private Image explosionSheet = new Image("/explosion/explosion.png");

    public AbstractDynamicGameObject(double x, double y) {
        this.setPosition(x, y);
        this.setHitPoints(100);
    }

    public void setVelocity(double x, double y) {
        this.velocityX = x;
        this.velocityY = y;
    }

    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
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

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(double hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void applyDamage(double hitPoints) {
        this.hitPoints -= hitPoints;
    }

    public boolean isAlive() {
        return this.getHitPoints() > 0;
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

    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.x, this.y, width, height);
    }

    public boolean isIntersecting(DynamicGameObject otherDynamicObject) {
        try {
            return otherDynamicObject.getBoundary().intersects(this.getBoundary());
        } catch (NullPointerException npe) {
            System.out.println();
            System.out.println(npe.toString());
        }
        return false;
    }

    public void draw(GraphicsContext gc) {
        //gc.drawImage(explosionSheet, explosionSheet.getWidth() - 128, 0, 128, 128, 0, 0, 800, 600);
    }

    public void update() {
        this.x += velocityX;
        this.y += velocityY;
    }
}

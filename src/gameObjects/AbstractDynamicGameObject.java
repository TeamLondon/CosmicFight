package gameObjects;

import interfaces.DynamicGameObject;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class AbstractDynamicGameObject implements DynamicGameObject{
    public Image image;
    public double x;
    public double y;
    public double velocityX;
    public double velocityY;
    public double width;
    public double height;
    private double hitPoints;
    private boolean isAlive;

    public AbstractDynamicGameObject(double x, double y) {
        this.x = x;
        this.y = y;
        this.hitPoints = 100;
        this.isAlive = true;
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
        return otherDynamicObject.getBoundary().intersects( this.getBoundary() );
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

    public void initiateDestroyAnimation() {
        this.setAlive(false);
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
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
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

    public void draw(GraphicsContext gc) {

    }

    public void update() {
        this.x += velocityX;
        this.y += velocityY;
    }
}

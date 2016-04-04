package gameObjects.dynamicGameObjects.attacks;

import interfaces.Attack;
import interfaces.DynamicGameObject;
import interfaces.Unit;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SimplePlayerAttack implements Attack {
    @Override
    public double getDamage() {
        return 0;
    }

    @Override
    public void applyAttack(Unit unitToAttack) {

    }

    @Override
    public void setVelocity(double x, double y) {

    }

    @Override
    public void addVelocity(double x, double y) {

    }

    @Override
    public Rectangle2D getBoundary() {
        return null;
    }

    @Override
    public boolean isIntersecting(DynamicGameObject otherDynamicObject) {
        return false;
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {

    }

    @Override
    public void update() {

    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public void setImage(Image image) {

    }

    @Override
    public void setImage(String imagePath) {

    }
}

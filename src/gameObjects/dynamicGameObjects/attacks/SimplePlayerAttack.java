package gameObjects.dynamicGameObjects.attacks;

import gameObjects.AbstractDynamicGameObject;
import interfaces.Attack;
import interfaces.DynamicGameObject;
import interfaces.Unit;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SimplePlayerAttack extends AbstractDynamicGameObject implements Attack {

    public SimplePlayerAttack(double x, double y) {
        super(x, y);
    }

    public double getDamage() {
        return 0;
    }
    public void applyAttack(Unit unitToAttack) {

    }

    public void draw(GraphicsContext graphicsContext) {

    }

    public void update() {

    }

}

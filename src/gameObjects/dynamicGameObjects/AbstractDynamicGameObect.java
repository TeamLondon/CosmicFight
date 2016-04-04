package gameObjects.dynamicGameObjects;

import gameObjects.AbstractGameObject;
import interfaces.DynamicGameObject;
import javafx.geometry.Rectangle2D;

public class AbstractDynamicGameObect extends AbstractGameObject implements DynamicGameObject {
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
}

package interfaces;

import javafx.geometry.Rectangle2D;

public interface DynamicGameObject extends StaticGameObject{
    void setVelocity(double x, double y);

    void addVelocity(double x, double y);

    Rectangle2D getBoundary();

    boolean isIntersecting(DynamicGameObject otherDynamicObject);
}

package interfaces;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public interface DynamicGameObject extends Visualizable{
    void setVelocity(double x, double y);

    void addVelocity(double x, double y);

    void setPosition(double x, double y);

    double getHitPoints();

    double getX();

    double getY();

    void applyDamage(double hitPoints);

    Rectangle2D getBoundary();

    boolean isIntersecting(DynamicGameObject otherDynamicObject);

    void initiateDestroyAnimation();


    void draw(GraphicsContext gc);

    void update();
}

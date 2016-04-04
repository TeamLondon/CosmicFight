package interfaces;

import javafx.scene.canvas.GraphicsContext;

public interface StaticGameObject extends Visualizable {
    double getX();

    double getY();

    void draw(GraphicsContext graphicsContext);

    void update();
}

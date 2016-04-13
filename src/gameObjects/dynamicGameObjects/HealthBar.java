package gameObjects.dynamicGameObjects;

import gameObjects.AbstractDynamicGameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HealthBar extends AbstractDynamicGameObject {
    private double redWidth;

    public HealthBar(double x, double y, double width, double height) {
        super(x, y);
        this.setWidth(width);
        this.setHeight(height);
        this.redWidth = this.getWidth();
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        gc.setFill(Color.GRAY);
        gc.fillRect(this.getX() - 2, this.getY() - 2, this.getWidth() + 4, this.getHeight() + 4);
        gc.setFill(Color.RED);
        gc.fillRect(this.getX(), this.getY(), redWidth, this.getHeight());
    }

    @Override
    public void update() {
        super.update();
    }

    public void setHealth(double health) {
        this.redWidth = health ;
    }
}

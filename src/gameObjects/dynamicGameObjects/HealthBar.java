package gameObjects.dynamicGameObjects;

import gameObjects.AbstractDynamicGameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class HealthBar extends AbstractDynamicGameObject {
    private double startHealth;
    private double redWidth;

    public HealthBar(double x, double y, double width, double height, double startHealth, String imgPath) {
        super(x, y);
        this.setWidth(width);
        this.setHeight(height * 0.3);
        this.redWidth = this.getWidth();
        this.startHealth = startHealth;
        this.setImage(new Image(imgPath, this.getWidth(), this.getHeight(), false, false));
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        gc.setFill(Color.rgb(230, 10, 10));
        gc.fillRect(this.getX() + 6, this.getY() + 5, redWidth, this.getHeight() - 10);
        gc.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public void update() {
        super.update();
    }

    public void setHealth(double health) {
        double amountInPercent = (health / startHealth) * 100;
        this.redWidth = this.getWidth() * (amountInPercent * 0.01) - 11;
    }

    @Override
    public void setPosition(double x, double y) {
        super.setPosition(x, y - (this.getHeight() + 15));
    }
}

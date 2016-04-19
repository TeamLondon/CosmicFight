package gameObjects.dynamicGameObjects.healthBars;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PackageHealthBar extends AbstractHealthBar {
    public PackageHealthBar(double x, double y, double width, double height, double startHealth, String imgPath) {
        super(x, y, width, height, startHealth, imgPath);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        gc.setFill(Color.rgb(24, 255, 78));
        gc.fillRect(this.getX() + 6, this.getY() + 5, this.getRedWidth() + 3, this.getHeight() - 6);
        gc.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth()+ 5, this.getHeight() + 5);
    }

    @Override
    public void update() {
        super.update();
    }
}

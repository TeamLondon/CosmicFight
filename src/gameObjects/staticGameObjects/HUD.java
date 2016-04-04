package gameObjects.staticGameObjects;

import interfaces.StaticGameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HUD implements StaticGameObject {
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

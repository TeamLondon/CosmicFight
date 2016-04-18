package gameObjects.dynamicGameObjects.attacks;

import utilities.Constants;
import gameObjects.AbstractDynamicGameObject;
import interfaces.Ammo;
import javafx.scene.canvas.GraphicsContext;

public class GiantBomb extends AbstractDynamicGameObject implements Ammo{

    public GiantBomb(double x, double y) {
        super(x, y);
        this.setImage(Constants.GIANT_BOMB_PATH);
        this.setWidth(Constants.GIANT_BOMB_WIDTH);
        this.setHeight(Constants.GIANT_BOMB_HEIGHT);
        this.setVelocity(0, -10);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        gc.drawImage(this.getImage(), this.getX() + 18, this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public Integer getDamage() {
        return 100;
    }
}

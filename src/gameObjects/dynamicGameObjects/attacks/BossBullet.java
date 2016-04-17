package gameObjects.dynamicGameObjects.attacks;

import core.Constants;
import gameObjects.AbstractDynamicGameObject;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class BossBullet extends AbstractDynamicGameObject{

    public BossBullet(double x, double y) {
        super(x, y);
        this.setImage(Constants.BOSS_BULLET_PATH);
        this.setWidth(Constants.BOSS_BULLET_WIDTH);
        this.setHeight(Constants.BOSS_BULLET_HEIGHT);
        this.setVelocity(0, 10);
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.getX(), this.getY(), this.getWidth() + 20, this.getHeight() + 20);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.getX() + 18, this.getY(), this.getWidth(), this.getHeight());
    }

    public void update() {
        super.update();
    }

    public Integer getDamage() {
        return 5;
    }
}

package gameObjects.dynamicGameObjects.enemies;

import core.Constants;
import gameObjects.AbstractDynamicGameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class FirstLevelBoss extends AbstractDynamicGameObject{


    public FirstLevelBoss(double x, double y) {
        super(x, y);
        this.setHitPoints(2000);
        this.setImage(new Image(Constants.BOSS_PATH));
        this.setWidth(300);
        this.setHeight(100);
        this.setVelocity(5, 0);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    public void update() {
        super.update();

        if (this.getX() < 0){
            this.setVelocity(5, 0);
        }

        if (this.getX() > Constants.WINDOW_WIDTH - this.getWidth()) {
            this.setVelocity(-5, 0);
        }
    }
}

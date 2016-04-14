package gameObjects.dynamicGameObjects.enemies;

import core.Constants;
import gameObjects.AbstractDynamicGameObject;
import interfaces.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class ChaoticEnemy extends AbstractDynamicGameObject implements Enemy {

    public ChaoticEnemy(double x, double y) {
        super(x, y);
        this.image = new Image(Constants.CHAOTIC_ENEMY_PATH);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, this.x, this.y, Constants.CHAOTIC_ENEMY_WIDTH, Constants.CHAOTIC_ENEMY_HEIGHT);
    }
    public void update() {
        //this.y += 3;
        this.setVelocity(((Math.random() * 40)) - 20, (Math.random()*10));
        this.x += this.velocityX;
        this.y += this.velocityY;
    }

    @Override
    public Integer getRewardPoints() {
        return 10;
    }
}

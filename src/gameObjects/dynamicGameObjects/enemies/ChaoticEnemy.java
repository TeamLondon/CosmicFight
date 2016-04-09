package gameObjects.dynamicGameObjects.enemies;

import core.Constants;
import gameObjects.AbstractDynamicGameObject;
import interfaces.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ChaoticEnemy extends AbstractDynamicGameObject implements Enemy {
    int luckyNum;

    public ChaoticEnemy(double x, double y, int luckyNum) {
        super(x, y);
        this.luckyNum = luckyNum;
        this.image = new Image(Constants.CHAOTIC_ENEMY_PATH);
    }

    public void draw(GraphicsContext gc) {
        double animX = (int) (Math.sin(System.currentTimeMillis() % 2000.0 / luckyNum * Math.PI * 2) * 150);
        double animY = (int) (Math.cos(System.currentTimeMillis() % 2000.0 / 1000 * Math.PI * 2) * 150);
        double finalX = this.x + animX;
        double finalY = this.y + animY;

        gc.drawImage(image, finalX, finalY, Constants.CHAOTIC_ENEMY_WIDTH, Constants.CHAOTIC_ENEMY_HEIGHT);
    }
    public void update() {
        this.y += 3;
    }
}

package gameObjects.dynamicGameObjects.obstacles;

import core.Constants;

public class RightAsteroid extends Asteroid {
    public RightAsteroid(double x, double y) {
        super(x, y);
        this.setImage(Constants.ROUND_ASTEROID_PATH);
        this.setWidth(Constants.ROUND_ASTEROID_WIDTH);
        this.setHeight(Constants.ROUND_ASTEROID_HEIGHT);
        this.setVelocity(8, 8);
    }
}

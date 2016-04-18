package gameObjects.dynamicGameObjects.obstacles;

import utilities.Constants;

public class RoundAsteroid extends Asteroid{

    public RoundAsteroid(double x, double y) {
        super(x, y);
        this.setImage(Constants.ROUND_ASTEROID_PATH);
        this.setWidth(Constants.ROUND_ASTEROID_WIDTH);
        this.setHeight(Constants.ROUND_ASTEROID_HEIGHT);
        this.setVelocity(0, 8);
    }
}

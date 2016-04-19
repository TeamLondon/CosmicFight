package core.managers;

import utilities.Constants;
import models.Position;

import java.util.Random;

public class PositionManager {
    private Random random;

    public PositionManager() {
        this.random = new Random();
    }

    public Position getPositionFor(String unitType) {
        int x = -1;
        int y = -1;
        switch (unitType) {
            case "BombPackage":
                x = random.nextInt(Constants.WINDOW_WIDTH - Constants.BONUS_WIDTH);
                y = 0;
                break;
            case "RightAsteroid":
                x = 0;
                y = random.nextInt(Constants.BONUS_HEIGHT / 2);
                break;
            case "LeftAsteroid":
                x = Constants.WINDOW_WIDTH - Constants.BONUS_WIDTH;
                y = random.nextInt(Constants.BONUS_HEIGHT / 2);
                break;
            case "FirstLevelBoss":
                x = random.nextInt(Constants.WINDOW_WIDTH - Constants.BONUS_WIDTH);
                y = 50;
                break;
            default:
                x = random.nextInt(Constants.WINDOW_WIDTH - Constants.BONUS_WIDTH);
                y = 0;
                break;
        }

        Position position = new Position(x, y);
        return position;
    }
}

package core;

import gameObjects.AbstractDynamicGameObject;
import gameObjects.dynamicGameObjects.enemies.ChaoticEnemy;
import gameObjects.dynamicGameObjects.enemies.SlowEnemy;
import gameObjects.dynamicGameObjects.rocks.RoundAsteroid;

import java.util.Random;

public class UnitFactory {
    public AbstractDynamicGameObject createUnit(double x, double y, String unitType) {
        AbstractDynamicGameObject unit;
        Random rand = new Random();

        if (unitType.equals("SlowEnemy")) unit = new SlowEnemy(x, y);
        else if (unitType.equals("ChaoticEnemy")) unit = new ChaoticEnemy(x, y, rand.nextInt(2000));
        else if (unitType.equals("RoundAsteroid")) unit = new RoundAsteroid(x, y);
        else unit = new SlowEnemy(x, y);

        return unit;
    }

}

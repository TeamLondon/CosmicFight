package core;

import gameObjects.AbstractDynamicGameObject;
import gameObjects.dynamicGameObjects.enemies.ChaoticEnemy;
import gameObjects.dynamicGameObjects.enemies.FirstLevelBoss;
import gameObjects.dynamicGameObjects.enemies.SlowEnemy;
import gameObjects.dynamicGameObjects.rocks.RoundAsteroid;

import java.util.Random;

public class UnitFactory {
    public AbstractDynamicGameObject createUnit(double x, double y, String unitType) {
        AbstractDynamicGameObject unit = null;

        switch (unitType){
            case "SlowEnemy":
                unit = new SlowEnemy(x, y);
                break;
            case "ChaoticEnemy":
                unit = new ChaoticEnemy(x, y);
                break;
            case "RoundAsteroid":
                unit = new RoundAsteroid(x, y);
                break;
            case "FirstLevelBoss":
                unit = new FirstLevelBoss(x, y);
                break;
        }
        return unit;
    }

}

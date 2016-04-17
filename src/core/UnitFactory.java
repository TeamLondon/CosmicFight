package core;

import gameObjects.AbstractDynamicGameObject;
import gameObjects.dynamicGameObjects.enemies.ChaoticEnemy;
import gameObjects.dynamicGameObjects.enemies.FirstLevelBoss;
import gameObjects.dynamicGameObjects.enemies.SlowEnemy;
import gameObjects.dynamicGameObjects.obstacles.LeftAsteroid;
import gameObjects.dynamicGameObjects.obstacles.RightAsteroid;
import gameObjects.dynamicGameObjects.obstacles.RoundAsteroid;

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
            case "LeftAsteroid":
                unit = new LeftAsteroid(x, y);
                break;
            case  "RightAsteroid":
                unit = new RightAsteroid(x,y);
                break;
        }
        return unit;
    }

}

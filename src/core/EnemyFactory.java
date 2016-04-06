package core;

import gameObjects.AbstractDynamicGameObject;
import gameObjects.dynamicGameObjects.enemies.ChaoticEnemy;
import gameObjects.dynamicGameObjects.enemies.SlowEnemy;

import java.util.Random;

public class EnemyFactory {
    public AbstractDynamicGameObject createEnemy(double x, double y, String enemyType) {
        AbstractDynamicGameObject enemy;
        Random rand = new Random();

        if (enemyType.equals("SlowEnemy")) enemy = new SlowEnemy(x, y);
        else if (enemyType.equals("ChaoticEnemy")) enemy = new ChaoticEnemy(x, y, rand.nextInt(2000));
        else enemy = new SlowEnemy(x, y);

        return enemy;
    }

}

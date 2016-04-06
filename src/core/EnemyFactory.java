package core;

import gameObjects.AbstractDynamicGameObject;
import gameObjects.dynamicGameObjects.enemies.SlowEnemy;

public class EnemyFactory {
    public AbstractDynamicGameObject createEnemy(double x, double y, String enemyType) {
         AbstractDynamicGameObject enemy;

        if (enemyType.equals("SlowEnemy")) enemy = new SlowEnemy(x, y);
        else enemy = new SlowEnemy(x, y);

        return enemy;
    }

}

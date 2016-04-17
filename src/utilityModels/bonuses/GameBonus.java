package utilityModels.bonuses;

import gameObjects.AbstractDynamicGameObject;
import interfaces.Bonus;

public abstract class GameBonus extends AbstractDynamicGameObject implements Bonus {
    public GameBonus(double x, double y) {
        super(x, y);
        this.setVelocity(0,1);
    }
}
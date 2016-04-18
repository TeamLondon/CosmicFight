package core.factories;

import enums.Attacks;
import gameObjects.dynamicGameObjects.attacks.Bullet;
import gameObjects.dynamicGameObjects.attacks.GiantBomb;
import interfaces.factories.AttacksFactory;
import interfaces.models.Attack;

public class SimpleAttackFactory implements AttacksFactory {
    @Override
    public Attack createAttack(Attacks attackType, double x, double y) {
        Attack attack = null;
        switch (attackType) {
            case Bomb:
                attack = new GiantBomb(x, y);
                break;
            case Bullet:
                attack = new Bullet(x, y);
                break;
            default:
                break;
        }

        return attack;
    }
}

package core.factories;

import enums.Boss;
import gameObjects.dynamicGameObjects.enemies.FirstLevelBoss;
import interfaces.factories.AttacksFactory;
import interfaces.factories.BossFactory;
import interfaces.models.Unit;
import core.handlers.ObjectHandler;

public class SimpleBossFactory implements BossFactory {
    private ObjectHandler objectHandler;
    private AttacksFactory attacksFactory;

    public SimpleBossFactory(ObjectHandler objectHandler, AttacksFactory attacksFactory) {
        this.objectHandler = objectHandler;
        this.attacksFactory = attacksFactory;
    }

    public Unit createUnit(Boss unitType, double x, double y) {
        Unit unit = null;

        switch (unitType) {
            case FirstLevelBoss:
                unit = new FirstLevelBoss(x, y, this.objectHandler, this.attacksFactory);
                break;
        }

        return unit;
    }
}

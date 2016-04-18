package core.factories;

import enums.Bonuses;
import interfaces.factories.BonusFactory;
import models.bonuses.BombPackage;
import models.bonuses.HealthPackage;
import models.contracts.Bonus;

public class SimpleBonusFactory implements BonusFactory {
    @Override
    public Bonus createBonus(Bonuses bonusType, double x, double y) {
        Bonus bonus = null;
        switch (bonusType) {
            case HealthPackage:
                bonus = new HealthPackage(x, y);
                break;
            case BombPackage:
                bonus = new BombPackage(x, y);
                break;
            default:
                break;
        }

        return bonus;
    }
}

package interfaces.factories;

import enums.Bonuses;
import models.contracts.Bonus;

public interface BonusFactory {
    Bonus createBonus(Bonuses bonusType, double x, double y);
}

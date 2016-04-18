package interfaces.factories;

import enums.Attacks;
import interfaces.models.Attack;

public interface AttacksFactory {
    Attack createAttack(Attacks attackType, double x, double y);
}

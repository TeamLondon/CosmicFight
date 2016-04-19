package interfaces.factories;

import enums.Boss;
import interfaces.models.Unit;

public interface BossFactory {
    Unit createUnit(Boss bossType, double x, double y);
}

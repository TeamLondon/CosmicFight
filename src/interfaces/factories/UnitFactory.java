package interfaces.factories;

import enums.Units;
import interfaces.models.Unit;

public interface UnitFactory {
    Unit createUnit(Units unitType, double x, double y);
}

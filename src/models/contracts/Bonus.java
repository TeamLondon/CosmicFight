package models.contracts;

import interfaces.models.DynamicGameObject;
import interfaces.models.Player;

public interface Bonus extends DynamicGameObject {
    void applyBonus(Player player);
}

package models.bonuses;

import interfaces.models.Player;

public class HealthPackage extends GameBonus {
    public HealthPackage(double x, double y) {
        super(x, y);
    }

    @Override
    public void applyBonus(Player player) {
        player.setHitPoints(player.getHitPoints() + 40);
    }
}

package core;

import core.managers.PositionManager;
import enums.Bonuses;
import enums.Units;
import interfaces.factories.AttacksFactory;
import interfaces.factories.BonusFactory;
import interfaces.factories.UnitFactory;
import interfaces.models.DynamicGameObject;
import models.Position;

import java.util.*;

public class Spawner {
    private static final List<Units> unitTypes = Collections.unmodifiableList(Arrays.asList(Units.values()));

    private UnitFactory unitFactory;
    private AttacksFactory attacksFactory;
    private BonusFactory bonusFactory;
    private Double distanceRate;
    private Double passedDistance;
    private Random random;
    private Boolean isBombPackageSpawned;
    private PositionManager positionManager;

    public Spawner(UnitFactory unitFactory, AttacksFactory attacksFactory, BonusFactory bonusFactory, PositionManager positionManager) {
        this.unitFactory = unitFactory;
        this.attacksFactory = attacksFactory;
        this.bonusFactory = bonusFactory;
        this.positionManager = positionManager;
        this.passedDistance = 0d;
        this.isBombPackageSpawned = false;
        this.random = new Random();
    }

    public DynamicGameObject spawn(Double currentDistance) {
        DynamicGameObject gameObject = null;
        if (this.passedDistance == 0) {
            this.passedDistance = currentDistance;
        }
        if (Math.abs(currentDistance - this.passedDistance) > this.distanceRate) {
            if (currentDistance < 2000 && currentDistance > 1900 && !this.isBombPackageSpawned) {
                this.isBombPackageSpawned = true;
                Position position = this.positionManager.getPositionFor("BombPackage");
                gameObject = this.bonusFactory.createBonus(Bonuses.BombPackage, position.getX(), position.getY());
            } else {
                Units unitType = unitTypes.get(this.random.nextInt(unitTypes.size()));
                Position position = this.positionManager.getPositionFor(unitType.toString());
                gameObject = this.unitFactory.createUnit(unitType, position.getX(), position.getY());
                this.passedDistance = currentDistance;
            }
        }

        return gameObject;
    }

    public void setDistance(Double distanceRate) {
        this.distanceRate = distanceRate;
    }
}

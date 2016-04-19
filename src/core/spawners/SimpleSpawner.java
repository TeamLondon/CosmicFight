package core.spawners;

import com.sun.org.apache.xpath.internal.operations.Bool;
import core.managers.PositionManager;
import enums.Bonuses;
import enums.Boss;
import enums.Units;
import gameObjects.dynamicGameObjects.enemies.FirstLevelBoss;
import interfaces.Spawner;
import interfaces.factories.AttacksFactory;
import interfaces.factories.BonusFactory;
import interfaces.factories.BossFactory;
import interfaces.factories.UnitFactory;
import interfaces.models.DynamicGameObject;
import models.Position;
import models.handlers.ObjectHandler;

import java.util.*;

public class SimpleSpawner implements Spawner {
    private static final List<Units> unitTypes = Collections.unmodifiableList(Arrays.asList(Units.values()));

    private UnitFactory unitFactory;
    private BossFactory bossFactory;
    private BonusFactory bonusFactory;
    private Double distanceRate;
    private Double passedDistance;
    private Random random;
    private Boolean isBombPackageSpawned;
    private PositionManager positionManager;
    private Boolean isFirstLevelBossSpawned;

    public SimpleSpawner(UnitFactory unitFactory, BossFactory bossFactory, BonusFactory bonusFactory, PositionManager positionManager) {
        this.unitFactory = unitFactory;
        this.bossFactory = bossFactory;
        this.bonusFactory = bonusFactory;
        this.positionManager = positionManager;
        this.passedDistance = 0d;
        this.isBombPackageSpawned = false;
        this.isFirstLevelBossSpawned = false;
        this.random = new Random();
    }

    @Override
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

        if (!this.isFirstLevelBossSpawned && this.passedDistance < 2000) {
            this.isFirstLevelBossSpawned = true;
            System.out.println("boss");
            Position position = this.positionManager.getPositionFor(Boss.FirstLevelBoss.toString());
            gameObject = this.bossFactory.createUnit(Boss.FirstLevelBoss, position.getX(), position.getY());
        }

        return gameObject;
    }

    public void setDistance(Double distanceRate) {
        this.distanceRate = distanceRate;
    }
}

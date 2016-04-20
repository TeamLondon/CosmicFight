package core.spawners;

import core.managers.PositionManager;
import enums.Bonuses;
import enums.Boss;
import enums.Units;
import interfaces.Spawner;
import interfaces.factories.BonusFactory;
import interfaces.factories.BossFactory;
import interfaces.factories.UnitFactory;
import interfaces.models.DynamicGameObject;
import models.Position;

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
    private boolean isHealthPackageSpawned;

    public SimpleSpawner(UnitFactory unitFactory, BossFactory bossFactory, BonusFactory bonusFactory, PositionManager positionManager) {
        this.unitFactory = unitFactory;
        this.bossFactory = bossFactory;
        this.bonusFactory = bonusFactory;
        this.positionManager = positionManager;
        this.passedDistance = 0d;
        this.isBombPackageSpawned = false;
        this.isFirstLevelBossSpawned = false;
        this.isHealthPackageSpawned = false;
        this.random = new Random();
    }

    @Override
    public DynamicGameObject spawn(Double currentDistance) {
        DynamicGameObject gameObject = null;
        if (this.passedDistance == 0) {
            this.passedDistance = currentDistance;
        }
        if (Math.abs(currentDistance - this.passedDistance) > this.distanceRate) {
            if (currentDistance < 2400 && currentDistance > 1900 && !this.isBombPackageSpawned) {
                this.isBombPackageSpawned = true;
                Position position = this.positionManager.getPositionFor("Package");
                gameObject = this.bonusFactory.createBonus(Bonuses.BombPackage, position.getX(), position.getY());
            } else if (this.passedDistance > 1300 && this.passedDistance < 1600 && !this.isHealthPackageSpawned) {
                this.isHealthPackageSpawned = true;
                Position position = this.positionManager.getPositionFor("Package");
                gameObject = this.bonusFactory.createBonus(Bonuses.HealthPackage, position.getX(), position.getY());
                return gameObject;
            } else if (this.passedDistance < 1100 && this.passedDistance > 800 && this.isHealthPackageSpawned){
                    this.isHealthPackageSpawned = false;
                    Position position = this.positionManager.getPositionFor("Package");
                    gameObject = this.bonusFactory.createBonus(Bonuses.HealthPackage, position.getX(), position.getY());
            } else if (!this.isHealthPackageSpawned && this.passedDistance < 300 ){
                this.isHealthPackageSpawned = true;
                Position position = this.positionManager.getPositionFor("Package");
                gameObject = this.bonusFactory.createBonus(Bonuses.HealthPackage, position.getX(), position.getY());
            } else {
                Units unitType = unitTypes.get(this.random.nextInt(unitTypes.size()));
                Position position = this.positionManager.getPositionFor(unitType.toString());
                gameObject = this.unitFactory.createUnit(unitType, position.getX(), position.getY());
                this.passedDistance = currentDistance;
            }
        }

        if (!this.isFirstLevelBossSpawned && this.passedDistance < 2000) {
            this.isFirstLevelBossSpawned = true;
            Position position = this.positionManager.getPositionFor(Boss.FirstLevelBoss.toString());
            gameObject = this.bossFactory.createUnit(Boss.FirstLevelBoss, position.getX(), position.getY());
        }

        return gameObject;
    }

    public void setDistance(Double distanceRate) {
        this.distanceRate = distanceRate;
    }

    public void initialize(){
        this.passedDistance = 0d;
        this.isHealthPackageSpawned = false;
        this.isFirstLevelBossSpawned = false;
        this.isBombPackageSpawned = false;
    }
}

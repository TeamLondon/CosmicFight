package core;

import interfaces.DynamicGameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spawner {
    private UnitFactory unitFactory;
    private List<String> unitTypes;
    private Double distanceRate;
    private Double passedDistance;
    private Random random;
    private Boolean isBombPackageSpawned;
    private PositionManager positionManager;

    public Spawner(UnitFactory unitFactory, PositionManager positionManager) {
        this.unitFactory = unitFactory;
        this.positionManager = positionManager;
        this.passedDistance = 0d;
        this.unitTypes = new ArrayList<>();
        this.unitTypes.add("SlowEnemy");
        this.unitTypes.add("ChaoticEnemy");
        this.unitTypes.add("RoundAsteroid");
        this.unitTypes.add("RightAsteroid");
        this.unitTypes.add("LeftAsteroid");
        this.random = new Random();
        this.isBombPackageSpawned = false;
    }

    public DynamicGameObject spawn(Double currentDistance) {
        DynamicGameObject gameObject = null;
        if (this.passedDistance == 0) {
            this.passedDistance = currentDistance;
        }

        if (Math.abs(currentDistance - this.passedDistance) > this.distanceRate) {
            String enemyType = this.unitTypes.get(random.nextInt(this.unitTypes.size()));

            if (currentDistance < 2000 && currentDistance > 1900 && !this.isBombPackageSpawned) {
                this.isBombPackageSpawned = true;
                Position position = this.positionManager.getPositionFor("BombPackage");
                gameObject = this.unitFactory.createUnit(position.getX(), position.getY(), "BombPackage");
            }else {

                Position position = this.positionManager.getPositionFor(enemyType);
                gameObject = this.unitFactory.createUnit(position.getX(), position.getY(), enemyType);
                this.passedDistance = currentDistance;
            }
        }

        return gameObject;
    }

    public void setDistance(Double distanceRate) {
        this.distanceRate = distanceRate;
    }
}

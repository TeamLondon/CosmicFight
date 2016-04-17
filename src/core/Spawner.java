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

    public Spawner(UnitFactory unitFactory) {
        this.unitFactory = unitFactory;
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

            // ToDo: Position manager.
            if (currentDistance < 2000 && currentDistance > 1900 && !this.isBombPackageSpawned) {
                this.isBombPackageSpawned = true;
                gameObject = unitFactory.createUnit(random.nextInt(650), 0, "BombPackage");
            }else {
                if (enemyType.equals("RightAsteroid")) {
                    gameObject = unitFactory.createUnit(0, random.nextInt(250), enemyType);
                } else if (enemyType.equals("LeftAsteroid")) {
                    gameObject = unitFactory.createUnit(650, random.nextInt(250), enemyType);
                } else {
                    gameObject = unitFactory.createUnit(random.nextInt(650), 0, enemyType);
                }
                System.out.println(this.passedDistance);
                this.passedDistance = currentDistance;
            }
        }

        return gameObject;
    }

    public void setDistance(Double distanceRate) {
        this.distanceRate = distanceRate;
    }

}

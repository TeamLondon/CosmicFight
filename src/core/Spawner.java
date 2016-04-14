package core;

import interfaces.DynamicGameObject;

import java.util.Random;

public class Spawner {
    private UnitFactory unitFactory;
    private String[] unitTypes;
    private Double distanceRate;
    private Double passedDistance;
    private Random random;

    public Spawner(UnitFactory unitFactory) {
        this.unitFactory = unitFactory;
        this.passedDistance = 0d;
        this.unitTypes = new String[3];
        this.unitTypes[0] = "SlowEnemy";
        this.unitTypes[1] = "ChaoticEnemy";
        this.unitTypes[2] = "RoundAsteroid";
        this.random = new Random();
    }

    public DynamicGameObject spawn(Double currentDistance)
    {
        DynamicGameObject gameObject = null;
        if (this.passedDistance == 0){
            this.passedDistance = currentDistance;
        }

        if (Math.abs( currentDistance - this.passedDistance) > this.distanceRate){
            String enemyType = this.unitTypes[random.nextInt(this.unitTypes.length)];
            gameObject = unitFactory.createUnit(random.nextInt(650) , 0, enemyType);
            this.passedDistance = currentDistance;
        }

        return gameObject;
    }

    public void setDistance(Double distanceRate){
        this.distanceRate = distanceRate;
    }

}

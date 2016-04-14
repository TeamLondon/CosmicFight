package gameObjects.dynamicGameObjects.effects;

import gameObjects.dynamicGameObjects.Particle;

import java.util.List;

public abstract class Emitter {
    public abstract List<Particle> emit(double x, double y);
}

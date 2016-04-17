package interfaces;

import gameObjects.dynamicGameObjects.effects.Particle;

import java.util.List;

public interface Emittable {
    public abstract List<Particle> emit(double x, double y);
}

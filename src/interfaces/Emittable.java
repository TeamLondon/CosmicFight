package interfaces;

import gameObjects.dynamicGameObjects.Particle;

import java.util.List;

public interface Emittable {
    public abstract List<Particle> emit(double x, double y);
}

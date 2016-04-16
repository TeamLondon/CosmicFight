package gameObjects.dynamicGameObjects.effects;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class FireEmitter extends Emitter {
    @Override
    public List<Particle> emit(double x, double y) {
        List<Particle> particles = new ArrayList<>();
        int numOfParticles = 15;

        for (int i = 0; i < numOfParticles; i++) {
            Particle p = new Particle(x, y, new Point2D((Math.random() - 0.5), Math.random() * -1),
                    4, 0.3, Color.rgb(230, 40, 45));
            particles.add(p);
        }
        return particles;
    }
}

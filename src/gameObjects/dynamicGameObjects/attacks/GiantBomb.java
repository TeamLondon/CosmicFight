package gameObjects.dynamicGameObjects.attacks;

import gameObjects.dynamicGameObjects.effects.Emitter;
import gameObjects.dynamicGameObjects.effects.FireEmitter;
import gameObjects.dynamicGameObjects.effects.Particle;
import gameObjects.dynamicGameObjects.effects.SmokeEmitter;
import utilities.Constants;
import gameObjects.AbstractDynamicGameObject;
import interfaces.models.Attack;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GiantBomb extends AbstractDynamicGameObject implements Attack {
    private Emitter emitter = new SmokeEmitter();
    private List<Particle> particles = new ArrayList<>();

    public GiantBomb(double x, double y) {
        super(x, y);
        this.setImage(Constants.GIANT_BOMB_PATH);
        this.setWidth(Constants.GIANT_BOMB_WIDTH);
        this.setHeight(Constants.GIANT_BOMB_HEIGHT);
        this.setVelocity(0, -7);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        gc.drawImage(this.getImage(), this.getX() + 18, this.getY(), this.getWidth(), this.getHeight());

        particles.addAll(emitter.emit(this.getX() + this.getWidth() - 6, this.getY() + this.getHeight()));

        for (Iterator<Particle> it = particles.iterator(); it.hasNext(); ) {
            Particle p = it.next();
            p.update();

            if (!p.isAlive()) {
                it.remove();
                continue;
            }

            p.draw(gc);
        }
    }

    @Override
    public Integer getDamage() {
        return 100;
    }
}

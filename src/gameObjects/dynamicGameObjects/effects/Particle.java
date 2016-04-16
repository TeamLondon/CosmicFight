package gameObjects.dynamicGameObjects.effects;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Paint;

public class Particle{

    private double x;
    private double y;

    private Point2D velocity;

    private double radius;
    private double life = 1.0;
    private double decay;

    private Paint color;

    public Particle(double x, double y, Point2D velocity, double radius, double expireTime, Paint color) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.radius = radius;
        this.color = color;
        this.decay = 0.016 / expireTime;
    }

    public boolean isAlive(){
        return life > 0;
    }

    public void update() {
        x += velocity.getX();
        y += velocity.getY();

        life -= decay;
    }

    public void draw(GraphicsContext gc) {
        gc.setGlobalAlpha(life);
        gc.setFill(color);
        gc.fillOval(x, y, radius, radius);
    }

}

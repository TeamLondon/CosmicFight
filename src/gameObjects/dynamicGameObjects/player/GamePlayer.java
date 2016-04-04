package gameObjects.dynamicGameObjects.player;

import gameObjects.AbstractDynamicGameObject;
import interfaces.Attack;
import interfaces.DynamicGameObject;
import interfaces.Player;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GamePlayer extends AbstractDynamicGameObject implements Player{
    private String name;
    private Integer score = 0;
    private double health = 100;

    public GamePlayer(double x, double y, String name) {
        super(x, y);
        this.name = name;
        this.setImage("/5.png");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getScore() {
        return score;
    }
    public Attack produceAttack() {
        return null;
    }
    public Double getHealth() {
        return health;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, x, y);
    }
    public void update() {
        x += velocityX;
        y += velocityY;
    }
}

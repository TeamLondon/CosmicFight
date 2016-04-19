package models.bonuses;

import enums.Attacks;
import gameObjects.dynamicGameObjects.healthBars.PackageHealthBar;
import interfaces.models.Player;
import javafx.scene.canvas.GraphicsContext;
import utilities.Constants;

public class BombPackage extends GameBonus {
    private PackageHealthBar healthBar;

    public BombPackage(double x, double y) {
        super(x, y);
        this.setImage(Constants.BONUS_PATH);
        this.setWidth(Constants.BONUS_WIDTH);
        this.setHeight(Constants.BONUS_HEIGHT);
        this.healthBar = new PackageHealthBar(this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getHitPoints(), Constants.SLOW_ENEMY_HEALTH_BAR_PATH);
    }


    @Override
    public void applyBonus(Player player) {
        player.addAttack(Attacks.Bomb);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight());

        this.healthBar.draw(gc);
    }

    @Override
    public void update() {
        super.update();
        this.healthBar.setPosition(this.getX(), this.getY());
        this.healthBar.setHealth(this.getHitPoints());
        this.healthBar.update();
    }
}

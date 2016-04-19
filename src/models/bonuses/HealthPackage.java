package models.bonuses;

import gameObjects.dynamicGameObjects.healthBars.PackageHealthBar;
import interfaces.models.HealthBar;
import interfaces.models.Player;
import javafx.scene.canvas.GraphicsContext;
import utilities.Constants;

public class HealthPackage extends GameBonus {
    private HealthBar healthBar;
    public HealthPackage(double x, double y) {
        super(x, y);
        this.setImage(Constants.HEALTH_PACKAGE_IMAGE_PATH);
        this.setWidth(Constants.BONUS_WIDTH);
        this.setHeight(Constants.BONUS_HEIGHT);
        this.healthBar = new PackageHealthBar(
                this.getX(),
                this.getY(),
                this.getWidth(),
                this.getHeight(),
                this.getHitPoints(),
                Constants.SLOW_ENEMY_HEALTH_BAR_PATH);
    }

    @Override
    public void update() {
        super.update();
        this.healthBar.setPosition(this.getX(), this.getY());
        this.healthBar.setHealth(this.getHitPoints());
        this.healthBar.update();
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight());

        this.healthBar.draw(graphicsContext);
    }

    @Override
    public void applyBonus(Player player) {
        player.setHitPoints(player.getHitPoints() + 40);
    }
}

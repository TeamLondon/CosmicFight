package models.bonuses;

import utilities.Constants;
import enums.Attacks;
import interfaces.models.Player;
import javafx.scene.canvas.GraphicsContext;

public class BombPackage extends GameBonus {
    public BombPackage(double x, double y) {
        super(x, y);
        this.setImage(Constants.BONUS_PATH);
        this.setWidth(Constants.BONUS_WIDTH);
        this.setHeight(Constants.BONUS_HEIGHT);
    }


    @Override
    public void applyBonus(Player player) {
        player.addAttack(Attacks.Bomb);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}

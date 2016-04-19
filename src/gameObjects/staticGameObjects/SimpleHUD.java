package gameObjects.staticGameObjects;

import javafx.scene.image.Image;
import utilities.Constants;
import enums.Attacks;
import gameObjects.AbstractStaticGameObject;
import interfaces.models.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SimpleHUD extends AbstractStaticGameObject implements interfaces.HUD{
    private Player player;
    private double healthValue;
    private double bombValue;

    public SimpleHUD() {
    }

    public void draw(GraphicsContext gc) {
        this.setImage(Constants.HUD_HEALTH_BAR_PATH);
        gc.setFill(Color.rgb(75, Math.abs((int)healthValue), 0));
        gc.fillRect(20, 10, healthValue, this.getHeight() - 25);
        gc.drawImage(this.getImage(), 1, 1, this.getWidth(), this.getHeight());
        Font theFont = Font.font("Impact", FontWeight.BOLD, 50);
        gc.setFont(theFont);
        gc.setFill(Color.rgb(44, 100, 200));
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);

        String pointsText = player.getHighScore().getPlayerScore().toString();
        gc.fillText( pointsText, 700, 50);
        gc.strokeText( pointsText, 700, 50);

        if (player.getAttacks().contains(Attacks.Bomb)) {
            this.setImage(Constants.HUD_COOLDOWN_BAR_PATH);
            gc.fillRect(84, Constants.WINDOW_HEIGHT - 42, bombValue, 20);
            gc.drawImage(this.getImage(), 1, Constants.WINDOW_HEIGHT - this.getImage().getHeight() + 20, this.getWidth(), this.getHeight() - 20);
            gc.drawImage(new Image(Constants.GIANT_BOMB_PATH), 45, Constants.WINDOW_HEIGHT - 52, 30, 40);
        }
    }
    public void update() {
        double amountInPercent = (player.getBombCooldown() / 20.0) * 100;
        this.bombValue = this.getWidth() * (amountInPercent * 0.01) - 89;
        this.healthValue = clamp(player.getHitPoints() * 2, 0, this.getWidth() - 32);
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public double clamp(double var, double min, double max) {
        if (var >= max) {
            return max;
        }
        else if(var <= min){
            return min;
        }
        else return var;
    }
}

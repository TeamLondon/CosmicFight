package gameObjects.staticGameObjects;

import core.Constants;
import gameObjects.AbstractStaticGameObject;
import gameObjects.dynamicGameObjects.player.GamePlayer;
import interfaces.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HUD extends AbstractStaticGameObject {
    private Player player;
    private double greenValue;

    public HUD(Player player) {
        this.player = player;
        this.setImage(Constants.HUD_HEALTH_BAR_PATH);
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.rgb(75, (int)greenValue, 0));
        gc.fillRect(20, 10, greenValue, this.getHeight() - 25);
        gc.drawImage(this.getImage(), 1, 1, this.getWidth(), this.getHeight());
        Font theFont = Font.font("Impact", FontWeight.BOLD, 50);
        gc.setFont(theFont);
        gc.setFill(Color.rgb(44, 100, 200));
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);

        String pointsText = player.getHighScore().getPlayerScore().toString();
        gc.fillText( pointsText, 700, 50);
        gc.strokeText( pointsText, 700, 50);
    }
    public void update() {
        greenValue = clamp(player.getHitPoints() * 2, 0, this.getWidth() - 32);
    }

    private double clamp(double var, double min, double max) {
        if (var >= max) return var = max;
        else if(var <= min)return var = min;
        else return var;
    }
}

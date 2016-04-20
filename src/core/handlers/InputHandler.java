package core.handlers;

import enums.Attacks;
import gameObjects.dynamicGameObjects.attacks.Bullet;
import gameObjects.dynamicGameObjects.attacks.GiantBomb;
import interfaces.models.Player;
import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    private Scene scene;
    private Player player;
    private ObjectHandler objectHandler;
    private List<String> input;

    public InputHandler(ObjectHandler objectHandler) {
        this.objectHandler = objectHandler;
        this.input = new ArrayList<>();
    }

    public ObjectHandler getObjectHandler() {
        return this.objectHandler;
    }

    public void  setPlayer(Player player){
        this.player = player;
    }

    public void clear(){
        this.input.clear();
    }

    public void refresh() {
        this.scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        this.input.add(code);
                });

        this.scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    if (!(code.equals("E"))) {
                        this.input.remove(code);
                    }
                });
        handleKeys();
    }
    private void handleKeys() {
        this.player.setVelocity(0,0);
        if (this.input.contains("LEFT") || this.input.contains("A"))
            this.player.addVelocity(-10,0);
        if (this.input.contains("RIGHT") || this.input.contains("D"))
             this.player.addVelocity(10,0);
        if (this.input.contains("UP") || this.input.contains("W"))
            this.player.addVelocity(0,-5);
        if (this.input.contains("DOWN") || this.input.contains("S"))
            this.player.addVelocity(0,5);
        if (this.input.contains("SPACE")) {
            if (player.getCurrentAttack() == Attacks.Bullet) {
                if (player.getBulletCooldown() >= 0.4) {
                    this.objectHandler.addDynamicObject(new Bullet(player.getX(), player.getY()));
                    player.resetBullet();
                }
            }else if (player.getCurrentAttack() == Attacks.Bomb) {
                if (player.getBombCooldown() >= 20.0) {
                    this.objectHandler.addDynamicObject(new GiantBomb(player.getX(), player.getY()));
                    player.resetBomb();
                }
            }
        }
        if (this.input.contains("E")) {
            this.player.changeAttack();
            this.input.remove("E");
        }


    }

    public void setScene(Scene scene) {
        if (scene != null) {
            this.scene = scene;
        }
    }
}

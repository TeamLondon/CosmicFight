package core;

import enums.Attacks;
import gameObjects.dynamicGameObjects.attacks.Bullet;
import gameObjects.dynamicGameObjects.attacks.GiantBomb;
import interfaces.Player;
import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    private Scene scene;
    private Player player;
    private ObjectHandler handler;
    private List<String> input;
    private long lastTime = System.nanoTime();

    public InputHandler(Scene scene, Player player, ObjectHandler handler) {
        this.scene = scene;
        this.player = player;
        this.input = new ArrayList<>();
        this.handler = handler;
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
                if (player.getBulletCooldown() <= 0.0) {
                    this.handler.addDynamicObject(new Bullet(player.getX(), player.getY()));
                    player.resetBullet();
                }
            }else if (player.getCurrentAttack() == Attacks.Bomb) {
                if (player.getBombCooldown() <= 0.0) {
                    this.handler.addDynamicObject(new GiantBomb(player.getX(), player.getY()));
                    player.resetBomb();
                }
            }
        }
        if (this.input.contains("E")) {
            player.changeAttack();
            this.input.remove("E");
        }


    }
}

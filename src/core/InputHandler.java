package core;

import gameObjects.dynamicGameObjects.attacks.Bullet;
import gameObjects.dynamicGameObjects.player.GamePlayer;
import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    private Scene scene;
    private GamePlayer player;
    private ObjectHandler handler;
    private List<String> input;
    private long lastTime = System.nanoTime();

    public InputHandler(Scene scene, GamePlayer player, ObjectHandler handler) {
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
                    this.input.remove(code);
                });
        handleKeys();
    }
    private void handleKeys() {
        long currentTime = System.nanoTime();

        double elapsedTime = (currentTime - lastTime) / 1_000_000_00.0;
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
            if (elapsedTime > player.getFireRate()) {
                this.handler.addDynamicObject(new Bullet(player.getX(), player.getY()));
                lastTime = currentTime;
            }
        }


    }
}

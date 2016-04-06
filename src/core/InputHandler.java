package core;

import gameObjects.dynamicGameObjects.player.GamePlayer;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    Scene scene;
    GamePlayer player;
    List<String> input;

    public InputHandler(Scene scene, GamePlayer player) {
        this.scene = scene;
        this.player = player;
        input = new ArrayList<>();
    }

    public void refresh() {
        scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        this.input.add(code);
                });

        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    this.input.remove(code);
                });

        scene.setOnMouseMoved(
                arg0 -> {
            if(arg0.getEventType() == MouseEvent.MOUSE_MOVED){
                handleMouse(arg0.getX(),arg0.getY(), player);
            }
        });
        handleKeys();
    }

    private void handleMouse(double x, double y, GamePlayer player) {
        player.setPosition(x, y);
    }

    private void handleKeys() {
        player.setVelocity(0,0);
        if (input.contains("LEFT"))
            player.addVelocity(-10,0);
        if (input.contains("RIGHT"))
            player.addVelocity(10,0);
        if (input.contains("UP"))
            player.addVelocity(0,-10);
        if (input.contains("DOWN"))
            player.addVelocity(0,10);
    }

}

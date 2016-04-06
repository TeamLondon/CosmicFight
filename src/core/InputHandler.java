package core;

import gameObjects.dynamicGameObjects.attacks.Bullet;
import gameObjects.dynamicGameObjects.player.GamePlayer;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    Scene scene;
    GamePlayer player;
    ObjectHandler handler;
    List<String> input;

    public InputHandler(Scene scene, GamePlayer player, ObjectHandler handler) {
        this.scene = scene;
        this.player = player;
        input = new ArrayList<>();
        this.handler = handler;
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

      //scene.setOnMouseMoved(
      //        arg0 -> {
      //    if(arg0.getEventType() == MouseEvent.MOUSE_MOVED){
      //        handleMouse(arg0.getX(),arg0.getY(), player);
      //    }
      //});
        handleKeys();
    }

   // private void handleMouse(double x, double y, GamePlayer player) {
   //     player.setPosition(x, y);
   // }

    private void handleKeys() {
        player.setVelocity(0,0);
        if (input.contains("LEFT") || input.contains("A"))
            player.addVelocity(-10,0);
        if (input.contains("RIGHT") || input.contains("D"))
            player.addVelocity(10,0);
        if (input.contains("UP") || input.contains("W"))
            player.addVelocity(0,-5);
        if (input.contains("DOWN") || input.contains("S"))
            player.addVelocity(0,5);
        if (input.contains("SPACE"))
            handler.addDynamicObject(new Bullet(player.getX(), player.getY()));
    }

}

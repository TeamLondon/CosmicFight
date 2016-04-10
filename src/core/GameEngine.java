package core;

import interfaces.Engine;
import interfaces.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;

public class GameEngine extends AnimationTimer implements Engine{

    private  Scene scene;

    public Player getCurrentPlayer() {
        return null;
    }

    public void run() {
    }

    @Override
    public void draw() {

    }

    @Override
    public void update() {

    }

    @Override
    public Scene unloadContent() {
        return this.scene;
    }

    public void handle(long now) {

    }
}

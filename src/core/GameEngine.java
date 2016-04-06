package core;

import interfaces.Engine;
import interfaces.Player;
import javafx.animation.AnimationTimer;

public class GameEngine extends AnimationTimer implements Engine{

    @Override
    public Player getCurrentPlayer() {
        return null;
    }

    public void Run() {
    }

    public void handle(long now) {

    }
}

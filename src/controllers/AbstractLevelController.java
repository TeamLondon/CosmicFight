package controllers;

import models.handlers.InputHandler;
import models.handlers.ObjectHandler;
import core.Spawner;
import gameObjects.staticGameObjects.HUD;
import interfaces.*;

public abstract class AbstractLevelController extends AbstractController {
    private Player player;
    private InputHandler inputHandler;
    private HUD hud;
    private Spawner spawner;

    public AbstractLevelController(
            StageManager stateManager,
            Database gameDatabase,
            Spawner spawner,
            InputHandler inputHandler,
            MessageBox messageBox,
            ConfirmBox confirmBox,
            HUD hud) {
        super.initialize(stateManager, gameDatabase, messageBox, confirmBox);
        this.spawner = spawner;
        this.player = gameDatabase.getPlayer();
        this.inputHandler = inputHandler;
        this.hud = hud;
    }


    protected Player getPlayer() {
        return player;
    }

    protected InputHandler getInputHandler() {
        return inputHandler;
    }

    protected HUD getHud() {
        return hud;
    }

    protected Spawner getSpawner() {
        return spawner;
    }
}

package controllers;

import interfaces.core.Database;
import interfaces.models.Player;
import models.contracts.ConfirmBox;
import models.contracts.MessageBox;
import models.handlers.InputHandler;
import core.Spawner;
import gameObjects.staticGameObjects.SimpleHUD;
import interfaces.*;

public abstract class AbstractLevelController extends AbstractController {
    private Player player;
    private InputHandler inputHandler;
    private SimpleHUD hud;
    private Spawner spawner;

    public AbstractLevelController(
            StageManager stateManager,
            Database gameDatabase,
            Spawner spawner,
            InputHandler inputHandler,
            MessageBox messageBox,
            ConfirmBox confirmBox,
            SimpleHUD hud) {
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

    protected SimpleHUD getHud() {
        return hud;
    }

    protected Spawner getSpawner() {
        return spawner;
    }
}

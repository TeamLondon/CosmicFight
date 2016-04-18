package controllers;

import models.handlers.InputHandler;
import models.handlers.ObjectHandler;
import core.Spawner;
import gameObjects.staticGameObjects.HUD;
import interfaces.*;

public abstract class AbstractLevelController extends AbstractController {
    private ObjectHandler objectHandler;
    private Player player;
    private InputHandler inputHandler;
    private HUD hud;
    private Spawner spawner;

    public AbstractLevelController(
            StageManager stateManager,
            Database gameDatabase,
            MessageBox messageBox,
            ConfirmBox confirmBox,
            ObjectHandler objectHandler,
            Player player,
            InputHandler inputHandler,
            HUD hud,
            Spawner spawner) {
        super.initialize(stateManager, gameDatabase, messageBox, confirmBox);
        this.spawner = spawner;
        this.objectHandler = objectHandler;
        this.player = player;
        this.inputHandler = inputHandler;
        this.hud = hud;
    }

    protected ObjectHandler getObjectHandler() {
        return objectHandler;
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

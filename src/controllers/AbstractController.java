package controllers;

import interfaces.Database;
import interfaces.StageController;

import interfaces.StageManager;

public abstract class AbstractController implements StageController {
    private StageManager stageManager;

    private Database gameDatabase;

    protected StageManager getStageManager() {
        return stageManager;
    }

    protected Database getGameDatabase() {
        return gameDatabase;
    }

    @Override
    public void setStageManager(StageManager stageManager) {
        this.gameDatabase = stageManager.getDatabase();
        this.stageManager = stageManager;
        this.stageManager.getStage().setOnCloseRequest(e -> {
            e.consume();
            this.onCloseRequest();
        });
    }


    public void onCloseRequest() {
        boolean answer = this.stageManager.getConfirmBox().display("Exit game", "Are you sure you want to exit?");
        if (answer){
            this.gameDatabase.saveHighScoreInfo();
            System.out.println("Data saved.");
            this.stageManager.getStage().close();
        }
    }
}

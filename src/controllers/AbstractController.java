package controllers;

import interfaces.*;

public abstract class AbstractController implements StageController {
    private StageManager stageManager;

    private Database gameDatabase;

    private MessageBox messageBox;

    private ConfirmBox confirmBox;

    protected ConfirmBox getConfirmBox() {
        return this.confirmBox;
    }

    protected MessageBox getMessageBox() {
        return this.messageBox;
    }

    protected StageManager getStageManager() {
        return this.stageManager;
    }

    protected Database getDatabase() {
        return this.gameDatabase;
    }

    @Override
    public void initialize(StageManager stageManager, Database database, MessageBox messageBox, ConfirmBox confirmBox) {
        this.stageManager = stageManager;
        this.gameDatabase = database;
        this.messageBox = messageBox;
        this.confirmBox = confirmBox;

        this.stageManager.getStage().setOnCloseRequest(e -> {
            e.consume();
            this.onCloseRequest();
        });
    }


    protected void onCloseRequest() {
        boolean isQuitting = this.confirmBox.display("Exit game", "Are you sure you want to exit?");
        if (isQuitting){
            this.gameDatabase.saveHighScoreInfo();
            this.stageManager.getStage().close();
        }
    }
}

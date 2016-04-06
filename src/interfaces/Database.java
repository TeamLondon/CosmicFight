package interfaces;

public interface Database {
    /*
    Player getPlayer();

    String getHighScore();
    */
    void clearHighScoreInfo();

    void saveHighScoreInfo();

    void loadHighScoreInfo();
}

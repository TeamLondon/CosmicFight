package interfaces;

public interface Database {
    Player getPlayer();

    void setPlayer(Player player);

    String getHighScore();

    void clearHighScoreInfo();

    void saveHighScoreInfo();

    void loadHighScoreInfo();

    void addHighScore(HighScore highScore);
}

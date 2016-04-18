package interfaces.core;

import interfaces.models.Player;
import models.contracts.HighScore;

public interface Database {
    Player getPlayer();

    void setPlayer(Player player);

    String getHighScore();

    void clearHighScoreInfo();

    void saveHighScoreInfo();

    void loadHighScoreInfo();

    void addHighScore(HighScore highScore);
}

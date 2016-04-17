package interfaces;

import java.io.Serializable;

public interface HighScore extends Serializable,Comparable<HighScore> {
    String getPlayerName();

    Integer getPlayerScore();

    void setPlayerScore(Integer playerScore);

}

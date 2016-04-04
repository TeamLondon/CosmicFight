package core;

import interfaces.Database;
import interfaces.HighScore;
import interfaces.Player;

import java.util.TreeSet;

public class GameDatabase implements Database{
    private Player currentPlayer;

    private TreeSet<HighScore> highScores;

    public GameDatabase(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.highScores = loadCurrentHighScores();
    }

    @Override
    public Player getPlayer() {
        return this.currentPlayer;
    }

    @Override
    public String getHighScore() {
        this.highScores.toString();
        return null;
    }

    private TreeSet<HighScore> loadCurrentHighScores() {
        return null;
    }
}

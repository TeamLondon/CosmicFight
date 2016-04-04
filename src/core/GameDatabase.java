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
        StringBuilder highScoreStringBuilder = new StringBuilder();
        int index = 1;
        for (HighScore highScore : highScores) {
            highScoreStringBuilder.append(String.format("%d. %s%n", index,highScore.toString()));
            index++;
        }

        return highScoreStringBuilder.toString();
    }

    private TreeSet<HighScore> loadCurrentHighScores() {
        // Use ObjectInputStream to read data and parse it here.
        // ToDo: Add into the interface another method to save data on game exit.
        return null;
    }
}

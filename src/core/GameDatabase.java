package core;

import interfaces.Database;
import interfaces.HighScore;
import interfaces.Player;

import java.io.*;
import java.util.TreeSet;

public class GameDatabase implements Database {
    private TreeSet<HighScore> highScores;

    private Player player;

    public GameDatabase() {
        loadHighScoreInfo();
    }

    @Override
    public void clearHighScoreInfo() {
        this.highScores.clear();
    }

    @Override
    public void saveHighScoreInfo() {
        String savePath = "res\\highscores.save";
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(savePath, false))) {
            objectOutputStream.writeObject(this.highScores);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void setPlayer(Player player) {
        if (player != null){
            this.player = player;
        }
    }

    public String getHighScore() {
        StringBuilder highScoreStringBuilder = new StringBuilder();
        int index = 1;
        for (HighScore highScore : highScores) {
            highScoreStringBuilder.append(String.format("%d. %s%n", index, highScore.toString()));
            index++;
        }

        return highScoreStringBuilder.toString();
    }

    @Override
    public void loadHighScoreInfo() {
        String loadPath = "res\\highscores.save";
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(loadPath))) {
            TreeSet<HighScore> loadedHighScores = (TreeSet<HighScore>) objectInputStream.readObject();
            if (loadedHighScores != null) {
                this.highScores = loadedHighScores;
            } else {
                this.highScores = new TreeSet<>();
            }
        } catch (IOException ioe) {
            // ToDo: pass exception further.
        } catch (ClassNotFoundException cnfe) {
            // ToDo: pass exception further.
        }

        if (this.highScores == null){
            this.highScores = new TreeSet<>();
        }
    }

    @Override
    public void addHighScore(HighScore highScore) {
        this.highScores.add(highScore);
    }
}
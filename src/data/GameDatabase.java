package data;

import interfaces.Database;
import interfaces.HighScore;
import interfaces.Player;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class GameDatabase implements Database {
    private Set<HighScore> highScores;

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
        List<HighScore> scores = this.highScores.stream().
                sorted((p1, p2) -> p2.getPlayerScore().compareTo(p1.getPlayerScore())).collect(Collectors.toList());
        StringBuilder highScoreStringBuilder = new StringBuilder();

        int index = 1;
        for (HighScore highScore : scores) {
            highScoreStringBuilder.append(String.format("%d. %s%n", index, highScore.toString()));
            index++;
        }

        return highScoreStringBuilder.toString();
    }

    @Override
    public void loadHighScoreInfo() {
        String loadPath = "res\\highscores.save";
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(loadPath))) {
            Set<HighScore> loadedHighScores = (HashSet<HighScore>) objectInputStream.readObject();
            if (loadedHighScores != null) {
                this.highScores = loadedHighScores;
                this.highScores = this.highScores.stream().sorted().collect(Collectors.toSet());
            } else {
                this.highScores = new HashSet<>();
            }
        } catch (IOException ioe) {
            // ToDo: pass exception further.
        } catch (ClassNotFoundException cnfe) {
            // ToDo: pass exception further.
        }

        if (this.highScores == null){
            this.highScores = new HashSet<>();
        }
    }

    @Override
    public void addHighScore(HighScore highScore) {
        this.highScores.add(highScore);
    }
}
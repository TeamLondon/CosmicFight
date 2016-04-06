package core;

import interfaces.Database;
import interfaces.HighScore;
import interfaces.Player;

import java.io.*;
import java.util.TreeSet;

public class GameDatabase implements Database {
    private TreeSet<HighScore> highScores;

    public GameDatabase() {
        loadHighScoreInfo();
    }

<<<<<<< HEAD
    @Override
    public void clearHighScoreInfo() {
        this.highScores.clear();
    }

    @Override
    public void saveHighScoreInfo() {
        String savePath = "res\\highscores.save";
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(savePath))) {
            objectOutputStream.writeObject(this.highScores);
        } catch (IOException ioe) {
            // ToDo: pass exception further.
=======
    public Player getPlayer() {
        return this.currentPlayer;
    }

    public String getHighScore() {
        StringBuilder highScoreStringBuilder = new StringBuilder();
        int index = 1;
        for (HighScore highScore : highScores) {
            highScoreStringBuilder.append(String.format("%d. %s%n", index,highScore.toString()));
            index++;
>>>>>>> 8700c62d1fee6bac47a33f47b45971cba22ea99a
        }
    }

    @Override
    public void loadHighScoreInfo() {
        String loadPath = "res\\highscores.save";
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(loadPath))) {
            TreeSet<HighScore> loadedHighScores = (TreeSet<HighScore>) objectInputStream.readObject();
            if (loadedHighScores != null){
                this.highScores = loadedHighScores;
            } else{
                this.highScores = new TreeSet<>();
            }
        } catch (IOException ioe) {
            // ToDo: pass exception further.
        } catch (ClassNotFoundException cnfe){
            // ToDo: pass exception further.
        }
    }
}

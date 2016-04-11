package utilityModels;

import interfaces.HighScore;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameHighScore implements HighScore {
    private String playerName;
    private Integer playerScore;

    public GameHighScore(String playerName, Integer playerScore) {
        this.setPlayerName(playerName);
        this.setPlayerScore(playerScore);
    }

    @Override
    public String getPlayerName() {
        return this.playerName;
    }

    @Override
    public Integer getPlayerScore() {
        return this.playerScore;
    }

    @Override
    public String toString() {
        String stringView = String.format("%s - %d", this.getPlayerName(), this.getPlayerScore());
        return stringView;
    }

    @Override
    public int compareTo(HighScore other) {
        int compareResult = other.getPlayerScore().compareTo(this.getPlayerScore());
        return compareResult;
    }

    private void setPlayerName(String playerName) {
        Pattern validationPattern = Pattern.compile("[\\w]{5,16}");
        Matcher validationMatcher = validationPattern.matcher(playerName);
        if (validationMatcher.find()){
            this.playerName = playerName;
        } else{
            throw new InvalidParameterException("Not valid player name!");
        }
    }

    private void setPlayerScore(Integer playerScore) {
        if (playerScore > 0){
            this.playerScore = playerScore;
        }else{
            throw new InvalidParameterException("Not valid score value!");
        }
    }
}

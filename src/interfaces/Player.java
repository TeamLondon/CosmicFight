package interfaces;

public interface Player extends AttackableUnit {
    String getName();

    void setName(String name);

    HighScore getHighScore();
}

package interfaces;

public interface Player extends DynamicGameObject, Unit {
    String getName();

    double getFireRate();

    void setName(String name);

    HighScore getHighScore();
}

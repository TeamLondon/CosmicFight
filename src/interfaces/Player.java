package interfaces;

public interface Player extends AttackableUnit, DynamicGameObject {
    String getName();
     double getFireRate();

    void setName(String name);

    HighScore getHighScore();
}

package interfaces;

public interface Player extends DynamicGameObject, Unit {
    String getName();

     double getBulletCooldown();

     double getBombCooldown();

    void resetBullet();

    void resetBomb();

    void setName(String name);

    HighScore getHighScore();
}

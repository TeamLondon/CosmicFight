package interfaces;

import enums.Attacks;

public interface Player extends DynamicGameObject, Unit {
    String getName();

    void addAttack(Attacks attack);

    double getBulletCooldown();

    double getBombCooldown();

    void resetBullet();

    void resetBomb();

    void setName(String name);

    HighScore getHighScore();

    void changeAttack();

    Attacks getCurrentAttack();

}

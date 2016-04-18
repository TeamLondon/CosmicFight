package interfaces;

import enums.Attacks;

import java.util.List;

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

    void addScore(int additionalScore);

    Attacks getCurrentAttack();

    List<Attacks> getAttacks();
}

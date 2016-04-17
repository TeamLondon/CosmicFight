package interfaces;

import enums.Attacks;

public interface Player extends DynamicGameObject, Unit {
    String getName();

<<<<<<< HEAD
    double getFireRate();
=======
     double getBulletCooldown();

     double getBombCooldown();

    void resetBullet();

    void resetBomb();
>>>>>>> 234e0378a6964639bd0bf02b1c5b2670e66248fb

    void setName(String name);

    HighScore getHighScore();

    void changeAttack();
    
    Attacks getCurrentAttack();

}

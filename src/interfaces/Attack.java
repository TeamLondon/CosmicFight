package interfaces;

public interface Attack extends DynamicGameObject {
    double getDamage();

    void applyAttack(Unit unitToAttack);
}

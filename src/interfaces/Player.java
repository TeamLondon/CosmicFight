package interfaces;

public interface Player extends DynamicGameObject, AttackableUnit {
    String getName();

    void setName();

    Integer getScore();
}

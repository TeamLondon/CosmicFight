package interfaces;

public interface Player extends AttackableUnit {
    String getName();
    void setName(String name);
    Integer getScore();
}

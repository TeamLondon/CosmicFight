package interfaces;

import interfaces.models.DynamicGameObject;

public interface Spawner {
    DynamicGameObject spawn(Double currentDistance);

    void setDistance(Double distance);
}

package interfaces;

import interfaces.models.Player;
import javafx.scene.Scene;

public interface Engine {
    Player getCurrentPlayer();

    void run();

    void draw();

    void update();

    Scene unloadContent();

    // Plausible addition of fields and methods.
}

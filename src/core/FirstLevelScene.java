package core;

import javafx.beans.NamedArg;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class FirstLevelScene extends Scene{
    private Scene scene;

    public FirstLevelScene(@NamedArg("root") Parent root, Scene scene) {
        super(root);
    }



}

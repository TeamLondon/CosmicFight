package controllers;

import interfaces.SceneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;

public class SceneManager extends StackPane {
    private HashMap<String, Node> scenes = new HashMap<>();

    public void addScene(String id, Node screen) {
        this.scenes.put(id, screen);
    }

    public Node getScene(String id) {
        return this.scenes.get(id);
    }

    public void loadScene(String name, String resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = myLoader.load();
            SceneController myScreenController = myLoader.getController();
            myScreenController.setSceneManager(this);
            this.addScene(name, loadScreen);
        } catch (IOException ioe){
             // AlertBox.show("Scene was not loaded.");
        }

    }

    public void setScene(final String name) {
        if (this.scenes.get(name) != null) {
            // If there is more than one screen removed the displayed one and add the new one.
            if (!this.getChildren().isEmpty()) {
                //remove the displayed screen
                this.getChildren().remove(0);

                //add the screen
                this.getChildren().add(0, this.scenes.get(name));
            } else {
                // No screens are displayed - directly add it.
                getChildren().add(this.scenes.get(name));
            }
        } else {
            // Alert.show("Scene hasn't been loaded");
        }
    }

    public void unloadScene(String name) {
        if (this.scenes.remove(name) == null) {
            System.out.println("Screen didn't exist");
        }
    }
}

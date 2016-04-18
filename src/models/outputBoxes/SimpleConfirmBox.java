package models.outputBoxes;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import models.contracts.ConfirmBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SimpleConfirmBox implements ConfirmBox{
    private boolean answer;
    public boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnMouseClicked(e -> {
            this.answer = true;
            window.close();
        });

        noButton.setOnMouseClicked(e -> {
            this.answer = false;
            window.close();
        });


        yesButton.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)){
                this.answer = true;
                window.close();
            }
        });
        noButton.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)){
                this.answer = false;
                window.close();
            }
        });

        VBox layout = new VBox(10);

        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return this.answer;
    }
}

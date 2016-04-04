package interfaces;

import javafx.scene.image.Image;

public interface Visualizable {
    Image getImage();
    void setImage(Image image);
    void setImage(String imagePath);
}

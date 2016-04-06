package core;

import gameObjects.AbstractDynamicGameObject;
import gameObjects.AbstractStaticGameObject;
import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;

public class ObjectHandler {
    LinkedList<AbstractDynamicGameObject> dynamicObjects = new LinkedList<AbstractDynamicGameObject>();
    LinkedList<AbstractStaticGameObject> staticObjects = new LinkedList<AbstractStaticGameObject>();

    public void update() {
        //This loop goes through all the objects in the game
        for (int i = 0; i < dynamicObjects.size(); i++) {
            //Gets them and saves their reference to the variable tempObject
            AbstractDynamicGameObject tempObject = dynamicObjects.get(i);

            //And initiates their update method so their fields get updated every time the controller.update() method gets initiated
            tempObject.update();
        }

        for (int i = 0; i < staticObjects.size(); i++) {
            //Gets them and saves their reference to the variable tempObject
            AbstractStaticGameObject tempObject = staticObjects.get(i);

            //And initiates their update method so their fields get updated every time the controller.update() method gets initiated
            tempObject.update();
        }
    }
    public void draw(GraphicsContext gc) {
        //This loop goes through all the objects in the game
        for (int i = 0; i < dynamicObjects.size(); i++) {
            //Gets them and saves their reference to the variable tempObject
            AbstractDynamicGameObject tempObject = dynamicObjects.get(i);

            //And invokes their draw() method so any new changes on their rendering (graphics) get taken into account
            tempObject.draw(gc);
        }

        for (int i = 0; i < staticObjects.size(); i++) {
            //Gets them and saves their reference to the variable tempObject
            AbstractStaticGameObject tempObject = staticObjects.get(i);

            //And invokes their draw() method so any new changes on their rendering (graphics) get taken into account
            tempObject.draw(gc);
        }
    }

    //These methods have to be used when adding new objects to the game or else they won't change color,speed etc..
    //This method adds an object to the handler list
    public void addDynamicObject(AbstractDynamicGameObject object) {
        this.dynamicObjects.add(object);
    }
    public void addStaticObject(AbstractStaticGameObject object) {
        this.staticObjects.add(object);
    }

    //These methods remove an object from the object list in the controller class
    //When an object is removed from the list, his update and draw methods are no longer called every frame
    public void removeDynamicObject(AbstractDynamicGameObject object) {
        this.dynamicObjects.remove(object);
    }
    public void removeStaticObject(AbstractStaticGameObject object) {
        this.staticObjects.remove(object);
    }
}

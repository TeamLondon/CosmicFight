package core;

import gameObjects.AbstractDynamicGameObject;
import gameObjects.AbstractStaticGameObject;
import gameObjects.dynamicGameObjects.attacks.Bullet;
import gameObjects.dynamicGameObjects.player.GamePlayer;
import gameObjects.dynamicGameObjects.rocks.RoundAsteroid;
import javafx.scene.canvas.GraphicsContext;
import sample.Main;

import java.util.LinkedList;

public class ObjectHandler {
    public LinkedList<AbstractDynamicGameObject> dynamicObjects = new LinkedList<AbstractDynamicGameObject>();
    LinkedList<AbstractStaticGameObject> staticObjects = new LinkedList<AbstractStaticGameObject>();

    public void update() {
        //This loop goes through all the objects in the game
        for (int i = 0; i < dynamicObjects.size(); i++) {
            //Gets them and saves their reference to the variable tempObject
            AbstractDynamicGameObject tempObject = dynamicObjects.get(i);
            /////////////////////////////////////////////Collision testing///////////////////////////////////////////////////////
            //If the current object is an instance of the bullet lass
            if (tempObject instanceof Bullet) {
                //Check if it is outside of the map
                if (tempObject.getY() < 0) {
                    //If yes - remove it
                    this.removeDynamicObject(tempObject);
                    tempObject = null;
                    //System.gc();
                    continue;
                }

                //Iterate through all game objects again
                for (int j = 0; j < this.dynamicObjects.size(); j++) {
                    AbstractDynamicGameObject currentTempObject = this.dynamicObjects.get(j);

                    //Check if the current object is the player or another bullet
                    if (currentTempObject instanceof GamePlayer || currentTempObject instanceof Bullet) {
                        //If yes - continue..
                        continue;
                    }else {
                        //Else check if it is intersecting with the bullet
                        if (tempObject.isIntersecting(currentTempObject)) {
                            //If yes subtract 10 from the total hitPoints of this object
                            currentTempObject.setHitPoints(currentTempObject.getHitPoints() - 10);
                            //Then destroy this bullet and initiate its death animation
                            tempObject.initiateDestroyAnimation();
                            this.removeDynamicObject(tempObject);
                            //tempObject = null;
                            //Then check if the current object currently has 0 health
                            if (currentTempObject.getHitPoints() <= 0) {
                                //If yes - initiate its death animation and remove it from the list
                                currentTempObject.initiateDestroyAnimation();
                                this.removeDynamicObject(currentTempObject);
                                currentTempObject = null;
                            }
                        }
                    }
                }
            }
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

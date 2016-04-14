package core;

import gameObjects.dynamicGameObjects.attacks.Bullet;
import gameObjects.dynamicGameObjects.player.GamePlayer;
import interfaces.DynamicGameObject;
import interfaces.StaticGameObject;
import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;

public class ObjectHandler {
    public LinkedList<DynamicGameObject> dynamicObjects = new LinkedList<DynamicGameObject>();
    public LinkedList<StaticGameObject> staticObjects = new LinkedList<StaticGameObject>();
    private GamePlayer player;

    public ObjectHandler(GamePlayer player) {
        this.player = player;
    }

    public void update() {
        //This loop goes through all the objects in the game
        for (int i = 0; i < dynamicObjects.size(); i++) {
            //Gets them and saves their reference to the variable tempObject
            DynamicGameObject tempObject = dynamicObjects.get(i);
            /////////////////////////////////////////////Collision testing///////////////////////////////////////////////////////
            //If the current object is an instance of the bullet class
            if (tempObject instanceof Bullet) {
                //Iterate through all game objects again
                for (int j = 0; j < this.dynamicObjects.size(); j++) {
                    DynamicGameObject currentTempObject = this.dynamicObjects.get(j);

                    //Check if the current object is the player or another bullet
                    if (currentTempObject instanceof GamePlayer || currentTempObject instanceof Bullet) {
                        //If yes - continue..
                        continue;
                    }else {
                        //Else check if it is intersecting with the bullet
                        if (tempObject.isIntersecting(currentTempObject)) {
                            //If yes subtract 10 from the total hitPoints of this object
                            currentTempObject.applyDamage(player.getDamage());
                            //Then destroy this bullet and initiate its death animation
                            tempObject.initiateDestroyAnimation();
                            this.removeDynamicObject(tempObject);
                            //tempObject = null;
                            //Then check if the current object currently has 0 health
                            if (currentTempObject.getHitPoints() <= 0) {
                                //If yes - initiate its death animation and remove it from the list
                                currentTempObject.initiateDestroyAnimation();
                                removeDynamicObject(currentTempObject);
                                currentTempObject = null;
                            }
                        }
                    }
                }
            }
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //Checks if the object is outside of the map
<<<<<<< HEAD
            if (tempObject.getY() > 600 || tempObject.getY() < 0) {
=======
            if (tempObject.getY() < 0 || tempObject.getY() > 600) {
>>>>>>> 12831cd3b7601e02cf9dad2673c909b0c9a9af95
                //If yes - remove it
                removeDynamicObject(tempObject);
                tempObject = null;
                //System.gc();
                continue;
            }
            //And initiates their update method so their fields get updated every time the controller.update() method gets initiated
            tempObject.update();
        }

        for (int i = 0; i < staticObjects.size(); i++) {
            //Gets them and saves their reference to the variable tempObject
            StaticGameObject tempObject = staticObjects.get(i);

            //And initiates their update method so their fields get updated every time the controller.update() method gets initiated
            tempObject.update();
        }
    }
    public void draw(GraphicsContext gc) {
        //This loop goes through all the objects in the game
        for (int i = 0; i < dynamicObjects.size(); i++) {
            //Gets them and saves their reference to the variable tempObject
            DynamicGameObject tempObject = dynamicObjects.get(i);

            //And invokes their draw() method so any new changes on their rendering (graphics) get taken into account
            tempObject.draw(gc);
        }

        for (int i = 0; i < staticObjects.size(); i++) {
            //Gets them and saves their reference to the variable tempObject
            StaticGameObject tempObject = staticObjects.get(i);

            //And invokes their draw() method so any new changes on their rendering (graphics) get taken into account
            tempObject.draw(gc);
        }
    }

    //These methods have to be used when adding new objects to the game or else they won't change color,speed etc..
    //This method adds an object to the handler list
    public void addDynamicObject(DynamicGameObject object) {
        this.dynamicObjects.add(object);
    }
    public void addStaticObject(StaticGameObject object) {
        this.staticObjects.add(object);
    }

    //These methods remove an object from the object list in the controller class
    //When an object is removed from the list, his update and draw methods are no longer called every frame
    public void removeDynamicObject(DynamicGameObject object) {
        this.dynamicObjects.remove(object);
    }
    public void removeStaticObject(StaticGameObject object) {
        this.staticObjects.remove(object);
    }
}

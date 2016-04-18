package models.handlers;

import gameObjects.dynamicGameObjects.attacks.BossBullet;
import gameObjects.dynamicGameObjects.enemies.FirstLevelBoss;
import gameObjects.dynamicGameObjects.player.GamePlayer;
import interfaces.*;
import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;

public class ObjectHandler {
    public LinkedList<DynamicGameObject> dynamicObjects = new LinkedList<DynamicGameObject>();
    private Player player;

    public ObjectHandler(Player player) {
        this.player = player;
        this.addDynamicObject(player);
    }

    public ObjectHandler() {
    }

    public void setPlayer(Player player){
        this.player = player;
        this.addDynamicObject(player);
    }
    public void update() {
        //This loop goes through all the objects in the game
        for (int i = 0; i < dynamicObjects.size(); i++) {
            //Gets them and saves their reference to the variable tempObject
            DynamicGameObject tempObject = dynamicObjects.get(i);

            //Checks if the object is outside of the map
            if (tempObject.getY() < 0 || tempObject.getY() > 600) {
                //If yes - remove it
                removeDynamicObject(tempObject);
                tempObject = null;
                //System.gc();
                continue;
            }
            /////////////////////////////////////////////Collision testing///////////////////////////////////////////////////////
            //If the current object is an instance of the Ammo interface
            if (tempObject instanceof Ammo) {
                //Iterate through all game objects again
                for (int j = 0; j < this.dynamicObjects.size(); j++) {
                    DynamicGameObject currentTempObject = this.dynamicObjects.get(j);

                    //Check if the current object is the player or another bullet
                    if (currentTempObject instanceof GamePlayer || currentTempObject instanceof Ammo || currentTempObject instanceof BossBullet) {
                        //If yes - continue..
                        continue;
                    }else {
                        //Else check if it is intersecting with the bullet
                        if (tempObject.isIntersecting(currentTempObject)) {
                            //If yes subtract 10 from the total hitPoints of this object
                            currentTempObject.applyDamage(((Ammo) tempObject).getDamage());
                            //Then destroy this bullet and initiate its death animation
                            this.removeDynamicObject(tempObject);
                            //tempObject = null;
                            //Then check if the current object currently has 0 health
                            if (currentTempObject.getHitPoints() <= 0) {
                                //If yes - initiate its death animation and remove it from the list
                                player.addScore(currentTempObject instanceof Enemy ? ((Enemy) currentTempObject).getRewardPoints(): 1);
                                removeDynamicObject(currentTempObject);
                                currentTempObject = null;
                            }
                        }
                    }
                }
            }else {
                if (!(tempObject instanceof GamePlayer)) {
                    if (tempObject.isIntersecting(this.player)) {
                        if (tempObject instanceof Bonus) {
                            ((Bonus)tempObject).applyBonus(this.player);
                            this.removeDynamicObject(tempObject);
                        } else {
                            if (tempObject instanceof FirstLevelBoss) {
                                player.applyDamage(1);
                            }else {
                                player.applyDamage(5);
                                this.removeDynamicObject(tempObject);
                            }
                        }
                    }
                }
            }
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
    }

    //These methods have to be used when adding new objects to the game or else they won't change color,speed etc..
    //This method adds an object to the handler list
    public void addDynamicObject(DynamicGameObject object) {
        this.dynamicObjects.add(object);
    }
    //These methods remove an object from the object list in the controller class
    //When an object is removed from the list, his update and draw methods are no longer called every frame
    public void removeDynamicObject(DynamicGameObject object) {
        this.dynamicObjects.remove(object);
    }
}

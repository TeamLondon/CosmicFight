package models.handlers;

import gameObjects.dynamicGameObjects.attacks.BossBullet;
import gameObjects.dynamicGameObjects.enemies.FirstLevelBoss;
import gameObjects.dynamicGameObjects.player.GamePlayer;
import interfaces.models.*;
import javafx.scene.canvas.GraphicsContext;
import models.contracts.Bonus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ObjectHandler {
    public LinkedList<DynamicGameObject> dynamicObjects = new LinkedList<>();
    private List<DynamicGameObject> intersecting = new ArrayList<>();
    private Player player;

    public ObjectHandler() {
    }

    public void setPlayer(Player player){
        this.player = player;
        this.addDynamicObject(player);
    }

    public void clear(){
        this.dynamicObjects.clear();
    }

    public void update() {
        for (int i = 0; i < dynamicObjects.size(); i++) {
            DynamicGameObject tempObject = dynamicObjects.get(i);
            //Checks if the object is outside of the map
            if (tempObject.getY() < 0 || tempObject.getY() > 600 || tempObject.getHitPoints() <= 0) {
                removeDynamicObject(tempObject);
            } else {
                if (tempObject instanceof GamePlayer) {
                    fillIntersectionListPlayer(tempObject);
                    handleCollisionWithPlayer();
                }else if (tempObject instanceof Attack) {
                    fillIntersectionListAttacks(tempObject);
                    handleCollisionWithAttack(tempObject);
                }
                intersecting.clear();
                tempObject.update();
            }
        }
    }

    private void fillIntersectionListPlayer(DynamicGameObject tempObject) {
        dynamicObjects.stream().parallel().forEach(e -> {
            if (e.isIntersecting(tempObject) && !(e instanceof GamePlayer) && !(e instanceof Attack)) {
                this.intersecting.add(e);
            }
        });
    }
    private void fillIntersectionListAttacks(DynamicGameObject tempObject) {
        dynamicObjects.stream().parallel().forEach(e -> {
            if (e.isIntersecting(tempObject) && !(e instanceof Attack)) {
                this.intersecting.add(e);
            }
        });
    }

    public void draw(GraphicsContext gc) {
        //This loop goes through all the objects in the game
        for (DynamicGameObject tempObject : dynamicObjects) {
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

//    private void handleCollision(DynamicGameObject tempObject) {
//        if (tempObject instanceof Attack) {
//            LinkedList<DynamicGameObject> dynamicObjects1 = this.dynamicObjects;
//            for (int i = 0; i < dynamicObjects1.size(); i++) {
//                DynamicGameObject currentTempObject = dynamicObjects1.get(i);
//                // If player is attacked.
//                if (currentTempObject instanceof Player && tempObject instanceof EnemyAttack) {
//                    handleEnemyAttack(tempObject, currentTempObject);
//                } else if (!(currentTempObject instanceof Player) && !(currentTempObject instanceof Attack)) {
//                    // If player is attacking.
//                    handlePlayerAttack(tempObject, currentTempObject);
//                }
//            }
//        }else {
//            if (!(tempObject instanceof GamePlayer)) {
//                handleCollisionWithPlayer(tempObject);
//            }
//        }
//    }
//
//    private void handleEnemyAttack(DynamicGameObject tempObject, DynamicGameObject currentTempObject) {
//        if (tempObject.isIntersecting(currentTempObject)) {
//            currentTempObject.applyDamage(((Attack) tempObject).getDamage());
//            this.removeDynamicObject(tempObject);
//        }
//    }
//
//    private void handlePlayerAttack(DynamicGameObject tempObject, DynamicGameObject currentTempObject) {
//        //Else check if it is intersecting with the bullet
//        if (tempObject.isIntersecting(currentTempObject)) {
//            //If yes subtract 10 from the total hitPoints of this object
//            currentTempObject.applyDamage(((Attack) tempObject).getDamage());
//            //Then destroy this bullet and initiate its death animation
//            this.removeDynamicObject(tempObject);
//            //Then check if the current object currently has 0 health
//            if (currentTempObject.getHitPoints() <= 0) {
//                //If yes - initiate its death animation and remove it from the list
//                this.player.addScore(currentTempObject instanceof Enemy ? ((Enemy) currentTempObject).getRewardPoints(): 1);
//                removeDynamicObject(currentTempObject);
//            }
//        }
//    }

    private void handleCollisionWithPlayer() {
        for (int j = 0; j < intersecting.size(); j++) {
            DynamicGameObject innerObject = intersecting.get(j);
            if (innerObject instanceof Bonus) {
                ((Bonus)innerObject).applyBonus(this.player);
                this.removeDynamicObject(innerObject);
            } else {
                if (innerObject instanceof FirstLevelBoss) {
                    player.applyDamage(1);
                }else {
                    player.applyDamage(5);
                    this.removeDynamicObject(innerObject);
                }
            }
        }
    }

    private void handleCollisionWithAttack(DynamicGameObject tempObject) {
        for (int j = 0; j < intersecting.size(); j++) {
            DynamicGameObject innerObject = intersecting.get(j);
            if (tempObject instanceof BossBullet) {
                if (innerObject instanceof GamePlayer) {
                    player.applyDamage(((BossBullet) tempObject).getDamage());
                    removeDynamicObject(tempObject);
                }
            }else {
                if (!(innerObject instanceof GamePlayer)) {
                    innerObject.applyDamage(((Attack) tempObject).getDamage());
                    //If you activate this IF STATEMENT and remove the new .addScore the scoring will work as before
                    //if (innerObject.getHitPoints() <= 0) {
                        //this.player.addScore(innerObject instanceof Enemy ? ((Enemy) innerObject).getRewardPoints(): 1);
                        this.player.addScore(innerObject instanceof Enemy ? 2: 1);
                    //}
                    removeDynamicObject(tempObject);
                }
            }
        }
    }
}

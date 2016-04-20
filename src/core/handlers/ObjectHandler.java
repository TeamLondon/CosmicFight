package core.handlers;

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

    public void setPlayer(Player player) {
        this.player = player;
        this.addDynamicObject(player);
    }

    public void clear() {
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
                } else if (tempObject instanceof Attack) {
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

    private void handleCollisionWithPlayer() {
        for (int j = 0; j < intersecting.size(); j++) {
            DynamicGameObject innerObject = intersecting.get(j);
            if (innerObject instanceof Bonus) {
                ((Bonus) innerObject).applyBonus(this.player);
                this.removeDynamicObject(innerObject);
            } else {
                if (innerObject instanceof FirstLevelBoss) {
                    player.applyDamage(1);
                } else {
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
            } else {
                if (!(innerObject instanceof GamePlayer)) {
                    innerObject.applyDamage(((Attack) tempObject).getDamage());
                    if (!(innerObject instanceof Bonus)) {
                        if (innerObject.getHitPoints() <= 0) {
                            this.player.addScore(innerObject instanceof Enemy ? ((Enemy) innerObject).getRewardPoints() : 1);
                        } else {
                            this.player.addScore(innerObject instanceof Enemy ? 2 : 1);
                        }

                        removeDynamicObject(tempObject);
                    }
                }
            }
        }
    }
}

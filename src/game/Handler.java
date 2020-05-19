/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.Game.STATE;
import static game.Game.gameState;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Zachary Bianucci
 *
 * maintain/update/render all of the objects in our room since we have more than
 * one object in the game, we will need to handle/process each one of those
 * objects loops through all of the objects in our game, individually updates
 * them and render them to the screen
 */
public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            //function within the linked list that will allow us to get the ID
            //of what object we are at, works like an array, since it is a for loop
            //it will get everything
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            //see notes on this in tick() method
            try{
            GameObject tempObject = object.get(i);

            tempObject.render(g);}catch(Exception e){
                System.out.println("error");
            }
        }
    }

    public void clearEnemies() {/*
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            if (tempObject.getID() == ID.Player) {

                object.clear();
                addObject(new Player((int) tempObject.getX(), (int) tempObject.getY(), ID.Player, this));
                //i--;
            }
        }*/
        for(int i =0; i<this.object.size();i++){
            GameObject tempObject = this.object.get(i);
            if(tempObject.getID()!=ID.Player||(tempObject.getID()==ID.Player&&(gameState==STATE.End||gameState==STATE.Win))){
                this.removeObject(tempObject);
                i--;
            }
        }
    }

    //handles adding objects in the list
    public void addObject(GameObject object) {
        this.object.add(object);
    }

    //handles removing objects in the list
    public void removeObject(GameObject object) {
        this.object.remove(object);
    }
}

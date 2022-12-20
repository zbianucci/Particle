package game;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Zachary Bianucci this GameObject will be what we refer to as all the
 * game objects player object and enemy objects are considered GameObject
 * everything in our game will be GameObject, they will inherit all of the
 * functions we put into the GameObject
 */
public abstract class GameObject {

    //objects can use these objects
    protected float x, y;
    protected ID id;
    //control the speed in the x direction, speed in y direction
    protected float velX, velY;

    //constructor sets the variables
    public GameObject(float x, float y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    //going to need it for our Player Class
    public abstract void tick();

    public abstract void render(Graphics g);
    
    public abstract Rectangle getBounds();

    //player class will have access to all of these setters and getters
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setID(ID id) {
        this.id = id;
    }

    public ID getID() {
        return id;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }
}

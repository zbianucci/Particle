/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Zachary Bianucci
 */
public class StraightBullets extends GameObject {

    private Handler handler;
    private GameObject player;
    private float speedX, speedY;
    private Color color;
    private int timer = 120;
    private int quadrant;
    private boolean angle, horizontal, verticle;

    public StraightBullets(int x, int y, boolean horizontal, boolean verticle, ID id, Handler handler, int speedX, int speedY, Color color, boolean angle) {
        super(x, y, id);
        //first chunk of if/else statements are for vert/horz bullets
        this.angle = angle;
        this.verticle = verticle;
        this.horizontal = horizontal;
        if (x < 0 && y > 0 && y < Game.HEIGHT) {
            quadrant = 1;
        } else if (x < Game.HEIGHT && x > 0 && y > Game.HEIGHT) {
            quadrant = 2;
        } else if (x > Game.HEIGHT && y > 0 && y < Game.HEIGHT) {
            quadrant = 3;
        } else if (x > 0 && x < Game.HEIGHT && y < 0) {
            quadrant = 4;
        } //angled quadrants
        //quad 5 - top left corner
        else if (x < 0 && y < 0) {
            quadrant = 5;
        } else if (x < 0 && y > Game.HEIGHT) {
            quadrant = 6;
        } else if (x > Game.WIDTH && y > Game.HEIGHT) {
            quadrant = 7;
        } else if (x > Game.WIDTH && y < 0) {
            quadrant = 8;
        }

        this.color = color;
        this.speedX = speedX;
        this.speedY = speedY;
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    @Override
    public void tick() {
        if (timer < 0 && (x > Game.WIDTH + 10 || x < -10 || y > Game.HEIGHT + 10 || y < -10)) {
            handler.removeObject(this);
        } else {
            timer--;
        }
        //quadrant 1 - left of screen, 2 - below screen, 3- right of screen, 4 - above screen
        switch (quadrant) {
            case 1:
                velX = speedX;
                velY = 0;
                break;
            case 2:
                velY = -speedY;
                velX = 0;
                break;
            case 3:
                velX = -speedX;
                velY = 0;
                break;
            case 4:
                velY = speedY;
                velX = 0;
                break;
            case 5:
                velX = speedX;
                velY = speedY;
                break;
            case 6:
                velX = speedX;
                velY = -speedY;
                break;
            case 7:
                velX = -speedX;
                velY = -speedY;
                break;
            case 8:
                velX = -speedX;
                velY = speedY;
                break;
            default:
                break;
        }

        x += velX;
        y += velY;

        handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.05f, handler));
    }

    @Override
    //creates the look of the enemy
    public void render(Graphics g) {

        g.setColor(color);
        g.fillRect((int) x, (int) y, 16, 16);
    }

}

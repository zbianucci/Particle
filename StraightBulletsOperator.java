/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Zachary Bianucci
 */
public class StraightBulletsOperator extends GameObject {

    private Random r = new Random();
    private Handler handler;
    private GameObject player;
    private int speedX, speedY, xPos, yPos,xPosA,yPosA, intensity, timer = 400, timer2 = 100, angleQuad;
    private boolean horizontal, verticle, angle;

    //this is technically an object in the game but will operate the Straight bullets
    //instead without actually having a physcial body
    public StraightBulletsOperator(int x, int y, ID id, Handler handler, int intensity, boolean horizontal, boolean verticle, boolean angle, int angleQuad) {
        super(x, y, id);
        //angleQuad - 0 is none, 1,2,3,4 are single quadrants, 5 is 1 and 3, 6 is 2 and 4, 7 is all 4
        this.angleQuad = angleQuad;
        this.verticle = verticle;
        this.horizontal = horizontal;
        this.handler = handler;
        this.intensity = intensity;
        this.angle = angle;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 0, 0);
    }

    @Override
    public void tick() {
        if (timer > 0) {
            timer--;
            
            if (angle && angleQuad == 5) {
                switch (r.nextInt(2) + 1) {
                    //quad 1
                    case 1:
                        xPos = -(r.nextInt(500) + 1);
                        yPos = -(r.nextInt(500) + 1);
                        break;
                    //quad 3
                    case 2:
                        xPos = r.nextInt(500) + 60 + Game.WIDTH;
                        yPos = r.nextInt(500) + 60 + Game.HEIGHT;
                        break;
                    default:
                        break;
                }
            } else if (angle && angleQuad == 6) {
                switch (r.nextInt(2) + 1) {
                    //quad 2
                    case 1:
                        xPos = -(r.nextInt(500) + 1);
                        yPos = r.nextInt(500) + Game.HEIGHT + 60;
                        break;
                    //quad 4
                    case 2:
                        xPos = r.nextInt(500) + 60 + Game.WIDTH;
                        yPos = -(r.nextInt(500) + 1);
                        break;
                    default:
                        break;
                }
            } else if (angle && angleQuad == 7) {
                switch (r.nextInt(4) + 1) {
                    //quad 1
                    case 1:
                        xPos = -(r.nextInt(500) + 1);
                        yPos = -(r.nextInt(500) + 1);
                        break;
                    case 2:
                        xPos = -(r.nextInt(500) + 1);
                        yPos = r.nextInt(500) + 60 + Game.HEIGHT;
                        break;
                    case 3:
                        xPos = r.nextInt(500) + 60 + Game.WIDTH;
                        yPos = r.nextInt(500) + 60 + Game.HEIGHT;
                        break;
                    case 4:
                        xPos = r.nextInt(500) + 60 + Game.WIDTH;
                        yPos = -(r.nextInt(500) + 1);
                        break;
                    default:
                        break;
                }
            }
            if (r.nextInt(intensity) == 0&&angle) {
                handler.addObject(new StraightBullets(xPos, yPos,horizontal,verticle, ID.BasicEnemy, handler, 5, 5, Color.pink, angle));
            }
            if (horizontal && verticle) {
                switch (r.nextInt(4) + 1) {
                    //cases are the corresponding quadrants
                    case 1:
                        xPos = -20;
                        yPos = r.nextInt(Game.HEIGHT);
                        break;
                    case 2:
                        xPos = r.nextInt(Game.WIDTH);
                        yPos = Game.HEIGHT + 60;
                        break;
                    case 3:
                        xPos = Game.WIDTH + 60;
                        yPos = r.nextInt(Game.HEIGHT);
                        break;
                    case 4:
                        xPos = r.nextInt(Game.WIDTH);
                        yPos = -20;
                        break;
                    default:
                        break;
                }
            } else if (horizontal && !verticle) {
                switch (r.nextInt(2) + 1) {
                    //cases are the corresponding quadrants
                    case 1:
                        xPos = -20;
                        yPos = r.nextInt(Game.HEIGHT);
                        break;
                    case 2:
                        xPos = Game.WIDTH + 60;
                        yPos = r.nextInt(Game.HEIGHT);
                        break;
                    default:
                        break;
                }
            } else if (verticle && !horizontal) {
                switch (r.nextInt(2) + 1) {
                    //cases are the corresponding quadrants
                    case 1:
                        xPos = r.nextInt(Game.WIDTH);
                        yPos = Game.HEIGHT + 60;
                        break;
                    case 2:
                        xPos = r.nextInt(Game.WIDTH);
                        yPos = -20;
                        break;
                    default:
                        break;
                }
            }
            if (r.nextInt(intensity) == 0&&(horizontal||verticle)) {
                handler.addObject(new StraightBullets(xPos, yPos,horizontal,verticle, ID.BasicEnemy, handler, 5, 5, Color.pink, angle));
            }
        } else {
            if (timer2 > 0) {
                timer2--;
            } else {
                handler.removeObject(this);
            }
        }

    }

    @Override
    //creates the look of the enemy
    public void render(Graphics g
    ) {
        g.setColor(new Color(0, 128, 0));
        g.fillRect((int) x, (int) y, 0, 0);
    }

}

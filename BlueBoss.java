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
public class BlueBoss extends GameObject {

    private Handler handler;
    private Color color;
    private int xPos, yPos, intensity, timer = 300, timer2 = 1950;
    private Random r = new Random();

    public BlueBoss(int x, int y, ID id, Handler handler, int speedX, int speedY, Color color, int intensity) {
        super(x, y, id);
        velX = speedX;
        velY = speedY;
        this.color = color;
        this.handler = handler;
        this.intensity = intensity;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 96, 96);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if (timer < 0) {
            if (timer2 > 0) {
                timer2--;
                if (y <= 0 || y >= Game.HEIGHT - 120) {
                    velY *= -1;
                }

                if (x <= 0 || x >= Game.WIDTH - 100) {
                    velX *= -1;
                }
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
                if (r.nextInt(intensity) == 0) {
                    handler.addObject(new BlueBossBullet(xPos, yPos, id.SmartEnemy, handler, 6, 6));
                }
            } else {
                velY=-1;
                velX=0;
            }
        } else {
            timer--;
            velX = 1;
            velY = 1;
        }
        //handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.02f, handler));
        //204,0,0
    }

    @Override
    //creates the look of the enemy
    public void render(Graphics g) {

        g.setColor(color);
        g.fillRect((int) x, (int) y, 96, 96);
    }
}

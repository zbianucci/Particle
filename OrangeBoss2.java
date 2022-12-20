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
public class OrangeBoss2 extends GameObject {

    private int timer = 2000, timer2 = 100;
    Random r = new Random();
    private Handler handler;
    private int velXBull = 0, velYBull = 0, intensity;

    public OrangeBoss2(int x, int y, ID id, Handler handler, int intensity) {
        super(x, y, id);

        this.handler = handler;
        this.intensity = intensity;
        velX = 0;
        velY = -2;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 48, 48);
    }

    @Override
    public void tick() {
        if (y <= -10) {
            handler.removeObject(this);
        }
        x += velX;
        y += velY;
        if (timer2 < 0) {
            if (timer > 0) {
                timer--;
                velY = Game.clamp(velY, -10, 10);
                int spawn = r.nextInt(intensity);
                //top left
                if (y < 2 && x < 2) {
                    velY = 2;
                    velX = 0;
                    velXBull = 5;
                    velYBull = 0;
                }
                //bottom left

                if (x < 55 && y > Game.HEIGHT - 78) {
                    velX = 2;
                    velY = 0;
                    velXBull = 0;
                    velYBull = -5;
                }
                //bottom right
                if (y > Game.HEIGHT - 78 && x > Game.WIDTH - 55) {
                    velX = 0;
                    velY = -2;
                    velXBull = -5;
                    velYBull = 0;
                }
                //top right
                if (y < 2 && x > Game.WIDTH - 55) {
                    velX = -2;
                    velY = 0;
                    velXBull = 0;
                    velYBull = 5;
                }
                if (spawn == 0) {

                    handler.addObject(new OrangeBossBullet((int) x, (int) y, ID.BasicEnemy, handler, velXBull, velYBull));
                }

            } else {
                int spawn = r.nextInt(intensity);
                velXBull = -5;
                velYBull = 0;
                if (spawn == 0) {
                    handler.addObject(new OrangeBossBullet((int) x, (int) y, ID.BasicEnemy, handler, velXBull, velYBull));
                }
            }
        }else {
            timer2--;
            int spawn = r.nextInt(intensity);
            velXBull = -5;
            velYBull = 0;
            if (spawn == 0) {
                handler.addObject(new OrangeBossBullet((int) x, (int) y, ID.BasicEnemy, handler, velXBull, velYBull));
            }
        }
    }

    @Override
    //creates the look of the enemy
    public void render(Graphics g) {

        g.setColor(new Color(255,165,0));
        g.fillRect((int) x, (int) y, 48, 48);
    }

}

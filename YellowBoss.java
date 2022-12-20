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
public class YellowBoss extends GameObject {

    private int timer = 1250, timer2 = 50, timer3 = 1000, size, intensity;
    Random r = new Random();
    private Handler handler;

    public YellowBoss(int x, int y, ID id, Handler handler, int intensity, int size) {
        super(x, y, id);
        this.intensity = intensity;
        this.size = size;
        this.handler = handler;
        if (size > 90) {
            velX = 0;
            velY = 2;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, size, size);
    }

    @Override
    public void tick() {
        if (y > Game.HEIGHT / 2 -72 && timer > 0) {
            velY = 0;
        }
        x += velX;
        y += velY;

        velX = Game.clamp(velX, -10, 10);
        int spawn = r.nextInt(intensity);
        if (spawn == 0 && timer > 0) {
            handler.addObject(new HardEnemyBullet((int) x + size / 2, (int) y + size / 2, ID.BasicEnemy, handler, 4, 4));
        }
        if (timer < 0) {
            velY = -2;
        } else {
            timer--;
        }
    }

    @Override
    //creates the look of the enemy
    public void render(Graphics g) {

        g.setColor(Color.yellow);
        g.fillRect((int) x, (int) y, size, size);
    }

}

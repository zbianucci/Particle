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
public class EnemyBoss extends GameObject {

    private int timer = 40, timer2 = 50, timer3 = 1000;
    Random r = new Random();
    private Handler handler;
    private int intensity;

    public EnemyBoss(int x, int y, ID id, Handler handler, int intensity) {
        super(x, y, id);

        this.handler = handler;
        velX = 0;
        velY = 2;
        this.intensity = intensity;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 96, 96);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if (timer <= 0) {
            velY = 0;
        } else {
            timer--;
        }

        if (timer <= 0) {
            timer2--;
        }
        if (timer2 <= 0) {
            if (velX == 0) {
                velX = 2;
            }
            if (velX > 0) {
                velX += 0.05f;
            }
            if (velX < 0) {
                velX -= 0.05f;
            }
            velX = Game.clamp(velX, -10, 10);
            int spawn = r.nextInt(intensity);
            if (spawn == 0&&timer3>100) {
                handler.addObject(new EnemyBossBullet((int) x + 48, (int) y + 48, ID.BasicEnemy, handler));
            }
        }
        if(timer2<=0){
            timer3--;
        }
        if (timer3 <= 100) {
            velY = -1;
        }else if(timer3<=0){
            handler.clearEnemies();
        }

        /*
        if (y <= 0 || y >= Game.HEIGHT - 32) {
            velY *= -1;
        }
         */
        if (x <= 0 || x >= Game.WIDTH - 96) {
            velX *= -1;
        }
        //handler.addObject(new Trail(x, y,ID.Trail, Color.red, 96,96,0.008f, handler));
    }

    @Override
    //creates the look of the enemy
    public void render(Graphics g) {

        g.setColor(new Color(204, 0, 0));
        g.fillRect((int) x, (int) y, 96, 96);
    }

}

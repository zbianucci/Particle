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
public class EnemyBossBullet extends GameObject {
private Handler handler;
Random r = new Random();
    public EnemyBossBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        
        this.handler = handler;
        
        velX = (r.nextInt(5 - -5)+-5);
        velY = 5;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y, 32, 32);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
/*
        if (y <= 0 || y >= Game.HEIGHT - 32) {
            velY *= -1;
        }

        if (x <= 0 || x >= Game.WIDTH - 16) {
            velX *= -1;
        }*/
        if(y>=Game.HEIGHT) handler.removeObject(this);
        handler.addObject(new Trail(x, y,ID.Trail, new Color(204,0,0), 16,16,0.02f, handler));
    }

    @Override
    //creates the look of the enemy
    public void render(Graphics g) {
        
        g.setColor(Color.red);
        g.fillRect((int)x,(int) y, 16, 16);
    }

}

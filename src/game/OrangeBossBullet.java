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
public class OrangeBossBullet extends GameObject {

    private Handler handler;
    private int velX, velY;
    Random r = new Random();

    public OrangeBossBullet(int x, int y, ID id, Handler handler, int velX, int velY) {
        super(x, y, id);

        this.handler = handler;

        this.velX = velX;
        this.velY = velY;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if(x>=Game.WIDTH+10) handler.removeObject(this);
        if(x<=-10) handler.removeObject(this);
        if(y>=Game.HEIGHT+10) handler.removeObject(this);
        if(y<=-10) handler.removeObject(this);
        handler.addObject(new Trail(x, y, ID.Trail, new Color(255,165,0), 16, 16, 0.05f, handler));
    }
    @Override
    //creates the look of the enemy
    public void render(Graphics g) {

        g.setColor(new Color(255,165,0));
        g.fillRect((int) x, (int) y, 16, 16);
    }

}

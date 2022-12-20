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
public class BasicEnemy extends GameObject {

    private Handler handler;
    private Color color;

    public BasicEnemy(int x, int y, ID id, Handler handler, int speedX, int speedY, Color color) {
        super(x, y, id);
        velX= speedX;
        velY= speedY;
        this.color = color;
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 32) {
            velY *= -1;
        }

        if (x <= 0 || x >= Game.WIDTH - 16) {
            velX *= -1;
        }
        handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.02f, handler));
        //204,0,0
    }

    @Override
    //creates the look of the enemy
    public void render(Graphics g) {

        g.setColor(color);
        g.fillRect((int) x, (int) y, 16, 16);
    }

}

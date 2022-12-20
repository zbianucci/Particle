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
public class MenuParticle extends GameObject {

    private Handler handler;
    Random r = new Random();
    private Color col;
    private int red;
    private int green;
    private int blue;
    private boolean random;
    private ColorMenu colorMenu = new ColorMenu();
    public MenuParticle(float x, float y, ID id, Handler handler, boolean random) {
        super(x, y, id);

        this.handler = handler;
        this.random = random;
        velX = (r.nextInt(7 - -7) + -7);
        velY = (r.nextInt(7 - -7) + -7);
        if (velX == 0) {
            velX = 1;
        }
        if (velY == 0) {
            velY = 1;
        }
        //creates a random color with a random variable red,green,blue
        red = r.nextInt(255);
        green = r.nextInt(255);
        blue = r.nextInt(255);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
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
        else if(Menu.gold)
        handler.addObject(new Trail((int) x, (int) y, ID.Trail, new Color(212, 175, 55), 16, 16, 0.05f, handler));
            else
        handler.addObject(new Trail((int) x, (int) y, ID.Trail, new Color(red, green, blue), 16, 16, 0.05f, handler));
    }

    @Override
    //creates the look of the enemy
    public void render(Graphics g) {

        g.setColor(col);
        g.fillRect((int) x, (int) y, 16, 16);
    }

}

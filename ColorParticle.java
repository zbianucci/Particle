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
public class ColorParticle extends GameObject {

    private Handler handler;
    private ColorMenu colorMenu = new ColorMenu();

    public ColorParticle(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 5;
        velY = 5;
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

        if (x <= 0 || x >= Game.WIDTH - 32) {
            velX *= -1;
        }
        if (Player.specialColorSparkle) //make sure to fix this
        {
            handler.addObject(new Trail(x, y, ID.Trail, colorMenu.getRandomColor(), 32, 32, 0.02f, handler));
        } else if (Player.specialColorRWB) {
            handler.addObject(new Trail(x, y, ID.Trail, colorMenu.getRWB(), 32, 32, 0.02f, handler));
        } else if(Player.specialColorOGP){
            handler.addObject(new Trail(x,y,ID.Trail,colorMenu.getOGP(),32,32,.02f,handler));
        }
        else{
            handler.addObject(new Trail(x, y, ID.Trail, colorMenu.getColor(), 32, 32, 0.02f, handler));
        }
    }

    @Override
    //creates the look of the particle
    public void render(Graphics g) {
        g.setColor(colorMenu.getColor());

        g.fillRect((int) x, (int) y, 32, 32);
    }

}

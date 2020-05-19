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
public class BlueBossBullet extends GameObject {

    private Handler handler;
    private GameObject blueBoss;
    private int speedX, speedY,timer = 120;

    public BlueBossBullet(float x, float y, ID id, Handler handler, int velX, int velY) {
        super(x, y, id);
        this.speedX = velX;
        this.speedY = velY;
        this.handler = handler;
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getID() == ID.BlueBoss) {
                blueBoss = handler.object.get(i);
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        float diffX = x - blueBoss.getX() - 48;
        float diffY = y - blueBoss.getY() - 48;
        float distance = (float) (Math.sqrt((x - blueBoss.getX()) * (x - blueBoss.getX()) + (y - blueBoss.getY()) * (y - blueBoss.getY())));

        velX = (float) (speedX * ((-1.0 / distance) * diffX));
        velY = (float) (speedY * ((-1.0 / distance) * diffY));

        handler.addObject(new Trail(x, y, ID.Trail, new Color(51, 153, 255), 16, 16, 0.08f, handler));
        if (timer<0) {
            handler.removeObject(this);
         }else
            timer--;
    }

    @Override
    //creates the look of the enemy
    public void render(Graphics g) {

        g.setColor(new Color(51, 153, 255));
        g.fillRect((int) x, (int) y, 16, 16);
    }

}

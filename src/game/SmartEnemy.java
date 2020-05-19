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
public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;
    private int speedX, speedY;

    public SmartEnemy(float x, float y, ID id, Handler handler, int velX, int velY) {
        super(x, y, id);
        this.speedX = velX;
        this.speedY = velY;
        this.handler = handler;
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getID() == ID.Player) {
                player = handler.object.get(i);
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

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) (Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY())));

        velX = (float) (speedX * ((-1.0 / distance) * diffX));
        velY = (float) (speedY * ((-1.0 / distance) * diffY));

        if (y <= 0 || y >= Game.HEIGHT - 32) {
            velY *= -1;
        }

        if (x <= 0 || x >= Game.WIDTH - 16) {
            velX *= -1;
        }
        handler.addObject(new Trail(x, y, ID.Trail, new Color(51, 255, 153), 16, 16, 0.02f, handler));
    }

    @Override
    //creates the look of the enemy
    public void render(Graphics g) {

        g.setColor(new Color(51, 255, 153));
        g.fillRect((int) x, (int) y, 16, 16);
    }

}

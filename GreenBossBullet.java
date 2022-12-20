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
public class GreenBossBullet extends GameObject {

    private Handler handler;
    private GameObject player;
    private float speedX, speedY;
    private Random r = new Random();

    public GreenBossBullet(int x, int y, ID id, Handler handler, int speedX, int speedY) {
        super(x, y, id);
        this.speedX = speedX;
        this.speedY = speedY;
        this.handler = handler;
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getID() == ID.Player) {
                player = handler.object.get(i);
            }
        }
        float diffX = 0;
        float diffY = 0;
        switch (r.nextInt(3)+1) {
            case 1:
                diffX = x - player.getX() - 8+128;
                diffY = y - player.getY() - 8;
                break;
            case 2:
                diffX = x - player.getX() - 8;
                diffY = y - player.getY() - 8;
                break;
            case 3:
                diffX = x - player.getX() - 8;
                diffY = y - player.getY() - 8+128;
                break;
            default:
                break;
        }

        float distance = (float) (Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY())));
        
        velX = (float) (speedX * ((-1.0 / distance) * diffX));
        velY = (float) (speedY * ((-1.0 / distance) * diffY));
    
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    @Override
    public void tick() {
        if (y <= -10 || y >= Game.HEIGHT + 10 || x <= -10 || x >= Game.WIDTH + 10) {
            handler.removeObject(this);
        }
        x += velX;
        y += velY;

        handler.addObject(new Trail(x, y, ID.Trail, new Color(0, 128, 0), 16, 16, 0.08f, handler));
    }

    @Override
    //creates the look of the enemy
    public void render(Graphics g) {

        g.setColor(new Color(0, 128, 0));
        g.fillRect((int) x, (int) y, 16, 16);
    }
}

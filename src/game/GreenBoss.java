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
public class GreenBoss extends GameObject {

    private Random r = new Random();
    private Handler handler;
    private GameObject player;
    private int speedX, speedY, intensity, timer = 200, timer2 = 2000,bulletSpeed;

    public GreenBoss(float x, float y, ID id, Handler handler, int velX, int velY, int intensity, int bulletSpeed) {
        super(x, y, id);
        this.intensity = intensity;
        this.speedX = velX;
        this.speedY = velY;
        this.handler = handler;
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getID() == ID.Player) {
                player = handler.object.get(i);
            }
        }
        this.bulletSpeed=bulletSpeed;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 96, 96);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) (Math.sqrt((x - (player.getX())) * (x - (player.getX())) + (y - (player.getY())) * (y - (player.getY()))));

        velX = (float) (speedX * ((-1.0 / (distance)) * diffX));
        velY = (float) (speedY * ((-1.0 / (distance)) * diffY));
        if (timer2 < 0) {
            velX=0;
            velY=-1;
        } else {
            timer2--;
            if (r.nextInt(intensity) == 0 && timer < 0) {
                handler.addObject(new GreenBossBullet((int) (x + 48), (int) (y + 48), ID.BasicEnemy, handler, bulletSpeed, bulletSpeed));
            } else {
                timer--;
            }
        }
    }

    @Override
    //creates the look of the enemy
    public void render(Graphics g) {
        g.setColor(new Color(0, 128, 0));
        g.fillRect((int) x, (int) y, 96, 96);
    }

}

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
public class TargetBullet extends GameObject {

    private Handler handler;
    private GameObject player;
    private float speedX, speedY;
    private int timer, timer2;
    private Color color;
    private int repeat,count,buffer;

    public TargetBullet(int x, int y, ID id, Handler handler, int speedX, int speedY, Color color, int repeat,int buffer) {
        super(x, y, id);
        this.count = 0;
        this.repeat = repeat;
        this.color = color;
        this.speedX = speedX;
        this.speedY = speedY;
        this.handler = handler;
        this.buffer = buffer;
        this.timer = buffer;
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getID() == ID.Player) {
                player = handler.object.get(i);
            }
        }

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) (Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY())));

        velX = (float) (speedX * ((-1.0 / distance) * diffX));
        velY = (float) (speedY * ((-1.0 / distance) * diffY));

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    @Override
    public void tick() {
        float diffY = y - player.getY() - 8;
        float diffX = x - player.getX() - 8;
        float distance = (float) (Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY())));
        if (count < repeat) {
            if (timer < 0) {
                velY = (float) (speedY * ((-1.0 / distance) * diffY));
                velX = (float) (speedX * ((-1.0 / distance) * diffX));
                timer = buffer;
                count++;
            } else {
                timer--;
            }
        }
        else{
            if(x>Game.WIDTH+10||x<-10||y>Game.HEIGHT+10||y<-10){
                handler.removeObject(this);
            }
        }

        x += velX;
        y += velY;

        handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.02f, handler));
    }

    @Override
    //creates the look of the enemy
    public void render(Graphics g) {

        g.setColor(color);
        g.fillRect((int) x, (int) y, 16, 16);
    }

}

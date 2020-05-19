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
public class IndigoBoss extends GameObject {

    private Handler handler;
    private Color color;
    private int count = 0, timer = 0, speedX, speedY;
    private boolean truth;

    public IndigoBoss(int x, int y, ID id, Handler handler, int speedX, int speedY, Color color) {
        super(x, y, id);
        this.speedX = speedX;
        this.speedY = speedY;
        velX = speedX;
        velY = speedY;
        this.color = color;
        this.handler = handler;
        truth = true;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 96, 96);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if (timer < 0) {
            if (y <= 0 || y >= Game.HEIGHT - 120) {
                velY *= -1;
                count++;
            }

            if (x <= 0 || x >= Game.WIDTH - 100) {
                velX *= -1;
                count++;
            }
        } else {
            timer--;
        }
        handler.addObject(new Trail(x, y, ID.Trail, color, 96, 96, 0.02f, handler));
        if (count >= 10 && x <Game.WIDTH/2&&x>Game.WIDTH/2-10&&truth) {
            System.out.println("here");
            handler.removeObject(this);
            handler.addObject(new IndigoBoss2((int) x, (int) y, ID.BasicEnemy, handler, (int)velX, (int)velY, color));
            handler.addObject(new IndigoBoss2((int) x, (int) y, ID.BasicEnemy, handler, (int)velX, -(int)velY, color));
            truth = false;
        }
    }

    @Override
    //creates the look of the enemy
    public void render(Graphics g) {

        g.setColor(color);
        g.fillRect((int) x, (int) y, 96, 96);
    }

}

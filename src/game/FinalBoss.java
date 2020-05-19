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
public class FinalBoss extends GameObject {

    private Handler handler;
    private Color color;
    private int intensity, timer = 0, width, height;
    private Random r = new Random();
    private ColorMenu colorMenu = new ColorMenu();
    //private int width = 0, height = 0;

    public FinalBoss(int x, int y, ID id, Handler handler, int width, int height) {
        super(x, y, id);
        this.color = color;
        this.handler = handler;
        this.intensity = intensity;
        this.width = width;
        this.height = height;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    @Override
    public void tick() {
        timer++;
        if (timer >= 250 && timer < 500) {
            //big
            on();
        } else if (timer >= 1000 && timer < 1300) {
            //medium
            on();
        } else if (timer >= 1750 && timer < 1900) {
            //small
            on();
        } else if (timer >= 2250 && timer < 2500) {
            //move
            on();
        } else if (timer >= 2750 && timer < 3000) {
            on();
        } else if (timer >= 3250 && timer < 3500) {
            on();
        } else if (timer >= 4000 && timer < 4250) {
            on();
        } else if (timer >= 4500 && timer < 4750) {
            on();
        } else if (timer >= 5000 && timer < 5250) {
            on();
        } else if (timer >= 5500) {
            handler.removeObject(this);
        } else {
            off();
        }

    }

    @Override
    //creates the look of the enemy
    public void render(Graphics g) {
        // g.setColor(Color.white);
        //g.drawRect(50, 50, Game.WIDTH - 10 - 100, Game.HEIGHT - 30 - 100);
        g.setColor(colorMenu.getRandomColorMenu());
        g.fillRect((int) x, (int) y, width, height);
    }

    //makes the screen flash
    public void on() {
        x = 0;
        y = 0;
        width = Game.WIDTH;
        height = Game.HEIGHT;
    }

    //turns off the screen
    public void off() {
        x = -1;
        y = -1;
        width = 0;
        height = 0;
    }
}

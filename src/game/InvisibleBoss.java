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
public class InvisibleBoss extends GameObject {

    private Handler handler;
    private Color color;
    private int xPos, yPos, intensity, timer = 0, timer2 = 750, timer3 = 500, timer4 = 500;
    private Random r = new Random();
    private ColorMenu colorMenu = new ColorMenu();
    private int width = 0, height = 0, size, speedX, speedY;
    private boolean truth;

    public InvisibleBoss(int x, int y, ID id, Handler handler, int width, int height, int size, int speedX, int speedY) {
        super(x, y, id);
        this.handler = handler;
        this.width = Game.WIDTH - 10 - (2 * width);
        this.height = Game.HEIGHT - 30 - (2 * height);
        this.speedX = speedX;
        this.speedY = speedY;
        this.truth = true;
        this.size = size;
    }

    public Rectangle getBounds() {
        //if (size == 96) {
            return new Rectangle((int) x + 32, (int) y + 32, width - 64, height - 64);
        //} else {
         //   return new Rectangle((int) x + 32*2, (int) y + 32*2, width - 64 * 2, height - 64 * 2);
        //}
    }

    @Override
    public void tick() {
        /*
        if (timer3 > 0) {
            g.drawRect((int) x, (int) y, Game.WIDTH - 10 - (width * 2), Game.HEIGHT - 30 - (height * 2));
        }
        velX = 4;
        velY = 4;*/
        timer++;
        if (timer >= 0 && timer < 750) {
            //default
        } else if (timer >= 750 && timer < 1500) {
            //medium
            draw(100, 100, Game.WIDTH - 10 - (2 * 100), Game.HEIGHT - 30 - (2 * 100));
        } else if (timer >= 1500 && timer < 2000) {
            //small
            draw(200, 150, size, size);
        } else if (timer >= 2000 && timer < 2500) {
            /*
            if (truth) {
                velX = 1;
                velY = 1;
                truth = false;
            }*/
            draw(20, 250, size, size);
        } else if (timer >= 2500 && timer < 3000) {
            draw(350, 250, size, size);
        } else if (timer >= 3000 && timer < 3750) {
            draw(20, 120, size, size);
        } else if (timer >= 3750 && timer < 5250) {
            if (truth) {
                velX = speedX;
                velY = speedY;
                truth = false;
            }
        } else if (timer >= 5250) {
            velX = 0;
            velY = 0;
        } else if (timer >= 5500) {
            handler.removeObject(this);
        }
        if (((y <= 0 || y >= Game.HEIGHT - 120)&&size<100)||((y <= 0 || y >= Game.HEIGHT - 120*2+20)&&size>100)) {
            velY *= -1;
        }

        if (((x <= 0 || x >= Game.WIDTH - 100)&&size<100)||((x <= 0 || x >= Game.WIDTH - 100*2)&&size>100)) {
            velX *= -1;
        }
        x += velX;
        y += velY;
        /*if (timer4 > 0) {
            if (timer < 0) {
                x = 100;
                y = 100;
                width = Game.WIDTH - 10 - (2 * 100);
                height = Game.HEIGHT - 30 - (2 * 100);
                //timer4--;
                System.out.println("yes " + timer2);
                if (timer2 > 0) {
                    timer2--;
                    //width = 50;
                    //height = Game.HEIGHT - 30;
                } else {
                    x = 200;
                    y = 150;
                    width = 96;
                    height = 96;
                    System.out.println("timer4");
                    timer4--;
                    //width = 0;
                    //height = 0;
                }

            } else {
                timer--;
            }
        }
        x += velX;
        y += velY;
        if (timer4 <= 0) {
            if (truth) {
                velX = 1;
                velY = 1;
                truth = false;
            }
            if (y <= 0 || y >= Game.HEIGHT - 120) {
                velY *= -1;
            }

            if (x <= 0 || x >= Game.WIDTH - 100) {
                velX *= -1;
            }
        }*/
    }

    public void draw(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    //creates the look of the enemy
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect((int) x, (int) y, width, height);
        g.setColor(Color.white);

        g.drawRect((int) x, (int) y, width, height);

        //g.setColor(colorMenu.getRandomColorMenu());
        //g.fillRect(0, 0, width, height);
    }

    public void setWidthHeight(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

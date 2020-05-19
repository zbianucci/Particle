/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Zachary Bianucci
 */
//heads up display
public class HUD {

    public int bounds = 0;
    public static float HEALTH = 100, HEALTH2 = 200;
    private int score = 0, level = 1, money = 0;

    public void tick() {
        HEALTH = Game.clamp((int) HEALTH, 0, 100 + (bounds / 2));
        HEALTH2 = Game.clamp((int) HEALTH2, 0, 200);
        score++;
        if (score % 10 == 0) {
            money++;
        }
    }

    //renders the health bar with variables to move
    public void render(Graphics g, Game game) {
        g.setColor(Color.gray);
        if (game.diff == 0 || game.diff == -1) {
            g.fillRect(15, 15, 200 + bounds, 32);
        } else if (game.diff == 1) {
            g.fillRect(15, 15, 400, 32);
        }
        if (game.diff == 0 || game.diff == -1) {
            g.setColor(Color.getHSBColor((1f * HEALTH) / 360, 1f, 1f));
        } else if (game.diff == 1) {
            g.setColor(Color.getHSBColor((1f * (HEALTH2 / 2)) / 360, 1f, 1f));
        }

        if (game.diff == 0 || game.diff == -1) {
            g.fillRect(15, 15, (int) (HEALTH * 2), 32);
        } else if (game.diff == 1) {
            g.fillRect(15, 15, (int) (HEALTH2 * 2), 32);
        }

        g.setColor(Color.white);
        if(Menu.gold){g.setColor(new Color(212, 175, 55));}
        if (game.diff == 0 || game.diff == -1) {
            g.drawRect(15, 15, 200 + bounds, 32);
        } else if (game.diff == 1) {
            g.drawRect(15, 15, 400, 32);
        }

        //g.drawString("Score: " + score, 15, 64);
        //g.drawString("Level: " + level, 15, 80);
        if (game.diff == 0 || game.diff == -1) {
            g.drawString("Press \"Space\" for Shop", 15, 94);
        }

        //string, x, y
        g.drawString("Score: " + score, 15, 64);
        if (game.diff == 0 || game.diff == -1) {
            g.drawString("Money: " + money, 15, 80);
        }
        //g.drawString("Money: "+money,15, 96);
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getMoney() {
        return money;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

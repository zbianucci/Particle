/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.Game.STATE;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Zachary Bianucci
 */
public class Shop extends MouseAdapter {

    Handler handler;
    HUD hud;
    Game game;
    private int box1 = 100;
    private int box2 = 100;
    private int box3 = 50;

    //box 1, 2, or 3 that is currently hovered
    private int box = 1;

    public Shop(Game game, Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        if (Menu.gold) {
            g.setColor(new Color(212, 175, 55));
        }
        g.setFont(new Font("arial", 0, 48));
        g.drawString("SHOP", Game.WIDTH / 2 - 85, 50);

        g.setColor(Color.black);
        //box 1 for clarity
        if (box == 1) {
            g.setColor(Color.white);
            g.fillRect(100, 100, 100, 80);
            g.setColor(Color.black);
        } else {
            g.fillRect(100, 100, 100, 80);
        }

        //box 2 for clarity
        if (box == 2) {
            g.setColor(Color.white);
            g.fillRect(250, 100, 100, 80);
            g.setColor(Color.black);
        } else {
            g.fillRect(250, 100, 100, 80);
        }

        //box 3 for clarity
        if (box == 3) {
            g.setColor(Color.white);
            g.fillRect(400, 100, 100, 80);
            g.setColor(Color.black);
        } else {
            g.fillRect(400, 100, 100, 80);
        }

        g.setColor(Color.white);
        if (Menu.gold) {
            g.setColor(new Color(212, 175, 55));
        }
        g.setFont(new Font("arial", 0, 12));

        //box 1 - health upgrade
        if (box == 1) {
            g.setColor(Color.black);
        }
        g.drawRect(100, 100, 100, 80);
        g.drawString("Upgrade Health", 110, 120);
        g.drawString("Cost: " + box1, 110, 140);
        if (box == 1) {
            g.setColor(Color.white);
        }
        g.drawRect(100, 100, 100, 80);
        //box 2 - speed upgrade
        if (box == 2) {
            g.setColor(Color.black);
        }
        //g.drawRect(100, 100, 100, 80);
        g.drawString("Upgrade Speed", 260, 120);
        g.drawString("Cost: " + box2, 260, 140);
        if (box == 2) {
            g.setColor(Color.white);
        }
        g.drawRect(250, 100, 100, 80);

        //box3 - health refill
        if (box == 3) {
            g.setColor(Color.black);
        }
        //g.drawRect(100, 100, 100, 80);
        g.drawString("Refill Health", 410, 120);
        g.drawString("Cost: " + box3, 410, 140);
        if (box == 3) {
            g.setColor(Color.white);
        }
        g.drawRect(400, 100, 100, 80);

        g.drawString("Money: " + hud.getMoney(), Game.WIDTH / 2 - 50, 270);
        g.drawString("Score: " + hud.getScore(), Game.WIDTH / 2 - 52, 300);
        g.drawString("Press RED button to resume", Game.WIDTH / 2 - 100, 330);
        if (game.gameState == STATE.Shop) {
            switch (box) {
                case 1:
                    if (KeyInput.getMenuEnter() && hud.getMoney() >= box1) {
                        hud.setMoney(hud.getMoney() - box1);
                        box1 += 100;
                        hud.bounds += 20;
                        hud.HEALTH = 100 + (hud.bounds / 2);
                    }
                    if (KeyInput.getMenuRight()) {
                        box = 2;
                    } else if (KeyInput.getMenuLeft()) {
                        box = 3;
                    }
                    break;
                case 2:
                    if (KeyInput.getMenuEnter() && hud.getMoney() >= box2) {
                        //you've selected box2 - speed upgrade
                        hud.setMoney(hud.getMoney() - box2);
                        box2 += 100;
                        KeyInput.speed++;
                    }
                    if (KeyInput.getMenuRight()) {
                        box = 3;
                    } else if (KeyInput.getMenuLeft()) {
                        box = 1;
                    }
                    break;
                case 3:
                    if (KeyInput.getMenuEnter() && hud.getMoney() >= box3) {
                        hud.setMoney(hud.getMoney() - box3);
                        hud.HEALTH = 100 + (hud.bounds / 2);
                        box3 += 50;
                    }
                    if (KeyInput.getMenuRight()) {
                        box = 1;
                    } else if (KeyInput.getMenuLeft()) {
                        box = 2;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //box1
        if (mx >= 100 && mx <= 200 && game.gameState == STATE.Shop) {
            if (my >= 100 && my <= 180) {
                //you've selected box1 - health upgrade
                if (hud.getMoney() >= box1) {
                    hud.setMoney(hud.getMoney() - box1);
                    box1 += 100;
                    hud.bounds += 20;
                    hud.HEALTH = 100 + (hud.bounds / 2);
                }
            }
        }
        //box2
        if (mx >= 250 && mx <= 350 && game.gameState == STATE.Shop) {
            if (my >= 100 && my <= 180) {
                if (hud.getMoney() >= box2) {
                    //you've selected box2 - speed upgrade
                    hud.setMoney(hud.getMoney() - box2);
                    box2 += 100;
                    KeyInput.speed++;
                }
            }
        }
        //box3
        if (mx >= 400 && mx <= 500 && game.gameState == STATE.Shop) {
            if (my >= 100 && my <= 180) {
                //you've selected box3 - health refill
                if (hud.getMoney() >= box3) {
                    hud.setMoney(hud.getMoney() - box3);
                    hud.HEALTH = 100 + (hud.bounds / 2);
                    box3 += 50;
                }
            }
        }
        if (game.gameState == STATE.End || game.gameState == STATE.Menu || game.gameState == STATE.Win) {
            box1 = 100;
            box2 = 100;
            box3 = 50;

        }
    }
}

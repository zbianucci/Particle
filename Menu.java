/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

//not sure why I have this import but it got it to compile
//import game.Game.STATE;
import game.Game.STATE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 *
 * @author Zachary Bianucci
 */
public class Menu extends MouseAdapter {

    public static boolean gold, hard;
    private Game game;
    private Handler handler;
    private Random r = new Random();
    private HUD hud;
    private ColorMenu colorMenu = new ColorMenu();

    //if these buttons are currently hovered by key selection
    private boolean menuPlay = true;
    private boolean menuHelp = false;
    private boolean menuExit = false;
    private boolean menuColor = false;

    //variables of the select screen
    private boolean selectEasy = true;
    private boolean selectNormal = false;
    private boolean selectHard = false;
    private boolean selectBack = false;
    //private ColorParticle colorParticle = new ColorParticle();

    private int colorType = 1;

    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e) {
        //stores mouse position
        int mx = e.getX();
        int my = e.getY();
        if (game.gameState == STATE.Menu) {
            //play button
            if (mouseOver(mx, my, 210, 150, 200, 64)) {/*
                game.gameState = STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                 */
                game.gameState = STATE.Select;
                return;
            }
            //help button
            if (mouseOver(mx, my, 210, 250, 200, 64)) {
                game.gameState = STATE.Help;
                return;
            }
            //quit button
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                System.exit(0);
            }
            //color button
            if (mouseOver(mx, my, 15, 350, 100, 80)) {
                handler.clearEnemies();
                ColorParticle colorParticle = new ColorParticle(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.ColorParticle, handler);
                handler.addObject(colorParticle);
                //colorParticle.tick();
                game.gameState = STATE.Colors;
                return;
            }
        }
        if (game.gameState == STATE.Select) {
            /* g.drawRect(210, 120, 200, 64);
            g.drawString("Easy", 270, 160);
            
            g.drawRect(210,200, 200, 64);
            g.drawString("Normal", 260, 240);

            g.drawRect(210, 280, 200, 64);
            g.drawString("Hard", 270, 320);

            g.drawRect(210, 360, 200, 64);
            g.drawString("Back", 270, 400);*/
            //easy button
            if (mouseOver(mx, my, 210, 120, 200, 64)) {
                game.gameState = STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, game));
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(10, 300, ID.BasicEnemy, handler, 4, 4, new Color(204, 0, 0)));
                KeyInput.speed = 5;
                game.diff = -1;
            }
            //normal button
            if (mouseOver(mx, my, 210, 200, 200, 64)) {
                game.gameState = STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, game));
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(10, 300, ID.BasicEnemy, handler, 5, 5, new Color(204, 0, 0)));
                KeyInput.speed = 5;
                game.diff = 0;
            }
            //hard button
            if (mouseOver(mx, my, 210, 280, 200, 64)) {
                game.gameState = STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, game));
                handler.clearEnemies();
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 7, 7, Color.yellow));

                game.diff = 1;
            }
            //back button
            if (mouseOver(mx, my, 210, 360, 200, 64)) {
                game.gameState = STATE.Menu;
                return;
            }
        }
        //back button for help
        if (game.gameState == STATE.Help) {
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = STATE.Menu;
                return;
            }
        }
        //try again button for game over and win
        if (game.gameState == STATE.End || game.gameState == STATE.Win) {
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = STATE.Menu;
            }
            hud.setLevel(1);
            hud.setScore(0);
            hud.setMoney(0);
        }
        if (game.gameState == STATE.Colors) {
            //back button
            if (mouseOver(mx, my, 210, 370, 200, 64)) {
                handler.clearEnemies();
                for (int i = 0; i < 20; i++) {
                    handler.addObject(new MenuParticle((float) r.nextInt(Game.WIDTH), (float) r.nextInt(Game.HEIGHT), ID.MenuParticle, handler, false));
                }
                game.gameState = STATE.Menu;
            }
            //box 1 - default
            if (mouseOver(mx, my, 25, 80, 100, 80)) {
                Player.specialColorRWB = false;
                Player.specialColorSparkle = false;
                Player.specialColorOGP = false;
                colorMenu.setColor(Color.white);
            }
            //box 2 - blue
            if (mouseOver(mx, my, 185, 80, 100, 80)) {
                Player.specialColorRWB = false;
                Player.specialColorSparkle = false;
                Player.specialColorOGP = false;
                colorMenu.setColor(Color.blue);
            }
            //box 3 - green
            if (mouseOver(mx, my, 345, 80, 100, 80)) {
                Player.specialColorRWB = false;
                Player.specialColorSparkle = false;
                Player.specialColorOGP = false;
                colorMenu.setColor(Color.green);
            }//box 4 - purple
            if (mouseOver(mx, my, 505, 80, 100, 80)) {
                Player.specialColorRWB = false;
                Player.specialColorSparkle = false;
                Player.specialColorOGP = false;
                colorMenu.setColor(128, 0, 128);
            }//box 5 - start of row 2 - orange
            if (mouseOver(mx, my, 25, 180, 100, 80)) {
                Player.specialColorRWB = false;
                Player.specialColorSparkle = false;
                Player.specialColorOGP = false;
                colorMenu.setColor(255, 130, 0);
            }//box 6 - pink
            if (mouseOver(mx, my, 185, 180, 100, 80)) {
                Player.specialColorRWB = false;
                Player.specialColorSparkle = false;
                Player.specialColorOGP = false;
                colorMenu.setColor(255, 20, 147);
            }//box 7 - gold
            if (mouseOver(mx, my, 345, 180, 100, 80)) {
                Player.specialColorRWB = false;
                Player.specialColorSparkle = false;
                Player.specialColorOGP = false;
                colorMenu.setColor(212, 175, 55);
            }//box 8 - random
            if (mouseOver(mx, my, 505, 180, 100, 80)) {
                Player.specialColorRWB = false;
                Player.specialColorSparkle = false;
                Player.specialColorOGP = false;
                colorMenu.setColor(colorMenu.getRandomColor());
            }//box 9 - start of row 3 - red,white,blue
            if (mouseOver(mx, my, 25, 280, 100, 80)) {
                Player.specialColorSparkle = false;
                Player.specialColorRWB = true;
                Player.specialColorOGP = false;
            }//box 10 - orange, green, purple
            if (mouseOver(mx, my, 185, 280, 100, 80)) {
                Player.specialColorRWB = false;
                Player.specialColorSparkle = false;
                Player.specialColorOGP = true;
            }//box 11 - sparkle
            if (mouseOver(mx, my, 345, 280, 100, 80)) {
                //Color.MAGENTA will tell player to override with colorMenu.getRandomColor()
                Player.specialColorRWB = false;
                Player.specialColorSparkle = true;
                Player.specialColorOGP = false;
            }//box 12 - invisible
            if (mouseOver(mx, my, 505, 280, 100, 80)) {
                colorMenu.setColor(Color.black);
                Player.specialColorRWB = false;
                Player.specialColorSparkle = false;
                Player.specialColorOGP = false;
            }
            return;

        }
    }

    public void mouseReleased(MouseEvent e) {

    }
//figures out if the mouse is pressed in the appropriate rectangle

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        //main menu
        if (game.gameState == STATE.Menu) {
            Font fnt = new Font("arial", 3, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 10);

            g.setColor(Color.black);
            g.fillRect(210, 150, 200, 64);
            g.fillRect(210, 250, 200, 64);
            g.fillRect(210, 350, 200, 64);
            g.fillRect(15, 350, 100, 80);

            g.setColor(Color.white);

            if (menuPlay) {
                if (KeyInput.getMenuEnter()) {
                    game.gameState = STATE.Select;
                    return;
                }
                g.fillRect(210, 150, 200, 64);
                //System.out.println(key.menuDown);
                if (KeyInput.getMenuDown()) {
                    menuPlay = false;
                    menuHelp = true;
                } else if (KeyInput.getMenuLeft()) {
                    menuPlay = false;
                    menuColor = true;
                }
            } else if (menuHelp) {
                g.fillRect(210, 250, 200, 64);
                if (KeyInput.getMenuEnter()) {
                    game.gameState = STATE.Help;
                    return;
                }
                if (KeyInput.getMenuUp()) {
                    menuPlay = true;
                    menuHelp = false;
                } else if (KeyInput.getMenuDown()) {
                    menuExit = true;
                    menuHelp = false;
                } else if (KeyInput.getMenuLeft()) {
                    menuHelp = false;
                    menuColor = true;
                }
            } else if (menuExit) {
                if (KeyInput.getMenuEnter()) {
                    System.exit(0);
                }
                g.fillRect(210, 350, 200, 64);
                if (KeyInput.getMenuUp()) {
                    menuHelp = true;
                    menuExit = false;
                } else if (KeyInput.getMenuLeft()) {
                    menuExit = false;
                    menuColor = true;
                }
            } else if (menuColor) {
                g.fillRect(15, 350, 100, 80);
                if (KeyInput.getMenuRight()) {
                    menuExit = true;
                    menuColor = false;
                }
                if (KeyInput.getMenuEnter()) {
                    handler.clearEnemies();
                    ColorParticle colorParticle = new ColorParticle(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.ColorParticle, handler);
                    handler.addObject(colorParticle);
                    game.gameState = STATE.Colors;
                    return;
                }
            }

            if (gold) {
                g.setColor(new Color(212, 175, 55));
                Polygon star = new Polygon();
                int[] x = {142, 152, 172, 152, 160, 140, 115, 128, 109, 132, 142};
                int[] y = {38, 62, 68, 80, 105, 85, 102, 75, 58, 60, 38};
                g.fillPolygon(x, y, 11);
            }
            if (hard) {
                Polygon star2 = new Polygon();
                int[] x = {472, 482, 502, 482, 490, 470, 445, 458, 439, 462, 472};
                int[] y = {38, 62, 68, 80, 105, 85, 102, 75, 58, 60, 38};
                g.fillPolygon(x, y, 11);
            }

            g.setFont(fnt3);
            g.drawString("Zach Bianucci Presents:", 195, 25);
            //Graphics2D g2d = (Graphics2D)g;
            g.drawString("ver 2.2", 390, 80);

            //g.fillPolygon();
            g.setFont(fnt);
            g.drawString("PARTICLE", 185, 70);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            if (!menuPlay) {
                g.drawString("Play", 270, 190);
            } else {
                g.setColor(Color.black);
                g.drawString("Play", 270, 190);
                g.setColor(Color.white);
            }

            g.drawRect(210, 250, 200, 64);
            if (!menuHelp) {
                g.drawString("Help", 270, 290);
            } else {
                g.setColor(Color.black);
                g.drawString("Help", 270, 290);
                g.setColor(Color.white);
            }

            g.drawRect(210, 350, 200, 64);
            if (!menuExit) {
                g.drawString("Quit", 270, 390);
            } else {
                g.setColor(Color.black);
                g.drawString("Quit", 270, 390);
                g.setColor(Color.white);
            }

            if (!menuColor) {
                g.drawString("Color", 25, 380);
            } else {
                g.setColor(Color.black);
                g.drawString("Color", 25, 380);
                g.setColor(Color.white);
            }
            g.drawRect(15, 350, 100, 80);

            //the color menu will determine the color that goes here
            if (colorMenu.getColor().equals(Color.black)) {
                g.setColor(Color.black);
                g.fillRect(30, 390, 70, 30);
                g.setColor(Color.white);
                g.drawRect(30, 390, 70, 30);
            } else if (Player.specialColorSparkle) {
                g.setColor(colorMenu.getRandomColorMenu());
                g.fillRect(30, 390, 70, 30);
            } else if (Player.specialColorRWB) {
                g.setColor(Color.red);
                g.fillRect(30, 390, 23, 30);
                g.setColor(Color.white);
                g.fillRect(53, 390, 24, 30);
                g.setColor(Color.blue);
                g.fillRect(77, 390, 23, 30);
            } else if (Player.specialColorOGP) {
                g.setColor(new Color(255, 130, 0));
                g.fillRect(30, 390, 23, 30);
                g.setColor(Color.green);
                g.fillRect(53, 390, 24, 30);
                g.setColor(new Color(128, 0, 128));
                g.fillRect(77, 390, 23, 30);
            } else {
                g.setColor(colorMenu.getColor());
                g.fillRect(30, 390, 70, 30);
                if (colorMenu.getColor().equals(Color.white)) {
                    g.setColor(Color.black);
                    g.drawRect(30, 390, 70, 30);
                    g.setColor(Color.white);
                }
            }
            //help menu
        } else if (game.gameState == STATE.Help) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setColor(Color.white);
            g.fillRect(210, 350, 200, 64);

            g.setFont(fnt);
            if (gold) {
                g.setColor(new Color(212, 175, 55));
            }
            g.drawString("Help", 251, 70);

            g.setFont(fnt3);
            g.drawString("Use the left joystick to move player and dodge enemies", 58, 150);
            g.drawString("Press the RED button to pause and BLACK button to quit", 45, 200);
            g.drawString("In NORMAL or EASY mode, press the RED button to shop", 45, 250);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.setColor(Color.black);
            g.drawString("Back", 270, 390);
            g.setColor(Color.white);
            if (KeyInput.getMenuEnter()) {
                game.gameState = STATE.Menu;
            }
            //Game Over menu
        } else if (game.gameState == STATE.End) {
            g.setColor(Color.black);
            g.fillRect(210, 350, 200, 64);
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.red);
            g.drawString("Game Over", 180, 70);

            g.setColor(Color.white);
            if (gold) {
                g.setColor(new Color(212, 175, 55));
            }
            g.setFont(fnt3);
            switch (game.diff) {
                case -1:
                    g.drawString("Mode: Easy", 250, 175);
                    break;
                case 0:
                    g.drawString("Mode: Normal", 250, 175);
                    break;
                case 1:
                    g.drawString("Mode: Hard", 250, 175);
                    break;
                default:
                    break;
            }
            g.drawString("You lost with a score of: " + hud.getScore(), 175, 200);

            g.setFont(fnt2);
            g.setColor(Color.white);
            g.fillRect(210, 350, 200, 64);
            g.setColor(Color.black);
            g.drawString("Menu", 270, 390);
            if (KeyInput.getMenuEnter()) {
                game.gameState = STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
                hud.setMoney(0);
            }
            //Select Difficulty menu
        } else if (game.gameState == STATE.Select) {
            g.setColor(Color.black);/*
            g.fillRect(210, 50, 200, 64);
            g.fillRect(210, 150, 200, 64);
            g.fillRect(210, 250, 200, 64);
            g.fillRect(210, 350, 200, 64);*/

            g.fillRect(210, 120, 200, 64);
            g.fillRect(210, 200, 200, 64);
            g.fillRect(210, 280, 200, 64);
            g.fillRect(210, 360, 200, 64);
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            if (gold) {
                g.setColor(new Color(212, 175, 55));
            }
            g.drawString("SELECT DIFFICULTY", 70, 70);

            g.setFont(fnt2);

            g.drawRect(210, 120, 200, 64);
            g.drawString("Easy", 270, 160);

            g.drawRect(210, 200, 200, 64);
            g.drawString("Normal", 260, 240);

            g.drawRect(210, 280, 200, 64);
            g.drawString("Hard", 270, 320);

            g.drawRect(210, 360, 200, 64);
            g.drawString("Back", 270, 400);
            if (selectEasy) {
                if (KeyInput.getMenuEnter()) {
                    game.gameState = STATE.Game;
                    handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, game));
                    handler.clearEnemies();
                    handler.addObject(new BasicEnemy(10, 300, ID.BasicEnemy, handler, 4, 4, new Color(204, 0, 0)));
                    KeyInput.speed = 5;
                    game.diff = -1;
                    return;
                }
                g.fillRect(210, 120, 200, 64);
                g.setColor(Color.black);
                g.drawString("Easy", 270, 160);
                //System.out.println(key.menuDown);
                if (KeyInput.getMenuDown()) {
                    selectEasy = false;
                    selectNormal = true;
                }
            } else if (selectNormal) {
                g.fillRect(210, 200, 200, 64);
                g.setColor(Color.black);
                g.drawString("Normal", 260, 240);
                //System.out.println(key.menuDown);
                if (KeyInput.getMenuEnter()) {
                    game.gameState = STATE.Game;
                    handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, game));
                    handler.clearEnemies();
                    handler.addObject(new BasicEnemy(10, 300, ID.BasicEnemy, handler, 5, 5, new Color(204, 0, 0)));
                    KeyInput.speed = 5;
                    game.diff = 0;
                    return;
                }
                if (KeyInput.getMenuUp()) {
                    selectEasy = true;
                    selectNormal = false;
                } else if (KeyInput.getMenuDown()) {
                    selectHard = true;
                    selectNormal = false;
                }
            } else if (selectHard) {
                if (KeyInput.getMenuEnter()) {
                    game.gameState = STATE.Game;
                    handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, game));
                    handler.clearEnemies();
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 7, 7, Color.yellow));
                    game.diff = 1;
                    return;
                }
                g.fillRect(210, 280, 200, 64);
                g.setColor(Color.black);
                g.drawString("Hard", 270, 320);
                //System.out.println(key.menuDown);
                if (KeyInput.getMenuUp()) {
                    selectNormal = true;
                    selectHard = false;
                } else if (KeyInput.getMenuDown()) {
                    selectBack = true;
                    selectHard = false;
                }
            } else if (selectBack) {
                g.fillRect(210, 360, 200, 64);
                g.setColor(Color.black);
                g.drawString("Back", 270, 400);
                //System.out.println(key.menuDown);
                if (KeyInput.getMenuUp()) {
                    selectHard = true;
                    selectBack = false;
                }
                if (KeyInput.getMenuEnter()) {
                    game.gameState = STATE.Menu;
                    return;
                }
            }

            /*
            g.drawRect(210, 50, 200, 64);
            g.drawString("Easy", 280, 190);
            
            g.drawRect(210, 150, 200, 64);
            g.drawString("Normal", 260, 190);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Hard", 270, 290);

            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);*/
            //color menu
        } else if (game.gameState == STATE.Colors) {
            g.setColor(Color.black);
            //g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
            //background to cover objects
            //handler.addObject(new ColorParticle(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.ColorParticle, handler));
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 20);

            //black boxes for clarity
            //g.setColor(Color.black);
            g.fillRect(25, 80, 100, 80);
            g.fillRect(185, 80, 100, 80);
            g.fillRect(345, 80, 100, 80);
            g.fillRect(505, 80, 100, 80);

            g.fillRect(25, 180, 100, 80);
            g.fillRect(185, 180, 100, 80);
            g.fillRect(345, 180, 100, 80);
            g.fillRect(505, 180, 100, 80);

            g.fillRect(25, 280, 100, 80);
            g.fillRect(185, 280, 100, 80);
            g.fillRect(345, 280, 100, 80);
            g.fillRect(505, 280, 100, 80);
            g.fillRect(210, 370, 200, 64);

            //title
            g.setFont(fnt);
            g.setColor(Color.white);
            if (gold) {
                g.setColor(new Color(212, 175, 55));
            }
            //g.setColor(colorMenu.getColor());
            g.drawString("CHOOSE COLOR", 110, 60);

            //default - start of row 1
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.fillRect(40, 120, 70, 30);
            if (gold) {
                g.setColor(new Color(212, 175, 55));
            }
            g.drawRect(25, 80, 100, 80);
            g.drawString("Default", 42, 110);

            //color2 - blue
            g.drawRect(185, 80, 100, 80);
            g.drawString("Blue", 214, 110);

            //color3 - green
            g.drawRect(345, 80, 100, 80);
            g.drawString("Green", 364, 110);

            //color4 - purple
            g.drawRect(505, 80, 100, 80);
            g.drawString("Purple", 524, 110);

            //color 5 - start of row 2 - orange
            g.drawRect(25, 180, 100, 80);
            g.drawString("Orange", 40, 210);

            //color 6 - pink
            g.drawRect(185, 180, 100, 80);
            g.drawString("Pink", 214, 210);

            //color 7 - gold
            g.drawRect(345, 180, 100, 80);
            g.drawString("Gold", 370, 210);

            //color 8 - random
            g.drawRect(505, 180, 100, 80);
            g.setColor(Color.white);
            g.fillRect(520, 220, 70, 30);
            if (gold) {
                g.setColor(new Color(212, 175, 55));
            }
            g.drawString("Random", 517, 210);

            //color 9 - start of row 3 - red,white,blue
            g.drawRect(25, 280, 100, 80);
            g.drawString("R W B", 45, 310);

            //color 10 - orange, green, purple
            g.drawRect(185, 280, 100, 80);
            g.drawString("O G P", 205, 310);

            //color 11 - sparkle
            g.drawRect(345, 280, 100, 80);

            g.drawString("Sparkle", 360, 310);

            //color 12 - invisible
            g.drawRect(505, 280, 100, 80);
            g.drawString("Invisible", 517, 310);
            //back
            Font fnt3 = new Font("arial", 1, 30);
            g.setFont(fnt3);
            g.drawRect(210, 370, 200, 64);
            g.drawString("Back", 275, 410);

            g.setColor(Color.white);

            //box 3 - green
            //box 4 - purple
            //box 5 - start of row 2 - orange
            //box 6 - pink
            //box 7 - gold
            //box 8 - random
            //box 9 - start of row 3 - red,white,blue
            //box 10 - orange, green, purple
            //box 11 - sparkle
            //box 12 - invisible
            switch (colorType) {
                case 0:
                    g.fillRect(210, 370, 200, 64);
                    g.setColor(Color.black);
                    g.drawString("Back", 275, 410);
                    if (KeyInput.getMenuUp()) {
                        colorType = 10;
                    } else if (KeyInput.getMenuDown()) {
                        colorType = 2;
                    }
                    if (KeyInput.getMenuEnter()) {
                        handler.clearEnemies();
                        for (int i = 0; i < 20; i++) {
                            handler.addObject(new MenuParticle((float) r.nextInt(Game.WIDTH), (float) r.nextInt(Game.HEIGHT), ID.MenuParticle, handler, false));
                        }
                        game.gameState = STATE.Menu;
                    }
                    break;
                case 1:
                    g.setFont(fnt2);
                    g.fillRect(25, 80, 100, 80);
                    g.setColor(Color.black);
                    g.drawString("Default", 42, 110);
                    g.drawRect(40, 120, 70, 30);
                    if (KeyInput.getMenuDown()) {
                        colorType = 5;
                    } else if (KeyInput.getMenuRight()) {
                        colorType = 2;
                    } else if (KeyInput.getMenuUp()) {
                        colorType = 9;
                    } else if (KeyInput.getMenuLeft()) {
                        colorType = 4;
                    }
                    if (KeyInput.getMenuEnter()) {
                        Player.specialColorRWB = false;
                        Player.specialColorSparkle = false;
                        Player.specialColorOGP = false;
                        colorMenu.setColor(Color.white);
                    }
                    break;
                case 2:
                    g.setFont(fnt2);
                    g.fillRect(185, 80, 100, 80);
                    g.setColor(Color.black);
                    g.drawString("Blue", 214, 110);
                    if (KeyInput.getMenuDown()) {
                        colorType = 6;
                    } else if (KeyInput.getMenuRight()) {
                        colorType = 3;
                    } else if (KeyInput.getMenuUp()) {
                        colorType = 0;
                    } else if (KeyInput.getMenuLeft()) {
                        colorType = 1;
                    }
                    if (KeyInput.getMenuEnter()) {
                        Player.specialColorRWB = false;
                        Player.specialColorSparkle = false;
                        Player.specialColorOGP = false;
                        colorMenu.setColor(Color.blue);
                    }
                    break;
                case 3:
                    g.setFont(fnt2);
                    g.fillRect(345, 80, 100, 80);
                    g.setColor(Color.black);
                    g.drawString("Green", 364, 110);
                    if (KeyInput.getMenuDown()) {
                        colorType = 7;
                    } else if (KeyInput.getMenuRight()) {
                        colorType = 4;
                    } else if (KeyInput.getMenuUp()) {
                        colorType = 0;
                    } else if (KeyInput.getMenuLeft()) {
                        colorType = 2;
                    }
                    if (KeyInput.getMenuEnter()) {
                        Player.specialColorRWB = false;
                        Player.specialColorSparkle = false;
                        Player.specialColorOGP = false;
                        colorMenu.setColor(Color.green);
                    }
                    break;
                case 4:
                    g.setFont(fnt2);
                    g.fillRect(505, 80, 100, 80);
                    g.setColor(Color.black);
                    g.drawString("Purple", 524, 110);
                    if (KeyInput.getMenuDown()) {
                        colorType = 8;
                    } else if (KeyInput.getMenuRight()) {
                        colorType = 1;
                    } else if (KeyInput.getMenuUp()) {
                        colorType = 12;
                    } else if (KeyInput.getMenuLeft()) {
                        colorType = 3;
                    }
                    if (KeyInput.getMenuEnter()) {
                        Player.specialColorRWB = false;
                        Player.specialColorSparkle = false;
                        Player.specialColorOGP = false;
                        colorMenu.setColor(128, 0, 128);
                    }
                    break;
                case 5:
                    g.setFont(fnt2);
                    g.fillRect(25, 180, 100, 80);
                    g.setColor(Color.black);
                    g.drawString("Orange", 40, 210);
                    if (KeyInput.getMenuDown()) {
                        colorType = 9;
                    } else if (KeyInput.getMenuRight()) {
                        colorType = 6;
                    } else if (KeyInput.getMenuUp()) {
                        colorType = 1;
                    } else if (KeyInput.getMenuLeft()) {
                        colorType = 8;
                    }
                    if (KeyInput.getMenuEnter()) {
                        Player.specialColorRWB = false;
                        Player.specialColorSparkle = false;
                        Player.specialColorOGP = false;
                        colorMenu.setColor(255, 130, 0);
                    }
                    break;
                case 6:
                    g.setFont(fnt2);
                    g.fillRect(185, 180, 100, 80);
                    g.setColor(Color.black);
                    g.drawString("Pink", 214, 210);
                    if (KeyInput.getMenuDown()) {
                        colorType = 10;
                    } else if (KeyInput.getMenuRight()) {
                        colorType = 7;
                    } else if (KeyInput.getMenuUp()) {
                        colorType = 2;
                    } else if (KeyInput.getMenuLeft()) {
                        colorType = 5;
                    }
                    if (KeyInput.getMenuEnter()) {
                        Player.specialColorRWB = false;
                        Player.specialColorSparkle = false;
                        Player.specialColorOGP = false;
                        colorMenu.setColor(255, 20, 147);
                    }
                    break;
                case 7:
                    g.setFont(fnt2);
                    g.fillRect(345, 180, 100, 80);
                    g.setColor(Color.black);
                    g.drawString("Gold", 370, 210);
                    if (KeyInput.getMenuDown()) {
                        colorType = 11;
                    } else if (KeyInput.getMenuRight()) {
                        colorType = 8;
                    } else if (KeyInput.getMenuUp()) {
                        colorType = 3;
                    } else if (KeyInput.getMenuLeft()) {
                        colorType = 6;
                    }
                    if (KeyInput.getMenuEnter()) {
                        Player.specialColorRWB = false;
                        Player.specialColorSparkle = false;
                        Player.specialColorOGP = false;
                        colorMenu.setColor(212, 175, 55);
                    }
                    break;
                case 8:
                    g.setFont(fnt2);
                    g.fillRect(505, 180, 100, 80);
                    g.drawRect(505, 180, 100, 80);

                    g.setColor(Color.black);
                    g.fillRect(520, 220, 70, 30);
                    g.drawString("Random", 517, 210);
                    if (KeyInput.getMenuDown()) {
                        colorType = 12;
                    } else if (KeyInput.getMenuRight()) {
                        colorType = 5;
                    } else if (KeyInput.getMenuUp()) {
                        colorType = 4;
                    } else if (KeyInput.getMenuLeft()) {
                        colorType = 7;
                    }
                    if (KeyInput.getMenuEnter()) {
                        Player.specialColorRWB = false;
                        Player.specialColorSparkle = false;
                        Player.specialColorOGP = false;
                        colorMenu.setColor(colorMenu.getRandomColor());
                    }

                    break;
                case 9:
                    g.setFont(fnt2);
                    g.fillRect(25, 280, 100, 80);
                    g.setColor(Color.black);
                    g.drawString("R W B", 45, 310);
                    if (KeyInput.getMenuDown()) {
                        colorType = 1;
                    } else if (KeyInput.getMenuRight()) {
                        colorType = 10;
                    } else if (KeyInput.getMenuUp()) {
                        colorType = 5;
                    } else if (KeyInput.getMenuLeft()) {
                        colorType = 12;
                    }
                    if (KeyInput.getMenuEnter()) {
                        Player.specialColorSparkle = false;
                        Player.specialColorRWB = true;
                        Player.specialColorOGP = false;
                    }
                    break;
                case 10:
                    g.setFont(fnt2);
                    g.fillRect(185, 280, 100, 80);
                    g.setColor(Color.black);
                    g.drawString("O G P", 205, 310);
                    if (KeyInput.getMenuDown()) {
                        colorType = 0;
                    } else if (KeyInput.getMenuRight()) {
                        colorType = 11;
                    } else if (KeyInput.getMenuUp()) {
                        colorType = 6;
                    } else if (KeyInput.getMenuLeft()) {
                        colorType = 9;
                    }
                    if (KeyInput.getMenuEnter()) {
                        Player.specialColorRWB = false;
                        Player.specialColorSparkle = false;
                        Player.specialColorOGP = true;
                    }
                    break;
                case 11:
                    g.setFont(fnt2);
                    g.fillRect(345, 280, 100, 80);
                    g.setColor(Color.black);
                    g.drawString("Sparkle", 360, 310);
                    if (KeyInput.getMenuDown()) {
                        colorType = 0;
                    } else if (KeyInput.getMenuRight()) {
                        colorType = 12;
                    } else if (KeyInput.getMenuUp()) {
                        colorType = 7;
                    } else if (KeyInput.getMenuLeft()) {
                        colorType = 10;
                    }
                    if (KeyInput.getMenuEnter()) {
                        //Color.MAGENTA will tell player to override with colorMenu.getRandomColor()
                        Player.specialColorRWB = false;
                        Player.specialColorSparkle = true;
                        Player.specialColorOGP = false;
                    }
                    break;
                case 12:
                    g.setFont(fnt2);
                    g.fillRect(505, 280, 100, 80);
                    g.setColor(Color.black);
                    g.drawString("Invisible", 517, 310);
                    if (KeyInput.getMenuDown()) {
                        colorType = 4;
                    } else if (KeyInput.getMenuRight()) {
                        colorType = 9;
                    } else if (KeyInput.getMenuUp()) {
                        colorType = 8;
                    } else if (KeyInput.getMenuLeft()) {
                        colorType = 11;
                    }
                    if (KeyInput.getMenuEnter()) {
                        colorMenu.setColor(Color.black);
                        Player.specialColorRWB = false;
                        Player.specialColorSparkle = false;
                        Player.specialColorOGP = false;
                    }
                    break;
                default:
                    break;
            }

            //colors
            g.setColor(Color.blue);//185, 80, 100, 80
            g.fillRect(200, 120, 70, 30);
            g.setColor(Color.green);
            g.fillRect(360, 120, 70, 30);
            g.setColor(new Color(128, 0, 128));
            g.fillRect(520, 120, 70, 30);
            g.setColor(new Color(255, 130, 0));
            g.fillRect(40, 220, 70, 30);
            g.setColor(new Color(255, 20, 147));
            g.fillRect(200, 220, 70, 30);
            g.setColor(new Color(212, 175, 55));
            g.fillRect(360, 220, 70, 30);
            g.setColor(Color.black);
            g.setFont(fnt3);
            g.drawString("?", 545, 245);
            g.fillRect(520, 320, 70, 30);
            g.setColor(Color.white);
            g.drawRect(520, 320, 70, 30);

            //red,white,blue
            g.setColor(Color.red);
            g.fillRect(40, 320, 23, 30);
            g.setColor(Color.white);
            g.fillRect(63, 320, 24, 30);
            g.setColor(Color.blue);
            g.fillRect(87, 320, 23, 30);

            //orange,green,purple
            g.setColor(new Color(255, 130, 0));
            g.fillRect(200, 320, 23, 30);
            g.setColor(Color.green);
            g.fillRect(223, 320, 24, 30);
            g.setColor(new Color(128, 0, 128));
            g.fillRect(247, 320, 23, 30);

            g.setColor(colorMenu.getRandomColorMenu());
            g.fillRect(360, 320, 70, 30);
            if (colorType == 8) {
                g.setColor(Color.white);
                g.setFont(fnt3);
                g.drawString("?", 545, 245);
            }
            g.setFont(fnt2);
            //win screen
        } else if (game.gameState == STATE.Win) {
            //similar copy of End
            g.setColor(Color.black);
            g.fillRect(210, 350, 200, 64);
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            if (gold) {
                g.setColor(new Color(212, 175, 55));
            }
            g.drawString("Congratulations,", 120, 70);
            g.drawString("You Win!", 200, 150);
            switch (game.diff) {
                case -1:
                    g.drawString("Mode: Easy", 165, 230);
                    break;
                case 0:
                    g.drawString("Mode: Normal", 145, 230);
                    break;
                case 1:
                    g.drawString("Mode: Hard", 165, 230);
                    break;
                default:
                    break;
            }
            if (KeyInput.getMenuEnter()) {
                game.gameState = STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
                hud.setMoney(0);
            }
            //g.setFont(fnt3);
            //g.drawString("You lost with a score of: " + hud.getScore(), 175, 200);
            g.setFont(fnt2);
            g.fillRect(210, 350, 200, 64);
            g.setColor(Color.black);
            g.drawString("Play Again", 230, 390);
            g.setColor(Color.white);
        }
    }
}

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

    public static boolean gold,hard;
    private Game game;
    private Handler handler;
    private Random r = new Random();
    private HUD hud;
    private ColorMenu colorMenu = new ColorMenu();
    //private ColorParticle colorParticle = new ColorParticle();

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
                System.exit(1);
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
            
            
            if(gold){
            g.setColor(new Color(212, 175, 55));
            Polygon star = new Polygon();
            int[] x = {142, 152, 172, 152, 160, 140, 115, 128, 109, 132, 142};
            int[] y = {38, 62, 68, 80, 105, 85, 102, 75, 58, 60, 38};
            g.fillPolygon(x, y, 11);}
            if(hard){
            Polygon star2 = new Polygon();
            int[] x = {472, 482, 502, 482, 490, 470, 445, 458, 439, 462, 472};
            int[] y = {38, 62, 68, 80, 105, 85, 102, 75, 58, 60, 38};
            g.fillPolygon(x, y, 11);}
            
            g.setFont(fnt3);
            g.drawString("Bianucci Games Presents:", 190, 25);
            //Graphics2D g2d = (Graphics2D)g;
            g.drawString("ver 1.1", 390,80);
            
            //g.fillPolygon();
            g.setFont(fnt);
            g.drawString("PARTICLE", 185, 70);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 270, 190);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 270, 290);

            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 270, 390);

            g.drawString("Color", 25, 380);
            g.drawRect(15, 350, 100, 80);

            //the color menu will determine the color that goes here
            if (colorMenu.getColor().equals(Color.black)) {
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
            }
            //help menu
        } else if (game.gameState == STATE.Help) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setColor(Color.black);
            g.fillRect(210, 350, 200, 64);

            g.setFont(fnt);
            g.setColor(Color.white);
            if(gold){g.setColor(new Color(212, 175, 55));}
            g.drawString("Help", 251, 70);

            g.setFont(fnt3);
            g.drawString("Use WASD keys to move player and dodge enemies", 70, 150);
            g.drawString("Press \"p\" to pause and \"ESC\" to quit", 140, 200);
            g.drawString("In NORMAL or EASY mode, press\"SPACE\" to shop", 75, 250);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);
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
            if(gold){g.setColor(new Color(212, 175, 55));}
            g.setFont(fnt3);
            switch (game.diff) {
                case -1:
                    g.drawString("Mode: Easy", 250,175);
                    break;
                case 0:
                    g.drawString("Mode: Normal", 250,175);
                    break;
                case 1:
                    g.drawString("Mode: Hard", 250,175);
                    break;
                default:
                    break;
            }
            g.drawString("You lost with a score of: " + hud.getScore(), 175, 200);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Try Again", 245, 390);
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
            if(gold){g.setColor(new Color(212, 175, 55));}
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
            if(gold){g.setColor(new Color(212, 175, 55));}
            //g.setColor(colorMenu.getColor());
            g.drawString("CHOOSE COLOR", 110, 60);

            //default - start of row 1
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.fillRect(40, 120, 70, 30);
            if(gold){g.setColor(new Color(212, 175, 55));}
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
            if(gold){g.setColor(new Color(212, 175, 55));}
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
            fnt2 = new Font("arial", 1, 30);
            g.setFont(fnt2);
            g.drawRect(210, 370, 200, 64);
            g.drawString("Back", 275, 410);

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
            if(gold){g.setColor(new Color(212, 175, 55));}
            g.drawString("Congratulations,", 120, 70);
            g.drawString("You Win!", 200, 150);
            switch (game.diff) {
                case -1:
                    g.drawString("Mode: Easy", 165,230);
                    break;
                case 0:
                    g.drawString("Mode: Normal", 145,230);
                    break;
                case 1:
                    g.drawString("Mode: Hard", 165,230);
                    break;
                default:
                    break;
            }
            //g.setFont(fnt3);
            //g.drawString("You lost with a score of: " + hud.getScore(), 175, 200);
            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Play Again", 230, 390);
        }
    }
}

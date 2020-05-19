package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Zachary Bianucci
 */
public class Player extends GameObject {

    private Random r = new Random();
    private Handler handler;
    private Game game;
    public static boolean specialColorSparkle = false, specialColorRWB = false, specialColorOGP = false;

    public Player(float x, float y, ID id, Handler handler, Game game) {
        super(x, y, id);
        this.handler = handler;
        this.game = game;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp((int) x, 0, Game.WIDTH - 37);
        y = Game.clamp((int) y, 0, Game.HEIGHT - 60);

        ColorMenu colorMenu = new ColorMenu();
        //Zach you need to change the constructor of the Trail Class to where the Color Menu has control of the colors therefore
        //255,255,255 should just be Color color
        //colorMenu.setColor(Color.white); 
        if (specialColorSparkle) {
            handler.addObject(new Trail(x, y, ID.Trail, colorMenu.getRandomColor(), 32, 32, 0.05f, handler));

        } else if (specialColorRWB) {
            handler.addObject(new Trail(x, y, ID.Trail, colorMenu.getRWB(), 32, 32, 0.05f, handler));

        } else if (specialColorOGP) {
            handler.addObject(new Trail(x, y, ID.Trail, colorMenu.getOGP(), 32, 32, 0.05f, handler));
        } else {
            handler.addObject(new Trail(x, y, ID.Trail, colorMenu.getColor(), 32, 32, 0.05f, handler));
        }

        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);//tempObject is now basic enemy

            if (tempObject.getID() == ID.OrangeBoss || tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.BlueBoss || tempObject.getID() == ID.FastEnemy || tempObject.getID() == ID.SmartEnemy || tempObject.getID() == ID.EnemyBoss) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //collision code
                    //fix this

                    if (game.diff == 0||game.diff==-1) {
                        HUD.HEALTH -= 1;
                    } else if (game.diff == 1) {
                        HUD.HEALTH2 -= 1;
                    }
                }
            } else if (tempObject.getID() == ID.FinalBoss && getBounds().intersects(tempObject.getBounds())) {
                for (int j = 0; j < handler.object.size(); j++) {
                    GameObject tempObject2 = handler.object.get(j);
                    if (tempObject2.getID() == ID.InvisibleBoss && !getBounds().intersects(tempObject2.getBounds())) {
                        if (game.diff == 0||game.diff==-1) {
                            HUD.HEALTH -= 1;
                        } else if (game.diff == 1) {
                            HUD.HEALTH2 -= 1;
                        }
                    }
                }
            }
//if(tempObject.getID()==ID.InvisibleBoss&&getBounds().intersects){

            //}
        }
    }

    public void render(Graphics g) {
        ColorMenu colorMenu = new ColorMenu();

        if (id == ID.Player) {
            //colorMenu.setColor(Color.white);

            g.setColor(colorMenu.getColor());
        }
        g.fillRect((int) x, (int) y, 32, 32);
    }
}

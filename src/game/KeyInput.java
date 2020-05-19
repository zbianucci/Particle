package game;

import game.Game.STATE;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Zachary Bianucci
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    public static int speed = 5;
    Game game;

    public KeyInput(Handler handler, Game game) {
        this.game = game;
        this.handler = handler;
        //game = new Game();
    }

    public void keyPressed(KeyEvent e) {
        if (game.diff == 1) {
            speed = 7;
        }
        //when you press a key on the keyboard it will display a number value that corresponds
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Player) {
                //key events for player 1
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    up = true;
                    tempObject.setVelY(-speed);
                }
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    down = true;
                    tempObject.setVelY(speed);
                }
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    tempObject.setVelX(speed);
                    right = true;
                }
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(-speed);
                    left = true;
                }
            }
        }
        if (key == KeyEvent.VK_P || (game.diff == 1 && key == KeyEvent.VK_SPACE)) {

            if (game.gameState == STATE.Game) {
                if (Game.paused) {

                    Game.paused = false;
                } else {
                    Game.paused = true;
                }
            }
        }
        if (key == KeyEvent.VK_ESCAPE) {
            if (game.gameState == STATE.Menu) {
                System.exit(1);
            }else if (game.gameState==STATE.Game&&game.diff!=1){
                HUD.HEALTH=-1;
            }else if (game.gameState==STATE.Game&&game.diff==1){
                HUD.HEALTH2=-1;
            }
        }
        /*
        else if(key==KeyEvent.VK_ESCAPE){
            game.gameState=STATE.End;
            //HUD.HEALTH-=10000;
            //HUD.HEALTH2-=20000;
            //HUD.HEALTH=100;
            //HUD.HEALTH2=200;
        /*
        if(HUD.HEALTH<0||HUD.HEALTH2<0){
            HUD.HEALTH=100;
            HUD.HEALTH2=200;
        }*/
        //access the shop via space
        if (key == KeyEvent.VK_SPACE && (game.diff == 0 || game.diff == -1)) {
            if (Game.gameState == STATE.Game) {
                Game.gameState = STATE.Shop;
            } else if (Game.gameState == STATE.Shop) {
                Game.gameState = STATE.Game;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Player) {
                //key events for player 1
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    up = false;
                    if (down) {
                        tempObject.setVelY(speed);
                    } else {
                        tempObject.setVelY(0);
                    }
                }
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    down = false;
                    if (up) {
                        tempObject.setVelY(-speed);
                    } else {
                        tempObject.setVelY(0);
                    }
                }
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    right = false;
                    if (left) {
                        tempObject.setVelX(-speed);
                    } else {
                        tempObject.setVelX(0);
                    }
                }
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    left = false;
                    if (right) {
                        tempObject.setVelX(speed);
                    } else {
                        tempObject.setVelX(0);
                    }
                }
            }
        }
    }

    public void setDirections(boolean truth) {
        up = truth;
        down = truth;
        left = truth;
        right = truth;
    }
}

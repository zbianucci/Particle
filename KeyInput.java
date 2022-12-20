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

    public static boolean menuUp = false;
    public static boolean menuDown = false;
    public static boolean menuLeft = false;
    public static boolean menuRight = false;
    public static boolean menuEnter = false;

    private static int menuUpCount = 0;
    private static int menuDownCount = 0;
    private static int menuLeftCount = 0;
    private static int menuRightCount = 0;
    private static int menuEnterCount = 0;
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
                if (key == KeyEvent.VK_UP) {//key == KeyEvent.VK_W ||
                    up = true;
                    tempObject.setVelY(-speed);
                }
                if (key == KeyEvent.VK_DOWN) {//key == KeyEvent.VK_S ||
                    down = true;
                    tempObject.setVelY(speed);
                }
                if (key == KeyEvent.VK_RIGHT) {//key == KeyEvent.VK_D ||
                    tempObject.setVelX(speed);
                    right = true;
                }
                if (key == KeyEvent.VK_LEFT) {//key == KeyEvent.VK_A ||
                    tempObject.setVelX(-speed);
                    left = true;
                }
            }
        }
        //if (key == KeyEvent.VK_P || (game.diff == 1 && key == KeyEvent.VK_SPACE)) {
        if (game.diff == 1 && key == KeyEvent.VK_I) {
            if (game.gameState == STATE.Game) {
                if (Game.paused) {

                    Game.paused = false;
                } else {
                    Game.paused = true;
                }
            }
        }
//        if (key == KeyEvent.VK_ESCAPE) {
        if (key == KeyEvent.VK_SPACE) {
            if (game.gameState == STATE.Menu || game.gameState == STATE.Win || game.gameState == STATE.End) {
                System.exit(0);
            } else if (game.gameState == STATE.Game && game.diff != 1) {
                HUD.HEALTH = -1;
            } else if (game.gameState == STATE.Game && game.diff == 1) {
                HUD.HEALTH2 = -1;
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
        if (key == KeyEvent.VK_I && (game.diff == 0 || game.diff == -1)) {
            if (Game.gameState == STATE.Game) {
                Game.gameState = STATE.Shop;
            } else if (Game.gameState == STATE.Shop) {
                Game.gameState = STATE.Game;
            }
        }
        //controls for menu selection
        if (game.gameState == STATE.Menu || game.gameState == STATE.Colors || game.gameState == STATE.Select || game.gameState == STATE.Help || game.gameState == STATE.End || game.gameState == STATE.Win || game.gameState == STATE.Shop) {
            if (key == KeyEvent.VK_UP) {//key == KeyEvent.VK_W ||
                menuUpCount++;
                menuUp = true;
            }
            if (key == KeyEvent.VK_DOWN) {//key == KeyEvent.VK_S ||
                menuDownCount++;
                menuDown = true;
            }
            if (key == KeyEvent.VK_RIGHT) {//key == KeyEvent.VK_D ||
                menuRightCount++;
                menuRight = true;
            }
            if (key == KeyEvent.VK_LEFT) {//key == KeyEvent.VK_A ||
                menuLeftCount++;
                menuLeft = true;
            }
            if (key == KeyEvent.VK_I) {
                menuEnterCount++;
                menuEnter = true;
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
        //controls for menu selection
//        if (game.gameState == STATE.Menu || game.gameState == STATE.Colors || game.gameState == STATE.Select || game.gameState == STATE.Help || g) {
        if (game.gameState != STATE.Game) {
            if (key == KeyEvent.VK_UP) {//key == KeyEvent.VK_W ||
                menuUp = false;
            }
            if (key == KeyEvent.VK_DOWN) {//key == KeyEvent.VK_S ||
                menuDown = false;
            }
            if (key == KeyEvent.VK_RIGHT) {//key == KeyEvent.VK_D ||
                menuRight = false;
            }
            if (key == KeyEvent.VK_LEFT) {//key == KeyEvent.VK_A ||
                menuLeft = false;
            }
            if (key == KeyEvent.VK_I) {
                menuEnter = false;
            }
        }
//        }
//        if (game.gameState == STATE.Game && key == KeyEvent.VK_ENTER) {
//            menuEnter = false;
//            System.out.println("ENTER-RELEASE");
//        }

    }

    //maybe these methods should increment every time they are called when it is true.
    //When the number is high, and the menuUp, menuDown, etc. are false, we reset to zero and return true.
    //the above should reset 
    public static boolean getMenuUp() {
        //key is released but has been down for an amount of time
        if (!menuUp && menuUpCount > 0) {
            menuUpCount = 0;
            return true;
        }
        return false;
    }

    public static boolean getMenuDown() {
        if (!menuDown && menuDownCount > 0) {
            menuDownCount = 0;
            return true;
        }
        return false;
    }

    public static boolean getMenuLeft() {
        if (!menuLeft && menuLeftCount > 0) {
            menuLeftCount = 0;
            return true;
        }
        return false;
    }

    public static boolean getMenuRight() {
        if (!menuRight && menuRightCount > 0) {
            menuRightCount = 0;
            return true;
        }
        return false;
    }

    public static boolean getMenuEnter() {
        if (!menuEnter && menuEnterCount > 0) {
            menuEnterCount = 0;
            return true;
        }
        return false;
    }

    public void setDirections(boolean truth) {
        up = truth;
        down = truth;
        left = truth;
        right = truth;
    }
}

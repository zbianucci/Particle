/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.Game.STATE;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Zachary Bianucci
 */
public class Spawn {

    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    private Game game;
    private ColorMenu colorMenu = new ColorMenu();

    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    public int getScoreKeep() {
        return scoreKeep;
    }

    public void tick() {
        scoreKeep++;
        //level increases to do spawns
        if (scoreKeep >= 500) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            //easy difficulty
            if (game.diff == -1) {
                switch (hud.getLevel()) {
                    case 4:
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 4, 4, new Color(204, 0, 0)));
                        break;
                    case 7:
                        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                        break;
                    case 10://red boss
                        handler.clearEnemies();
                        handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler, 20));
                        break;
                    case 12:
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 5, 5, new Color(204, 0, 0)));
                        break;
                    case 14:
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler, 1, 1));
                        break;
                    case 15:
                        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                        break;
                    case 18:
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler, 2, 2));
                    case 19://orange boss
                        handler.clearEnemies();
                        handler.addObject(new OrangeBoss(0, 0, ID.OrangeBoss, handler, 30));
                        handler.addObject(new OrangeBoss2(Game.WIDTH - 48, Game.HEIGHT, ID.OrangeBoss, handler, 30));
                        break;
                    case 24:
                        handler.clearEnemies();
                        //handler.addObject(new TargetBullet(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 5,5, Color.orange));
                        handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 5, 5, new Color(255, 165, 0)));
                        break;
                    case 28:
                        handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 5, 5, new Color(255, 165, 0)));
                        break;
                    case 30:
                        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                        break;
                    case 32: //yellow boss
                        handler.clearEnemies();
                        handler.addObject(new YellowBoss((Game.WIDTH / 2) - 56, -120, ID.BasicEnemy, handler, 70, 96));
                        //bosses in the four corners
                        handler.addObject(new YellowBoss(0, 0, ID.BasicEnemy, handler, 70, 32));
                        handler.addObject(new YellowBoss(Game.WIDTH - 39, Game.HEIGHT - 60, ID.BasicEnemy, handler, 70, 32));
                        break;
                    case 35:
                        handler.clearEnemies();
                        handler.addObject(new TargetBullet(-10, -10, ID.FastEnemy, handler, 3, 3, Color.yellow, 15, 100));
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 5, 5, Color.red));
                        break;
                    case 36:
                        handler.addObject(new TargetBullet(-10, Game.HEIGHT + 10, ID.FastEnemy, handler, 5, 5, Color.yellow, 15, 100));
                        break;
                    case 40:
                        handler.addObject(new TargetBullet(-10, -10, ID.FastEnemy, handler, 6, 6, Color.yellow, 20, 165));
                        break;
                    case 42:
                        handler.addObject(new TargetBullet(-10, -10, ID.FastEnemy, handler, 5, 5, Color.yellow, 17, 70));
                        break;
                    case 46: //green boss
                        handler.clearEnemies();
                        handler.addObject(new GreenBoss((Game.WIDTH / 2) - 56, -120, ID.BasicEnemy, handler, 1, 1, 35, 3));
                        break;
                    case 50:
                        handler.clearEnemies();
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 30, true, false, false, 5));
                        break;
                    case 52:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 28, false, true, false, 5));
                        break;
                    case 53:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 25, true, false, false, 5));
                        break;
                    case 54:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 25, false, true, false, 5));
                        break;
                    case 55:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 20, true, true, false, 5));
                        break;
                    case 57:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 20, true, false, false, 5));
                        break;
                    case 58:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 20, false, true, false, 5));
                        break;
                    case 59:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 15, true, true, false, 5));
                        break;
                    case 60://blue boss
                        handler.clearEnemies();
                        handler.addObject(new BlueBoss(-96, -96, ID.BlueBoss, handler, 1, 1, new Color(51, 153, 255), 25));
                        break;
                    case 65:
                        handler.clearEnemies();
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 25, false, false, true, 5));
                        break;
                    case 66:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 25, false, false, true, 6));
                        break;
                    case 67:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 25, false, false, true, 7));
                        break;
                    case 68:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 30, true, false, true, 7));
                        break;
                    case 69:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 30, false, true, true, 7));
                        break;
                    case 70:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 30, true, true, true, 7));
                        break;
                    case 72:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 25, false, false, true, 5));
                        break;
                    case 73:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 25, false, false, true, 6));
                        break;
                    case 74:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 25, false, false, true, 7));
                        break;
                    case 76:
                        handler.clearEnemies();
                        //Indigo boss
                        handler.clearEnemies();
                        handler.addObject(new IndigoBoss(1, 1, ID.BasicEnemy, handler, 3, 3, new Color(75, 0, 130)));
                        break;
                    case 81:
                        handler.clearEnemies();
                        handler.addObject(new FinalBoss(0, 0, ID.FinalBoss, handler, Game.WIDTH, Game.HEIGHT));
                        handler.addObject(new InvisibleBoss(50, 50, ID.InvisibleBoss, handler, 50, 50, 96 * 2,1,1));
                        break;
                    case 92:
                        if (colorMenu.getColor().equals(Color.black)) {
                            Menu.gold = true;
                        }
                        handler.clearEnemies();
                        game.gameState = STATE.Win;
                        handler.clearEnemies();
                        break;
                    default:
                        break;
                }
            } //normal difficulty
            else if (game.diff == 0) {
                switch (hud.getLevel()) {
                    case 3:
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 5, 5, new Color(204, 0, 0)));
                        break;
                    case 4:
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 5, 5, new Color(204, 0, 0)));
                        break;
                    case 5:
                        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                        break;
                    case 6:
                        break;
                    case 7:
                        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                        break;

                    case 10://red boss
                        handler.clearEnemies();
                        handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler, 20));
                        break;
                    case 12:
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 5, 5, new Color(204, 0, 0)));
                        break;
                    case 13:
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler, 1, 1));
                        break;
                    case 14:
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler, 2, 2));
                        break;
                    case 15:
                        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                        break;
                    case 16:
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler, 3, 3));
                        break;
                    case 17:
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler, 4, 4));
                        break;
                    case 18:
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler, 5, 5));
                        break;
                    case 19://orange boss
                        handler.clearEnemies();
                        handler.addObject(new OrangeBoss(0, 0, ID.OrangeBoss, handler, 15));
                        handler.addObject(new OrangeBoss2(Game.WIDTH - 48, Game.HEIGHT, ID.OrangeBoss, handler, 15));
                        break;
                    case 24:
                        handler.clearEnemies();
                        //handler.addObject(new TargetBullet(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 5,5, Color.orange));
                        handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 7, 7, new Color(255, 165, 0)));
                        break;
                    case 25:
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 7, 4, Color.red));
                        break;
                    case 26:
                        handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 8, 8, new Color(255, 165, 0)));
                        break;
                    case 28:
                        handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 5, 5, new Color(255, 165, 0)));
                        break;
                    case 29:
                        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                        break;
                    case 31:
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler, 2, 4));
                        break;
                    case 32: //yellow boss
                        handler.clearEnemies();
                        handler.addObject(new YellowBoss((Game.WIDTH / 2) - 56, -120, ID.BasicEnemy, handler, 50, 96));
                        //bosses in the four corners
                        handler.addObject(new YellowBoss(0, 0, ID.BasicEnemy, handler, 50, 32));
                        handler.addObject(new YellowBoss(0, Game.HEIGHT - 60, ID.BasicEnemy, handler, 50, 32));
                        handler.addObject(new YellowBoss(Game.WIDTH - 39, Game.HEIGHT - 60, ID.BasicEnemy, handler, 50, 32));
                        handler.addObject(new YellowBoss(Game.WIDTH - 39, 0, ID.BasicEnemy, handler, 50, 32));
                        break;
                    case 35:
                        handler.clearEnemies();
                        handler.addObject(new TargetBullet(-10, -10, ID.FastEnemy, handler, 5, 5, Color.yellow, 20, 60));
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 5, 5, Color.red));
                        break;
                    case 36:
                        handler.addObject(new TargetBullet(-10, Game.HEIGHT + 10, ID.FastEnemy, handler, 5, 5, Color.yellow, 20, 80));
                        break;
                    case 37:
                        handler.addObject(new TargetBullet(Game.WIDTH + 10, Game.HEIGHT + 10, ID.FastEnemy, handler, 5, 5, Color.yellow, 20, 40));
                        break;
                    case 38:
                        handler.addObject(new TargetBullet(Game.WIDTH, -10, ID.FastEnemy, handler, 5, 5, Color.yellow, 20, 70));
                        break;
                    case 40:
                        handler.addObject(new TargetBullet(-10, -10, ID.FastEnemy, handler, 6, 6, Color.yellow, 20, 165));
                        handler.addObject(new TargetBullet(Game.WIDTH + 10, Game.HEIGHT + 10, ID.FastEnemy, handler, 5, 5, Color.yellow, 20, 30));
                        break;
                    case 41:
                        handler.addObject(new TargetBullet(Game.WIDTH + 10, -10, ID.FastEnemy, handler, 5, 5, Color.yellow, 20, 68));
                        handler.addObject(new TargetBullet(-10, Game.HEIGHT + 10, ID.FastEnemy, handler, 5, 5, Color.yellow, 20, 45));
                        break;
                    case 42:
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 8, 4, Color.cyan));
                        handler.addObject(new TargetBullet(Game.WIDTH + 10, Game.HEIGHT + 10, ID.FastEnemy, handler, 5, 5, Color.yellow, 17, 50));
                        handler.addObject(new TargetBullet(-10, -10, ID.FastEnemy, handler, 7, 7, Color.yellow, 17, 60));
                        break;
                    case 46: //green boss
                        handler.clearEnemies();
                        handler.addObject(new GreenBoss((Game.WIDTH / 2) - 56, -120, ID.BasicEnemy, handler, 1, 1, 15, 7));
                        break;
                    case 50:
                        handler.clearEnemies();
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 20, true, false, false, 5));
                        break;
                    case 51:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 15, false, true, false, 5));
                        break;
                    case 52:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 15, true, true, false, 5));
                        //handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 8, 4, Color.cyan));
                        break;
                    case 53:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 7, true, false, false, 5));
                        break;
                    case 54:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 7, false, true, false, 5));
                        break;
                    case 55:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 10, true, true, false, 5));
                        break;
                    case 56:
                        handler.addObject(new TargetBullet(Game.WIDTH + 10, Game.HEIGHT + 10, ID.FastEnemy, handler, 4, 4, Color.yellow, 30, 60));
                        break;
                    case 57:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 20, true, false, false, 5));
                        break;
                    case 58:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 20, false, true, false, 5));
                        break;
                    case 59:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 13, true, true, false, 5));
                        break;
                    case 60://blue boss
                        handler.clearEnemies();
                        handler.addObject(new BlueBoss(-96, -96, ID.BlueBoss, handler, 1, 1, new Color(51, 153, 255), 10));
                        break;
                    case 65:
                        handler.clearEnemies();
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 15, false, false, true, 5));
                        break;
                    case 66:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 15, false, false, true, 6));
                        break;
                    case 67:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 15, false, false, true, 7));
                        break;
                    case 68:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 17, true, false, true, 7));
                        break;
                    case 69:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 17, false, true, true, 7));
                        break;
                    case 70:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 20, true, true, true, 7));
                        break;
                    case 71:
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 6, 6, new Color(204, 0, 0)));
                        break;
                    case 72:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 15, false, false, true, 5));
                        break;
                    case 73:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 15, false, false, true, 6));
                        break;
                    case 74:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 15, false, false, true, 7));
                        break;
                    case 76:
                        handler.clearEnemies();
                        //Indigo boss
                        handler.clearEnemies();
                        handler.addObject(new IndigoBoss(1, 1, ID.BasicEnemy, handler, 5, 5, new Color(75, 0, 130)));
                        break;
                    case 81:
                        handler.clearEnemies();
                        handler.addObject(new FinalBoss(0, 0, ID.FinalBoss, handler, Game.WIDTH, Game.HEIGHT));
                        handler.addObject(new InvisibleBoss(50, 50, ID.InvisibleBoss, handler, 50, 50, 96,1,1));
                        break;
                    case 92:
                        if (colorMenu.getColor().equals(Color.black)) {
                            Menu.gold = true;
                        }
                        handler.clearEnemies();
                        game.gameState = STATE.Win;
                        handler.clearEnemies();
                        break;
                    default:
                        break;
                }
            } else if (game.diff == 1) {

                switch (hud.getLevel()) {
                    case 3:
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 5, 5, new Color(204, 0, 0)));
                        break;
                    case 4:
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 5, 5, new Color(204, 0, 0)));
                        break;
                    case 5:
                        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                        break;
                    case 6:
                        break;
                    case 7:
                        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                        break;

                    case 10://red boss
                        handler.clearEnemies();
                        handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler, 15));
                        break;
                    case 12:
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 5, 5, new Color(204, 0, 0)));
                        break;
                    case 13:
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler, 1, 1));
                        break;
                    case 14:
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler, 2, 2));
                        break;
                    case 15:
                        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                        break;
                    case 16:
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler, 3, 3));
                        break;
                    case 17:
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler, 4, 4));
                        break;
                    case 18:
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler, 5, 5));
                        break;
                    case 19://orange boss
                        handler.clearEnemies();
                        handler.addObject(new OrangeBoss(0, 0, ID.OrangeBoss, handler, 15));
                        handler.addObject(new OrangeBoss2(Game.WIDTH - 48, Game.HEIGHT, ID.OrangeBoss, handler, 15));
                        break;
                    case 24:
                        handler.clearEnemies();
                        //handler.addObject(new TargetBullet(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 5,5, Color.orange));
                        handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 7, 7, new Color(255, 165, 0)));
                        break;
                    case 25:
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 7, 4, Color.red));
                        break;
                    case 26:
                        handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 8, 8, new Color(255, 165, 0)));
                        break;
                    case 28:
                        handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 5, 5, new Color(255, 165, 0)));
                        break;
                    case 29:
                        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                        break;
                    case 31:
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler, 2, 4));
                        break;
                    case 32: //yellow boss
                        handler.clearEnemies();
                        handler.addObject(new YellowBoss((Game.WIDTH / 2) - 56, -120, ID.BasicEnemy, handler, 50, 96));
                        //bosses in the four corners
                        handler.addObject(new YellowBoss(0, 0, ID.BasicEnemy, handler, 50, 32));
                        handler.addObject(new YellowBoss(0, Game.HEIGHT - 60, ID.BasicEnemy, handler, 50, 32));
                        handler.addObject(new YellowBoss(Game.WIDTH - 39, Game.HEIGHT - 60, ID.BasicEnemy, handler, 50, 32));
                        handler.addObject(new YellowBoss(Game.WIDTH - 39, 0, ID.BasicEnemy, handler, 50, 32));
                        break;
                    case 35:
                        handler.clearEnemies();
                        handler.addObject(new TargetBullet(-10, -10, ID.FastEnemy, handler, 5, 5, Color.yellow, 20, 60));
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 5, 5, Color.red));
                        break;
                    case 36:
                        handler.addObject(new TargetBullet(-10, Game.HEIGHT + 10, ID.FastEnemy, handler, 5, 5, Color.yellow, 20, 80));
                        break;
                    case 37:
                        handler.addObject(new TargetBullet(Game.WIDTH + 10, Game.HEIGHT + 10, ID.FastEnemy, handler, 5, 5, Color.yellow, 20, 40));
                        break;
                    case 38:
                        handler.addObject(new TargetBullet(Game.WIDTH, -10, ID.FastEnemy, handler, 5, 5, Color.yellow, 20, 70));
                        break;
                    case 40:
                        handler.addObject(new TargetBullet(-10, -10, ID.FastEnemy, handler, 6, 6, Color.yellow, 20, 165));
                        handler.addObject(new TargetBullet(Game.WIDTH + 10, Game.HEIGHT + 10, ID.FastEnemy, handler, 5, 5, Color.yellow, 20, 30));
                        break;
                    case 41:
                        handler.addObject(new TargetBullet(Game.WIDTH + 10, -10, ID.FastEnemy, handler, 5, 5, Color.yellow, 20, 68));
                        handler.addObject(new TargetBullet(-10, Game.HEIGHT + 10, ID.FastEnemy, handler, 5, 5, Color.yellow, 20, 45));
                        break;
                    case 42:
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 8, 4, Color.cyan));
                        handler.addObject(new TargetBullet(Game.WIDTH + 10, Game.HEIGHT + 10, ID.FastEnemy, handler, 5, 5, Color.yellow, 17, 50));
                        handler.addObject(new TargetBullet(-10, -10, ID.FastEnemy, handler, 7, 7, Color.yellow, 17, 60));
                        break;
                    case 46: //green boss
                        handler.clearEnemies();
                        handler.addObject(new GreenBoss((Game.WIDTH / 2) - 56, -120, ID.BasicEnemy, handler, 1, 1, 15, 7));
                        break;
                    case 50:
                        handler.clearEnemies();
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler, 1, 1));
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 20, true, false, false, 5));
                        break;
                    case 51:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 15, false, true, false, 5));
                        break;
                    case 52:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 15, true, true, false, 5));
                        //handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 8, 4, Color.cyan));
                        break;
                    case 53:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 7, true, false, false, 5));
                        break;
                    case 54:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 7, false, true, false, 5));
                        break;
                    case 55:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 10, true, true, false, 5));
                        break;
                    case 56:
                        handler.addObject(new TargetBullet(Game.WIDTH + 10, Game.HEIGHT + 10, ID.FastEnemy, handler, 4, 4, Color.yellow, 30, 60));
                        break;
                    case 57:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 20, true, false, false, 5));
                        break;
                    case 58:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 20, false, true, false, 5));
                        break;
                    case 59:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 13, true, true, false, 5));
                        break;
                    case 60://blue boss
                        handler.clearEnemies();
                        handler.addObject(new BlueBoss(-96, -96, ID.BlueBoss, handler, 1, 1, new Color(51, 153, 255), 8));
                        break;
                    case 65:
                        handler.clearEnemies();
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler, 2, 2));
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 15, false, false, true, 5));
                        break;
                    case 66:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 15, false, false, true, 6));
                        break;
                    case 67:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 15, false, false, true, 7));
                        break;
                    case 68:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 17, true, false, true, 7));
                        break;
                    case 69:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 17, false, true, true, 7));
                        break;
                    case 70:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 20, true, true, true, 7));
                        break;
                    case 71:
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 6, 6, new Color(204, 0, 0)));
                        break;
                    case 72:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 15, false, false, true, 5));
                        break;
                    case 73:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 15, false, false, true, 6));
                        break;
                    case 74:
                        handler.addObject(new StraightBulletsOperator(0, 0, ID.BasicEnemy, handler, 15, false, false, true, 7));
                        break;
                    case 76:
                        handler.clearEnemies();
                        //Indigo boss
                        handler.clearEnemies();
                        handler.addObject(new IndigoBoss(1, 1, ID.BasicEnemy, handler, 5, 5, new Color(75, 0, 130)));
                        break;
                    case 81:
                        handler.clearEnemies();
                        handler.addObject(new FinalBoss(0, 0, ID.FinalBoss, handler, Game.WIDTH, Game.HEIGHT));
                        handler.addObject(new InvisibleBoss(50, 50, ID.InvisibleBoss, handler, 50, 50, 96,2,2));
                        break;
                    case 92:
                        if (colorMenu.getColor().equals(Color.black)) {
                            Menu.gold = true;
                        }
                        Menu.hard = true;
                        handler.clearEnemies();
                        game.gameState = STATE.Win;
                        handler.clearEnemies();
                        break;
                    default:
                        break;
                }
            }
        }
    }
}

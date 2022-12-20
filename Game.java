/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 *
 * @author Zachary Bianucci
 */
public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 2L;

    //16 by 9 ratio? for window
    public static final int WIDTH = 640, HEIGHT = 480;
    //public static final int WIDTH = 1920, HEIGHT = 1080;
    private Thread thread;
    //is it running or not running OR is the thread on or not on?
    private boolean running = false, truth = true;

    public static boolean paused = false;
    public int diff = 0;
    //-1=easy
    //0=normal
    //1=hard

    private Window window;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    private Random r = new Random();
    private KeyInput keys;
    private Shop shop;
    private ColorMenu colorMenu;
    private Music music = new Music();

    //now makes STATE an instance variable
    public enum STATE {
        Menu,
        Select,
        Game,
        Help,
        Shop,
        End,
        Colors,
        Win
    };

    public static STATE gameState = STATE.Menu;

    public Game() {

        handler = new Handler();
        hud = new HUD();
        shop = new Shop(this, handler, hud);
        menu = new Menu(this, handler, hud);
        keys = new KeyInput(handler, this);
        this.addKeyListener(keys);
        this.addMouseListener(menu);
        this.addMouseListener(shop);

        //Music.load();
        //Music.getMusic("music").loop();
        window = new Window(WIDTH, HEIGHT, "PARTICLE", this);
        

        spawner = new Spawn(handler, hud, this);

        r = new Random();
        if (gameState == STATE.Game) {
            //spawns the player in the middle of the screen with WIDTH/2-32 etc.
            handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, this));
            handler.addObject(new BasicEnemy(Game.WIDTH - 50, Game.HEIGHT - 50, ID.BasicEnemy, handler, 5, 5, new Color(204, 0, 0)));
        } else if (gameState == STATE.Colors) {
            // handler.clearEnemies();
            // handler.addObject(new ColorParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.ColorParticle, handler));
        } else if (gameState == STATE.Menu) {
            for (int i = 0; i < 20; i++) {
                handler.addObject(new MenuParticle((float) r.nextInt(WIDTH), (float) r.nextInt(HEIGHT), ID.MenuParticle, handler, false));
            }
        }
        //music.playMusic();
    }
    //starts the thread

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    //starts the thread
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            //run an error bug in the console, tells me why it couldn't do something
            e.printStackTrace();
        }
    }/*
    @Override
    public void run() {

        this.requestFocus();
        //the game loop! very important and complicated, a lot of math
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }*/
 /*
    public void run() {
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0 ;
    while (running) {
        long now = System.nanoTime();
        delta += (now - lastTime) / ns;
        lastTime = now;
        while (delta >= 1) {
            tick();
            delta--;
        }
        if(running)
            render();
        frames++;

        if(System.currentTimeMillis() - timer > 1000) {
            timer += 1000;
            System.out.println("FPS: " + frames);
            frames = 0;
        }
    }
    stop();
}*/
    public void run() {
        long now;
        long updateTime;
        long wait;
        int frames = 0;
        long timer = System.currentTimeMillis();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        while (running) {
            now = System.nanoTime();

            tick();
            render();

            updateTime = System.nanoTime() - now;
            wait = (OPTIMAL_TIME - updateTime) / 1000000;
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
            try {
                Thread.sleep(wait);
                //System.out.println("true");
            } catch (Exception e) {

            }

        }
        stop();
    }

    private void tick() {
        if (gameState == STATE.End || gameState == STATE.Win || gameState == STATE.Colors || gameState == STATE.Shop || gameState == STATE.Menu || gameState == STATE.Select || gameState == STATE.Help || (gameState == STATE.Game && paused)) {
            window.defaultCursor();
        }
        if (gameState == STATE.Game) {
            truth = true;
            if (!paused) {
                window.blankCursor();
                hud.tick();
                spawner.tick();
                handler.tick();

                if (HUD.HEALTH <= 0) {
                    HUD.HEALTH = 100;
                    gameState = STATE.End;
                    handler.clearEnemies();
                    for (int i = 0; i < 20; i++) {
                        handler.addObject(new MenuParticle((float) r.nextInt(WIDTH), (float) r.nextInt(HEIGHT), ID.MenuParticle, handler, false));
                    }
                } else if (HUD.HEALTH2 <= 0) {
                    HUD.HEALTH2 = 200;
                    gameState = STATE.End;
                    handler.clearEnemies();
                    for (int i = 0; i < 20; i++) {
                        handler.addObject(new MenuParticle((float) r.nextInt(WIDTH), (float) r.nextInt(HEIGHT), ID.MenuParticle, handler, false));
                    }
                }
            }
        } else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select || gameState == STATE.Help || gameState == STATE.Colors) {
            keys.setDirections(false);
            menu.tick();
            handler.tick();
        } else if (gameState == STATE.Win) {
            keys.setDirections(false);
            menu.tick();
            handler.tick();
            HUD.HEALTH = 100;
            HUD.HEALTH = 200;
            if (truth) {
                for (int i = 0; i < 20; i++) {
                    handler.addObject(new MenuParticle((float) r.nextInt(WIDTH), (float) r.nextInt(HEIGHT), ID.MenuParticle, handler, true));
                }
                truth = false;
            }/*
                    handler.clearEnemies();
                    for(int i = 0; i<20; i++){
                        handler.addObject(new MenuParticle((float)r.nextInt(WIDTH),(float)r.nextInt(HEIGHT),ID.MenuParticle,handler));
                    }*/
        }
    }

    private void render() {
        //helps lower the FPS
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            //3 is how many buffers it creates, not reccomended to go over 3
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        //fills the background with a black color or whatever color you want
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        if (paused) {
            g.setColor(Color.white);
            g.drawString("PAUSED", 400, 100);
        }

        if (gameState == STATE.Game) {
            hud.render(g, this);

        } else if (gameState == STATE.Shop) {
            shop.render(g);
        } else if (gameState == STATE.Menu || gameState == STATE.Win || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select || gameState == STATE.Colors) {
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    //this will help keep the player in the JFrame
    public static float clamp(float var, float min, float max) {
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }
    }

    public static void main(String[] args) {
        new Game();

    }
}

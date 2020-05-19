/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Zachary Bianucci
 */
public class ColorMenu {

    private static Color color = Color.white;
    private static int red = 1, white, blue, orange =1,green,purple;
    private int r = 0, g = 0, b = 0;
    private Random rand = new Random();

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.color = new Color(r, g, b);
    }

    public Color getColor() {
        //System.out.println(color);
        return color;
    }

    public Color getRandomColor() {
        r = rand.nextInt(255);
        g = rand.nextInt(255);
        b = rand.nextInt(255);
        setColor(r, g, b);
        return color;
    }
    public Color getRandomColorMenu(){
        r = rand.nextInt(255);
        g = rand.nextInt(255);
        b = rand.nextInt(255);
        return new Color(r,g,b);
    }
    //Colors class will use this to assign the color the user asks for
    public void assignColor(Color color) {
        this.color = color;
    }

    //makes the RedWhiteBlue color option of the color method
    public Color getRWB() {
        setColor(Color.white);
        //if(red>100000||white>100000||blue>100000)
            //red=1;white=0;blue=0;
        if (red < white && red < blue) {
            red++;
            return Color.red;
        } else if (white < red || white < blue) {
            white++;
            return Color.white;
        } else {
            blue++;
            return Color.blue;
        }
    }
    public Color getOGP(){
        setColor(Color.white);
        if (orange < green && orange < purple) {
            orange++;
            return new Color(255, 130, 0);
        } else if (green < orange || green < purple) {
            green++;
            return Color.green;
        } else {
            purple++;
            return new Color(128,0,128);
        }
    }
}

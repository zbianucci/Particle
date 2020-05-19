/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Zachary Bianucci
 */
public class Music {

    public static Map<String, Music> musicMap = new HashMap<String, Music>();

    public static void load() {
        try {
            // musicMap.put("music", new Music("src\\game\\Safety Dance.wav"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Music getMusic(String key) {
        return musicMap.get(key);
    }

    public static void playMusic() {

        InputStream music;
        try {
            //Safety Dance - Men Without Hats
            music = new FileInputStream(new File("src\\game\\Safety Dance.wav"));            
            AudioStream safetyDance = new AudioStream(music);
            AudioPlayer.player.start(safetyDance);
            Thread.sleep(163000);
            AudioPlayer.player.stop(safetyDance);

            //Do Ya - Electric Light Orchestra
            music = new FileInputStream(new File("src\\game\\Do Ya.wav"));            
            AudioStream doYa = new AudioStream(music);
            AudioPlayer.player.start(doYa);
            Thread.sleep(227000);
            AudioPlayer.player.stop(doYa);
            
            //The Trooper - Iron Maiden
            music = new FileInputStream(new File("src\\game\\The Trooper.wav"));            
            AudioStream theTrooper = new AudioStream(music);
            AudioPlayer.player.start(theTrooper);
            Thread.sleep(261000);
            AudioPlayer.player.stop(theTrooper);
            
            //Panama - Van Halen
            music = new FileInputStream(new File("src\\game\\Panama.wav"));            
            AudioStream panama = new AudioStream(music);
            AudioPlayer.player.start(panama);
            Thread.sleep(211000);
            AudioPlayer.player.stop(panama);
            
            //Sunglasses at Night - Corey Hart
            music = new FileInputStream(new File("src\\game\\Sunglasses at Night.wav"));            
            AudioStream sunglassesAtNight = new AudioStream(music);
            AudioPlayer.player.start(sunglassesAtNight);
            Thread.sleep(236000);
            AudioPlayer.player.stop(sunglassesAtNight);
            
            //What is Love - Haddaway
            music = new FileInputStream(new File("src\\game\\What is Love.wav"));            
            AudioStream whatIsLove = new AudioStream(music);
            AudioPlayer.player.start(whatIsLove);
            Thread.sleep(265000);
            AudioPlayer.player.stop(whatIsLove);
            playMusic();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
 /*
        try {
            System.out.println(1);
            AudioData data = new AudioStream(new FileInputStream("src\\game\\Safety Dance.wav")).getData();
            System.out.println(2);
            ContinuousAudioDataStream sound = new ContinuousAudioDataStream(data);
            System.out.println(3);
            AudioPlayer.player.start(sound);
            System.out.println(4);
        } catch (Exception e) {
            System.out.println("error");
        }*/
 /*
        File file = new File("src\\game\\Safety Dance.wav");
        AudioClip ac = getAudioClip(file);
        ac.play();   //play once
        ac.stop();   //stop playing
        ac.loop();*/
    }
}

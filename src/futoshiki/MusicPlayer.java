/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

/**
 *
 * @author bdtw20
 */
public class MusicPlayer {
    
    private Clip clip;
    private FloatControl gainControl;
    private boolean musicPlaying;
    
    /**
     * Constructor for the music player. 
     */
    public MusicPlayer() {
        File folder = new File("src\\AudioFiles");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try {
            File file = new File("src\\AudioFiles\\MainMenuMusic.wav");
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;

            stream = AudioSystem.getAudioInputStream(file);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.loop(Integer.MAX_VALUE);
            musicPlaying = true;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
    }
    
    /**
     * Void play music method
     * 
     * Plays music of current clip. 
     */
    public void playMusic(){
        clip.start();
        musicPlaying = true;
    }
    
    /**
     * Void stop music method
     * 
     * Stops music of current clip.
     */
    public void stopMusic(){
        clip.stop();
        musicPlaying = false;
    }

    /**
     * Return method of clip
     * @return Clip
     * 
     * This method returns the current clip.
     */
    public Clip getClip() {
        return clip;
    }

    /**
     * Void method set clip
     * @param clip 
     * 
     * Sets the clip for the music player to play and register.
     */
    public void setClip(Clip clip) {
        this.clip = clip;
    }

    /**
     * Return method of the FloatControl
     * @return FloatControl
     * 
     * Returns FloatControl of music player.
     */
    public FloatControl getGainControl() {
        return gainControl;
    }

    /**
     * Void method set FloatControl
     * @param gainControl 
     * 
     * Sets the FloatControl for the music player.
     */
    public void setGainControl(FloatControl gainControl) {
        this.gainControl = gainControl;
    }

    /**
     * Return method for checking if the music is playing.
     * @return boolean
     * 
     * Returns true if music is playing, false otherwise.
     */
    public boolean isMusicPlaying() {
        return musicPlaying;
    }

    /**
     * Void method set musicPLaying boolean
     * @param musicPlaying 
     * 
     * Sets the boolean value for seeing if the music is playing or not
     */
    public void setMusicPlaying(boolean musicPlaying) {
        this.musicPlaying = musicPlaying;
    }
    
    /**
     * Void method for changing the volume 
     * @param i 
     * 
     * Changes the volume of the clip. The range for i is between 80 and 0.
     */
    public void changeVolume(float i){
        gainControl.setValue(i-80.0f);
    }
    
    /**
     * Return method for getting value of the volume
     * @return float
     * 
     * Returns the value of the volume of the music
     */
    public float getValueGainControl(){
        return 80+gainControl.getValue();
    }
    
    /**
     * Void method for changing the clip of the music player
     * 
     * Sets the new clip with a new file which is predefined by the creator to
     * swap the music.
     */
    public void changeMusicToGameMusic(){
        if(musicPlaying){
            clip.stop();
        }
        try {
            File file = new File("src\\AudioFiles\\GameMusic.wav");
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;

            stream = AudioSystem.getAudioInputStream(file);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.loop(Integer.MAX_VALUE);
            if(!musicPlaying){
                clip.stop();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Void method for changing the clip back to the original clip 
     * 
     * Sets the clip back to the original clip it was playing.
     */
    void changeMusicToMainMenu() {
        if(musicPlaying){
            clip.stop();
        }
        try {
            File file = new File("src\\AudioFiles\\MainMenuMusic.wav");
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;

            stream = AudioSystem.getAudioInputStream(file);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.loop(Integer.MAX_VALUE);
            if(!musicPlaying){
                clip.stop();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

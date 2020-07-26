/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bdtw20
 */
public class MusicPlayerTest {

    public MusicPlayerTest() {
    }

    @Before
    public void setUp() {

    }

    /**
     * Test of playMusic method, of class Music7Player.
     */
    @Test
    public void testPlayMusic() {
        MusicPlayer mp = new MusicPlayer();
        System.out.println("testPlayMusic()");
        mp.playMusic();
        assertTrue(mp.isMusicPlaying());
        mp.stopMusic();
    }

    /**
     * Test of stopMusic method, of class MusicPlayer.
     */
    @Test
    public void testStopMusic() {
        MusicPlayer mp = new MusicPlayer();
        System.out.println("testStopMusic()");
        mp.stopMusic();
        assertFalse(mp.isMusicPlaying());
    }

    /**
     * Test of isMusicPlaying method, of class MusicPlayer.
     */
    @Test
    public void testIsMusicPlaying() {
        System.out.println("testIsMusicPlaying()");
        MusicPlayer mp = new MusicPlayer();
        mp.playMusic();
        assertTrue(mp.isMusicPlaying());
        mp.stopMusic();
        assertFalse(mp.isMusicPlaying());
    }

    /**
     * Test of setMusicPlaying method, of class MusicPlayer.
     */
    @Test
    public void testSetMusicPlaying() {
        System.out.println("testSetMusicPlaying()");
        MusicPlayer mp = new MusicPlayer();
        mp.setMusicPlaying(true);
        assertTrue(mp.isMusicPlaying());
        mp.setMusicPlaying(false);
        assertFalse(mp.isMusicPlaying());
    }

    /**
     * Test of changeVolume method, of class MusicPlayer.
     */
    @Test
    public void testChangeVolume() {
        System.out.println("testChangeVolume()");
        MusicPlayer mp = new MusicPlayer();
        mp.playMusic();
        mp.changeVolume(70);
        assertTrue(mp.getValueGainControl()==70.0);
        mp.stopMusic();
    }

    /**
     * Test of getValueGainControl method, of class MusicPlayer.
     */
    @Test
    public void testGetValueGainControl() {
        System.out.println("testChangeVolume()");
        MusicPlayer mp = new MusicPlayer();
        mp.playMusic();
        assertTrue(mp.getValueGainControl()==80.0);
        mp.stopMusic();
    }

    /**
     * Test of changeMusicToGameMusic method, of class MusicPlayer.
     */
    @Test
    public void testChangeMusicToGameMusic() {
        System.out.println("testChangeMusicToGameMusic()");
        MusicPlayer mp = new MusicPlayer();
        Clip clip = mp.getClip();
        mp.changeMusicToGameMusic();
        assertFalse(clip.equals(mp.getClip()));
    }

    /**
     * Test of changeMusicToMainMenu method, of class MusicPlayer.
     */
    @Test
    public void testChangeMusicToMainMenu() {
        System.out.println("testChangeMusicToMainMenu()");
        MusicPlayer mp = new MusicPlayer();
        
        mp.changeMusicToGameMusic();
        Clip clip = mp.getClip();
        mp.changeMusicToMainMenu();
        assertFalse(clip.equals(mp.getClip()));
    }
    
    /**
     * Test for getting MainMenuMusic
     */
    @Test
    public void testGetMainMenuMusic(){
        System.out.println("testGetMainMenuMusic()");
        MusicPlayer mp = new MusicPlayer();
        Clip clip = mp.getClip();
        assertEquals(clip,mp.getClip());
    }
    
    /**
     * Test for getting GameMusic
     */
    @Test
    public void testGetGameMusic(){
        System.out.println("testGetGameMusic()");
        MusicPlayer mp = new MusicPlayer();
        mp.changeMusicToGameMusic();
        Clip clip = mp.getClip();
        assertEquals(clip,mp.getClip());
    }
    
    /**
     * Test for getting getGainControl()
     */
    @Test
    public void TestGetGainControl(){
        System.out.println("testGetGainControl()");
        MusicPlayer mp = new MusicPlayer();
        assertFalse(mp.getGainControl().equals(null));
    }
    
    /**
     * Test Error if setClip is not valid
     */
    @Test
    public void TestErrorWithOpenFile(){
        System.out.println("TestErrorWithSetClip()");
        MusicPlayer mp = new MusicPlayer();
        try{
            File file = new File("asdad");
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;

            stream = AudioSystem.getAudioInputStream(file);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            mp.setClip(clip);
        }catch(Exception e){
            assertEquals("asdad (The system cannot find the file specified)", e.getMessage());
        }
        
    }
}

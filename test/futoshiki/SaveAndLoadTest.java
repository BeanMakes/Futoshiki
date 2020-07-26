/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bdtw20
 */
public class SaveAndLoadTest {
    private SaveAndLoad saveAndLoad;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    public SaveAndLoadTest() {
        saveAndLoad = new SaveAndLoad();
    }
    
    @Before
    public void setUp() {
        
    }

    /**
     * Test of getEasyFile method, of class SaveAndLoad.
     */
    @Test
    public void testGetEasyFile() {
        System.out.println("testGetEasyFile()");
        File f = new File("src\\SaveFile\\" + "Easy " + sdf.format(new Date()) + ".futo");
        assertEquals(saveAndLoad.getEasyFile(),f);
    }

    /**
     * Test of getMediumFile method, of class SaveAndLoad.
     */
    @Test
    public void testGetMediumFile() {
        System.out.println("testGetMediumFile()");
        File f = new File("src\\SaveFile\\" + "Medium " + sdf.format(new Date()) + ".futo");
        assertEquals(saveAndLoad.getMediumFile(),f);
    }

    /**
     * Test of getHardFile method, of class SaveAndLoad.
     */
    @Test
    public void testGetHardFile() {
        System.out.println("testGetHardFile()");
        File f = new File("src\\SaveFile\\" + "Hard " + sdf.format(new Date()) + ".futo");
        assertEquals(saveAndLoad.getHardFile(),f);
    }

    /**
     * Test of getCustomFile method, of class SaveAndLoad.
     */
    @Test
    public void testGetCustomFile() {
        System.out.println("testGetCustomFile()");
        File f = new File("src\\SaveFile\\" + "Custom " + sdf.format(new Date()) + ".futo");
        assertEquals(saveAndLoad.getCustomFile(),f);
    }

    /**
     * Test of saveGame for easy method, of class SaveAndLoad.
     */
    @Test
    public void testEasySaveGame() {
        System.out.println("testEasySaveGame()");
        Futoshiki game = new Futoshiki(3);
        game.fillPuzzle(3, 3, 3);
        saveAndLoad.saveGame("Easy", game);
        File f = new File("src\\SaveFile\\" + "Easy " + sdf.format(new Date()) + ".futo");
        assertFalse(f.equals(null));
        f.delete();
    }
    
    /**
     * Test of saveGame for Medium method, of class SaveAndLoad.
     */
    @Test
    public void testMediumSaveGame() {
        System.out.println("testMediumSaveGame()");
        Futoshiki game = new Futoshiki(5);
        game.fillPuzzle(5, 10, 10);
        saveAndLoad.saveGame("Medium", game);
        File f = new File("src\\SaveFile\\" + "Medium " + sdf.format(new Date()) + ".futo");
        assertFalse(f.equals(null));
        f.delete();
    }
    
    /**
     * Test of saveGame for hard method, of class SaveAndLoad.
     */
    @Test
    public void testHardSaveGame() {
        System.out.println("testHardSaveGame()");
        Futoshiki game = new Futoshiki(10);
        game.fillPuzzle(10, 20, 20);
        saveAndLoad.saveGame("Hard", game);
        File f = new File("src\\SaveFile\\" + "Hard " + sdf.format(new Date()) + ".futo");
        assertFalse(f.equals(null));
        f.delete();
    }
    
    /**
     * Test of saveGame for custom method, of class SaveAndLoad.
     */
    @Test
    public void testCustomSaveGame() {
        System.out.println("testCustomSaveGame()");
        Futoshiki game = new Futoshiki(4);
        game.fillPuzzle(4, 3, 3);
        saveAndLoad.saveGame("Custom", game);
        File f = new File("src\\SaveFile\\" + "Custom " + sdf.format(new Date()) + ".futo");
        assertFalse(f.equals(null));
        f.delete();
    }
    
    /**
     * Test for quick load game
     */
    @Test
    public void testLoadGameQuick(){
        System.out.println("testLoadGameQuick()");
        Futoshiki game = new Futoshiki(4);
        game.fillPuzzle(4, 4, 4);
        saveAndLoad.saveGame("Custom", game);
        File f = new File("src\\SaveFile\\" + "Custom " + sdf.format(new Date()) + ".futo");
        assertFalse(saveAndLoad.loadGameQuick(f).equals(null));
        f.delete();
    }
}

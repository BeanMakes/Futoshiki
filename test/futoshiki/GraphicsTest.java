/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import org.junit.Before;
import org.junit.Test;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import static org.junit.Assert.*;

/**
 *
 * @author bdtw20
 */
public class GraphicsTest {

    private Graphics graphics;
    private Stage stage;

    public GraphicsTest() {
    }

    @Before
    public void setUp() {
        graphics = new Graphics();
    }

    /**
     * Test of getNumBoxes method, of class Graphics.
     */
    @Test
    public void testGetNumBoxes() {
        System.out.println("testGetNumBoxes()");
        graphics = new Graphics();
        assertFalse(graphics.getNumBoxes().equals(null));

    }

    /**
     * Test of setNumBoxes method, of class Graphics.
     */
    @Test
    public void testSetNumBoxes() {
        System.out.println("testSetNumBoxes()");
        graphics = new Graphics();
        try {
            int width = 200, height = 200;
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D ig2 = bi.createGraphics();

            Font font = new Font("TimesRoman", Font.BOLD, 20);
            ig2.setFont(font);
            String message = "Test";
            FontMetrics fontMetrics = ig2.getFontMetrics();
            int stringWidth = fontMetrics.stringWidth(message);
            int stringHeight = fontMetrics.getAscent();
            ig2.setPaint(Color.black);
            ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);

            ImageIO.write(bi, "PNG", new File("Test.PNG"));
            File f1 = new File("Test.png");
            FileInputStream fos1 = new FileInputStream(f1);
            Image image = new Image(fos1, 20, 20, false, false);
            graphics.setNumBoxes(image);
            assertFalse(graphics.getNumBoxes().equals(null));
            fos1.close();
            if (f1.delete()) {
                System.out.println("File deleted successfully");
            } else {
                System.out.println("Failed to delete the file");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Test of getErrorBoxes method, of class Graphics.
     */
    @Test
    public void testGetErrorBoxes() {
        System.out.println("testGetErrorBoxes()");
        graphics = new Graphics();
        assertFalse(graphics.getErrorBoxes().equals(null));
    }

    /**
     * Test of setErrorBoxes method, of class Graphics.
     */
    @Test
    public void testSetErrorBoxes() {
        System.out.println("testSetErrorBoxes()");
        graphics = new Graphics();
        try {
            int width = 200, height = 200;
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D ig2 = bi.createGraphics();

            Font font = new Font("TimesRoman", Font.BOLD, 20);
            ig2.setFont(font);
            String message = "Test";
            FontMetrics fontMetrics = ig2.getFontMetrics();
            int stringWidth = fontMetrics.stringWidth(message);
            int stringHeight = fontMetrics.getAscent();
            ig2.setPaint(Color.black);
            ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);

            ImageIO.write(bi, "PNG", new File("Test.PNG"));
            File f1 = new File("Test.png");
            FileInputStream fos1 = new FileInputStream(f1);
            Image image = new Image(fos1, 20, 20, false, false);
            graphics.setErrorBoxes(image);
            assertFalse(graphics.getNumBoxes().equals(null));
            fos1.close();
            if (f1.delete()) {
                System.out.println("File deleted successfully");
            } else {
                System.out.println("Failed to delete the file");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of getBackgroundImage method, of class Graphics.
     */
    @Test
    public void testGetBackgroundImage() {
        System.out.println("testGetBackgroundImage()");
        graphics = new Graphics();
        assertFalse(graphics.getBackgroundImage().equals(null));
    }

    /**
     * Test of setBackgroundImage method, of class Graphics.
     */
    @Test
    public void testSetBackgroundImage() {
        System.out.println("testSetBackgroundImage()");
        graphics = new Graphics();
        try {
            int width = 200, height = 200;
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D ig2 = bi.createGraphics();

            Font font = new Font("TimesRoman", Font.BOLD, 20);
            ig2.setFont(font);
            String message = "Test";
            FontMetrics fontMetrics = ig2.getFontMetrics();
            int stringWidth = fontMetrics.stringWidth(message);
            int stringHeight = fontMetrics.getAscent();
            ig2.setPaint(Color.black);
            ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);

            ImageIO.write(bi, "PNG", new File("Test.PNG"));
            File f1 = new File("Test.png");
            FileInputStream fos1 = new FileInputStream(f1);
            Image image = new Image(fos1, 20, 20, false, false);
            graphics.setBackgroundImage(image);
            assertFalse(graphics.getBackgroundImage().equals(null));
            fos1.close();
            if (f1.delete()) {
                System.out.println("File deleted successfully");
            } else {
                System.out.println("Failed to delete the file");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of getGameOverImageBoxes method, of class Graphics.
     */
    @Test
    public void testGetGameOverImageBoxes() {
        System.out.println("testGetGameOverImageBoxes()");
        graphics = new Graphics();
        assertFalse(graphics.getGameOverImageBoxes().equals(null));
        
    }

    /**
     * Test of setGameOverImageBoxes method, of class Graphics.
     */
    @Test
    public void testSetGameOverImageBoxes() {
        System.out.println("testSetGameOverImageBoxes()");
        graphics = new Graphics();
        try {
            int width = 200, height = 200;
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D ig2 = bi.createGraphics();

            Font font = new Font("TimesRoman", Font.BOLD, 20);
            ig2.setFont(font);
            String message = "Test";
            FontMetrics fontMetrics = ig2.getFontMetrics();
            int stringWidth = fontMetrics.stringWidth(message);
            int stringHeight = fontMetrics.getAscent();
            ig2.setPaint(Color.black);
            ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);

            ImageIO.write(bi, "PNG", new File("Test.PNG"));
            File f1 = new File("Test.png");
            FileInputStream fos1 = new FileInputStream(f1);
            Image image = new Image(fos1, 20, 20, false, false);
            graphics.setGameOverImageBoxes(image);
            assertFalse(graphics.getGameOverImageBoxes().equals(null));
            fos1.close();
            if (f1.delete()) {
                System.out.println("File deleted successfully");
            } else {
                System.out.println("Failed to delete the file");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of getPenIcon method, of class Graphics.
     */
    @Test
    public void testGetPenIcon() {
        System.out.println("testGetPenIcon()");
        graphics = new Graphics();
        assertFalse(graphics.getPenIcon().equals(null));
        
    }

    /**
     * Test of setPenIcon method, of class Graphics.
     */
    @Test
    public void testSetPenIcon() {
        System.out.println("testSetPenIcon()");
        graphics = new Graphics();
        try {
            int width = 200, height = 200;
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D ig2 = bi.createGraphics();

            Font font = new Font("TimesRoman", Font.BOLD, 20);
            ig2.setFont(font);
            String message = "Test";
            FontMetrics fontMetrics = ig2.getFontMetrics();
            int stringWidth = fontMetrics.stringWidth(message);
            int stringHeight = fontMetrics.getAscent();
            ig2.setPaint(Color.black);
            ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);

            ImageIO.write(bi, "PNG", new File("Test.PNG"));
            File f1 = new File("Test.png");
            FileInputStream fos1 = new FileInputStream(f1);
            Image image = new Image(fos1, 20, 20, false, false);
            graphics.setPenIcon(image);
            assertFalse(graphics.getPenIcon().equals(null));
            fos1.close();
            if (f1.delete()) {
                System.out.println("File deleted successfully");
            } else {
                System.out.println("Failed to delete the file");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of getPencilIcon method, of class Graphics.
     */
    @Test
    public void testGetPencilIcon() {
        System.out.println("testGetPencilIcon()");
        graphics = new Graphics();
        assertFalse(graphics.getPencilIcon().equals(null));
    }

    /**
     * Test of setPencilIcon method, of class Graphics.
     */
    @Test
    public void testSetPencilIcon() {
        System.out.println("testSetPencilIcon()");
        graphics = new Graphics();
        try {
            int width = 200, height = 200;
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D ig2 = bi.createGraphics();

            Font font = new Font("TimesRoman", Font.BOLD, 20);
            ig2.setFont(font);
            String message = "Test";
            FontMetrics fontMetrics = ig2.getFontMetrics();
            int stringWidth = fontMetrics.stringWidth(message);
            int stringHeight = fontMetrics.getAscent();
            ig2.setPaint(Color.black);
            ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);

            ImageIO.write(bi, "PNG", new File("Test.PNG"));
            File f1 = new File("Test.png");
            FileInputStream fos1 = new FileInputStream(f1);
            Image image = new Image(fos1, 20, 20, false, false);
            graphics.setPencilIcon(image);
            assertFalse(graphics.getPencilIcon().equals(null));
            fos1.close();
            if (f1.delete()) {
                System.out.println("File deleted successfully");
            } else {
                System.out.println("Failed to delete the file");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of numImage method, of class Graphics.
     */
    @Test
    public void testNumImage() {
        System.out.println("testNumImage()");
        assertFalse(graphics.numImage().equals(null));
    }

    /**
     * Test of backImage method, of class Graphics.
     */
    @Test
    public void testBackImage() {
        System.out.println("testBackImage()");
        assertFalse(graphics.backImage().equals(null));
    }

    /**
     * Test of errorNum method, of class Graphics.
     */
    @Test
    public void testErrorNum() {
        System.out.println("testErrorNum()");
        assertFalse(graphics.errorNum().equals(null));
    }

    /**
     * Test of gameOverBoxImage method, of class Graphics.
     */
    @Test
    public void testGameOverBoxImage() {
        System.out.println("testGameOverBoxImage()");
        assertFalse(graphics.gameOverBoxImage().equals(null));
    }

    /**
     * Test of penIconImage method, of class Graphics.
     */
    @Test
    public void testPenIconImage() {
        System.out.println("testPenIconImage()");
        assertFalse(graphics.penIconImage().equals(null));
    }

    /**
     * Test of pencilIconImage method, of class Graphics.
     */
    @Test
    public void testPencilIconImage() {
        System.out.println("testPencilIconImage()");
        assertFalse(graphics.pencilIconImage().equals(null));
    }

}

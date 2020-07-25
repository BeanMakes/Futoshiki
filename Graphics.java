/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author bdtw20
 */
public class Graphics {
    private Image numBoxes;
    private Image errorBoxes;
    private Image backgroundImage;
    private Image gameOverImageBoxes;
    private Image penIcon;
    private Image pencilIcon;
    
    /**
     * Constructor for the graphics
     */
    public Graphics(){
        try{
            File f1 = new File("src\\Images\\gridSquare.png");
            File f2 = new File("src\\\\Images\\\\ErrorImage.png");
            File f3 = new File("src\\\\Images\\\\Background.png");
            File f4 = new File("src\\\\Images\\\\gridSquare.png");
            File f5 = new File("src\\\\Images\\\\penIcon.png");
            File f6 = new File("src\\\\Images\\\\pencilIcon.png");
            FileInputStream fos1 = new FileInputStream(f1);
            FileInputStream fos2 = new FileInputStream(f2);
            FileInputStream fos3 = new FileInputStream(f3);
            FileInputStream fos4 = new FileInputStream(f4);
            FileInputStream fos5 = new FileInputStream(f5);
            FileInputStream fos6 = new FileInputStream(f6);
            numBoxes = new Image(fos1, 50, 50, false, false);
            errorBoxes = new Image(fos2, 50, 50, false, false);
            backgroundImage = new Image(fos3, 50, 50, false, false);
            gameOverImageBoxes = new Image(fos4, 30, 30, false, false);
            penIcon = new Image(fos5, 20, 20, false, false);
            pencilIcon = new Image(fos6, 20, 20, false, false);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Return method of numBoxes image.
     * @return Image
     * 
     * Returns the image for the number boxes images.
     */
    public Image getNumBoxes() {
        return numBoxes;
    }

    /**
     * Void method to set image for numBoxes.
     * @param Image: numBoxes
     * 
     * Sets the image of for the number boxes..
     */
    public void setNumBoxes(Image numBoxes) {
        this.numBoxes = numBoxes;
    }

    /**
     * Return method of errorBoxes image.
     * @return Image
     * 
     * Returns the image for the error boxes .
     */
    public Image getErrorBoxes() {
        return errorBoxes;
    }

    /**
     * Void method to set image for errorBoxes.
     * @param errorBoxes 
     * 
     * Sets the image for the error boxes.
     */
    public void setErrorBoxes(Image errorBoxes) {
        this.errorBoxes = errorBoxes;
    }

    /**
     * Return method of the backgroundImage.
     * @return Image
     * 
     * Returns the image for the background image.
     */
    public Image getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * Void method to set image for backgroundImage.
     * @param backgroundImage 
     * 
     * Sets up the image for the background Image.
     */
    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * Return method of the GameOverImageBoxes
     * @return Image
     * 
     * Returns the image for the gameOverImageBoxes
     */
    public Image getGameOverImageBoxes() {
        return gameOverImageBoxes;
    }

    /**
     * Void method to set image for the GameOverImageBoxes
     * @param gameOverImageBoxes 
     * 
     * Sets up the image for the GameOverImageBoxes
     */
    public void setGameOverImageBoxes(Image gameOverImageBoxes) {
        this.gameOverImageBoxes = gameOverImageBoxes;
    }

    /**
     * Return method of the PenIcon
     * @return Image
     * 
     * Returns the image for the PenIcon
     */
    public Image getPenIcon() {
        return penIcon;
    }

    /**
     * Void method to set the image for the PenIcon
     * @param penIcon 
     * 
     * Sets up the image for the PenIcon
     */
    public void setPenIcon(Image penIcon) {
        this.penIcon = penIcon;
    }

    /**
     * Return method of the PencilIcon
     * @return Image
     * 
     * Returns the image for the PencilIcon
     */
    public Image getPencilIcon() {
        return pencilIcon;
    }

    /**
     * Void method to set the image for the pencilIcon
     * @param pencilIcon 
     * 
     * Sets the image for the pencilIcon
     */
    public void setPencilIcon(Image pencilIcon) {
        this.pencilIcon = pencilIcon;
    }
    
    /**
     * Return method for getting the imageView of numBoxes
     * @return ImageView
     * 
     * Returns the ImageView for the number boxes image
     */
    public ImageView numImage(){
        return new ImageView(numBoxes);
    }
    
    /**
     * Return method for getting the imageView of background Image
     * @return ImageView
     * 
     * Returns the ImageView for the background image
     */
    public ImageView backImage(){
        return new ImageView(backgroundImage);
    }
    
    /**
     * Return method for getting the imageView of errorNum Image
     * @return ImageView
     * 
     * Returns the ImageView for the errorNum image
     */
    public ImageView errorNum(){
        return new ImageView(errorBoxes);
    }
    
    /**
     * Return method for getting the imageView of gameOverBox Image
     * @return ImageView
     * 
     * Returns the ImageView for the gameOverBox image
     */
    public ImageView gameOverBoxImage(){
        return new ImageView(gameOverImageBoxes);
    }
    
    /**
     * Return method for getting the imageView of penIcon Image
     * @return ImageView
     * 
     * Returns the ImageView for the penIcon image
     */
    public ImageView penIconImage(){
        return new ImageView(penIcon);
    }
    
    /**
     * Return method for getting the imageView of pencilIcon Image
     * @return ImageView
     * 
     * Returns the ImageView for the pencilIcon image
     */
    public ImageView pencilIconImage(){
        return new ImageView(pencilIcon);
    }   
        
}

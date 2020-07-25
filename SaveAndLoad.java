/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author bdtw20
 */
public class SaveAndLoad {

    private File easyFile;
    private File mediumFile;
    private File hardFile;
    private File customFile;
    private SimpleDateFormat sdf;

    /**
     * Constructor for the SaveAndLoad Class
     */
    public SaveAndLoad() {
        sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        easyFile = new File("src\\SaveFile\\" + "Easy " + sdf.format(new Date()) + ".futo");
        mediumFile = new File("src\\SaveFile\\" + "Medium " + sdf.format(new Date()) + ".futo");
        hardFile = new File("src\\SaveFile\\" + "Hard " + sdf.format(new Date()) + ".futo");
        customFile = new File("src\\SaveFile\\" + "Custom " + sdf.format(new Date()) + ".futo");
    }

    /**
     * Return method for getting the easyFile
     * @return File
     * 
     * Returns the File from easyFile
     */
    public File getEasyFile() {
        return easyFile;
    }

    /**
     * Void method to set the easyFile
     * @param easyFile 
     * 
     * Sets the File for the easyFile 
     */
    public void setEasyFile(File easyFile) {
        this.easyFile = easyFile;
    }

    /**
     * Return method for getting the mediumFile
     * @return File
     * 
     * Returns the File from the mediumFile
     */
    public File getMediumFile() {
        return mediumFile;
    }

    /**
     * Void method to set the mediumFile
     * @param mediumFile 
     * 
     * Sets the File for the mediumFile
     */
    public void setMediumFile(File mediumFile) {
        this.mediumFile = mediumFile;
    }

    /**
     * Return method for getting the hardFile
     * @return File
     * 
     * Returns the File from the hardFile
     */
    public File getHardFile() {
        return hardFile;
    }

    /**
     * Void method to set the hardFile
     * @param hardFile 
     * 
     * Sets the File for the hardFile
     */
    public void setHardFile(File hardFile) {
        this.hardFile = hardFile;
    }
    
    /**
     * Return method for getting the customFile
     * @return File
     * 
     * Returns the File from the customFile
     */
    public File getCustomFile() {
        return customFile;
    }

    /**
     * Void method to set customFile
     * @param customFile 
     * 
     * Sets the File for the customFile
     */
    public void setCustomFile(File customFile) {
        this.customFile = customFile;
    }

    /**
     * Void method to quicksave a Futoshiki game
     * @param s
     * @param game 
     * 
     * Saves a current Futoshiki puzzle under the predetermined directory and 
     * will pick which save is right via the String.
     */
    public void saveGame(String s, Futoshiki game) {
        File f = null;
        if (s.equals("Easy")) {
            f = easyFile;
        } else if (s.equals("Medium")) {
            f = mediumFile;
        } else if (s.equals("Hard")) {
            f = hardFile;
        } else if (s.equals("Custom")) {
            f = customFile;
        }
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
            oos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Void method to save a Futoshiki game under the users own directory
     * @param stage
     * @param game 
     * 
     * Saves a current Futoshiki puzzle but will have a pop up file chooser which
     * will let the user type in what their file should be save as.
     */
    public void saveGameAs(Stage stage, Futoshiki game) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Game");
        configureFileChooser(fileChooser);

        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(game);
                oos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Return method to load a Futoshiki game with fileChooser
     * @param stage
     * @return Futoshiki
     * 
     * Returns a Futoshiki object from a file which the user can pick from using
     * the file chooser.
     */
    public Futoshiki loadGame(Stage stage) {

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load Game");
            configureFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(stage);
            
            return loadGameQuick(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    /**
     * Returns a Futoshiki object from a quick load
     * @param f
     * @return Futoshiki
     * 
     * Returns a Futoshiki object from file f with out the file chooser.
     */
    public Futoshiki loadGameQuick(File f){
        try {
            FileInputStream fos = new FileInputStream(f);
            ObjectInputStream oos = new ObjectInputStream(fos);
            Futoshiki result = (Futoshiki) oos.readObject();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*
    FileChooser setup
    */
    private static void configureFileChooser(
            final FileChooser fileChooser) {
                fileChooser.setInitialDirectory(new File("src\\SaveFile\\"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("FUTO", "*.futo"));
    }
}

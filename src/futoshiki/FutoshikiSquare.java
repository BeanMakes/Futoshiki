/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki;

import java.io.Serializable;

/**
 *
 * @author bdtw20
 */
public class FutoshikiSquare implements Serializable{
    private boolean editable;
    private int number;

    /**
     * Constructor for Futoshiki Square
     * 
     * @param editable Boolean value to make the number editable or not
     * @param number Int number it will contain
     */
    public FutoshikiSquare(boolean editable, int number) {
        this.editable = editable;
        this.number = number;
    }

    /**
     * This method returns boolean value of the object.
     * @return Boolean Return editable value
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * This method sets the boolean value of the square.
     * 
     * @param editable boolean
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * This method returns the number vakue
     * 
     * @return int Returns number value
     */
    public int getNumber() {
        return number;
    }

    /**
     * This method sets the number value
     * 
     * @param number int
     */
    public void setNumber(int number) {
        this.number = number;
    }
    
}

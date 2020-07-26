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
public abstract class ConstraintsAbstract implements Serializable{

    private String[] constraints;
    private int Square1;
    private int Square2;
    private boolean sameRow;

    public String[] getConstraints() {
        return constraints;
    }

    public void setConstraints(String[] constraints) {
        this.constraints = constraints;
    }

    public int getSquare2() {
        return Square2;
    }

    public void setSquare2(int Square2) {
        this.Square2 = Square2;
    }

    public int getSquare1() {
        return Square1;
    }

    public void setSquare1(int Square1) {
        this.Square1 = Square1;
    }

    public boolean isSameRow() {
        return sameRow;
    }

    public void setSameRow(boolean sameRow) {
        this.sameRow = sameRow;
    }

    public ConstraintsAbstract(int Square1, int Square2, boolean sameRow) {
        this.Square1 = Square1;
        this.Square2 = Square2;
        this.sameRow = sameRow;
    }

    public String setConstraintsForGrid() {
        if (Square1 > Square2) {
            
            if (sameRow) {
                return getConstraints()[0];
            } else {
                return getConstraints()[1];
            }
        } else {
            
            if (sameRow) {
                return getConstraints()[0];
            } else {
                return getConstraints()[1];
            }
        }
    }

}

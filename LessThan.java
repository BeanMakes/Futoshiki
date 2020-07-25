/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki;

/**
 *
 * @author bdtw20
 */
public class LessThan extends ConstraintsAbstract{

    /**
     * Constructor for the Lessthan object
     * 
     * @param Square1 int
     * @param Square2 int
     * @param sameRow boolean
     */
    public LessThan(int Square1, int Square2,boolean sameRow) {
        super(Square1,Square2,sameRow);
        String[] s = {"<","^"};
        super.setConstraints(s);
    }
    
    
}

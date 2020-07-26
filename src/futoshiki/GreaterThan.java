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
public class GreaterThan extends ConstraintsAbstract{

    /**
     * The Constructor for the GreaterThan class.
     * 
     * @param Square1 int
     * @param Square2 int
     * @param sameRow boolean 
     */
    public GreaterThan(int Square1, int Square2,boolean sameRow) {
        super(Square1, Square2, sameRow);
        String[] cons = {">", "v"};
        super.setConstraints(cons);
    }
    
}

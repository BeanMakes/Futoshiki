/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author bdtw20
 */
public class FutoshikiState {
    private Futoshiki futoshikiState;
    private ArrayList<Integer> numForState;

    /**
     * Constructor for the Futoshiki state
     * @param futoshikiState 
     */
    public FutoshikiState(Futoshiki futoshikiState) {
        this.futoshikiState = futoshikiState;
        this.numForState = new ArrayList<>();
        for(int i = 0; i<this.futoshikiState.getSize();i++){
            numForState.add(i+1);
        }
    }
    
    /**
     * Return method for the children of futoshiki current state
     * @return ArrayList
     * 
     * Returns a list of the children of the Futoshiki grid
     */
    public Collection<Futoshiki> getChildren(){
        ArrayList<Futoshiki> temp = new ArrayList<>();
        for(int n: numForState){
            int row = 0;
            int col = 0;
            boolean emptySquare = false;
            Futoshiki f = new Futoshiki(futoshikiState.getSize());
            for (int i = 0; i < futoshikiState.getSize(); i++) {
                for (int j = 0; j < futoshikiState.getSize(); j++) {
                    f.getGrid()[i][j] = futoshikiState.getGrid()[i][j];
                }
            }
            for (int i = 0; i < futoshikiState.getSize()-1; i++) {
                for (int j = 0; j < futoshikiState.getSize(); j++) {
                    f.getRowConstraints()[i][j] = futoshikiState.getRowConstraints()[i][j];
                }
            }
            for (int i = 0; i < futoshikiState.getSize(); i++) {
                for (int j = 0; j < futoshikiState.getSize()-1; j++) {
                    f.getColConstraints()[i][j] = futoshikiState.getColConstraints()[i][j];
                }
                
            }
            while(row<f.getSize()&&!emptySquare){
                while(col<f.getSize()&&!emptySquare){
                    if(f.getGrid()[row][col]==null){
                        emptySquare = true;
                    }else{
                        col++;
                    }
                    
                }
                if(!emptySquare){
                    row++;
                    col=0;
                }
            }
            if(f.isUnique(n, row, col)&&f.checkingConstraintsInPlaceCol()){
                f.setSquare(n,row,col);
                temp.add(f);
            }
        }
        return temp;
    }
}

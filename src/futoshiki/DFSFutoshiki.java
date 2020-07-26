package futoshiki;

import java.util.ArrayList;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bdtw20
 */
public class DFSFutoshiki {
    private Futoshiki currentState;

    /**
     * Constructor for Depth first search
     * @param currentState 
     */
    public DFSFutoshiki(Futoshiki currentState) {
        this.currentState = currentState;
    }
    
    /**
     * Depth First Search algorithm
     * @return Futoshiki
     * 
     * Preforms a depth first search of currentState of borad to find if there 
     * is a solution for a legal puzzle.
     */
    public Futoshiki depthFirstSearch(){
        Stack toDoList = new Stack();
        ArrayList<Futoshiki> seenList = new ArrayList<>();
        toDoList.push(currentState);
        while(!toDoList.empty()){
            Futoshiki x = (Futoshiki)toDoList.pop();
            if(x.isPuzzleFillComp()){
                return x;
            } else{
                seenList.add(x);
                FutoshikiState futoshikiState = new FutoshikiState(x);
                ArrayList<Futoshiki> temp = (ArrayList<Futoshiki>) futoshikiState.getChildren();
                for(Futoshiki f: temp){
                    if(!toDoList.contains(f) && !seenList.contains(f)){
                        toDoList.push(f);
                    }
                }
            }
        }
        return null;
    }
    
    
}

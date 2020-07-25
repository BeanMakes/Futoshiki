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
public class FutoshikiMain {
    
    private static Futoshiki game;
    
    /**
     * Runs the Futoshiki game.
     * 
     * @param args unused
     */
    /*public static void main(String[] args) {
        Parser p = new Parser();
        Command c = null;
        System.out.print("Enter a command>");
        while ((c = p.getCommand()) != null && c.getCommand() != CommandWord.QUIT) {
            System.out.println(switchCase(c));
            System.out.println(c);
            System.out.print(">");
            
        }
    }*/
    
    /*
    What each command will do when the user enters it in the console.
    */
    private static String switchCase(Command c){
        switch(c.getCommand()){
                case NEW:
                    game = new Futoshiki(c.getValue());
                    game.fillPuzzle(c.getValue(), c.getValue()+3, c.getValue()+3);
                    return game.displayString();
                    
                case MARK:
                    if(game == null){
                        return "The puzzle hasn't been created yet. Please create \"new\" puzzle with the NEW command.";
                    }else{
                        try{
                        game.setSquare(c.getValue(), c.getColumn(),c.getRow());
                        }catch(Exception e){
                            return "Please enter a value which is in the grid.";
                        }
                        String s = game.displayString();
                        if(game.isPuzzleComplete()){
                            s+= "\nCongratulation you have completed the puzzle.";
                            for(int i = 0; i<game.getSize(); i++){
                                for(int j = 0; j<game.getSize();j++){
                                    game.getGrid()[i][j].setEditable(false);
                                }
                            }
                        }
                        return s;
                        
                    }
                case CLEAR:
                    if(game == null){
                        return "The puzzle hasn't been created yet. Please create \"new\" puzzle with the NEW command.";
                    }else{
                        game.clearSquare(c.getColumn(),c.getRow());
                        return game.displayString();
                    }
                case QUIT:
                    return c.getMsg();
                case UNKNOWN:
                    return c.getMsg();
            }
        return null;
    }
    
}

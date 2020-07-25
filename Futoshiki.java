/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki;

import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author bdtw20
 */
public class Futoshiki implements Serializable{

    //Class storaging of the data for the object
    private FutoshikiSquare[][] answer;
    private FutoshikiSquare[][] grid;
    private ConstraintsAbstract[][] rowConstraints;
    private ConstraintsAbstract[][] colConstraints;
    private ArrayList<String> numberProblems;
    private ArrayList<String> rowNum;
    private ArrayList<String> colNum;
    private ArrayList<Integer> genNumber;
    private ArrayList<Integer> firstRowNum;
    private ArrayList<Integer> firstColNum;
    private ArrayList<Integer> fillRowNum;
    private int size;
    private final Random rnd = new Random();
    private boolean newGame;

    /**
     * Constructor for the Futoshiki puzzle. It throws an error if the size is 
     * less than 3.
     * 
     * @param i The size of the puzzle
     */
    Futoshiki(int i) {
        if (i < 3) {
            throw new IndexOutOfBoundsException("Invalid build, put in a number which is greater than 4.");
        } else {
            grid = new FutoshikiSquare[i][i];
            answer = new FutoshikiSquare[i][i];
            size = i;
            rowConstraints = new ConstraintsAbstract[i - 1][i];
            colConstraints = new ConstraintsAbstract[i][i - 1];
            numberProblems = new ArrayList<>();
            rowNum = new ArrayList<>();
            colNum = new ArrayList<>();
            genNumber = new ArrayList<>(size * size);
            firstRowNum = new ArrayList<>();
            firstColNum = new ArrayList<>();
            fillRowNum = new ArrayList();
            newGame = false;
        }
    }

    /*
    Getter methods
     */
    
    /**
     * This method is used to get the 2D array for the game grid.
     * 
     * @return FutoshikiSquare[][] returns the grid for the Futoshiki puzzle
     */
    public FutoshikiSquare[][] getGrid() {
        return grid;
    }

    /**
     * This method is used to get the 2D array for the answer grid.
     * 
     * @return FutoshikiSquare[][] returns the 2D array for the Futoshiki puzzle
     * answer.
     */
    public FutoshikiSquare[][] getAnswer() {
        return answer;
    }

    /**
     * This method is used to get the size of the puzzle.
     * 
     * @return int The size of the puzzle
     */
    public int getSize() {
        return size;
    }

    /**
     * This method gets the 2D array of the Row Constraints for the puzzle.
     * 
     * @return ConstraintsAbstract[][] Returns the RowContraints 2D Array
     */
    public ConstraintsAbstract[][] getRowConstraints() {
        return rowConstraints;
    }

    /**
     * This method gets the 2D array of the Column Constraints for the puzzle.
     * 
     * @return ConstraintsAbstract[][] Returns the ColConstraints 2D Array
     */
    public ConstraintsAbstract[][] getColConstraints() {
        return colConstraints;
    }

    /*
    Setter methods
     */
    
    /**
     * This method sets the square in the game. It takes in a number, row and 
     * column. It will edit the grid[row][column] with a new FutoshikiSquare()
     * which is the number object.
     * 
     * @param i The number that will be inputed in to the Square.
     * @param row The Row it will be in.
     * @param col The Column it will be in.
     */
    public void setSquare(int i, int row, int col) {
        if (grid[row][col] == null) {
            grid[row][col] = new FutoshikiSquare(true, i);
        } else if (grid[row][col].isEditable() == false) {
            System.out.println("You can not edit this square! Please pick another one.");
         
        } 
        else if (i < 1 || i > size) {
            System.out.println("Enter a number between 1 - " + size);
        }
        
        else {
            grid[row][col] = new FutoshikiSquare(true, i);
        }
        newGame = true;
    }

    /**
     * This method clears the box with a number if the number is not editable.
     * If the user tries to use this method on a non editable number, a message
     * will be sent out in the console.
     * 
     * @param row The Row it will be in
     * @param col The Column it will be in
     */
    public void clearSquare(int row, int col) {
        if (grid[row][col].isEditable() == false) {
            System.out.println("You can not edit this square! Please pick another one.");
        } else {
            grid[row][col] = null;
        }
    }

    /**
     * This method will set a constraint in the rowConstraint 2D array. This
     * method will only create a GreaterThan or a LessThan object only if there
     * are numbers in the squares it will be comparing. The method will throw
     * an exception if the row or/and column is out of bounds of the size of the
     * 2D array.
     * 
     * @param row The Row it will be in.
     * @param col The Column it will be in.
     */
    public void setRowConstraints(int row, int col) {
        if (row >= size - 1 ) {
            throw new IndexOutOfBoundsException("Invalid number input for rows. Put number between 0 and " + (size - 2));
        }  else if (col >= size) {
            throw new IndexOutOfBoundsException("Invalid number input for column. Put number between 0 and " + (size - 1));
        }
        
        else if (grid[row+1][col] == null || grid[row][col] == null){
            rowConstraints[row][col] = null;
        }
        
        else if(grid[row][col].getNumber()>grid[row][col].getNumber()) {
            rowConstraints[row][col] = new GreaterThan(grid[row+1][col].getNumber(),grid[row][col].getNumber(), true);
        }
        else{
            rowConstraints[row][col] = new LessThan(grid[row][col].getNumber(),grid[row+1][col].getNumber(), true);
        }
    }

    /**
     * This method will set a constraint in the colConstraint 2D array. This
     * method will only create a GreaterThan or a LessThan object only if there
     * are numbers in the squares it will be comparing. The method will throw
     * an exception if the row or/and column is out of bounds of the size of the
     * 2D array.
     * 
     * @param row The Row it will be in.
     * @param col The Column it will be in.
     */
    public void setColConstraints(int row, int col) {
        if (row >= size) {
            throw new IndexOutOfBoundsException("Invalid number input for rows. Put number between 0 and " + (size - 1));
        } else if (grid[row][col+1] == null || grid[row][col] == null){
            colConstraints[row][col] = null;
        }
        
        else if (col >= size - 1) {
            throw new IndexOutOfBoundsException("Invalid number input for column. Put number between 0 and " + (size - 2));
        } else if(grid[row][col].getNumber()>grid[row][col].getNumber()) {
            colConstraints[row][col] = new GreaterThan(grid[row][col].getNumber(),grid[row][col+1].getNumber(), false);
        }
        else{
            colConstraints[row][col] = new LessThan(grid[row][col].getNumber(),grid[row][col+1].getNumber(), false);
        }
    }

    /**
     * This method sets up the puzzle for the user to play. It will make a legal
     * and solvable puzzle. 
     * 
     * @param n Number of numbers left in the puzzle.
     * @param m Number of row constraints left in the puzzle
     * @param p Number of column constraints left in the puzzle
     */
    public void fillPuzzle(int n, int m, int p) {
        if (newGame) {
            grid = new FutoshikiSquare[size][size];
            rowConstraints = new ConstraintsAbstract[size - 1][size];
            colConstraints = new ConstraintsAbstract[size][size - 1];
        }
        fullFilledPuzzle();
        for (int i = 0; i < size * size - n; i++) {
            int posX = rnd.nextInt(size);
            int posY = rnd.nextInt(size);
            while (grid[posX][posY] == null) {
                posX = rnd.nextInt(size);
                posY = rnd.nextInt(size);
            }
            grid[posX][posY] = null;
        }
        int constraintLength = rowConstraints.length;
        
        for (int i = 0; i < ((size - 1) * size) - m; i++) {
            int posX = rnd.nextInt(constraintLength);
            int posY = rnd.nextInt(constraintLength + 1);
            while (rowConstraints[posX][posY] == null) {
                posX = rnd.nextInt(constraintLength);
                posY = rnd.nextInt(constraintLength + 1);
            }
            rowConstraints[posX][posY] = null;
        }
        constraintLength = colConstraints.length;
        for (int i = 0; i < ((size - 1) * size) - p; i++) {
            int posX = rnd.nextInt(constraintLength);
            int posY = rnd.nextInt(constraintLength - 1);
            while (colConstraints[posX][posY] == null) {
                posX = rnd.nextInt(constraintLength);
                posY = rnd.nextInt(constraintLength - 1);
            }
            colConstraints[posX][posY] = null;
        }
    }


    /**
     * This method creates a visual representation of the game so that the user
     * will be able to see the puzzle.
     * 
     * @return String This string will make it displayable for the console for 
     * the game so the player can see the game.
     */
    public String displayString() {
        String result = "";
        for (int j = 0; j < size; j++) { //increment of the number of rows
            String numBlock = "";
            String colCon = "";
            result += border() + "\n";
            for (int i = 0; i < size; i++) { //increment of the number of columns
                if (i > size - 2) { //Checks for the last column and if so no constraints on the end of it
                    if (grid[i][j] == null) {
                        numBlock += "|" + spacesEmptyboxes() + "|";

                    } else {
                        numBlock += "|" + spaces(grid[i][j].getNumber()) + grid[i][j].getNumber() + "|";
                    }
                } else {
                    if (rowConstraints[i][j] == null) { //Row constraints
                        if (grid[i][j] == null) {
                            numBlock += "|" + spacesEmptyboxes() + "|" + " ";
                        } else {
                            numBlock += "|" + spaces(grid[i][j].getNumber()) + grid[i][j].getNumber() + "|" + " ";
                        }
                    } else {
                        if (grid[i][j] == null) {
                            numBlock += "|" + spacesEmptyboxes() + "|" + rowConstraints[i][j].setConstraintsForGrid();
                        } else {
                            numBlock += "|" + spaces(grid[i][j].getNumber()) + grid[i][j].getNumber() + "|" + rowConstraints[i][j].setConstraintsForGrid();
                        }
                    }

                }

            }
            result += numBlock + "\n";
            result += border() + "\n";
            for (int i = 0; i < size; i++) { //Column constraints
                if (j > size - 2) {

                } else {
                    if (colConstraints[i][j] == null) {
                        colCon += "   " + spacesEmptyboxes();
                    } else {
                        colCon += spacesEmptyboxes() + colConstraints[i][j].setConstraintsForGrid() + "  ";
                    }
                }
            }
            result += colCon + "\n";
        }
        return result;
    }
    
    /**
     * This method creates a puzzle which fill out the whole grid with legal
     * numbers and constraints which work. This method as well creates the
     * answer which is used to compare with the puzzle when the user starts the
     * game.
     */
    public void fullFilledPuzzle() {
        resetGenNumbers();
        resetFirstRow();
        resetFirstCol();
        while (!checkDiscreteNumbers()) {
            for (int i = 1; i < size; i++) {
                setRowGenNum(i);
                int j = 1;
                while (j < size) {
                    int pickRandom = rnd.nextInt(fillRowNum.size());
                    int k = 0;
                    while (k < fillRowNum.size() && !(isUnique(fillRowNum.get(k), j, i))) {
                        k++;
                    }
                    while (!isUnique(fillRowNum.get(pickRandom), j, i)) {
                        pickRandom = rnd.nextInt(fillRowNum.size());
                        if (k >= fillRowNum.size()) {
                            redoRow(i);
                            j = 1;
                        }
                    }
                    setSquare(fillRowNum.remove(pickRandom), j, i);
                    grid[j][i].setEditable(false);
                    j++;
                }
            }
        }
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                answer[j][i] = grid[j][i];
            }
        }
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j].getNumber() > grid[i + 1][j].getNumber()) {
                    GreaterThan g = new GreaterThan(grid[i][j].getNumber(), grid[i + 1][j].getNumber(), true);
                    rowConstraints[i][j] = g;
                } else {
                    LessThan l = new LessThan(grid[i][j].getNumber(), grid[i + 1][j].getNumber(), true);
                    rowConstraints[i][j] = l;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (grid[i][j].getNumber() > grid[i][j + 1].getNumber()) {
                    GreaterThan g = new GreaterThan(grid[i][j].getNumber(), grid[i][j + 1].getNumber(), false);
                    colConstraints[i][j] = g;
                } else {
                    LessThan l = new LessThan(grid[i][j].getNumber(), grid[i][j + 1].getNumber(), false);
                    colConstraints[i][j] = l;
                }
            }
        }
        
    }
    
    /**
     * This method returns true of the game is completely filled with numbers 
     * and if they comply with the rules of the game ie they do not repeat in 
     * their rows or column or it fits the constraint comparison.
     * 
     * @return boolean If the puzzle is completely filled with numbers and they
     * comply with rules of the game.
     */
    public boolean isPuzzleComplete() {
        boolean done = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == null) {
                    done = false;
                }
            }
        }

        return done && isLegal();
    }

    /**
     * This method returns true if the puzzle is legal in the state it is in
     * without it having to be complete but as well if it is complete as long it
     * complies with the correct rules.
     * 
     * @return boolean true if puzzle is legal in its state at the moment 
     */
    public boolean isLegal() {
        boolean legal = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == null || answer[i][j] == null) {

                } else {
                    if (grid[i][j].getNumber() != answer[i][j].getNumber()) {
                        legal = false;
                        numberProblems.add("" + grid[i][j].getNumber());
                        rowNum.add("" + i);
                        colNum.add("" + j);
                    } else if (!checkingConstraintsInPlaceRow()) {
                        legal = false;
                    } else if (!checkingConstraintsInPlaceCol()) {
                        legal = false;
                    }
                }
            }
        }
        return legal;
    }

    /**
     * This method returns a String which when displayed in the console, it will
     * return all the numbers wrong in the puzzle and their places in the board.
     * 
     * @return String The string of problems wrong with numbers and their place
     * in the board
     */
    public String getProblems() {
        String errors = "";
        if (!isLegal()) {
            for (int i = 0; i < numberProblems.size(); i++) {
                errors = errors + "The following numbers have been repeated: " + numberProblems.get(i) + ". in row: " + rowNum.get(i) + ". in column: " + colNum.get(i) + ".\n";
                i++;
            }
        }
        numberProblems.clear();
        return errors;
    }
    
    /**
     * Return method for checking if puzzle is complete and filled
     * @return boolean
     * 
     * Returns true if puzzle is legal and filled else false
     */
    public boolean isPuzzleFillComp(){
        boolean done = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == null) {
                    done = false;
                }
            }
        }
        return checkDiscreteNumbers() && done;
    }
    
    /**
     * Return method for checking the constraints if they are legal
     * @return boolean
     * 
     * Returns true if the number satisfy the constraints  
     */
    public boolean checkingConstraintsInPlaceCol() {
        int i = 0;
        int j = 0;
        boolean correctCon = true;
        while (i < size && correctCon) {
            while (j < size - 1 && correctCon) {
                if (colConstraints[i][j] != null) {
                    if (colConstraints[i][j].setConstraintsForGrid().equals("^")) {
                        if (grid[i][j] == null || grid[i][j + 1] == null) {

                        } else {
                            correctCon = grid[i][j].getNumber() < grid[i][j + 1].getNumber();
                        }
                    } else {
                        if (grid[i][j] == null || grid[i][j + 1] == null) {

                        } else {
                            correctCon = grid[i][j].getNumber() > grid[i][j + 1].getNumber();
                        }
                    }
                }
                j++;
            }
            j = 0;
            i++;
        }
        return correctCon;
    }
    
    /**
     * Return method for checking if number is unique in the row
     * @param num
     * @param row
     * @param col
     * @return boolean
     * 
     * Returns true if the number is unique in the row or column else false
     */
    public boolean isUnique(int num, int row, int col) {
        boolean unique = true;
        for (int i = 0; i < size; i++) {
            if (grid[i][col] == null) {

            } else {
                if (grid[i][col].getNumber() == num) {
                    unique = false;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (grid[row][i] == null) {

            } else {
                if (grid[row][i].getNumber() == num) {
                    unique = false;
                }
            }
        }
        return unique;
    }

    /*
    Private method
    */
    /*
    Method to build the border of the grid in display string
     */
    private String border() {
        String result = "";
        String border = "--";

        for (int i = 0; i < numOfDigits(size); i++) {
            border += "-";
        }

        for (int i = 0; i < size; i++) {
            result += border + " ";
        }
        return result;
    }

    /*
    Counts the number digits in a number so that it can make the display string scalable
     */
    private int numOfDigits(int num) {
        int count = 0;
        int number = num;
        while (number > 0) {
            number = number / 10;
            count++;
        }
        return count;
    }

    /*
    Determines the number spaces depending on the size of the game created
     */
    private String spaces(int num) {
        int number = num;
        String result = "";
        if (numOfDigits(number) == numOfDigits(size)) {
            return result;
        } else {
            for (int i = 0; i < numOfDigits(size) - numOfDigits(number); i++) {
                result += " ";
            }
            return result;
        }
    }

    /*
    Seperate method to do for the when there is no number in the boxes
     */
    private String spacesEmptyboxes() {
        String result = "";
        for (int i = 0; i < numOfDigits(size); i++) {
            result += " ";
        }
        return result;
    }

    /*
    Returns true if there numbers are discrete in their row and column else
    false.
    */
    private boolean checkDiscreteNumbers() {
        boolean noZeros = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = i + 1; k < size; k++) {
                    if (grid[i][j] == null || grid[k][j] == null) {
                        noZeros = false;
                    } else {
                        if (grid[i][j].getNumber() == grid[k][j].getNumber()) {

                            numberProblems.add(String.valueOf(grid[i][j].getNumber()));
                            colNum.add(String.valueOf(i));
                            rowNum.add(String.valueOf(j));
                            numberProblems.add(String.valueOf(grid[i][j].getNumber()));
                            colNum.add(String.valueOf(k));
                            rowNum.add(String.valueOf(j));

                        }
                    }
                }
                for (int l = j + 1; l < size; l++) {
                    if (grid[i][j] == null || grid[i][l] == null) {
                        noZeros = false;
                    } else {
                        if (grid[i][j].getNumber() == grid[i][l].getNumber()) {
                            numberProblems.add(String.valueOf(grid[i][j].getNumber()));
                            colNum.add(String.valueOf(i));
                            rowNum.add(String.valueOf(j));

                            numberProblems.add(String.valueOf(grid[i][j].getNumber()));
                            colNum.add(String.valueOf(i));
                            rowNum.add(String.valueOf(l));

                        }
                    }
                }

            }
        }
        return numberProblems.isEmpty() && noZeros;
    }

    /*
    Returns true if the constraints are match the correct comparison im row
    */
    private boolean checkingConstraintsInPlaceRow() {
        int i = 0;
        int j = 0;
        boolean correctCon = true;
        while (i < size - 1 && correctCon) {
            while (j < size && correctCon) {
                if (rowConstraints[i][j] != null) {
                    if (rowConstraints[i][j].setConstraintsForGrid().equals("<")) {
                        if (grid[i][j] == null || grid[i + 1][j] == null) {

                        } else {
                            correctCon = grid[i][j].getNumber() < grid[i + 1][j].getNumber();
                        }
                    } else {
                        if (grid[i][j] == null || grid[i + 1][j] == null) {

                        } else {
                            correctCon = grid[i][j].getNumber() > grid[i + 1][j].getNumber();
                        }
                    }
                }
                j++;
            }
            j = 0;
            i++;
        }
        return correctCon;
    }

    /*
    Reset the list of numbers the computer can pick from without any repitions
    */
    private void resetGenNumbers() {
        genNumber = new ArrayList<>();
        for (int j = 1; j <= size; j++) {
            for (int i = 1; i <= size - 2; i++) {
                genNumber.add(j);

            }
        }
    }

    /*
    Constructs the ArrayList for the first row of the grid
    */
    private void FirstRow() {
        for (int i = 1; i <= size; i++) {

            firstRowNum.add(i);

        }
    }

    /*
    Constructs the ArrayList for the first column of the grid
    */
    private void FirstCol() {
        int n = 0;
        for (int i = 1; i <= size; i++) {
            firstColNum.add(i);
        }
        while (grid[0][0].getNumber() != firstColNum.get(n)) {
            n++;
        }
        firstColNum.remove(n);
    }

    /*
    Constructs the first row of the grid
    */
    private void resetFirstRow() {
        FirstRow();
        for (int col = 0; col < size && firstRowNum.size() > 0; col++) {
            int iter = rnd.nextInt(firstRowNum.size());
            int randNum = firstRowNum.get(iter);
            setSquare(randNum, col, 0);
            grid[col][0].setEditable(false);
            firstRowNum.remove(iter);
        }
        genNumber.add(grid[0][0].getNumber());
    }

    /*
    Constructs the first column of the grid
    */
    private void resetFirstCol() {
        FirstCol();
        for (int row = 1; row < size; row++) {
            int iter = rnd.nextInt(firstColNum.size());
            int randNum = firstColNum.get(iter);
            setSquare(randNum, 0, row);
            grid[0][row].setEditable(false);
            firstColNum.remove(iter);
            
        }
    }

    /*
    Constructs the ith row ArrayList of the grid
    */
    private void setRowGenNum(int row) {
        int n = 0;
        for (int i = 1; i <= size; i++) {
            fillRowNum.add(i);
        }
        while (grid[0][row].getNumber() != fillRowNum.get(n)) {
            n++;
        }

        fillRowNum.remove(n);
    }

    /*
    Reset the ith row of the grid
    */
    private void redoRow(int row) {
        fillRowNum.clear();
        setRowGenNum(row);
        for (int i = 1; i < size; i++) {
            grid[i][row] = null;
        }

    }

}

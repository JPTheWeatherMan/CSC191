/* Course: CSC 191
 * Project: HW 3
 * Date: Sept 24 2021
 * Author: Jarrett P Hale
 * Purpose: This program allows the user to play the number memory game on a board
            with a minimum size of 2x2 and maximum size of 14x14. 
 */
package jarretthalehw3;
import java.util.Scanner;
import java.lang.Math;

public class JarrettHaleHW3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = 3;
        //gets the size of the board from the user
        do{
            System.out.print("Enter a size for the board: ");
            size = scanner.nextInt();
            if(size < 2 || size > 14 || size % 2 != 0){
                System.out.println("Pick new value");
            }
        }while(size < 2 || size > 14 || size % 2 != 0);
        
        //keeps giving new boards while the user wants to play,
        //prompts after each game
        char wantToPlay = 'Y';
        while(wantToPlay == 'Y'){
            gameBoard game = new gameBoard(size);
            game.printBoardValuesShown();
            game.playGame(); 
            System.out.println("Wanna play again? (y/n)");
            wantToPlay = Character.toUpperCase(scanner.next().charAt(0));
            if(wantToPlay != 'Y'){
                System.out.println("See ya!");
            }
        }
         
    }
    
}

class gameBoard{
    //holds the int value for each tile
    int[][] board;
    
    //holds if each tile has been found
    boolean[][] foundMatches;
    
    //controls if game is in play or not
    boolean gameWon;
    
    int rows;
    int cols;
    
    public gameBoard(int size){
        board = new int[size][size];
        rows = size;
        cols = size;
        generateBoard(size);
        gameWon = false;
        foundMatches = new boolean[size][size];
    }
    
    
    //To be honest I don't know why this gets stuck generating random numbers on board size 6+
    void generateBoard(int size){
        int maxSize = size * 2;
        
        int[] occurrenceCount = new int[maxSize];
        
        //go through every tile
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                boolean placed = false;
                while(!placed){
                    //generates a random number from 0 to length of occurrenceCount
                    int random = (int)(Math.random() * maxSize);
                    if(occurrenceCount[random] == 0 || occurrenceCount[random] == 1){
                        //adds one to value if added to board
                        board[i][j] = random + 1;
                        
                        //add one to the occurrence
                        occurrenceCount[random]++;
                        
                        //point is placed
                        placed = true;
                        break;
                    }
                    
                    //if the board is completely full then you can exit
                    if(isBoardPopulated()){
                        break;
                    }
                }
            }
        }
    }
    
    public void playGame(){
        Scanner scanner = new Scanner(System.in);
        
        //keeps serving turns until game condition is swapped
        while(gameWon == false){
            
            int cardOneRow = 0;
            int cardOneCol = 0;
            int cardTwoRow = 0;
            int cardTwoCol = 0;
            
            this.printBoardShowFoundValues();
            //gets card one row
            do{
                System.out.print("Enter row for card one: ");
                cardOneRow = scanner.nextInt() - 1;
                if(cardOneRow < 0 || cardOneRow > this.cols){
                    System.out.println("Must be within 1 - " + this.rows);
                }
            }while(cardOneRow < 0 || cardOneRow > this.cols);

            //gets card one col
            do{
                System.out.print("Enter col for card one: ");
                cardOneCol = scanner.nextInt() - 1;
                if(cardOneCol < 0 || cardOneCol > this.cols){
                    System.out.println("Must be within 1 - " + this.rows);
                }
            }while(cardOneCol < 0 || cardOneCol > this.cols);

            //gets card two row
            do{
                System.out.print("Enter row for card two: ");
                cardTwoRow = scanner.nextInt() - 1;
                if(cardTwoRow < 0 || cardTwoRow > this.cols){
                    System.out.println("Must be within 1 - " + this.rows);
                }
            }while(cardTwoRow < 0 || cardTwoRow > this.cols);

            //gets card two col
            do{
                System.out.print("Enter col for card two: ");
                cardTwoCol = scanner.nextInt() - 1;
                if(cardTwoCol < 0 || cardTwoCol > this.cols){
                    System.out.println("Must be within 1 - " + this.rows);
                }
            }while(cardTwoCol < 0 || cardTwoCol > this.cols);

            printBoardShowPair(cardOneRow, cardOneCol, cardTwoRow, cardTwoCol);

            //checks if cards are the same int value on board and not the same card
            if((this.board[cardOneRow][cardOneCol] == this.board[cardTwoRow][cardTwoCol]) && !(cardOneRow == cardTwoRow && cardOneCol == cardTwoCol)){
                System.out.println("A match!");
                foundMatches[cardOneRow][cardOneCol] = true;
                foundMatches[cardTwoRow][cardTwoCol] = true;
            }
            
            //check win condidtion at end of a turn
            this.checkWin();
        }
    }
    
    boolean isBoardPopulated(){
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(this.board[i][j] == 0)
                    return false;
            }
        }
        return true;
    }
    
    //runs through and if it enounters a single not found pair then the game is not over
    public boolean checkWin(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(foundMatches[i][j] == false){
                    return false;
                }
            }
        }
        this.gameWon = true;
        System.out.println("You won!");
        return true;
    }
    
    //BELOW HERE THE PRINT FUNCTIONS LIVE -- NOT PRETTY
    
    //will show every value without censoring
    public void printBoardValuesShown(){
        System.out.print("  ");
        for (int i = 0; i < cols; i++) {
            System.out.print("  "+(i + 1)+"  ");
        }
        System.out.println("");
        System.out.println("____________________________________");
        for (int i = 0; i < rows; i++) {
            System.out.print((i+1) + " |");
            for (int j = 0; j < cols; j++) {
                System.out.print("  "+this.board[i][j] + "  ");
            }  
            System.out.println("");
        }
        System.out.println("");
    }
    
    //normal one used during game. Shows values that have already been found by player
    public void printBoardShowFoundValues(){
        System.out.print("  ");
        for (int i = 0; i < cols; i++) {
            System.out.print("  "+(i + 1)+"  ");
        }
        System.out.println("");
        System.out.println("____________________________________");
        for (int i = 0; i < rows; i++) {
            System.out.print((i+1) + " |");
            for (int j = 0; j < cols; j++) {
                if(this.foundMatches[i][j] == true){
                    System.out.print("  "+this.board[i][j] + "  ");
                }
                else{
                    System.out.print("  "+"*" + "  ");
                }
            }  
            System.out.println("");
        }
        System.out.println("");
    }
    
    //This is the initial turn board, prints with every value hidden from player
    public void printBoardValuesHidden(){
        System.out.print("   ");
        for (int i = 0; i < cols; i++) {
            System.out.print("  "+(i + 1)+"  ");
        }
        System.out.println("");
        System.out.println("____________________________________");
        for (int i = 0; i < rows; i++) {
            System.out.print((i+1) + " |");
            for (int j = 0; j < cols; j++) {
                System.out.print("  "+"*"+ "  ");
            }  
            System.out.println("");
        }
        System.out.println("");
    }
    
    //this one is used during the turn to show either already found tiles or tiles selected by player
    public void printBoardShowPair(int row1, int col1, int row2, int col2){
        System.out.print("   ");
        for (int i = 0; i < cols; i++) {
            System.out.print("  "+(i + 1)+"  ");
        }
        System.out.println("");
        System.out.println("____________________________________");
        for (int i = 0; i < rows; i++) {
            System.out.print((i+1) + " |");
            for (int j = 0; j < cols; j++) {
                if((i == row1) && (j == col1)){
                    System.out.print("  "+this.board[i][j]+ "  ");
                }
                else if((i == row2) && (j == col2)){
                    System.out.print("  "+this.board[i][j]+ "  ");
                }
                else if(this.foundMatches[i][j] == true){
                    System.out.print("  "+this.board[i][j] + "  ");
                }
                else{     
                    System.out.print("  "+"*"+ "  ");
                }
            }  
            System.out.println("");
        }
        System.out.println("");
    }
    
}
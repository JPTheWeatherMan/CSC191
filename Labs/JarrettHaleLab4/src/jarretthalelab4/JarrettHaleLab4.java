/* Course: CSC 191
 * Project: Lab 4
 * Date: Sept 8 2021
 * Author: Jarrett P Hale
 * Purpose: 2 player tic tac toe with win count and custom board.
 */
package jarretthalelab4;
import java.util.Scanner;

public class JarrettHaleLab4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int quitCode = 0;
        char currentTurn = 'X';
        char[][] board = new char[3][3];
        int turnChoiceRow;
        int turnChoiceCol;
        int playerXWinCount = 0;
        int playerOWinCount = 0;
        // TODO code application logic here
        do{
            printBoard(board);
            do{
                System.out.print("Enter a row for Player " + currentTurn+"(1,2,3): ");
                turnChoiceRow = scanner.nextInt()-1;
                System.out.print("Enter a Col for Player " + currentTurn+"(1,2,3): ");
                turnChoiceCol = scanner.nextInt()-1;
                if(thereExistsACharacter(board, turnChoiceRow, turnChoiceCol)){
                    System.out.println("A Token is already there");
                    //just some values to mike the while freak out.
                    turnChoiceRow = 5;
                    turnChoiceCol = 5;
                }
            }while((turnChoiceRow < 0 || turnChoiceRow > 4) || (turnChoiceCol < 0 || turnChoiceCol > 4));
            

            board[turnChoiceRow][turnChoiceCol] = currentTurn;
            currentTurn = setTurn(currentTurn);
            
            //win condition is an array with 2 indexes. first is a boolean for if there's a winner,
            // second is the winner.
            char[] winCondition = checkWinConditionAndWinner(board);
            if(winCondition[0] == 'T'){
                if(winCondition[1]!= 'N')
                    System.out.println(winCondition[1]+ " Wins!");
                if(winCondition[1] == 'X')
                    playerXWinCount++;
                if(winCondition[1] == 'O')
                    playerOWinCount++;
                if(winCondition[1] == 'N')
                    System.out.println("Nobody wins");
                System.out.println("X has "+playerXWinCount+" Wins.");
                System.out.println("O has "+playerOWinCount+" Wins.");
                System.out.println("Do you want to play again(Y/N)?");
                char decision = scanner.next().charAt(0);
                if(decision == 'y' || decision == 'Y'){
                    board = new char[3][3];
                }
                if(decision == 'n' || decision == 'N'){
                    quitCode = -1;
                }
            }
        }while(quitCode != -1);

    }
    
    public static void printBoard(char[][] board){
        System.out.println("  1  2  3 ");
        System.out.println("1| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);  
        System.out.println("------------");
        System.out.println("2| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);  
        System.out.println("------------");
        System.out.println("3| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]); 
    }
    
    // I know... hardcoded win conditions... sad
    public static char[] checkWinConditionAndWinner(char[][] board){
        //First value is boolean for if there is a winner, second value is the winnner
        char[] winConditionAndWinner = {'F', '_'};
        //Checks each row for a winner
        for(int row = 0; row < board.length; row++){
            if(board[row][0] == 'X' && board[row][1] == 'X' && board[row][2] == 'X'){
                winConditionAndWinner[0] = 'T';
                winConditionAndWinner[1] = 'X';
            }
            if(board[row][0] == 'O' && board[row][1] == 'O' && board[row][2] == 'O'){
                winConditionAndWinner[0] = 'T';
                winConditionAndWinner[1] = 'O';
            }
        }
        
        //checks each column for a winner
        for (int col = 0; col < board[0].length; col++) {
            if(board[0][col] == 'X' && board[1][col] == 'X' && board[2][col] == 'X'){
                winConditionAndWinner[0] = 'T';
                winConditionAndWinner[1] = 'X';
            }
            if(board[0][col] == 'O' && board[1][col] == 'O' && board[2][col] == 'O'){
                winConditionAndWinner[0] = 'T';
                winConditionAndWinner[1] = 'O';
            }
        }
      
        //Checks Diagnal win conditions
        if(board[1][1] == 'X'){
            if(board[0][0] == 'X' && board[2][2] == 'X'){
                winConditionAndWinner[0] = 'T';
                winConditionAndWinner[1] = 'X'; 
            }
            if(board[2][0] == 'X' && board[0][2] == 'X'){
                winConditionAndWinner[0] = 'T';
                winConditionAndWinner[1] = 'X';  
            }
        }
        if(board[1][1] == 'O'){
            if(board[0][0] == 'O' && board[2][2] == 'O'){
                winConditionAndWinner[0] = 'T';
                winConditionAndWinner[1] = 'O';  
            }
            if(board[2][0] == 'O' && board[0][2] == 'O'){
                winConditionAndWinner[0] = 'T';
                winConditionAndWinner[1] = 'O';  
            }
        }
        
        //full board and no winner
        String accumulator = "";
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                //only if there are actually characters there then add them to the accumulator
                if(board[row][col] == 'X' || board[row][col] == 'O')
                    accumulator += board[row][col];
            }
        }
        if(accumulator.length() == 9){
            winConditionAndWinner[0] = 'T';
            winConditionAndWinner[1] = 'N';
        }
        return winConditionAndWinner;
    }
    
    public static char setTurn(char currentTurn){
        if (currentTurn == 'X')
            return 'O';
        return 'X';
    }
    
    public static boolean thereExistsACharacter(char[][] board, int row, int column){
        if(board[row][column] == 'X' || board[row][column] == 'Y')
            return true;
        return false;
    }
}

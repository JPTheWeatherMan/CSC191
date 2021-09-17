/*
 * Course: CSC 191
 * Project: Lab 5
 * Date: 9/15/21
 * Author: Jarrett Hale
 * Purpose: This program generates a 2D array of random numbers with the user
            choosing how many rows and columns. The goal is to find an occurrence
            of 4 of the same integer in a row.
 */
package jarretthalelab5;
import java.util.Scanner;
import java.util.Random;

public class JarrettHaleLab5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] board;
        int rows = -1;
        int cols = -1;
        
        //collects input from user for size of 2D array
        do{
            System.out.print("How many rows?(>4): ");
            rows = scanner.nextInt();
            System.out.print("How many cols?(>4): ");
            cols = scanner.nextInt();
            if(rows < 4 || cols < 4){
                System.out.println("Rows and Cols must be greater than 4");
            }
            board = new int[rows][cols];
        }while(rows < 4 || cols < 4);
        
        //once user input is collected populate the board with random values
        populateBoard(board);
        //print board to console
        printBoard(board);
        System.out.println();
        //runs the test suite to see if 4 consecutive identical integers can be found.
        if(findFourConsecutive(board))
            System.out.println("4 consecutive identical numbers were found!");
        else
            System.out.println("Nothing found...");
    }
    
    //populates board with random values from 0-9
    public static void populateBoard(int[][] board){
        Random random = new Random();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = random.nextInt(10);
            }
        }
    }
    
    //prints a 2D array to the console
    public static void printBoard(int[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    //returns true if all inputs are the same integer
    public static boolean areFourParamsEqual(int w, int x, int y, int z){
        if(w == x)
            if(x == y)
                if(y == z)
                    return true;
        return false;
    }
    
    //runs each search function to get some of the crowd out of the main function
    public static boolean findFourConsecutive(int[][] board){
        //I thought it was best to break this into 4 functions, one for each type.
        if(findVerticalMatch(board) || findHorizontalMatch(board) || findDownSlantMatch(board) || findUpSlantMatch(board))
            return true;
        return false;
    }
    
    //this function handles finding matches within a column
    public static boolean findVerticalMatch(int[][] board){
        for (int i = 0; i < board.length-3; i++) {
            //checks each column up until length - 3 so no out of bound
            for (int j = 0; j < board[i].length; j++) {
                if(areFourParamsEqual(board[i][j],board[i+1][j],board[i+2][j],board[i+3][j]))
                    return true;
            }
        }
        return false;
    }
    
    //handles finding matches within a row
    public static boolean findHorizontalMatch(int[][] board){
        //checks each row up until length - 3 so no out of bound
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length-3; j++) {
                if(areFourParamsEqual(board[i][j],board[i][j+1],board[i][j+2],board[i][j+3]))
                    return true;
            }
        }
        return false;
    }
    
    //handles finding a match in a case like [0][0], [1][1], [2][2], [3][3]
    public static boolean findDownSlantMatch(int[][] board){
        //checks in a downward slant by using matching indexes
        for (int i = 0; i < board.length-3; i++) {
            for (int j = 0; j < board[i].length-3; j++) {
                if(areFourParamsEqual(board[i][j],board[i+1][j+1],board[i+2][j+2],board[i+3][j+3]))
                    return true;
            }
        }
        return false;
    }
    
    //handles finding a match in a case like [0][3], [1][2], [2][1], [3][0]
    public static boolean findUpSlantMatch(int[][] board){
        //checks in an up slant by using indexes that are kinda like the binomial theorem
        for (int i = 0; i < board.length-3; i++) {
            for (int j = 3; j < board[i].length; j++) {
                if(areFourParamsEqual(board[i][j],board[i+1][j-1],board[i+2][j-2],board[i+3][j-3]))
                    return true;
            }
        }
        return false;
    }
    
}

/*
 * Course: CSC 191
 * Project: Lab 1
 * Date: 8/18/21
 * Author: Jarrett P Hale
 * Purpose: This program takes input from the user and constructs
            some geometric shapes at the height of input using nested
            for loops.
 */

package jarretthalelab1;

import java.util.Scanner;
public class Jarretthalelab1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a height: ");
        int userHeight = scanner.nextInt();
        System.out.println("Obtuse Triangle:");
        printObtuseTriangle(userHeight);
        System.out.println();
        System.out.println("V shape:");
        printV(userHeight);
        System.out.println();
        System.out.println("Parallelogram:");
        printParallelogram(userHeight);

    }
    
    public static void printObtuseTriangle(int height){
        //manages lines
        for (int i = 0; i < height + 1; i++) {
            //handles front spacing
            for (int k = height - i; k > 0; k--) {
                System.out.print("  ");
            }
            //places stars at end
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }
    public static void printV(int height){
        int leftSide = 1;
        int rightSide = height*2-1 ;
        //prints line and manages state
        for (int i = 1; i <= height; i++) {
            //indexes current spot in line
            for (int currentPlace = 1; currentPlace < height*2; currentPlace++) {
                //case where current place is on edge
                if(currentPlace == leftSide || currentPlace == rightSide){
                    System.out.print("*");
                }
                //case where current place is not on edge
                else{
                    System.out.print(" ");
                }
            }
            leftSide++;
            rightSide--;
            System.out.println("");
        }
    }
    
    public static void printParallelogram(int height){
        //handles lines
        for(int i = 0; i < height; i++){
            //handles stars
            for(int j = 0; j < height; j++){
                System.out.print("*");
            }
             //breaks after each star line
             System.out.println();
             
             //handles white spaces
             for(int l = 0; l <= i; l++){
                System.out.print("  ");
            }
        }
    }
    
}

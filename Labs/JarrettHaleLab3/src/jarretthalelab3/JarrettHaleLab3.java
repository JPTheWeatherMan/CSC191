/*
 * course: CSC 191
 * project: Lab 3
 * date: 9/1/2021
 * author: Jarrett P Hale
 * Purpose: This program takees input from the user and generates a random
            distribution of runs on a Bean Machine.
 */
package jarretthalelab3;
import java.util.Scanner;

public class JarrettHaleLab3 {

    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        int ballsToDrop = 0;
        int numberOfSlots = 0;
        do{
            System.out.print("Enter the amount of balls to drop(>0): ");
            ballsToDrop = scanner.nextInt();
            System.out.println();
            System.out.print("Enter the number of slots(>0): ");
            numberOfSlots = scanner.nextInt();
            System.out.println();
        }while(ballsToDrop < 1 || numberOfSlots < 1);
        String[] ballPaths = generateBallPaths(ballsToDrop, numberOfSlots);
        int[] populatedSlots = populateSlots(ballsToDrop, numberOfSlots, ballPaths);
        printInformation(ballPaths, populatedSlots);
    }
    
    public static String determineBallPath(int numberOfSlots){
        String ballPathStringRepresentation = "";
        for (int i = 0; i < numberOfSlots - 1; i++) {
            int choice = (int)Math.round(Math.random());
            if(choice == 0)
                ballPathStringRepresentation = ballPathStringRepresentation.concat("L");
            if(choice == 1)
                ballPathStringRepresentation = ballPathStringRepresentation.concat("R");
        }
        return ballPathStringRepresentation;
    }
    
    public static int[] populateSlots(int numberOfBalls, int numberOfSlots, String[] ballPaths){
        int[] slots = new int[numberOfSlots];
        for (int i = 0; i < numberOfBalls; i++) {
            int currentIndex = (numberOfSlots -1) / 2;
            for (int j = 0; j < ballPaths[i].length(); j++) {
                if(ballPaths[i].charAt(j) == 'L')
                    if(currentIndex > 0)
                        currentIndex--;
                if(ballPaths[i].charAt(j) == 'R')
                    if(currentIndex < slots.length - 1)
                        currentIndex++;
            }
            slots[currentIndex]++;
        }
        return slots;
    }
    
    public static String[] generateBallPaths(int numberOfBalls, int numberOfSlots){
        String[] ballPaths = new String[numberOfBalls];
        for (int i = 0; i < numberOfBalls; i++) {
            String path = determineBallPath(numberOfSlots);
            ballPaths[i] = path;
        }
        return ballPaths;
    }
    
    public static void printInformation(String[] ballPaths, int[] populatedSlots){
        System.out.println("-----Ball Path Summary-----");
        for (int i = 0; i < ballPaths.length; i++) {
            System.out.println("Ball #"+i+" path: "+ballPaths[i]);
        }
        System.out.println("");
        System.out.println("-----Ball placement histogram-----");
        for (int i = 0; i < populatedSlots.length; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < populatedSlots[i]; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }
    
    public static void printIntArray(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    
    public static void printStringArray(String[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    
    
}

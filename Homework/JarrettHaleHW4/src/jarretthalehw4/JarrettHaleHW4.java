/* Course: CSC 191
 * Project: HW 4
 * Date: Oct 22 2021
 * Author: Jarrett P Hale
 * Purpose: This program allows the user to enter a string of their choice 
            and then use some functions served through a menu to see various 
            methods of generating substrings via recursion.
 */
package jarretthalehw4;
import java.util.Scanner;


public class JarrettHaleHW4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //Controls exit condidtion
        int quitCode = 1;
        
        //So that string collection only runs one
        boolean stringCollected = false;
        
        //Variable for main menu
        int menuChoice = 0;
        
        //collect string from user
        String userString = "";
        do{
            System.out.println("Enter a string below to manipulate: ");
            userString = scanner.nextLine();
            stringCollected = true;
        }while(stringCollected = false);
        
        //show menu
        do{
            System.out.println("");
            System.out.println("1. printSub1");
            System.out.println("2. printSub2");
            System.out.println("3. printSub3");
            System.out.println("4. printSub4");
            System.out.println("5. Quit");
            System.out.println("What would you like to do? ");
            
            //get user choice
            menuChoice = scanner.nextInt();
            
            //validate choice
            if(menuChoice > 5 || menuChoice < 1)
                System.out.println("Must be valid choice");
            
            switch(menuChoice){
                //printSub1
                case 1:
                    printSub1(userString);
                    System.out.println("");
                    break;
                    
                //printSub2    
                case 2:
                    String reversed = reverseString(userString);
                    printSub2(reversed);
                    break;
                    
                //printSub3    
                case 3:
                    printSub3(userString);
                    break;
                    
                //printSub4
                case 4:
                    printSub4(userString);
                    break;
                    
                //Quit
                case 5:
                    System.out.println("See ya");
                    quitCode = 0;
                    break;
            }
        }while(quitCode != 0 || (menuChoice > 5 || menuChoice < 1));
    }
    
    static public void printSub1(String n){
        if(n.length() == 0)
            return;
        else
            printSub1(n.substring(0, n.length() - 1));
            System.out.print(n+", ");
    }

    
    static public void printSub2(String n){
        if(n.length() == 0)
            return;
        
        printSub2(n.substring(0, n.length() - 1));
        System.out.print(n+", ");
    }
    
    static public void printSub3(String n){
        if(n.length() == 0)
            return;
        else
            printSub1(n.substring(0, n.length() - 1));
            //printSub1Heler(n);
            System.out.print(n+", ");
    }
    
    static public void printSub4(String n){
        //base case... wtf is the base case here?
        //idk bout this 
        if (n.length() == 1)
        {
            System.out.println(n);
            return;
        }


        //recursive case
        if (n.length() != 0)
        {

            System.out.println(n);
            printSub4(n.substring(0, n.length()-1)); //gets the first 5 substrings
        }
    }
    
    static public String reverseString(String str) {
        if ((null == str) || (str.length() <= 1)) {
            return str;
        }
        return reverseString(str.substring(1)) + str.charAt(0);
    }   
}

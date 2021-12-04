/* Course: CSC 191
 * Project: Lab 7
 * Date: October 6 2021
 * Author: Jarrett P Hale
 * Purpose: This program comes with 3 functions to demonstrate recursion.
 *          one to detect duplicate strings, another for single digit equations,
 *          and one final function to perform indexOf recursively.
 */
package jarretthalelab7;
import java.util.Scanner;

public class JarrettHaleLab7 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int quitCode = 1;
        int choice;
        do{
            System.out.println("");
            System.out.println("1. isDuplicate");
            System.out.println("2. evalAS1");
            System.out.println("3. indexOf");
            System.out.println("4. quit");
            System.out.println("Which would you like to test?");
            choice = scanner.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter a string to check equivilent duplicity: ");
                    String s = scanner.next();
                    System.out.println(isDuplicate(s));
                    break;
                    
                case 2:
                    System.out.println("Enter an addition/substracton only problem with only single digits");
                    String e = scanner.next();
                    System.out.println(e+ "evaluates to:" + evalAS1(e));; 
                    break;
                    
                case 3:
                    System.out.println("Enter a string: ");
                    String s1 = scanner.next();
                    System.out.println("Enter a character: ");
                    String s2 = scanner.next();
                    System.out.println("Index of "+s2 +" is: " + indexOf(s1, s2));
                    break;
                
                case 4:
                    quitCode = 0;
                    System.out.println("See ya!");
                    break;
                
                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }while(quitCode != 0);
    }
    
    public static boolean isDuplicate(String s){
        // grab middle point
        int middle = s.length() / 2;
        
        //if the lengtth is only 0 or 1 it's true by definition
        if(s.length() == 0 || s.length() == 1)
            return true;
        
        //compare the character at start and beginning, if they're the same trim the string and call again
        if(s.charAt(0) == s.charAt(middle)){
            //drops first and middle characters
            String debug = s.substring(1, middle) + s.substring(middle+1);
            return isDuplicate(debug);
        }
        return false;
    }
    
    public static int evalAS1(String e){
        int total = 0; 
        // just return if theres not enough operands
        if(e.length() == 0 || e.length() == 1 || e.length() == 2)
            return Integer.parseInt(e.substring(0,1));
        
        
        if(e.charAt(1) == '+'){
            total += Integer.parseInt(e.substring(0,1)) + Integer.parseInt(e.substring(2,3));
            e = total + e.substring(3);
            return evalAS1(e);
        }
        if(e.charAt(1) == '-'){
            total += Integer.parseInt(e.substring(0,1)) - Integer.parseInt(e.substring(2,3));
            e = total + e.substring(3);
            return evalAS1(e);
        }
        
        //something bad happened if we get here
        return -1;
    }
  
    public static int indexOf(String s1, String s2){
       return indexOf(s1, s2, 0);
    }
    
    private static int indexOf(String s1, String s2, int count){
        //if the length of the first string is one and doesnt match the character, it doesnt exsist
        if(s1.length() == 1 && !s2.equals(s1))
            return -1;
        
        //ayyyy a match
        if(Character.toString(s1.charAt(0)).equals(s2))
            return count;
        
        //chop
        s1 = s1.substring(1);
        
        //count of runs
        count++;
        
        //recurse
        return indexOf(s1, s2, count);
    }
}

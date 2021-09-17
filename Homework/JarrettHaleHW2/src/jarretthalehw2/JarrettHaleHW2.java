/* Course: CSC 191
 * Project: HW 2
 * Date: Sept 16 2021
 * Author: Jarrett P Hale
 * Purpose: This program allows for the user to create a representation of a theater
            and set pricing for each of the seats. The user is able to search by
            price of seats and seat availablity. In addition the user is also able
            to "purchase" upt to 6 tickets in a given transaction.
 */
package jarretthalehw2;
import java.util.Scanner;


public class JarrettHaleHW2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    }
    
}
class Theater{ 
     
    private double[][] seats; 
    private String eventName; 
     
    public Theater(int n, int m, String e){ 
        seats = new double[n][m]; 
        eventName = e; 
    } 
}
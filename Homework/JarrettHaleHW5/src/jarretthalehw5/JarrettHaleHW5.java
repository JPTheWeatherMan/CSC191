/* Course: CSC 191
 * Project: HW 5
 * Date: Oct 31 2021
 * Author: Jarrett P Hale
 * Purpose: This program features an implementation of string functions found
            in the java standard library but with a recursive twist. A menu is
            presented and strings are manipulated
 */
package jarretthalehw5;
import java.util.Scanner;

public class JarrettHaleHW5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int quitCode = 1;
        int choice = 0;
        boolean isFirstRun = true;
        StringInstance sStore = new StringInstance();
        do{
            if(isFirstRun){
                System.out.println("Enter String 1:");
                String s1 = scanner.nextLine();
                sStore.sets1(s1);
                System.out.println("Enter String 2:");
                String s2 = scanner.nextLine();
                sStore.sets2(s2);
                isFirstRun = false;
            }
            System.out.println("1. removeDup");
            System.out.println("2. intersection");
            System.out.println("3. union");
            System.out.println("4. difference");
            System.out.println("5. submit new strings");
            System.out.println("6. quit");
            System.out.println("What would you like to do?");
            choice = scanner.nextInt();
            if(choice > 6 || choice < 1){
                System.out.println("Please enter valid selection");
            }
            switch(choice){
                //remove duplicates
                case 1:
                    boolean selected = false;
                    int stringSelection = 0;
                    do{
                        System.out.println("1. s1");
                        System.out.println("2. s2?");
                        System.out.println("Would you like to remove duplicates from s1 or s2?");
                        stringSelection = scanner.nextInt();
                        if(stringSelection > 2 || stringSelection < 1)
                            System.out.println("Please enter valid selection");
                        else
                            selected = true;
                    }while(selected = false || stringSelection > 2 || stringSelection < 1);
                    switch(stringSelection){
                        case 1:
                            String removeDups = removeDups(sStore.gets1());
                            System.out.println("s1 with duplicates removed: "+removeDups);
                            sStore.sets1(removeDups);
                            break;
                        case 2:
                            removeDups = removeDups(sStore.gets2());
                            System.out.println("s2 with duplicates removed: "+removeDups);
                            sStore.sets2(removeDups);
                            break;
                    }
                //intersection
                case 2:
                    String intersection = intersection(removeDups(sStore.gets1()),removeDups(sStore.gets2()));
                    System.out.println("Intersection between "+sStore.gets1()+" and "+sStore.gets2()+" is: "+intersection);
                    break;
                //union
                case 3:
                    String union = union(removeDups(sStore.gets1()),removeDups(sStore.gets2()));
                    System.out.println("Union between "+sStore.gets1()+" and "+sStore.gets2()+" is: "+union);
                    break;
                //difference
                case 4:
                    String difference = difference(removeDups(sStore.gets1()),removeDups(sStore.gets2()));
                    System.out.println("Difference between "+sStore.gets1()+" and "+sStore.gets2()+" is: "+difference);
                    break;
                //submit new strings
                case 5:
                    isFirstRun = true;
                    System.out.println("Resetting...");
                    System.out.println("");
                    break;
                //quit
                case 6:
                    System.out.println("See ya!");
                    quitCode = 0;
                    break;
            }
        }while(quitCode != 0 || choice > 6 || choice < 1);

    }
        
    //remove duplicates from a string recursively
    public static String removeDups(String s){
        if(s.length() <= 1)
            return s;
        if(s.substring(1,2).equals(s.substring(0,1)))
            return removeDups(s.substring(1));
        else
            return s.substring(0,1) + removeDups(s.substring(1));
            
    }
    
    //takes two duplicate free strings and returns intersection of characters
    //between the two
    public static String intersection(String s1, String s2){
        if(s1.length() == 0 && s2.length() == 0){
            return "";
        }
        if(s2.length()==0)
            return s1;
        if(s1.contains(Character.toString(s2.charAt(0))))
            return intersection(s1, s2.substring(1));
        else
            return intersection(s1+Character.toString(s2.charAt(0)), s2.substring(1));
        
        
    }
    
    //takes two duplicate free strings and returns the string of all letters 
    //that are present in either string
    public static String union(String s1, String s2){
        if(s2.length()==0)
            return s1;
        if(s1.length()==0)
            return s2;
        if(s1.contains(Character.toString(s2.charAt(0))))
            return union(s1, s2.substring(1));
        else
            return union(s1.substring(1)+Character.toString(s2.charAt(0)), s2.substring(1));
    }
    
    //takes two duplicate free strings and returns the string of characters that are
    //only present in s1
    public static String difference(String s1, String s2){
        if(s2.contains(Character.toString(s1.charAt(0)))){
            return difference(s1.substring(1), s2.substring(1));
        }
        if(s2.length()==0)
            return s1;
        if(s1.length()== 0)
            return "";
        else
            return difference(s1.substring(1)+Character.toString(s1.charAt(0)), s2.substring(1));
        
    }

}

class StringInstance{
    private String s1;
    private String s2;
    
    public StringInstance(String s1, String s2){
        this.s1 = s1;
        this.s2 = s2;
    }
    
    public StringInstance(){
        this.s1 = "";
        this.s2 = "";
    }
    
    public void sets1(String s){
        this.s1 = s;
    }
    
    public String gets1(){
        return this.s1;
    }
    
    public void sets2(String s){
        this.s2 = s;
    }
    
    public String gets2(){
        return this.s2;
    }
    
}

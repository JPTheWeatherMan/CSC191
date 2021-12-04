/*
 * course: CSC 191
 * project: lab 8
 * date: 10/20/21
 * author: Jarrett Hale
 * purpose: Add more funcationality to the recursion class in the form of methods
            that manipulate or verify an array.
 */
package jarretthalelab8;
import java.util.Scanner;


public class JarrettHaleLab8 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //variable to control quit statuis
        int quitCode = 1;
        
        //get array length
        System.out.print("Enter length for array: ");
        int length = scanner.nextInt();
        int[] a = new int[length];
        
        //populate array
        for (int i = 0; i < length; i++) {
            System.out.print("Enter value for index "+i+": ");
            a[i] = scanner.nextInt();
        }
        
        //i love to instantiate
        Recursion r = new Recursion(a);
        
        //to initiaite the menu
        int choice = 0;
        do{
            System.out.println("");
            System.out.println("1. Rotate array");
            System.out.println("2. Check sort status of array up to index");
            System.out.println("3. Check if all elements are same value");
            System.out.println("4. Check if all elements are even integers");
            System.out.println("5. Quit");
            choice = scanner.nextInt();
            if(choice > 5 || choice < 1){
                System.out.println("choice must be 1-5");
            }
            switch(choice){
                case 1:
                    System.out.println("Original array:");
                    r.printArray();
                    r.rotateR(a.length);
                    System.out.println("Mutated array");
                    r.printArray();
                    break;
                    
                case 2:
                    //index to search to, gathered from user
                    int index = 0;
                    do{
                        System.out.print("What index would you like to search up to? ");
                        index = scanner.nextInt();
                        if(index < 0 || index > a.length - 1){
                            System.out.println("Must choose valid index");
                        }
                    }while(index < 0 || index > a.length - 1);
                    boolean sorted = r.sortedAsFarAs(index);
                    if(sorted == true)
                        System.out.println("Array is sorted to "+index);
                    if(sorted == false)
                        System.out.println("Not sorted");
                    break;
                    
                case 3:
                    boolean allEqual = r.elementsAreSameValue(a.length-1);
                    if(allEqual == true)
                        System.out.println("All elements are the same");
                    if(allEqual == false)
                        System.out.println("All elements are not the same");
                    break;
                    
                case 4:
                    boolean allEven = r.areElementsEven(a.length-1);
                    if(allEven == true)
                        System.out.println("All elements are even");
                    if(allEven == false)
                        System.out.println("All elements are not even");
                    break;
                    
                case 5:
                    System.out.println("See ya!");
                    quitCode = 0;
                    break;
            }
            choice = 0;
         }while(choice > 5 || choice < 1 && quitCode != 0);
                   
    }
    
}

class Recursion{
    private int[] a;
    
    //constructor
    public Recursion(int[] array){
        a = array;
    }

    //get and set methods for data members
    public int[] getA(){
	return a;
    }

    public void setA(int[] array){
	a = array;
    }
    
    //print array forward
    // n in these methods will be the number of elements to affect
    public void printF(int n){
        if(n>0){
            printF(n-1);
            System.out.print(a[n-1]);
        }
    }
    
    //print array backward
    public void printB(int n){
        if(n>0){
            System.out.print(a[n-1]);
            printB(n-1);
        }
    }
    
    //smallest element
    public int minA(int n){
        if(n==1)
            return a[0];
        return Math.min(minA(n-1), a[n-1]);
    }
    
    //reversing an array
    public void reverseA(){
        reverseA(0, a.length-1);
    }
    
    private void reverseA(int l, int r){
        if(l<r){
            int t = a[l];
            a[l] = a[r];
            a[r] = t;
            reverseA(l+1, r-1);
        }
    }
    
    //mode of the int array - int that appears most often
    public int mode(int n){
        if (n==1)
            return a[0];
        int m = mode(n-1);
        int c1 = 0, c2 = 1;
        for (int i = 0; i < n-1; i++) {
            if(a[i]==m)
                c1++;
            else if(a[i]==a[n-1])
                c2++;
        }
        if(c1>c2)
            return m;
        return a[n-1];
        
    }
    
    // New functions live here.
    //1.) Rotate n elements in array to the left.
    public void rotateR(int n){
        if(n > 1){
            rotateR(n-1);
            int temp = a[0];
            a[0] = a[n - 1];
            a[n-1] = temp;
        }
    }
    
    //2.) Are the first n elements are sorted?
    public boolean sortedAsFarAs(int n){
        if(n > 0){
            sortedAsFarAs(n - 1);
            //if this element is smaller than the one to the left, array is not sorted
            if(a[n] < a[n - 1])
                return false;
        }
        //returns true if condition is never met
        return true;
    }
    
    //3.) Are all numbers equal to each other?
    public boolean elementsAreSameValue(int index){
        //if elements is not the same as the one to the left, elements are not all same value
        if(a[index] != a[index - 1]){
            return false;
        }
        //keep going until we only have one element on the left
        else if(index != 1){
            return elementsAreSameValue(index - 1);
        }
        return true;
    }
    
    //4.) Are all numbers even?
    public boolean areElementsEven(int index){
        System.out.println(a[index]);
        
        //if a single element is not even, bail
        if(a[index] % 2 != 0){
            return false;
        }
        else if (index != 0){
            return areElementsEven(index - 1);
        }
        return true;
    }
    
    //helper function that helps
    public void printArray(){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println("");
    }
 
}
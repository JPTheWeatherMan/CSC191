/*
course: CSC 191

date: aug 24 2021
author: Jarrett Hale
purpose: Class 2 Demo
 */
package aug24fall21;

class Student {
    //instance variables or fields - private data only available to tthis class
    private String name;
    private double test1;
    private double test2;
    
    // constructors are used to create an object from the class
    public Student(String name, double test1, double test2){
        this.name = name;
        this.test1 = test1;
        this.test2 = test2;
    }
    
    // default constructor without parameters - this is overloading
    public Student(){
        
    }
    
    //accessors and mutators
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public double getTest1(){
        return this.test1;
    }
    
    public void setTest1(double test1){
        this.test1 = test1;
        if(test1 >= 0){
            this.test1 = test1;
        }
        else
            System.out.println("you can't have a test score less than 0");
    }
    
    public double getTest2(){
        return this.test2;
    }
    
    public void setTest2(double test2){
        this.test2 = test2;
        if(test2 >= 0){
            this.test2 = test2;
        }
        else
            System.out.println("you can't have a test score less than 0");
    }
    
    //this is inherited from Object
    @Override
    public String toString(){
        return name + "'s test 1 score is "+test1+" and test 2 score is "+test2;
    }
    
    public double computeAverage(){
        double testAverage =  (test1 + test2) / 2;
        return testAverage;
    }
}

class OnlineStudent extends Student{
    //in the child class we put new class members and redefine inherited
    //class members if needed
    
    private double discussion;
    
    public OnlineStudent(String name, double test1, double test2, double discussion){
        super(name, test1, test2);
        this.discussion = discussion;
    }
    
    //gets and sets for the new instance varibales
    public double getDiscussion(){
        return this.discussion;
    }
    
    public void setDiscussion(double discussion){
        this.discussion = discussion;
        if(discussion >= 0){
            this.discussion = discussion;
        }
        else
            System.out.println("you can't have a discussion score less than 0");
    }
    
    @Override
    public String toString(){
        return super.toString()+" and a discussion grade of "+discussion;
    }
    
    @Override
    public double computeAverage(){
        double average = (discussion + getTest1()+getTest2()) / 3;
        return average;
    }
}

public class Aug24Fall21 {

    public static void main(String[] args) {
        // TODO code application logic here
        Student s1 = new Student("Bob", 87, 78);
        System.out.println(s1); //printing the object envokes toString() and prints memory addr
        System.out.println(s1.getName()+"'s course average is "+s1.computeAverage());
        OnlineStudent os1 = new OnlineStudent("Bobby", 87,78,100);
        System.out.println(os1.getName()+"'s course average is "+os1.computeAverage());
        System.out.println(os1);
    }
    
}

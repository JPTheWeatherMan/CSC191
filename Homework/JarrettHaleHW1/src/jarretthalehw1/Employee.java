/*
 * Course: CSC 191
 * Project: Homework 1
 * Date: 8/29/21
 * Author: Jarrett P Hale
 * Purpose: This program has an employee superclass and a salesperson subclass.
            Each class contains information relating to their real life 
 */
package jarretthalehw1;


public class Employee {
    private String name;
    private String email;
    private double payRate;
    private int idNumber;
    private double[] timeSheet;
    
    public Employee(String name, String email, double payRate, int idNumber, double[] timeSheet){
        this.name = name;
        this.email = email;
        this.payRate = payRate;
        this.idNumber = idNumber;
        this.timeSheet = timeSheet;
    }
    
    public Employee(){
        this.name = "Default";
        this.email = "Default@Default.com";
        this.payRate = 0.0;
        this.idNumber = 0;
        this.timeSheet = new double[7];
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public double getPayRate(){
        return this.payRate;
    }
    
    public void setPayRate(double payRate){
        this.payRate = payRate;
    }
    
    public int getIdNumber(){
        return this.idNumber;
    }
    
    public void setIdNumber(int idNumber){
        this.idNumber = idNumber;
    }
    
    public double[] getTimeSheet(){
        return this.timeSheet;
    }
    
    public void setTimeSheet(double[] timeSheet){
        this.timeSheet = timeSheet;
    }
    
    public void addShiftToDay(int index, double hoursWorked){
        this.timeSheet[index] = hoursWorked;
    }
    
    public double calculatePay(){
        double hoursWorked = 0.0;
        for (int i = 0; i < timeSheet.length; i++) {
            hoursWorked += timeSheet[i];
        }
        double pay = this.payRate * hoursWorked * 0.65;
        return pay;
    }
    
    @Override
    public String toString(){
        return "Employee Name: "+this.name+", Email: "+this.email+", Payrate: " +
                this.payRate+", ID Number: "+this.idNumber;
    }
}

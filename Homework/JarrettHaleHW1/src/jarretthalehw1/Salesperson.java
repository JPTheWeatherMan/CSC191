/*
 * Course: CSC 191
 * Project: Homework 1
 * Date: 8/29/21
 * Author: Jarrett P Hale
 * Purpose: This program has an employee superclass and a salesperson subclass.
            Each class contains information relating to their real life 
 */
package jarretthalehw1;


public class Salesperson extends Employee {
    private double commissionRate;
    
    public Salesperson(String name, String email, double payRate, int idNumber, double[] timeSheet, double commissionRate){
        super(name, email, payRate, idNumber, timeSheet);
        this.commissionRate = commissionRate;
    }
    
    public Salesperson(){
        this.commissionRate = 0.0;
    }
    
    public double getCommissionRate(){
        return this.commissionRate;
    }
    
    public void setCommissionRate(double commissionRate){
        this.commissionRate = commissionRate;
    }
    
    public double calculatePay(int weeklySales){
        double hoursWorked = 0.0;
        for (int i = 0; i < this.getTimeSheet().length; i++) {
            hoursWorked += this.getTimeSheet()[i];
        }
        double pay = ((this.getPayRate() * hoursWorked)+(weeklySales * this.commissionRate))*0.65;
        return pay;
    }
    
    @Override
    public String toString(){
        return "Salesperson Name: "+this.getName()+", Email: "+this.getEmail()+", Payrate: " +
                this.getPayRate()+", ID Number: "+this.getIdNumber()+", Commission Rate: "+
                this.commissionRate;
    }
}

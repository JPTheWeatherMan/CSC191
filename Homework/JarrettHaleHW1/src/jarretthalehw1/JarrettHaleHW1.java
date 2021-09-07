/*
 * Course: CSC 191
 * Project: Homework 1
 * Date: 8/29/21
 * Author: Jarrett P Hale
 * Purpose: This program has an employee superclass and a salesperson subclass.
            Each class contains information relating to their real life 
 */
package jarretthalehw1;


public class JarrettHaleHW1 {


    public static void main(String[] args) {
        double[] empHours = {7.4,0,0,8.2,15.6,0,5.0};
        double[] salesHours = {2.5,4.2,5.2,3.1,0,0,1.1};
        Employee emp = new Employee("Tony","Tony@TonysPizza.com", 25.50, 77777, empHours);
        Salesperson sales = new Salesperson("Mario","Mario@TonysPizza.com", 15.25, 77778, salesHours, 0.2);
        System.out.println(emp.toString());
        System.out.println(sales.toString());
        System.out.print("Tony's Hours: ");
        for (int i = 0; i < emp.getTimeSheet().length; i++) {
            System.out.print(emp.getTimeSheet()[i]+", ");
        }
        System.out.println("");
        System.out.print("Mario's Hours: ");
        for (int i = 0; i < sales.getTimeSheet().length; i++) {
            System.out.print(sales.getTimeSheet()[i]+", ");
        }
        System.out.println("");
        sales.setEmail("Mario@MariosPizza.com");
        sales.setCommissionRate(0.25);
        System.out.println(sales.toString());
        System.out.println("Tony's Pay: "+emp.calculatePay());
        System.out.println("Mario's Pay: "+sales.calculatePay(7777));
    }
    
}

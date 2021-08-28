/*
 * Course: CSC 191
 * Project: Lab2
 * Date: 8/25/21
 * Author: Jarrett P Hale
 * Purpose: 
 */
package jarretthalelab2;
import java.util.Scanner;

public class JarrettHaleLab2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double userXPos;
        double userYPos;
        double userHeight = 0;
        double userWidth = 0;
        Rectangle2D r1 = new Rectangle2D();
        System.out.print("Enter X coordinate for center: ");
        userXPos = scanner.nextInt();
        System.out.print("Enter Y coordinate for center: ");
        userYPos = scanner.nextInt();
        do{
            System.out.print("Enter Rectangle Height(Must be greater than 0): ");
            userHeight = scanner.nextInt();
        }while(userHeight < 1);
        do{
            System.out.print("Enter Rectangle Width(Must be greater than 0): ");
            userWidth = scanner.nextInt();
        }while(userWidth < 1);
        
        Rectangle2D r2 = new Rectangle2D(userXPos, userYPos, userHeight, userWidth);
        System.out.println("The area of r1 is: " + r1.getArea());
        System.out.println("The perimeter of r1 is: " + r1.getPerimeter());
        System.out.println("The area of r2 is: " + r2.getArea());
        System.out.println("The perimeter of r2 is: " + r2.getPerimeter());
        System.out.println("r1 contains r2: " + r1.contains(r2));
        
        double userXToTest;
        double userYToTest;
        System.out.print("Enter X coordinate to test: ");
        userXToTest = scanner.nextInt();
        System.out.print("Enter Y coordinate to test: ");
        userYToTest = scanner.nextInt();
        if(r1.contains(userXPos, userYPos)){
            System.out.println("The point is found in r1!");
        }
        else{
            System.out.println("The point is not found in r1.");
        }
        if(r2.contains(userXPos, userYPos)){
            System.out.println("The point is found in r2!");
        }
        else{
            System.out.println("The point is not found in r2.");
        }
        
        System.out.println(r1.toString());
        System.out.println(r2.toString());
        
        System.out.print("Enter new X coordinate for center: ");
        userXPos = scanner.nextInt();
        System.out.print("Enter new Y coordinate for center: ");
        userYPos = scanner.nextInt();
        do{
            System.out.print("Enter new Rectangle Height(Must be greater than 0): ");
            userHeight = scanner.nextInt();
        }while(userHeight < 1);
        do{
            System.out.print("Enter new Rectangle Width(Must be greater than 0): ");
            userWidth = scanner.nextInt();
        }while(userWidth < 1);
        r1 = new Rectangle2D(userXPos, userYPos, userHeight, userWidth);

        System.out.println("The area of r1 is: " + r1.getArea());
        System.out.println("The perimeter of r1 is: " + r1.getPerimeter());
        System.out.println("The area of r2 is: " + r2.getArea());
        System.out.println("The perimeter of r2 is: " + r2.getPerimeter());
        System.out.println("r1 contains r2: " + r1.contains(r2));
        
        System.out.print("Enter X coordinate to test: ");
        userXToTest = scanner.nextInt();
        System.out.print("Enter Y coordinate to test: ");
        userYToTest = scanner.nextInt();
        if(r1.contains(userXPos, userYPos)){
            System.out.println("The point is found in r1!");
        }
        else{
            System.out.println("The point is not found in r1.");
        }
        if(r2.contains(userXPos, userYPos)){
            System.out.println("The point is found in r2!");
        }
        else{
            System.out.println("The point is not found in r2.");
        }
    }
    
}


class Rectangle2D{
    private double centerX;
    private double centerY;
    private double height;
    private double width;
    
    public Rectangle2D(double centerX, double centerY, double height, double width){
        this.centerX = centerX;
        this.centerY = centerY;
        this.height = height;
        this.width = width;
    }
    
    public Rectangle2D(){
        this.centerX = 0;
        this.centerY = 0;
        this.height = 1;
        this.width = 1;
    }
    
    public double getCenterX(){
        return this.centerX;
    }
    
    public void setCenterX(double centerX){
        this.centerX = centerX;
    }
    
    public double getCenterY(){
        return this.centerY;
    }
    
    public void setCenterY(double centerY){
        this.centerY = centerY;
    }
    
    public double getHeight(){
        return this.height;
    }
    
    public void setHeight(double height){
        this.height = height;
    }
    
    public double getWidth(){
        return this.width;
    }
    
    public void setWidth(double width){
        this.width = width;
    }
    
    @Override
    public String toString(){
        String information = "The center of the rectangle is at ("+this.centerX+
                " , "+this.centerY+") with a height of "+this.height+" and a"+
                " width of "+this.width;
        return information;
    }
    
    public double getArea(){
        return width * height;
    }
    
    public double getPerimeter(){
        return (2 * width) + (2 * height);
    }
    
    public boolean contains(double x, double y){
        if(x < (this.centerX - width) || x > (this.centerX + width)){
            if(y < (this.centerY - height) || y > (this.centerY + height)){
                return false;
            }
        }
        return true;
    }
    
    public boolean contains(Rectangle2D rectangle){
        //i thought i would need the lengths of the sides, but I'm not sure now.
        double thisRectangleTopAndBottom = (this.centerX - width)+(this.centerX + width);
        double thisRectangleLeftAndRight = (this.centerY - height)+(this.centerY + height);
        
        double passedRectangleTopAndBottom = (rectangle.getCenterX() - rectangle.getWidth())+(rectangle.getCenterX() + rectangle.getWidth());
        double passedRectangleLeftAndRight = (rectangle.getCenterY() - rectangle.getHeight())+(rectangle.getCenterY() + rectangle.getHeight());
        
        double thisRectangleLeftX = this.centerX - this.width;
        double thisRectangleRightX = this.centerX + this.width;
        double thisRectangleTopY = this.centerY + this.height;
        double thisRectangleBottomY = this.centerY - this.height;
        
        double passedRectangleLeftX = rectangle.getCenterX() - rectangle.getWidth();
        double passedRectangleRightX = rectangle.getCenterX() + rectangle.getWidth();
        double passedRectangleTopY = rectangle.getCenterY() + rectangle.getHeight();
        double passedRectangleBottomY = rectangle.getCenterY() - rectangle.getHeight();
        
        // center point is inside original Rectangle
        if(rectangle.getCenterX() < thisRectangleRightX && rectangle.getCenterX() > thisRectangleLeftX)
            if(rectangle.getCenterY() < thisRectangleTopY && rectangle.getCenterY() > thisRectangleBottomY)
                return true;
        
        // checks for edges on Y for top of passed rectangle
        if(passedRectangleTopY < thisRectangleTopY && passedRectangleTopY > thisRectangleBottomY){
            //ensures is inside on X axis for both if checks
            if(passedRectangleLeftX > thisRectangleLeftX && passedRectangleLeftX < thisRectangleRightX)
                return true;
            if(passedRectangleRightX < thisRectangleRightX && passedRectangleRightX > thisRectangleLeftX)
                return true;
        }
        
        // checks for edges on y for bottom of passed rectangle
        if(passedRectangleBottomY > thisRectangleBottomY && passedRectangleBottomY < thisRectangleTopY){
            //ensures is inside on X axis for both if checks
            if(passedRectangleLeftX > thisRectangleLeftX && passedRectangleLeftX < thisRectangleRightX)
                return true;
            if(passedRectangleRightX < thisRectangleRightX && passedRectangleRightX > thisRectangleLeftX)
                return true;
        }
        
        // checks for edges on left x for passed rectangle
        if(passedRectangleLeftX > thisRectangleLeftX && passedRectangleLeftX < thisRectangleRightX){
            //checks to see if y value is valid for collision
            if(passedRectangleTopY < thisRectangleTopY && passedRectangleTopY > thisRectangleBottomY)
                return true;
            if(passedRectangleBottomY > thisRectangleBottomY && passedRectangleBottomY < thisRectangleTopY)
                return true;
        }
        
        //checks for edges on right x for passed rectangle
        if(passedRectangleRightX < thisRectangleRightX && passedRectangleRightX > thisRectangleLeftX){
            //checks to see if y value is valid for collision
            if(passedRectangleTopY < thisRectangleTopY && passedRectangleTopY > thisRectangleBottomY)
                return true;
            if(passedRectangleBottomY > thisRectangleBottomY && passedRectangleBottomY < thisRectangleTopY)
                return true;
        }
        return false;  
    }
    
   
}
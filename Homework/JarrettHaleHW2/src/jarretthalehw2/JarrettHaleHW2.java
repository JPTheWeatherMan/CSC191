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
        Theater currentShow = Theater.promptForNewTheater();
        currentShow.mainMenuHandler();
    }
    
}

//Theater Class
class Theater{ 
     
    private double[][] seats; 
    private String eventName; 
     
    public Theater(int n, int m, String e){ 
        seats = new double[n][m]; 
        eventName = e; 
    }
    
  
    //another util function for handling the main menu once a theater has been made.
    public void mainMenuHandler(){
        Scanner scanner = new Scanner(System.in);
        
        //just a variable to change the value of to break a while loop
        int quitCode = 1;
        // keeps track of users choice
        int choice;
        do{
            System.out.println("");
            System.out.println("");
            System.out.println("1.) Set price for seats");
            System.out.println("2.) Check if seats are available");
            System.out.println("3.) Find seats by price");
            System.out.println("4.) Purchase seats");
            System.out.println("5.) Show entire seating chart");
            System.out.println("6.) Exit");
            System.out.print("What would you like to do? ");
            choice = scanner.nextInt();
            switch(choice){
                // set price for seats
                case 1:
                    double price;
                    for (int row = 0; row < this.seats.length; row++) {
                        do{
                            System.out.print("Enter the price for row " + row + ": ");
                            price = scanner.nextDouble();
                            if(price < 0)
                                System.out.println("Price must be above 0.0");
                        }while(price < 0.0);
                        setPriceForRowOfSeats(row, price);
                    }
                    choice = 0;
                    break;

                //check if seats are avaliable
                case 2:
                    int[]rowAndSeatNumber = gatherRowAndSeatNumberFromUser();
                    int row = rowAndSeatNumber[0];
                    int seat = rowAndSeatNumber[1];
                    
                    if(checkAvailability(row, seat))
                        System.out.println("Seat is available");
                    else
                        System.out.println("Seat is not available");
                    break;
                    
                //find seats by price
                case 3:
                    do{
                        System.out.print("Enter price you want to search for: ");
                        price = scanner.nextDouble();
                        if(price < 0)
                            System.out.println("price must be above 0");
                    }while(price < 0);
                    boolean[] rowsThatMatchPrice = rowsThatMatchPrice(price);
                    System.out.println("");
                    System.out.println("These rows were found to match your price: ");
                    for (int i = 0; i < rowsThatMatchPrice.length; i++) {
                        if(rowsThatMatchPrice[i] == true){
                            System.out.print(i + ", ");
                        }
                    }
                    System.out.println("");
                    break;
                    
                //purchase seats    
                case 4:
                    int amountToPurchase;
                    int rowToPurchaseIn; 
                    int startingIndex;
                    int endingIndex;
                    
                    do{
                        System.out.print("How many seats would you like to purchase? ");
                        amountToPurchase = scanner.nextInt();
                        if(amountToPurchase < 0 || amountToPurchase > 6)
                            System.out.println("Must be between 1 and 6");
                    }while(amountToPurchase < 0 || amountToPurchase > 6);
                    
                    do{
                        System.out.print("Which row would you like to purchase in? ");
                        rowToPurchaseIn = scanner.nextInt();
                        if(rowToPurchaseIn < -1 || rowToPurchaseIn > this.seats[0].length -1)
                            System.out.println("Must be between 0 and " + (this.seats[0].length -1));
                    }while(amountToPurchase < -1 || amountToPurchase > this.seats[0].length -1);
                    
                    printRowOfSeats(rowToPurchaseIn);
                    
                    do{
                        System.out.println("Where would you like your seats to start? ");
                        startingIndex = scanner.nextInt();
                        System.out.println("Where would you like your seats to end? ");
                        endingIndex = scanner.nextInt();
                        for(int i = startingIndex; i <= endingIndex; i++){
                            if(!checkAvailability(rowToPurchaseIn, i)){
                                System.out.println("One of the seats you selected is unavailable. Choose again");
                                //just some stupid numbers to break the if
                                startingIndex = 100;
                                endingIndex = 99;
                                break;
                            }
                        }
                        if(startingIndex < 0 || startingIndex > this.seats[0].length - amountToPurchase || endingIndex < startingIndex){
                            System.out.println("Invalid entries. Either out of bounds or ending index is higher than starting index.");
                        }
                        
                        //if i were refacotring this i wouldn't even deal with an ending index tbh but i'm in too deep
                        // doesnt even check if they got the right amount lol but IM IN TOO DEEP
                        if(endingIndex - startingIndex > 6){
                            System.out.println("You selected too many seats");
                            //those dumb numbers again to break the loop
                            startingIndex = 100;
                            endingIndex = 99;
                        }
                        
                    }while(startingIndex < 0 || startingIndex > this.seats[0].length - amountToPurchase || endingIndex < startingIndex);
                    
                    purchaseSeats(rowToPurchaseIn, startingIndex, endingIndex);
                    break;
                    
                //print all seats
                case 5:
                    printSeats();
                    choice = 0;
                    break;
                    
                // LET ME OUT!
                case 6:
                    System.out.println("See ya!");
                    quitCode = 0;
                    break;
            }
        }while(quitCode != 0 || choice > 6 || choice < 1);
    }
    
    //Sets all the prices of a param row to a param price
    public void setPriceForRowOfSeats(int rowIndex, double price){
        for (int i = 0; i < this.seats[rowIndex].length; i++) {
            this.seats[rowIndex][i] = price;
        }
    }
    
    public boolean checkAvailability(int row, int seat){
        if(this.seats[row][seat] != -1 && this.seats[row][seat] != 0)
            return true;
        return false;
    }
    
    // finds seat by price
    public boolean[] rowsThatMatchPrice(double price){
        boolean[] rowPriceMatch = new boolean [this.seats.length - 1];
        for (int i = 0; i < this.seats.length; i++) {
            if(this.seats[i][0] == price)
                rowPriceMatch[i] = true;
        }
        return rowPriceMatch;
    }
    
    public void purchaseSeats(int row, int seatStart, int seatEnd){
        //these if blocks handle checking if the seats fall on the edge of a row 
        if(seatStart == 1){
            this.seats[row][seatStart - 1] = -1;
            this.seats[row][seatEnd + 1] = -1;
            this.seats[row][seatEnd + 2] = -1;
            for(int i = seatStart; i <= seatEnd; i++){
                this.seats[row][i] = 0;
            }
        }
        else if(seatStart == 2){
            this.seats[row][seatStart - 1] = -1;
            this.seats[row][seatStart - 2] = -1;
            this.seats[row][seatEnd + 1] = -1;
            this.seats[row][seatEnd + 2] = -1;
            for(int i = seatStart; i <= seatEnd; i++){
                this.seats[row][i] = 0;
            }
        }
        else if(seatEnd == this.seats[0].length - 2){
            this.seats[row][seatEnd + 1] = -1;
            this.seats[row][seatStart - 1] = -1;
            this.seats[row][seatStart - 2] = -1;
            for(int i = seatStart; i <= seatEnd; i++){
                this.seats[row][i] = 0;
            }
        }
        else if(seatEnd == this.seats[0].length - 2){
            this.seats[row][seatEnd + 1] = -1;
            this.seats[row][seatEnd + 2] = -1;
            this.seats[row][seatStart - 1] = -1;
            this.seats[row][seatStart - 2] = -1;
            for(int i = seatStart; i <= seatEnd; i++){
                this.seats[row][i] = 0;
            }
        }
        else{
            for(int i = seatStart; i <= seatEnd; i++){
                this.seats[row][i] = 0;
            }  
        }
    }
    
    public void printSeats(){
        System.out.println("");
        //places name halfway down line
        for (int i = 0; i < this.seats[0].length / 2; i++) {
            System.out.print(" ");
        }
        System.out.println(this.eventName);
        for (int i = 0; i < this.seats.length; i++) {
            for (int j = 0; j < this.seats[i].length; j++) {
                if(this.seats[i][j] == 0){
                    System.out.print("X ");
                }
                else if(this.seats[i][j] == -1){
                    System.out.print("U ");
                }
                else{
                    System.out.print("O ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    //util for printing a single row
    public void printRowOfSeats(int row){
        for (int i = 0; i < this.seats[row].length; i++) {
            if(this.seats[row][i] == 0){
                System.out.print("X ");
            }
            else if(this.seats[row][i] == -1){
                System.out.print("U ");
            }
            else{
                System.out.print("O ");
            }
        }
        System.out.println("");
        for (int i = 0; i < this.seats[row].length; i++) {
            System.out.print(i + " ");
        }
    }
    
    //util function to gather a row and seat number from the user. I accidentally overbuilt this one lol.
    public int[] gatherRowAndSeatNumberFromUser(){  
        Scanner scanner = new Scanner(System.in);
        int row;
        int seat;
        do{
            System.out.print("Which row?(0 Based)");
            row = scanner.nextInt();
            System.out.print("Which seat in row " + row + "?(0 Based) ");
            seat = scanner.nextInt();
            if(row < 0 || row > seats.length || seat < 0 || seat > seats[0].length){
                System.out.println("Row must be 0 to " + (seats.length-1) + "and seat must be 0 to " + (seats[0].length-1));
            }
        }while(row < 0 || row > seats.length-1 || seat < 0 || seat > seats[0].length-1);
        int[] rowAndSeatNumber = {row, seat};
        return rowAndSeatNumber;
    }
        
    //Util function to get information for a show, returns instatiated theater
    public static Theater promptForNewTheater(){
        Scanner scanner = new Scanner(System.in);
        int numRows = -1;
        int numSeats = -1;
        String eventName;
        do{
            System.out.print("Enter the name of the Event: ");
            eventName = scanner.nextLine();
            System.out.print("Enter the rows of seats: ");
            numRows = scanner.nextInt();
            System.out.print("Enter the amount of seats in each row: ");
            numSeats = scanner.nextInt();
        }while(numRows < 0 || numSeats < 0);
        return new Theater(numRows, numSeats, eventName);
    }
}



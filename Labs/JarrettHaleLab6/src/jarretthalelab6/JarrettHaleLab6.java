/*
 * Course: CSC 191
 * Project: Lab 5
 * Date: 9/22/21
 * Author: Jarrett Hale
 * Purpose: This program reads in a text file and generates a cipher based on 
            a key.
 */
package jarretthalelab6;

import java.io.*;
import java.util.Scanner;

public class JarrettHaleLab6 {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        MyCipher cipher = new MyCipher();
        
        //initial key is set to 0
        int key = 0;
        
        //collects key from user
        do{
            System.out.println("What is the key for your cipher?");
            key = scanner.nextInt();
            if(key < 0 || key > 27){
                System.out.println("Must be greater than 1 and less than 27");
            }
            cipher.setKey(key);
        }while(key < 0 || key > 27);
        
        //default file path on my machine
        cipher.encode("c:\\javafiles\\attack.txt");
    }
    
}

class MyCipher{
    private int key = 0;
    
    MyCipher(){
        this.key = 0;
    }
    
    public int getKey(){
        return this.key;
    }
    
    public void setKey(int key){
        this.key = key;
    }
    
    //string is file path to encode
    public void encode(String s){
        try{
            File textInput = new File(s);
            if(!textInput.exists()){
                System.out.println("File does not exist");
                System.exit(0);
            }
            Scanner input = new Scanner(textInput);
            while(input.hasNextLine()){
                String statement = input.nextLine();
                String encryptedCipher = "";
                
                //loop through and for each letter generate a character for the cypher
                for(int i = 0; i < statement.length(); i++){
                    if(statement.charAt(i) == 32){
                        encryptedCipher = encryptedCipher + Character.toString(97 + key - 1);
                    }
                    else if(statement.charAt(i) + key > 122){
                        int offset = statement.charAt(i) + key - 124;
                        encryptedCipher = encryptedCipher + Character.toString(97 + offset);
                    }
                    else{
                        encryptedCipher = encryptedCipher + Character.toString(statement.charAt(i)+key);
                    }
                }
                System.out.println("Statement: "+ statement);
                System.out.println("Encrpyted: " + encryptedCipher.toString());
                System.out.println("");
                //System.out.println("word: "+ word);
            }
            input.close();
        } catch(IOException e){
            System.out.println("Problem encountered");
        }
    }
}

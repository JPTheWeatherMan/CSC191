/* Course: CSC 191
 * Project: Lab 4
 * Date: Nov 10 2021
 * Author: Jarrett P Hale
 * Purpose: 2 player tic tac toe with win count and GUI
 */
package jarretthalelab11;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

import java.awt.event.*;


public class JarrettHaleLab11 {

    public static void main(String[] args) {
        GameWindow application = new GameWindow();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}

//just abstracted lab 4 to a class
class GameState{
    
    char currentTurn;
    char[][] board;
    int playerXWinCount;
    int playerOWinCount;
    int ties;
    
    public GameState(){
        this.currentTurn = 'X';
        this.board = new char[3][3];
        this.playerXWinCount = 0;
        this.playerOWinCount = 0;
        this.ties = 0;
    }
    
    public int getWins(char player){
        switch(player){
            case 'X':
                return this.playerXWinCount;
            case 'O':
                return this.playerOWinCount;
            case 'N':
                return this.ties;
        }
        return -1;
    }
    
    public void addWin(char player){
        if (player == 'X')
            this.playerXWinCount++;
        if (player == 'O')
            this.playerOWinCount++;
        if (player == 'N')
            this.ties++;
    }
    public void resetBoard(){
        this.board = new char[3][3];
    }
    
    public char getCurrentTurn(){
        return this.currentTurn;
    }
    
    public char getValue(int row, int col){
        return this.board[row][col];
    }
    
    public void setValue(int row, int col, char value){
        this.board[row][col] = value;
    }
    
    public char[] checkWinConditionAndWinner(){
        //First value is boolean for if there is a winner, second value is the winnner
        char[] winConditionAndWinner = {'F', '_'};
        //Checks each row for a winner
        for(int row = 0; row < this.board.length; row++){
            if(this.board[row][0] == 'X' && this.board[row][1] == 'X' && this.board[row][2] == 'X'){
                winConditionAndWinner[0] = 'T';
                winConditionAndWinner[1] = 'X';
            }
            if(this.board[row][0] == 'O' && this.board[row][1] == 'O' && this.board[row][2] == 'O'){
                winConditionAndWinner[0] = 'T';
                winConditionAndWinner[1] = 'O';
            }
        }
        
        //checks each column for a winner
        for (int col = 0; col < this.board[0].length; col++) {
            if(this.board[0][col] == 'X' && this.board[1][col] == 'X' && this.board[2][col] == 'X'){
                winConditionAndWinner[0] = 'T';
                winConditionAndWinner[1] = 'X';
            }
            if(this.board[0][col] == 'O' && board[1][col] == 'O' && this.board[2][col] == 'O'){
                winConditionAndWinner[0] = 'T';
                winConditionAndWinner[1] = 'O';
            }
        }
      
        //Checks Diagnal win conditions
        if(this.board[1][1] == 'X'){
            if(this.board[0][0] == 'X' && this.board[2][2] == 'X'){
                winConditionAndWinner[0] = 'T';
                winConditionAndWinner[1] = 'X'; 
            }
            if(this.board[2][0] == 'X' && this.board[0][2] == 'X'){
                winConditionAndWinner[0] = 'T';
                winConditionAndWinner[1] = 'X';  
            }
        }
        if(this.board[1][1] == 'O'){
            if(this.board[0][0] == 'O' && this.board[2][2] == 'O'){
                winConditionAndWinner[0] = 'T';
                winConditionAndWinner[1] = 'O';  
            }
            if(this.board[2][0] == 'O' && this.board[0][2] == 'O'){
                winConditionAndWinner[0] = 'T';
                winConditionAndWinner[1] = 'O';  
            }
        }
        
        //full board and no winner
        String accumulator = "";
        for (int row = 0; row < this.board.length; row++) {
            for (int col = 0; col < this.board[row].length; col++) {
                //only if there are actually characters there then add them to the accumulator
                if(this.board[row][col] == 'X' || this.board[row][col] == 'O')
                    accumulator += board[row][col];
            }
        }
        if(accumulator.length() == 9){
            winConditionAndWinner[0] = 'T';
            winConditionAndWinner[1] = 'N';
        }
        return winConditionAndWinner;
    }
    
    public void setTurn(){
        if (this.currentTurn == 'X')
            this.currentTurn = 'O';
        else
            this.currentTurn = 'X';
    }
    
    public boolean thereExistsACharacter(int row, int column){
        if(this.board[row][column] == 'X' || this.board[row][column] == 'Y')
            return true;
        return false;
    }
}

class GameWindow extends JFrame{
    //state is basically lab 4, just used as a store of state rather than an application
    private GameState state;
    //containers for buttons
    private JPanel buttonPanel;
    
    //each button
    private JButton r0c0;
    private JButton r0c1;
    private JButton r0c2;
    private JButton r1c0;
    private JButton r1c1;
    private JButton r1c2;
    private JButton r2c0;
    private JButton r2c1;
    private JButton r2c2;
    
    private JLabel playerXWinsLabel;
    private JLabel playerOWinsLabel;
    private JLabel tiesLabel;
    
    private JTextField playerXWinsField;
    private JTextField playerOWinsField;
    private JTextField tiesField;
    
    
    public GameWindow(){
        state = new GameState();
        createUserInterface();
    }
    
    private void createUserInterface(){
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        
        //setup output values
        playerXWinsLabel = new JLabel();
        playerXWinsLabel.setBounds(20 ,330, 50, 21);
        playerXWinsLabel.setText("X Wins: ");
        contentPane.add(playerXWinsLabel);
        
        playerXWinsField = new JTextField();
        playerXWinsField.setBounds(71, 330, 50, 21);
        playerXWinsField.setEditable(false);
        playerXWinsField.setHorizontalAlignment(JTextField.RIGHT);
        playerXWinsField.setText("0");
        contentPane.add(playerXWinsField);
        
        playerOWinsLabel = new JLabel();
        playerOWinsLabel.setBounds(150 ,330, 50, 21);
        playerOWinsLabel.setText("O Wins: ");
        contentPane.add(playerOWinsLabel);
        
        playerOWinsField = new JTextField();
        playerOWinsField.setBounds(200, 330, 50, 21);
        playerOWinsField.setEditable(false);
        playerOWinsField.setHorizontalAlignment(JTextField.RIGHT);
        playerOWinsField.setText("0");
        contentPane.add(playerOWinsField);
        
        tiesLabel = new JLabel();
        tiesLabel.setBounds(100 ,375, 50, 21);
        tiesLabel.setText("Ties: ");
        contentPane.add(tiesLabel);
        
        tiesField = new JTextField();
        tiesField.setBounds(150, 375, 50, 21);
        tiesField.setEditable(false);
        tiesField.setHorizontalAlignment(JTextField.RIGHT);
        tiesField.setText("0");
        contentPane.add(tiesField);
        
        
        //setup button panel
        buttonPanel = new JPanel();
        buttonPanel.setBorder(new TitledBorder("Board"));
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(20,30,265,275);
        contentPane.add(buttonPanel);
        
        //setup buttons in button panel
        r0c0 = new JButton();
        r0c0.setBounds(20, 30, 75, 75);
        r0c0.setText(null);
        r0c0.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                int row = 0;
                int col = 0;
                buttonClick(row, col, r0c0);
            }
        });
        buttonPanel.add(r0c0);
        
        r0c1 = new JButton();
        r0c1.setBounds(95, 30, 75, 75);
        r0c1.setText(null);
        r0c1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                int row = 0;
                int col = 1;
                buttonClick(row, col, r0c1);
            }
        });
        buttonPanel.add(r0c1);
        
        r0c2 = new JButton();
        r0c2.setBounds(170, 30, 75, 75);
        r0c2.setText(null);
        r0c2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                int row = 0;
                int col = 2;
                buttonClick(row, col, r0c2);
            }
        });
        buttonPanel.add(r0c2);
        
        r1c0 = new JButton();
        r1c0.setBounds(20, 105, 75, 75);
        r1c0.setText(null);
        r1c0.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                int row = 1;
                int col = 0;
                buttonClick(row, col, r1c0);
            }
        });
        buttonPanel.add(r1c0);
        
        r1c1 = new JButton();
        r1c1.setBounds(95, 105, 75, 75);
        r1c1.setText(null);
        r1c1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                int row = 1;
                int col = 1;
                buttonClick(row, col, r1c1);
            }
        });
        buttonPanel.add(r1c1);
        
        r1c2 = new JButton();
        r1c2.setBounds(170, 105, 75, 75);
        r1c2.setText(null);
        r1c2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                int row = 1;
                int col = 2;
                buttonClick(row, col, r1c2);
            }
        });
        buttonPanel.add(r1c2);
        
        r2c0 = new JButton();
        r2c0.setBounds(20, 180, 75, 75);
        r2c0.setText(null);
        r2c0.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                int row = 2;
                int col = 0;
                buttonClick(row, col, r2c0);
            }
        });
        buttonPanel.add(r2c0);
        
        r2c1 = new JButton();
        r2c1.setBounds(95, 180, 75, 75);
        r2c1.setText(null);
        r2c1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                int row = 2;
                int col = 1;
                buttonClick(row, col, r2c1);
            }
        });
        buttonPanel.add(r2c1);
        
        r2c2 = new JButton();
        r2c2.setBounds(170, 180, 75, 75);
        r2c2.setText(null);
        r2c2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                int row = 2;
                int col = 2;
                buttonClick(row, col, r2c2);
            }
        });
        buttonPanel.add(r2c2);
        
        
        setTitle("Tic-Tac-Toe");
        setSize(330, 500);
        setVisible(true);
    }
    
    public void buttonClick(int row, int col, JButton button){
        //only places character if there is not already a character there
        if(!state.thereExistsACharacter(row,col)){
            state.setValue(row, col, state.getCurrentTurn());
            button.setText(Character.toString(state.getCurrentTurn()));
            setButtonValue(button, state.getCurrentTurn());
            state.setTurn();
        }
        //first value is if there is a winner, second value is who the winner is: X, O, or N for nobody
        char[] winCondition = state.checkWinConditionAndWinner();
        if(winCondition[0] == 'T'){
            state.resetBoard();
            clearButtonValues();
            char winningPlayer = winCondition[1];
            state.addWin(winningPlayer);
            switch(winningPlayer){
                case 'X':
                    int currentWins = Integer.parseInt(playerXWinsField.getText());
                    currentWins++;
                    playerXWinsField.setText(Integer.toString(currentWins));
                    break;
                case 'O':
                    currentWins = Integer.parseInt(playerOWinsField.getText());
                    currentWins++;
                    playerOWinsField.setText(Integer.toString(currentWins));
                    break;
                case 'N':
                    currentWins = Integer.parseInt(tiesField.getText());
                    currentWins++;
                    tiesField.setText(Integer.toString(currentWins));
                    break;
            }
        }
    }
    
    public void setButtonValue(JButton button, char value){
        button.setText(Character.toString(value));
    }
    
    public void clearButtonValues(){
        r0c0.setText(null);
        r0c1.setText(null);
        r0c2.setText(null);
        r1c0.setText(null);
        r1c1.setText(null);
        r1c2.setText(null);
        r2c0.setText(null);
        r2c1.setText(null);
        r2c2.setText(null);
    }
    
}
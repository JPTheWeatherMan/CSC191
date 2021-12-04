/* course: CSC 191
 * project: lab 10
 * date: Nov 3 2021
 * author: Jarrett Hale
 * purpose: This program is a GUI calculator with implementation.
 */

//WARNING: GARBAGE CODE BELOW, I know this is bad, it's been a semester lol.
package jarretthalehw6;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class JarrettHaleHW6 {


    public static void main(String[] args) {
        Calculator application = new Calculator();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
class Calculator extends JFrame{
    
    private JPanel numberJPanel;
    private JPanel operatorJPanel;
    private JPanel clearPanel;
    
    private JTextField output;
    
    //numbers
    private JButton oneButton;
    private JButton twoButton;
    private JButton threeButton;
    private JButton fourButton;
    private JButton fiveButton;
    private JButton sixButton;
    private JButton sevenButton;
    private JButton eightButton;
    private JButton nineButton;
    private JButton zeroButton;
    private JButton doubleZeroButton;
    
    //operators
    private JButton plusOperatorButton;
    private JButton minusOperatorButton;
    private JButton multiplicationOperatorButton;
    private JButton divisionOperatorButton;
    private JButton equalsOperatorButton;
    private JButton decimalOperatorButton;
    
    //clear buttons
    private JButton clearButton;
    private JButton clearEntryButton;
    
    //off button
    private JButton offButton;
    
    CalulatorState state;
    
    public Calculator(){
        createUserInterface();
        this.state = new CalulatorState();
    }
    
    private void createUserInterface(){
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        
        //set up output field
        output = new JTextField();
        output.setBounds(20, 20, 730, 20);
        output.setEditable(false);
        output.setText("0");
        output.setHorizontalAlignment(JTextField.RIGHT);
        contentPane.add(output);
        
        //off button
        offButton = new JButton();
        offButton.setBounds(625, 300, 125, 75);
        offButton.setText("OFF");
        offButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                offPressed();
            }
        });
        contentPane.add(offButton);
        
        //setup number panel
        numberJPanel = new JPanel();
        numberJPanel.setLayout(null);
        numberJPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        numberJPanel.setBounds(20,60,270,350);
        contentPane.add(numberJPanel);
        
        //numbers for number panel
        oneButton = new JButton();
        oneButton.setBounds(25, 25, 75, 75);
        oneButton.setText("1");
        oneButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                numberPressed(oneButton.getText());
            }
        });
        numberJPanel.add(oneButton);
        
        twoButton = new JButton();
        twoButton.setBounds(100, 25, 75, 75);
        twoButton.setText("2");
        twoButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                numberPressed(twoButton.getText());
            }
        });
        numberJPanel.add(twoButton);
        
        threeButton = new JButton();
        threeButton.setBounds(175, 25, 75, 75);
        threeButton.setText("3");
        threeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                numberPressed(threeButton.getText());
            }
        });
        numberJPanel.add(threeButton);
        
        fourButton = new JButton();
        fourButton.setBounds(25, 100, 75, 75);
        fourButton.setText("4");
        fourButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                numberPressed(fourButton.getText());
            }
        });
        numberJPanel.add(fourButton);
        
        fiveButton = new JButton();
        fiveButton.setBounds(100, 100, 75, 75);
        fiveButton.setText("5");
        fiveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                numberPressed(fiveButton.getText());
            }
        });
        numberJPanel.add(fiveButton);
        
        sixButton = new JButton();
        sixButton.setBounds(175, 100, 75, 75);
        sixButton.setText("6");
        sixButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                numberPressed(sixButton.getText());
            }
        });
        numberJPanel.add(sixButton);
        
        sevenButton = new JButton();
        sevenButton.setBounds(25, 175, 75, 75);
        sevenButton.setText("7");
        sevenButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                numberPressed(sevenButton.getText());
            }
        });
        numberJPanel.add(sevenButton);
        
        eightButton = new JButton();
        eightButton.setBounds(100, 175, 75, 75);
        eightButton.setText("8");
        eightButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                numberPressed(eightButton.getText());
            }
        });
        numberJPanel.add(eightButton);
        
        nineButton = new JButton();
        nineButton.setBounds(175, 175, 75, 75);
        nineButton.setText("9");
        nineButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                numberPressed(nineButton.getText());
            }
        });
        numberJPanel.add(nineButton);
        
        zeroButton = new JButton();
        zeroButton.setBounds(25, 250, 75, 75);
        zeroButton.setText("0");
        zeroButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                numberPressed(zeroButton.getText());
            }
        });
        numberJPanel.add(zeroButton);
        
        doubleZeroButton = new JButton();
        doubleZeroButton.setBounds(100, 250, 150, 75);
        doubleZeroButton.setText("00");
        doubleZeroButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                numberPressed(doubleZeroButton.getText());
            }
        });
        numberJPanel.add(doubleZeroButton);
        
        //operator panel
        operatorJPanel = new JPanel();
        operatorJPanel.setLayout(null);
        operatorJPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        operatorJPanel.setBounds(350,60,225,350);
        contentPane.add(operatorJPanel);
        
        //operator buttons
        plusOperatorButton = new JButton();
        plusOperatorButton.setBounds(25, 25, 75, 175);
        plusOperatorButton.setText("+");
        plusOperatorButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        plusOperatorButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                operatorPressed("ADD");
            }
        });
        operatorJPanel.add(plusOperatorButton);
        
        decimalOperatorButton = new JButton();
        decimalOperatorButton.setBounds(25, 250, 75, 75);
        decimalOperatorButton.setText(".");
        decimalOperatorButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        operatorJPanel.add(decimalOperatorButton);
        
        minusOperatorButton = new JButton();
        minusOperatorButton.setBounds(125, 25, 75, 75);
        minusOperatorButton.setText("-");
        minusOperatorButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        minusOperatorButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                operatorPressed("SUBTRACT");
            }
        });
        operatorJPanel.add(minusOperatorButton);
        
        multiplicationOperatorButton = new JButton();
        multiplicationOperatorButton.setBounds(125, 100, 75, 75);
        multiplicationOperatorButton.setText("*");
        multiplicationOperatorButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        multiplicationOperatorButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                operatorPressed("MULTIPLY");
            }
        });
        operatorJPanel.add(multiplicationOperatorButton);
        
        divisionOperatorButton = new JButton();
        divisionOperatorButton.setBounds(125, 175, 75, 75);
        divisionOperatorButton.setText("/");
        divisionOperatorButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        divisionOperatorButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                operatorPressed("DIVISION");
            }
        });
        operatorJPanel.add(divisionOperatorButton);
        
        equalsOperatorButton = new JButton();
        equalsOperatorButton.setBounds(125, 250, 75, 75);
        equalsOperatorButton.setText("=");
        equalsOperatorButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        equalsOperatorButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                operatorPressed("EQUALS");
            }
        });
        operatorJPanel.add(equalsOperatorButton);
        
        //clear panel
        clearPanel = new JPanel();
        clearPanel.setLayout(null);
        clearPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        clearPanel.setBounds(625,60,125,225);
        contentPane.add(clearPanel);
        
        //clear panel buttons
        clearButton = new JButton();
        clearButton.setBounds(25, 25, 75, 75);
        clearButton.setText("C");
        clearButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                ccePressed();
            }
        });
        clearPanel.add(clearButton);
        
        clearEntryButton = new JButton();
        clearEntryButton.setBounds(25, 125, 75, 75);
        clearEntryButton.setText("C/E");
        clearEntryButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                ccePressed();
            }
        });
        clearPanel.add(clearEntryButton);
        
        
        //properties for the window
        setTitle("Calculator");
        setSize(800, 500);
        setVisible(true);
    }
    
    //sets state of operators depending on which one is zeroed out, i know awful solution
    //generates number input with string concats and converts to double for state storage
    private void numberPressed(String numberValue){
        if(Double.parseDouble(this.output.getText()) == 0.0)
            this.output.setText("");
        String currentValue = output.getText();
        String nextNum = currentValue + numberValue;
        if(this.state.getOutputValue() == 0.0)
            this.state.outputValue = Double.parseDouble(nextNum);
        if(this.state.getCurrentDecimalValue() == 0.0)
            this.state.currentDecimalValue = Double.parseDouble(nextNum);
        System.out.println(this.state.getOutputValue());
        this.output.setText(nextNum);   
    }
    
    private void operatorPressed(String operation){
        //would definitely use an enum for this in the future
        //sets operation if input pressed is not equals
        //if equals performs operation with state
        if(operation.equals("ADD")){
            this.state.setOperation("ADD");
            this.state.setCurrentDecimalValue(this.state.getOutputValue());
            this.output.setText("0");
        }
        if(operation.equals("SUBTRACT")){
            this.state.setOperation("SUBTRACT");
        }
        if(operation.equals("MULTIPLY")){
            this.state.setOperation("MULTIPLY");
        }
        if(operation.equals("DIVIDE")){
            this.state.setOperation("DIVIDE");
        }
        if(operation.equals("EQUALS")){
            if(this.state.getOperation().equals("ADD")){
                this.state.setResult(this.state.getCurrentDecimalValue() + this.state.getOutputValue());
            }
            if(this.state.getOperation().equals("SUBTRACT")){
                this.state.setResult(this.state.getCurrentDecimalValue() - this.state.getOutputValue());
            }
            if(this.state.getOperation().equals("MULTIPLY")){
                this.state.setResult(this.state.getCurrentDecimalValue() * this.state.getOutputValue());
            }
            if(this.state.getOperation().equals("DIVIDE")){
                this.state.setResult(this.state.getCurrentDecimalValue() / this.state.getOutputValue());
            }
            this.output.setText(Double.toString(this.state.getResult()));
        }
    }
    
    private void offPressed(){
        System.exit(0);
    }
    
    private void ccePressed(){
        this.output.setText("0");
        this.state.setCurrentDecimalValue(0.0);
        this.state.setOutputValue(0.0);
        this.state.setResult(0.0);
    }
}

//basic state, just holds information so it's not so hard to keep up with
class CalulatorState{
    double currentDecimalValue;
    double outputValue;
    double result;
    String operation;
    
    public  CalulatorState(){
        this.currentDecimalValue = 0.0;
        this.outputValue = 0.0;
        this.result = 0.0;
        this.operation = "ADD";
    }
    
    public String toString(int x){
        return Double.toString(x);
    }
    
    public double toDouble(String x){
        return Double.parseDouble(x);
    }
    
    public double getCurrentDecimalValue(){
        return this.currentDecimalValue;
    }
    
    public double getOutputValue(){
        return this.outputValue;
    }
    
    public double getResult(){
        return this.result;
    }
    
    public void setResult(double newValue){
        this.result = newValue;
    }
    
    public void setCurrentDecimalValue(double newValue){
        this.currentDecimalValue = newValue;
    }
    
    public void setOutputValue(double newValue){
        this.outputValue = newValue;
    }
    
    public void setOperation(String newOperation){
        this.operation = newOperation;
    }
    
    public String getOperation(){
        return this.operation;
    }
}

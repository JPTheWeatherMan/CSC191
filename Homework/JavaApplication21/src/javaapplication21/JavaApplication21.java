
package javaapplication21;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class JavaApplication21 {


    public static void main(String[] args) {
        GUI application = new GUI();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}

class GUI extends JFrame{
    private JPanel buttonPanel;
    private JButton button;
    
    public GUI(){
        createUserInterface();
    }
    
    private void createUserInterface(){
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBorder(new TitledBorder("Button Panel"));
        buttonPanel.setBounds(50,50,125,125);
        contentPane.add(buttonPanel);
        
        button = new JButton();
        button.setBounds(75, 75, 75, 25);
        button.setText("Button");
        contentPane.add(button);
        
        setTitle("GUI");
        setSize(300, 300);
        setVisible(true);
    }
}

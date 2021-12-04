/*
 * course: CSC 191
 * project: lab 12
 * date:  November 17th, 2021
 * author: Jarrett Hale
 * Purpose: GUI application for collecting a list of movies. Data structure for the
            list shows understanding of linked lists and their advantages for resizeability
 */
package jarretthalelab12;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class JarrettHaleLab12 {

    public static void main(String[] args) {
        MovieFrontEnd frontEnd = new MovieFrontEnd();
    }
    
}

class MovieFrontEnd extends JFrame{
    
    LinkedList<Movie> state = new LinkedList<Movie>();
    
    private JPanel inputMoviePanel;
    private JLabel outputMovieLabel;
    
    private JLabel movieNameLabel;
    private JLabel movieMediaLabel;
    private JLabel movieReleaseYearLabel;
    
    private JTextField movieNameTextField;
    private JTextField movieMediaTextField;
    private JTextField movieReleaseYearTextField;
    
    private JButton addMovieButton;
    private JButton showMoviesButton;
    
    private JTextArea movieOutputTextArea;
    
    public MovieFrontEnd(){
        createUserInterface();
    }
    
    private void createUserInterface(){
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        
        inputMoviePanel = new JPanel();
        inputMoviePanel.setLayout(null);
        inputMoviePanel.setBorder(new TitledBorder("Input Movie"));
        inputMoviePanel.setBounds(20, 20, 250, 250);
        contentPane.add(inputMoviePanel);
        
        movieNameLabel = new JLabel();
        movieNameLabel.setBounds(20, 30, 80, 20);
        movieNameLabel.setText("Movie Name:");
        inputMoviePanel.add(movieNameLabel);
        
        movieNameTextField = new JTextField();
        movieNameTextField.setBounds(115, 32, 100, 20);
        inputMoviePanel.add(movieNameTextField);
  
        movieMediaLabel = new JLabel();
        movieMediaLabel.setBounds(20, 75, 80, 20);
        movieMediaLabel.setText("Media:");
        inputMoviePanel.add(movieMediaLabel);
        
        movieMediaTextField = new JTextField();
        movieMediaTextField.setBounds(115, 80, 100, 20);
        inputMoviePanel.add(movieMediaTextField);
        
        movieReleaseYearLabel = new JLabel();
        movieReleaseYearLabel.setBounds(20, 120, 80, 20);
        movieReleaseYearLabel.setText("Movie Media:");
        inputMoviePanel.add(movieReleaseYearLabel);
        
        movieReleaseYearTextField = new JTextField();
        movieReleaseYearTextField.setBounds(115, 122, 100, 20);
        inputMoviePanel.add(movieReleaseYearTextField);
        
        addMovieButton = new JButton();
        addMovieButton.setBounds(60,200,120, 30);
        addMovieButton.setText("Add Movie");
        addMovieButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                addMovieButtonActionPerformed(event);
            }
        });
        inputMoviePanel.add(addMovieButton);
        
        outputMovieLabel = new JLabel();
        outputMovieLabel.setText("Movies:");
        outputMovieLabel.setBounds(290, 20, 80, 20);
        contentPane.add(outputMovieLabel);
        
        movieOutputTextArea = new JTextArea();
        movieOutputTextArea.setBounds(290, 50, 325, 250);
        movieOutputTextArea.setEditable(false);
        movieOutputTextArea.setText("Year\tMedia\tTitle");
        contentPane.add(movieOutputTextArea);
        
        showMoviesButton = new JButton();
        showMoviesButton.setBounds(300, 300, 120, 30);
        showMoviesButton.setText("Show Movies");
        showMoviesButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                showMoviesButtonActionPerformed(event);
            }
        });
        contentPane.add(showMoviesButton);
        
        setTitle("Movies");
        setSize(800, 450);
        setVisible(true);
    }
    
    private void addMovieButtonActionPerformed(ActionEvent event){
        String currentMovieNameInput = movieNameTextField.getText();
        movieNameTextField.setText("");
        
        String currentMovieMediaInput = movieMediaTextField.getText();
        movieMediaTextField.setText("");
        
        int movieReleaseYearInput;
        if(movieReleaseYearTextField.getText().length() > 4)
            movieReleaseYearInput = -1;
        else
            movieReleaseYearInput = Integer.parseInt(movieReleaseYearTextField.getText());
        movieReleaseYearTextField.setText("");
        
        Movie currentMovie = new Movie(currentMovieNameInput, currentMovieMediaInput, movieReleaseYearInput);
        state.add(currentMovie);
    }
    
    private void showMoviesButtonActionPerformed(ActionEvent event){
        movieOutputTextArea.setText("Year\tMedia\tTitle");
        for(Movie currentMovie : state){
            movieOutputTextArea.append("\n"+currentMovie.year + "\t" + currentMovie.media+"\t"+currentMovie.name);
        }
    }
}

class Movie{
    String name;
    String media;
    int year;
    
    public Movie(String n, String m, int y){
        name = n;
        media = m;
        year = y;
    }
}
/*
 * IST 240 - Deliverable 1
 * Group 5 Members: Theodore Nguyen, Riyank Goel, Nicholas Marzullo
 * Professor Choman
 */
package group5.pkgfinal.project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * Berks Game
 */
public class Options extends JPanel {

    //Main menu
    ImageIcon sourceImage1 = new ImageIcon("images/GameOver.jpg");
    Image menuImage = sourceImage1.getImage();

    //player and theme images
    ImageIcon sourceLionImage = new ImageIcon("images/Nittany_Lion.jpg");
    ImageIcon sourceFootballImage = new ImageIcon("images/football.jpg");
    ImageIcon sourceStudentImage = new ImageIcon("images/student.jpg");
    ImageIcon sourceSportsImage = new ImageIcon("images/sports.jpg");
    ImageIcon sourceMathImage = new ImageIcon("images/math.jpg");
    ImageIcon sourceJavaImage = new ImageIcon("images/java.jpg");

    //Player Selection Buttons
    JButton lionButton,
            footballButton,
            studentButton;

    //Theme Selection Buttons
    JButton sportsButton,
            mathButton,
            javaButton;

    //back to the menu button
    JButton backButton;

    //Labels for titles,players, and themes
    JLabel playerTitle,
            themeTitle,
            lionText,
            descriptionText,
            footballText,
            studentText,
            sportsText,
            mathText,
            javaText;

    String sports,
            math,
            java;

    String currentTheme;

    //constructor
    public Options() {
        super();
        InitialSetUpForOptions();

    }

    public void InitialSetUpForOptions() {
        setBackground(Color.white);
        setLayout(null);

        //Back to the intro panel button
        backButton = new JButton("click here to go back to the Menu Screen");
        add(backButton);
        backButton.setBounds(new Rectangle(875, 600, 300, 30));

        //Title texts
        playerTitle = new JLabel("Choose a Character");
        playerTitle.setFont(new Font("Century Gothic", Font.BOLD, 24));
        playerTitle.setForeground(Color.white);
        add(playerTitle);
        playerTitle.setBounds(new Rectangle(325, 0, 300, 60));
        themeTitle = new JLabel("Choose a Theme");
        themeTitle.setFont(new Font("Century Gothic", Font.BOLD, 24));
        themeTitle.setForeground(Color.white);
        add(themeTitle);
        themeTitle.setBounds(new Rectangle(825, 0, 300, 60));
        descriptionText = new JLabel("(Click an icon to select a character and a theme)");
        descriptionText.setFont(new Font("Century Gothic", Font.BOLD, 12));
        descriptionText.setForeground(Color.white);
        add(descriptionText);
        descriptionText.setBounds(new Rectangle(10, 615, 300, 60));

        //player icon options
        lionText = new JLabel("Nittany Lion");
        lionText.setFont(new Font("Century Gothic", Font.BOLD, 42));
        lionText.setForeground(Color.orange);
        add(lionText);
        lionText.setBounds(new Rectangle(325, 125, 300, 60));
        footballText = new JLabel("Saquon Barkley");
        footballText.setFont(new Font("Century Gothic", Font.BOLD, 36));
        footballText.setForeground(Color.orange);
        add(footballText);
        footballText.setBounds(new Rectangle(305, 325, 300, 60));
        studentText = new JLabel("Student");
        studentText.setFont(new Font("Century Gothic", Font.BOLD, 42));
        studentText.setForeground(Color.blue);
        add(studentText);
        studentText.setBounds(new Rectangle(360, 515, 300, 60));

        //theme options
        sportsText = new JLabel("Sports");
        sportsText.setFont(new Font("Century Gothic", Font.BOLD, 42));
        sportsText.setForeground(Color.orange);
        add(sportsText);
        sportsText.setBounds(new Rectangle(855, 125, 300, 60));
        mathText = new JLabel("Math");
        mathText.setFont(new Font("Century Gothic", Font.BOLD, 42));
        mathText.setForeground(Color.blue);//default
        add(mathText);
        mathText.setBounds(new Rectangle(865, 325, 300, 60));
        javaText = new JLabel("Java");
        javaText.setFont(new Font("Century Gothic", Font.BOLD, 42));
        javaText.setForeground(Color.orange);
        add(javaText);
        javaText.setBounds(new Rectangle(860, 515, 300, 60));

        //Character Buttons
        lionButton = new JButton(sourceLionImage);
        add(lionButton);
        lionButton.setBounds(new Rectangle(200, 80, 80, 150));
        footballButton = new JButton(sourceFootballImage);
        add(footballButton);
        footballButton.setBounds(new Rectangle(200, 280, 80, 150));
        studentButton = new JButton(sourceStudentImage);
        add(studentButton);
        studentButton.setBounds(new Rectangle(200, 480, 80, 150));

        //Theme Buttons
        sportsButton = new JButton(sourceSportsImage);
        add(sportsButton);
        sportsButton.setBounds(new Rectangle(600, 80, 150, 150));
        mathButton = new JButton(sourceMathImage);
        add(mathButton);
        mathButton.setBounds(new Rectangle(600, 280, 150, 150));
        javaButton = new JButton(sourceJavaImage);
        add(javaButton);
        javaButton.setBounds(new Rectangle(600, 480, 150, 150));

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuImage, 0, 0, this);
    }

}

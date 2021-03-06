/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.pkgfinal.project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * Berks Game Multiple Choice Game
 */
public class WorldCampusGame extends JPanel implements ActionListener {

    //Main Penn State map Image
    ImageIcon sourceBerksImage1 = new ImageIcon("images/FindThePerson.jpg");
    Image berksImage = sourceBerksImage1.getImage();

    //back to the menu button
    JButton backToMap;
    JLabel displayQuestion;
    ButtonGroup group;

    JButton findImage;
    JTextField displayAnswer;
    JLabel funFact;
    JLabel worldCampusScore;
    JLabel recentPlays;

    RadioButtonMultipleChoice multipleChoice1;

    Boolean scored;

    XML_240 worldCampusXML;
    String xmlFile, theme;

    GameScore gameScore;
    
    MainMap mainMap;

    //constructor
    public WorldCampusGame(GameScore gameScore, JLabel score, JLabel recentPlays, MainMap mainMap) {
        super();
        this.mainMap = mainMap;
        
        //sets the theme to blank and if it was scored yet to false
        theme = "";
        scored = false;
        worldCampusScore = score;
        this.gameScore = gameScore;
        this.recentPlays = recentPlays;
        worldCampusXML = new XML_240();// creates the 240 class that reads and writes XML
        setBackground(Color.white);
        setLayout(null);
        this.setBounds(new Rectangle(600, 100, 50, 50));

        //Adds a fact about the campus to the panel
        funFact = new JLabel("Fun Fact! This Campus was established in 1958");
        add(funFact);
        funFact.setBounds(new Rectangle(10, 10, 400, 30));
        funFact.setFont(new Font("Century Gothic", Font.BOLD, 16));
        funFact.setForeground(Color.blue);
        funFact.setOpaque(true);
        //adds a back button to the game
        backToMap = new JButton("click here to go back to the Map");
        add(backToMap);
        backToMap.setBounds(new Rectangle(500, 10, 300, 30));

        //Adds the components for the multiple choice game. THe question and answers are all blank.
        //The Radio buttons and question label are filled through an XML document which is selected in the options menu.
        displayQuestion = new JLabel("", SwingConstants.CENTER);
        displayQuestion.setFont(new Font("Century Gothic", Font.BOLD, 20));
        displayQuestion.setOpaque(true);
        displayQuestion.setForeground(Color.blue);
        displayQuestion.setBounds(new Rectangle(0, 0, 300, 60));

        displayAnswer = new JTextField();

        //Adds all the components to the map
        add(displayQuestion);
        displayQuestion.setBounds(new Rectangle(400, 50, 500, 50));
        add(displayAnswer);
        displayAnswer.setBounds(new Rectangle(500, 550, 300, 50));

        //Creates the box to be found
        findImage = new JButton();
        findImage.addActionListener(this);

        //puts the box in a random cordinate
        int x = ranCordinate(100, 950);
        int y = ranCordinate(100, 500);
        findImage.setBounds(new Rectangle(x, y, 50, 50));
        add(findImage);

        createQuestions("Math");
    }

    //Sets background image
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(berksImage, 0, 0, this);
    }

    //Fills in the missing text for the questions and answers in the constructor.
    public void createQuestions(String inputTheme) {
        theme = inputTheme;
        if (theme == "Math") {
            xmlFile = "WorldCampusGameMath.xml";
        } else if (theme == "Sports") {
            xmlFile = "WorldCampusGameSports.xml";
        } else if (theme == "Java") {
            xmlFile = "WorldCampusGameJava.xml";
        }

        String q1 = "";
        String a1 = "";

        if (theme != "") {
            worldCampusXML.openReaderXML(xmlFile);
            q1 = (String) worldCampusXML.ReadObject();//reads the lines in the XML file from the top to bottom.
            a1 = (String) worldCampusXML.ReadObject();

        }

        ImageIcon sourceImage1 = new ImageIcon(a1);

        findImage.setIcon(sourceImage1);

        displayQuestion.setText(q1);

    }

    //finds a random cordinate to place the image to be found
    public int ranCordinate(int x, int y) {
        return (int) ((Math.random() * (y - x)) + x);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object obj = e.getSource();
        if (scored == false) {
            gameScore.addToList("World Campus Game");
            System.out.println(gameScore.recentlyPlayed);
        }

        if (obj == findImage && scored == false) {
            gameScore.increaseScore(1);
            worldCampusScore.setText("Score: " + gameScore.score);
            recentPlays.setText("Recent Plays: " + gameScore.listGames());
            displayAnswer.setText("Found It !!");
            scored = true;
            gameScore.increaseGameComplete();//add this to your code so that the game knows when to add the "world campus" to the map.
            findImage.removeActionListener(this);//after the correct answer is put in, they cant change it
            mainMap.gameOverGame.setBounds(new Rectangle(1050, 50, 100, 100));

        }
    }
}

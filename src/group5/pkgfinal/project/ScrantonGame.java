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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * Scranton True or false game
 */
public class ScrantonGame extends JPanel implements ActionListener {

    //Main Penn State map Image
    ImageIcon sourceBerksImage1 = new ImageIcon("images/Scranton.jpg");
    Image berksImage = sourceBerksImage1.getImage();

    //back to the menu button
    JButton backToMap;
    JLabel displayQuestion1,
            displayQuestion2,
            displayQuestion3;
    ButtonGroup group1, group2, group3;

    JRadioButton trueQuestion1, falseQuestion1,
            trueQuestion2, falseQuestion2,
            trueQuestion3, falseQuestion3;

    JTextField displayAnswer1,
            displayAnswer2,
            displayAnswer3;

    JLabel funFact;
    JLabel scrantonScore;
    JLabel recentPlays;

    RadioButtonMultipleChoice trueChoice1, falseChoice1,
            trueChoice2, falseChoice2,
            trueChoice3, falseChoice3;

    Boolean scored1,scored2,scored3;

    XML_240 scrantonXML;
    String xmlFile, theme;

    GameScore gameScore;
    MainMap mainMap;

    //constructor
    public ScrantonGame(GameScore gameScore, JLabel score, JLabel recentPlays, MainMap mainMap) {
        super();
        //sets the theme to blank and if it was scored yet to false
        theme = "";
        scored1 = false;
        scored2 = false;
        scored3 = false;
        scrantonScore = score;
        this.gameScore = gameScore;
        this.recentPlays = recentPlays;
        this.mainMap = mainMap;
        scrantonXML = new XML_240();// creates the 240 class that reads and writes XML
        setBackground(Color.white);
        setLayout(null);

        //Adds a fact about the campus to the panel
        funFact = new JLabel("Fun Fact! This Campus currently resides 992 Students");
        add(funFact);
        funFact.setBounds(new Rectangle(10, 10, 400, 30));
        funFact.setFont(new Font("Century Gothic", Font.BOLD, 16));
        funFact.setForeground(Color.white);
        //adds a back button to the game
        backToMap = new JButton("click here to go back to the Map");
        add(backToMap);
        backToMap.setBounds(new Rectangle(500, 10, 300, 30));

        //Adds the components for the multiple choice game. THe question and answers are all blank.
        //The Radio buttons and question label are filled through an XML document which is selected in the options menu.
        displayQuestion1 = new JLabel("",SwingConstants.CENTER);
        displayQuestion1.setOpaque(true);
        displayQuestion1.setBackground(Color.gray);
        displayQuestion1.setForeground(Color.black);
        displayQuestion1.setBounds(new Rectangle(0, 0, 300, 60));

        displayQuestion2 = new JLabel("",SwingConstants.CENTER);
        displayQuestion2.setOpaque(true);
        displayQuestion2.setBackground(Color.gray);
        displayQuestion2.setForeground(Color.black);
        displayQuestion2.setBounds(new Rectangle(0, 0, 300, 60));

        displayQuestion3 = new JLabel("",SwingConstants.CENTER);
        displayQuestion3.setOpaque(true);
        displayQuestion3.setBackground(Color.gray);
        displayQuestion3.setForeground(Color.black);
        displayQuestion3.setBounds(new Rectangle(0, 0, 300, 60));

        displayAnswer1 = new JTextField();
        displayAnswer2 = new JTextField();
        displayAnswer3 = new JTextField();

        trueChoice1 = new RadioButtonMultipleChoice(false, "");
        falseChoice1 = new RadioButtonMultipleChoice(false, "");
        trueChoice2 = new RadioButtonMultipleChoice(false, "");
        falseChoice2 = new RadioButtonMultipleChoice(false, "");
        trueChoice3 = new RadioButtonMultipleChoice(false, "");
        falseChoice3 = new RadioButtonMultipleChoice(false, "");

        trueQuestion1 = trueChoice1.button;
        falseQuestion1 = falseChoice1.button;
        trueQuestion2 = trueChoice2.button;
        falseQuestion2 = falseChoice2.button;
        trueQuestion3 = trueChoice3.button;
        falseQuestion3 = falseChoice3.button;

        group1 = new ButtonGroup();//first group of t/f questions
        group1.add(trueQuestion1);
        group1.add(falseQuestion1);
        group2 = new ButtonGroup();//2nd group of t/f questions
        group2.add(trueQuestion2);
        group2.add(falseQuestion2);
        group3 = new ButtonGroup();//3rd group of t/f questions
        group3.add(trueQuestion3);
        group3.add(falseQuestion3);

        //Adds all the components to the map
        add(displayQuestion1);
        displayQuestion1.setBounds(new Rectangle(300, 150, 600, 50));
        add(trueQuestion1);
        trueQuestion1.setBounds(new Rectangle(50, 150, 80, 50));
        add(falseQuestion1);
        falseQuestion1.setBounds(new Rectangle(150, 150, 80, 50));
        add(displayAnswer1);
        displayAnswer1.setBounds(new Rectangle(1000, 150, 100, 50));

        add(displayQuestion2);
        displayQuestion2.setBounds(new Rectangle(300, 300, 600, 50));
        add(trueQuestion2);
        trueQuestion2.setBounds(new Rectangle(50, 300, 80, 50));
        add(falseQuestion2);
        falseQuestion2.setBounds(new Rectangle(150, 300, 80, 50));
        add(displayAnswer2);
        displayAnswer2.setBounds(new Rectangle(1000, 300, 100, 50));

        add(displayQuestion3);
        displayQuestion3.setBounds(new Rectangle(300, 450, 600, 50));
        add(trueQuestion3);
        trueQuestion3.setBounds(new Rectangle(50, 450, 80, 50));
        add(falseQuestion3);
        falseQuestion3.setBounds(new Rectangle(150, 450, 80, 50));
        add(displayAnswer3);
        displayAnswer3.setBounds(new Rectangle(1000, 450, 100, 50));

        trueQuestion1.addActionListener(this);
        trueQuestion2.addActionListener(this);
        trueQuestion3.addActionListener(this);
        falseQuestion1.addActionListener(this);
        falseQuestion2.addActionListener(this);
        falseQuestion3.addActionListener(this);

        createQuestions("Math");//sets default to Math
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
            xmlFile = "ScrantonGameMath.xml";
        } else if (theme == "Sports") {
            xmlFile = "ScrantonGameSports.xml";
        } else if (theme == "Java") {
            xmlFile = "ScrantonGameJava.xml";
        }

        String q1 = "";
        String t1 = "";
        String f1 = "";

        String q2 = "";
        String t2 = "";
        String f2 = "";

        String q3 = "";
        String t3 = "";
        String f3 = "";

        Boolean t1Boolean = false;
        Boolean f1Boolean = false;
        Boolean t2Boolean = false;
        Boolean f2Boolean = false;
        Boolean t3Boolean = false;
        Boolean f3Boolean = false;

        if (theme != "") {
            scrantonXML.openReaderXML(xmlFile);
            q1 = (String) scrantonXML.ReadObject();//reads the lines in the XML file from the top to bottom.
            t1Boolean = (Boolean) scrantonXML.ReadObject();
            t1 = (String) scrantonXML.ReadObject();
            f1Boolean = (Boolean) scrantonXML.ReadObject();
            f1 = (String) scrantonXML.ReadObject();
            q2 = (String) scrantonXML.ReadObject();//reads the lines in the XML file from the top to bottom.
            t2Boolean = (Boolean) scrantonXML.ReadObject();
            t2 = (String) scrantonXML.ReadObject();
            f2Boolean = (Boolean) scrantonXML.ReadObject();
            f2 = (String) scrantonXML.ReadObject();
            q3 = (String) scrantonXML.ReadObject();//reads the lines in the XML file from the top to bottom.
            t3Boolean = (Boolean) scrantonXML.ReadObject();
            t3 = (String) scrantonXML.ReadObject();
            f3Boolean = (Boolean) scrantonXML.ReadObject();
            f3 = (String) scrantonXML.ReadObject();

        }
        displayQuestion1.setText(q1);
        trueChoice1.changeButtonText(t1);
        falseChoice1.changeButtonText(f1);
        displayQuestion2.setText(q2);
        trueChoice2.changeButtonText(t2);
        falseChoice2.changeButtonText(f2);
        displayQuestion3.setText(q3);
        trueChoice3.changeButtonText(t3);
        falseChoice3.changeButtonText(f3);

        trueChoice1.changeIsCorrect(t1Boolean);
        falseChoice1.changeIsCorrect(f1Boolean);
        trueChoice2.changeIsCorrect(t2Boolean);
        falseChoice2.changeIsCorrect(f2Boolean);
        trueChoice3.changeIsCorrect(t3Boolean);
        falseChoice3.changeIsCorrect(f3Boolean);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object obj = e.getSource();
        if (scored1 == false && scored2 == false && scored3 == false) {
            gameScore.addToList("Scranton Game");
            System.out.println(gameScore.recentlyPlayed);
        }

        if (obj == trueQuestion1 && scored1 == false) {
            if (trueChoice1.isTrue == true) {
                gameScore.increaseScore(1);//increases the score by 1 point. change the number in the paranthases by the actual score.
            }
            scored1 = true;
            scrantonScore.setText("Score: " + gameScore.score);
            displayAnswer1.setText(trueChoice1.isCorrect);
            if (scored1 == true && scored2 == true && scored3 == true){
                mainMap.scrantonGame.setBackground(Color.red);//sets the color to red on the main map
            }
        }
        if (obj == falseQuestion1 && scored1 == false) {
            if (falseChoice1.isTrue == true) {
                gameScore.increaseScore(1);//increases the score by 1 point. change the number in the paranthases by the actual score.
            }
            scored1 = true;
            scrantonScore.setText("Score: " + gameScore.score);
            displayAnswer1.setText(falseChoice1.isCorrect);
            if (scored1 == true && scored2 == true && scored3 == true){
                mainMap.scrantonGame.setBackground(Color.red);//sets the color to red on the main map
            }
        }

        if (obj == trueQuestion2 && scored2 == false) {
            if (trueChoice2.isTrue == true) {
                gameScore.increaseScore(1);//increases the score by 1 point. change the number in the paranthases by the actual score.
            }
            scored2 = true;
            scrantonScore.setText("Score: " + gameScore.score);
            displayAnswer2.setText(trueChoice2.isCorrect);
            if (scored1 == true && scored2 == true && scored3 == true){
                mainMap.scrantonGame.setBackground(Color.red);//sets the color to red on the main map
            }

        }
        if (obj == falseQuestion2 && scored2 == false) {
            if (falseChoice2.isTrue == true) {
                gameScore.increaseScore(1);//increases the score by 1 point. change the number in the paranthases by the actual score.
            }
            scored2 = true;
            scrantonScore.setText("Score: " + gameScore.score);
            displayAnswer2.setText(falseChoice2.isCorrect);
            if (scored1 == true && scored2 == true && scored3 == true){
                mainMap.scrantonGame.setBackground(Color.red);//sets the color to red on the main map
            }
        }

        if (obj == trueQuestion3 && scored3 == false) {
            if (trueChoice3.isTrue == true) {
                gameScore.increaseScore(1);//increases the score by 1 point. change the number in the paranthases by the actual score.
            }
            scrantonScore.setText("Score: " + gameScore.score);
            recentPlays.setText("Recent Plays: " + gameScore.listGames());
            displayAnswer3.setText(trueChoice3.isCorrect);
            scored3 = true;
            gameScore.increaseGameComplete();//add this to your code so that the game knows when to add the "world campus" to the map.
            if (gameScore.gameComplete == 5) {//needs to reach 5 before the WorldCampus is revealed
                mainMap.showWorldCampus();
            }
            if (scored1 == true && scored2 == true && scored3 == true){
                mainMap.scrantonGame.setBackground(Color.red);//sets the color to red on the main map
            }

        }
        if (obj == falseQuestion3 && scored3 == false) {
            if (falseChoice3.isTrue == true) {
                gameScore.increaseScore(1);//increases the score by 1 point. change the number in the paranthases by the actual score.
            }
            scrantonScore.setText("Score: " + gameScore.score);
            recentPlays.setText("Recent Plays: " + gameScore.listGames());
            displayAnswer3.setText(falseChoice3.isCorrect);
            scored3 = true;
            gameScore.increaseGameComplete();//add this to your code so that the game knows when to add the "world campus" to the map.
            if (gameScore.gameComplete == 5) {//needs to reach 5 before the WorldCampus is revealed
                mainMap.showWorldCampus();
            }
            if (scored1 == true && scored2 == true && scored3 == true){
                mainMap.scrantonGame.setBackground(Color.red);//sets the color to red on the main map
            }
        }

    }
}
 

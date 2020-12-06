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
 *Mont alto game is a game where the user tries to guess the unscrambled word
 * The word is based off of the theme.
 */
public class MontAltoGame extends JPanel implements ActionListener {

    //Main Penn State map Image
    ImageIcon sourceBerksImage1 = new ImageIcon("images/MontAlto.jpg");
    Image berksImage = sourceBerksImage1.getImage();

    //back to the menu button
    JButton backToMap;
    JLabel displayQuestion;
    JLabel recentPlays;
    ButtonGroup group;

    JLabel displayAnswer;
    JLabel funFact;
    JLabel berksScore;

    //game componenents
    JLabel scrambledWord;
    JLabel hint;
    JTextField unscrambledWordInput;
    String guessedWord,
            matchedWord;

    Boolean scored;

    XML_240 montAltoXML;
    String xmlFile, theme;

    GameScore gameScore;

    MainMap mainMap;

    //constructor
    public MontAltoGame(GameScore gameScore, JLabel score, JLabel recentPlays, MainMap mainMap) {
        super();
        //sets the theme to blank and if it was scored yet to false
        theme = "";
        scored = false;
        berksScore = score;
        this.gameScore = gameScore;
        this.mainMap = mainMap;
        this.recentPlays = recentPlays;
        montAltoXML = new XML_240();// creates the 240 class that reads and writes XML
        setBackground(Color.white);
        setLayout(null);

        //Adds a fact about the campus to the panel
        funFact = new JLabel("Fun Fact! This Campus is known for forestry majors!");
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
        displayQuestion = new JLabel();
        displayQuestion.setOpaque(true);
        displayQuestion.setBackground(Color.blue);
        displayQuestion.setForeground(Color.white);
        displayQuestion.setFont(new Font("Arial",Font.PLAIN ,14));
        displayQuestion.setBounds(new Rectangle(0, 0, 300, 60));

        displayAnswer = new JLabel("",SwingConstants.CENTER);
        displayAnswer.setOpaque(true);
        displayAnswer.setBackground(Color.white);
        displayAnswer.setForeground(Color.black);
        displayAnswer.setBounds(new Rectangle(0, 0, 300, 60));

        //displays the scrambled word based on the theme
        scrambledWord = new JLabel("",SwingConstants.CENTER);
        scrambledWord.setOpaque(true);
        scrambledWord.setBackground(Color.gray);
        scrambledWord.setForeground(Color.black);
        scrambledWord.setFont(new Font("Arial",Font.BOLD ,16));
        scrambledWord.setBounds(new Rectangle(0, 0, 300, 60));
        
        hint = new JLabel("",SwingConstants.CENTER);
        hint.setOpaque(true);
        hint.setBackground(Color.gray);
        hint.setForeground(Color.black);
        hint.setFont(new Font("Arial",Font.BOLD ,16));
        hint.setBounds(new Rectangle(0, 0, 300, 60));

        unscrambledWordInput = new JTextField("");

        //Adds all the components to the map
        add(displayQuestion);
        displayQuestion.setBounds(new Rectangle(400, 50, 500, 50));
        add(scrambledWord);
        scrambledWord.setBounds(new Rectangle(500, 150, 300, 50));
        add(unscrambledWordInput);
        unscrambledWordInput.addActionListener(this);
        unscrambledWordInput.setBounds(new Rectangle(500, 400, 300, 50));
        add(displayAnswer);
        displayAnswer.setBounds(new Rectangle(500, 550, 300, 50));
        add(hint);
        hint.setBounds(new Rectangle(400, 300, 500, 50));

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
            xmlFile = "MontAltoGameMath.xml";
        } else if (theme == "Sports") {
            xmlFile = "MontAltoGameSports.xml";
        } else if (theme == "Java") {
            xmlFile = "MontAltoGameJava.xml";
        }

        String q1 = "";
        String s1 = "";
        String s2 = "";
        String h1 = "";

        if (theme != "") {
            montAltoXML.openReaderXML(xmlFile);
            q1 = (String) montAltoXML.ReadObject();//reads the lines in the XML file from the top to bottom.
            s1 = (String) montAltoXML.ReadObject();
            s2 = (String) montAltoXML.ReadObject();
            h1 = (String) montAltoXML.ReadObject();
        }
        displayQuestion.setText(q1);
        scrambledWord.setText(s1);
        matchedWord = s2;
        hint.setText(h1);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object obj = e.getSource();

        if (scored == false) {

        }

        if (obj == unscrambledWordInput) {
            guessedWord = unscrambledWordInput.getText();
            if (guessedWord.equals(matchedWord)) {
                gameScore.addToList("Mont Alto Game");
            recentPlays.setText("Recent Plays: " + gameScore.listGames());
                gameScore.increaseScore(1);
                berksScore.setText("Score: " + gameScore.score);
                displayAnswer.setText("Correct!");
                displayAnswer.setForeground(Color.GREEN);
                scored = true;
                gameScore.increaseGameComplete();
                unscrambledWordInput.removeActionListener(this);//after the correct answer is put in, they cant change it
                mainMap.montAltoGame.setBackground(Color.red);//sets the color to red on the main map
                if (gameScore.gameComplete == 5) {
                    mainMap.showWorldCampus();
                }
            //if the user enters nothing it promts them to enter something
            }else if (guessedWord.equals("")){
                displayAnswer.setText("Please input text before hitting enter!");
            }else {
                displayAnswer.setText("Incorrect! Guess Again!");
                displayAnswer.setForeground(Color.RED);
                unscrambledWordInput.setText("");
            }
        }

    }
}
